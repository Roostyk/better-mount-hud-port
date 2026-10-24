/*
 * Better Mount HUD (Unofficial Port)
 * Copyright (C) 2026 Roostyk
 * Unofficial port of Better Mount HUD by Lortseam, licensed under GPL-3.0.
 * See the LICENSE file for the full license text.
 */
package com.roostyk.bettermounthudport;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

/**
 * Changes the vanilla HUD layers while the player rides a mount.
 *
 * <p>NeoForge stacks the right-side bars with {@link Gui#rightHeight}. Once the hunger bar is drawn
 * again, the mount's hearts and the air bubbles move up on their own.</p>
 */
public final class MountHudRenderer {
    /** Color of the vanilla experience level text. */
    private static final int EXPERIENCE_LEVEL_COLOR = 0x80FF20;

    private MountHudRenderer() {
    }

    @SubscribeEvent
    public static void onLayerPre(RenderGuiLayerEvent.Pre event) {
        // Hide the idle jump bar, so the experience bar can take its place.
        if (event.getName().equals(VanillaGuiLayers.JUMP_METER) && shouldShowExperienceInsteadOfJumpBar()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onLayerPost(RenderGuiLayerEvent.Post event) {
        ResourceLocation layer = event.getName();
        GuiGraphics graphics = event.getGuiGraphics();
        if (layer.equals(VanillaGuiLayers.FOOD_LEVEL)) {
            renderFoodWhileRiding(graphics);
        } else if (layer.equals(VanillaGuiLayers.EXPERIENCE_BAR)) {
            if (shouldShowExperienceInsteadOfJumpBar()) {
                Minecraft.getInstance().gui.renderExperienceBar(graphics, graphics.guiWidth() / 2 - 91);
            }
        } else if (layer.equals(VanillaGuiLayers.EXPERIENCE_LEVEL)) {
            if (shouldShowExperienceInsteadOfJumpBar()) {
                renderExperienceLevel(graphics);
            }
        }
    }

    /** Vanilla skips the hunger bar when the mount has hearts. Draw it in its usual spot. */
    private static void renderFoodWhileRiding(GuiGraphics graphics) {
        Minecraft minecraft = Minecraft.getInstance();
        if (!MountHudConfig.SHOW_FOOD.get() || !isHudVisible(minecraft)
                || minecraft.gameMode == null || !minecraft.gameMode.canHurtPlayer()) {
            return;
        }
        if (!(minecraft.getCameraEntity() instanceof Player player)) {
            return;
        }
        if (!vehicleShowsHearts(player.getVehicle())) {
            return; // Vanilla already drew the hunger bar.
        }

        Gui gui = minecraft.gui;
        minecraft.getProfiler().push("food");
        gui.renderFood(graphics, player, graphics.guiHeight() - gui.rightHeight, graphics.guiWidth() / 2 + 91);
        gui.rightHeight += 10;
        minecraft.getProfiler().pop();
    }

    /** Same text as the vanilla experience level, which vanilla hides while riding a jumping mount. */
    private static void renderExperienceLevel(GuiGraphics graphics) {
        Minecraft minecraft = Minecraft.getInstance();
        int level = minecraft.player.experienceLevel;
        if (level <= 0) {
            return;
        }
        minecraft.getProfiler().push("expLevel");
        Font font = minecraft.font;
        String text = Integer.toString(level);
        int x = (graphics.guiWidth() - font.width(text)) / 2;
        int y = graphics.guiHeight() - 31 - 4;
        graphics.drawString(font, text, x + 1, y, 0, false);
        graphics.drawString(font, text, x - 1, y, 0, false);
        graphics.drawString(font, text, x, y + 1, 0, false);
        graphics.drawString(font, text, x, y - 1, 0, false);
        graphics.drawString(font, text, x, y, EXPERIENCE_LEVEL_COLOR, false);
        minecraft.getProfiler().pop();
    }

    /**
     * True while the player rides a jumping mount (horse, camel, ...) and is not using its jump.
     * The jump bar comes back as soon as the jump key is held or the jump is charging.
     */
    private static boolean shouldShowExperienceInsteadOfJumpBar() {
        if (!MountHudConfig.SHOW_EXPERIENCE.get()) {
            return false;
        }
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        if (player == null || minecraft.gameMode == null || !isHudVisible(minecraft)) {
            return false;
        }
        PlayerRideableJumping mount = player.jumpableVehicle();
        if (mount == null || !minecraft.gameMode.hasExperience()) {
            return false;
        }
        if (minecraft.options.keyJump.isDown() || player.getJumpRidingScale() > 0) {
            return false;
        }
        return !MountHudConfig.SHOW_JUMP_COOLDOWN.get() || mount.getJumpCooldown() <= 0;
    }

    /** Same rule as vanilla: the mount shows hearts if it wants to and has at least one heart. */
    private static boolean vehicleShowsHearts(Entity vehicle) {
        if (!(vehicle instanceof LivingEntity living) || !living.showVehicleHealth()) {
            return false;
        }
        return (int) (living.getMaxHealth() + 0.5F) / 2 > 0;
    }

    /** NeoForge fires layer events even when the HUD is hidden with F1, so check it here. */
    private static boolean isHudVisible(Minecraft minecraft) {
        return !minecraft.options.hideGui;
    }
}
