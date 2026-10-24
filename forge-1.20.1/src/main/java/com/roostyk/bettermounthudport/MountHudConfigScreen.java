/*
 * Better Mount HUD [NeoForge/Forge Port]
 * Copyright (C) 2026 Roostyk
 * Unofficial port of Better Mount HUD by Lortseam, licensed under GPL-3.0.
 * See the LICENSE file for the full license text.
 */
package com.roostyk.bettermounthudport;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.Nullable;

/**
 * Simple settings screen, opened from Mods → Better Mount HUD → Config.
 * Forge 1.20.1 has no built-in config screen, so this one shows an on/off button per option.
 */
public final class MountHudConfigScreen extends Screen {
    private static final String KEY = "bettermounthudport.configuration.";
    private static final int BUTTON_WIDTH = 260;
    private static final int BUTTON_HEIGHT = 20;
    private static final int ROW_HEIGHT = 24;

    @Nullable
    private final Screen parent;

    public MountHudConfigScreen(@Nullable Screen parent) {
        super(Component.translatable(KEY + "title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int x = (this.width - BUTTON_WIDTH) / 2;
        int y = this.height / 4;
        addToggle(MountHudConfig.SHOW_FOOD, "showFoodWhileRiding", x, y);
        addToggle(MountHudConfig.SHOW_EXPERIENCE, "showExperienceWhileRiding", x, y + ROW_HEIGHT);
        addToggle(MountHudConfig.SHOW_JUMP_COOLDOWN, "showJumpBarDuringCooldown", x, y + ROW_HEIGHT * 2);

        addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, button -> onClose())
                .bounds((this.width - 200) / 2, this.height - 28, 200, BUTTON_HEIGHT)
                .build());
    }

    private void addToggle(ForgeConfigSpec.BooleanValue value, String name, int x, int y) {
        addRenderableWidget(CycleButton.onOffBuilder(value.get())
                .withTooltip(state -> Tooltip.create(Component.translatable(KEY + name + ".tooltip")))
                .create(x, y, BUTTON_WIDTH, BUTTON_HEIGHT, Component.translatable(KEY + name),
                        (button, enabled) -> value.set(enabled)));
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(graphics);
        graphics.drawCenteredString(this.font, this.title, this.width / 2, 15, 0xFFFFFF);
        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public void onClose() {
        MountHudConfig.SPEC.save();
        if (this.minecraft != null) {
            this.minecraft.setScreen(this.parent);
        }
    }
}
