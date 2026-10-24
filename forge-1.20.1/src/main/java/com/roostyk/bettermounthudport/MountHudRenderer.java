/*
 * Better Mount HUD (Unofficial Port)
 * Copyright (C) 2026 Roostyk
 * Unofficial port of Better Mount HUD by Lortseam, licensed under GPL-3.0.
 * See the LICENSE file for the full license text.
 */
package com.roostyk.bettermounthudport;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Changes the vanilla HUD overlays while the player rides a mount.
 *
 * <p>Forge stacks the right-side bars with {@link ForgeGui#rightHeight}, in this order:
 * hunger, air, mount hearts. The original mod keeps the mount's hearts right above the hunger bar
 * and puts the air bubbles above the hearts, so they do not jump up when the player dives.
 * We do the same by lifting the air overlay over the hearts instead of replacing any overlay.</p>
 */
public final class MountHudRenderer {
    /** Height of the air bubbles drawn this frame, added back after the mount's hearts. */
    private static int airHeight;
    /** How far the air overlay was lifted this frame (0 if it was not lifted). */
    private static int airLift;
    /** {@link ForgeGui#rightHeight} right after lifting, used to measure the air bubbles. */
    private static int airStart;

    private MountHudRenderer() {
    }

    @SubscribeEvent
    public static void onOverlayPre(RenderGuiOverlayEvent.Pre event) {
        // Hide the idle jump bar, so the experience bar can take its place.
        if (is(event, VanillaGuiOverlay.JUMP_BAR) && shouldShowExperienceInsteadOfJumpBar()) {
            event.setCanceled(true);
        }
    }

    /** Lowest priority: runs last, and only if no other mod cancelled the air overlay. */
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onAirPre(RenderGuiOverlayEvent.Pre event) {
        if (!is(event, VanillaGuiOverlay.AIR_LEVEL)) {
            return;
        }
        airLift = 0;
        airHeight = 0;
        ForgeGui gui = forgeGui();
        if (gui == null || Minecraft.getInstance().options.hideGui || !gui.shouldDrawSurvivalElements()) {
            return;
        }
        int rows = mountHeartRows(Minecraft.getInstance().player.getVehicle());
        if (rows > 0) {
            airLift = rows * 10;
            gui.rightHeight += airLift;
            airStart = gui.rightHeight;
        }
    }

    @SubscribeEvent
    public static void onOverlayPost(RenderGuiOverlayEvent.Post event) {
        ForgeGui gui = forgeGui();
        if (gui == null) {
            return;
        }
        if (is(event, VanillaGuiOverlay.FOOD_LEVEL)) {
            renderFoodWhileRiding(gui, event);
        } else if (is(event, VanillaGuiOverlay.AIR_LEVEL)) {
            if (airLift > 0) {
                // Put the stack back where the mount's hearts start, and remember the bubbles' height.
                airHeight = gui.rightHeight - airStart;
                gui.rightHeight -= airLift + airHeight;
                airLift = 0;
            }
        } else if (is(event, VanillaGuiOverlay.MOUNT_HEALTH)) {
            gui.rightHeight += airHeight;
            airHeight = 0;
        } else if (is(event, VanillaGuiOverlay.EXPERIENCE_BAR)) {
            if (shouldShowExperienceInsteadOfJumpBar()) {
                renderExperienceBar(gui, event.getGuiGraphics(), event.getWindow().getGuiScaledWidth() / 2 - 91);
            }
        }
    }

    /** Forge skips the hunger bar when the mount shows hearts. Draw it in its usual spot. */
    private static void renderFoodWhileRiding(ForgeGui gui, RenderGuiOverlayEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (!MountHudConfig.SHOW_FOOD.get() || minecraft.options.hideGui || !gui.shouldDrawSurvivalElements()) {
            return;
        }
        Entity vehicle = minecraft.player.getVehicle();
        if (vehicle == null || !vehicle.showVehicleHealth()) {
            return; // Forge already drew the hunger bar.
        }
        Window window = event.getWindow();
        gui.setupOverlayRenderState(true, false);
        // renderFood draws at the current stack height and raises rightHeight by one row.
        gui.renderFood(window.getGuiScaledWidth(), window.getGuiScaledHeight(), event.getGuiGraphics());
    }

    /** The experience bar with its level number, the same way Forge draws it when not riding. */
    private static void renderExperienceBar(ForgeGui gui, GuiGraphics graphics, int x) {
        gui.setupOverlayRenderState(true, false);
        graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
        gui.renderExperienceBar(graphics, x);
        RenderSystem.enableBlend();
        graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
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
        if (player == null || minecraft.gameMode == null || minecraft.options.hideGui) {
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

    /** Rows of hearts Forge draws for the mount: 10 hearts per row, at most 30 hearts. */
    private static int mountHeartRows(Entity vehicle) {
        if (!(vehicle instanceof LivingEntity living)) {
            return 0;
        }
        int hearts = Math.min((int) (living.getMaxHealth() + 0.5F) / 2, 30);
        return (hearts + 9) / 10;
    }

    private static boolean is(RenderGuiOverlayEvent event, VanillaGuiOverlay overlay) {
        ResourceLocation id = event.getOverlay().id();
        return id.equals(overlay.id());
    }

    private static ForgeGui forgeGui() {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null || !(minecraft.gui instanceof ForgeGui gui)) {
            return null;
        }
        return gui;
    }
}
