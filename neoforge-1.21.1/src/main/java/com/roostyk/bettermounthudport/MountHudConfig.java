/*
 * Better Mount HUD [NeoForge/Forge Port]
 * Copyright (C) 2026 Roostyk
 * Unofficial port of Better Mount HUD by Lortseam, licensed under GPL-3.0.
 * See the LICENSE file for the full license text.
 */
package com.roostyk.bettermounthudport;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * Client config. Every change to the vanilla HUD can be turned off on its own.
 */
public final class MountHudConfig {
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.BooleanValue SHOW_FOOD;
    public static final ModConfigSpec.BooleanValue SHOW_EXPERIENCE;
    public static final ModConfigSpec.BooleanValue SHOW_JUMP_COOLDOWN;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        SHOW_FOOD = builder
                .comment("Keep the hunger bar visible while riding a mount with hearts.",
                        "The mount's hearts move above the hunger bar.")
                .define("showFoodWhileRiding", true);

        SHOW_EXPERIENCE = builder
                .comment("Show the experience bar and level while riding a jumping mount.",
                        "The jump bar appears instead while you hold the jump key.")
                .define("showExperienceWhileRiding", true);

        SHOW_JUMP_COOLDOWN = builder
                .comment("Keep the jump bar visible while the mount's jump is on cooldown",
                        "(for example, the camel's dash), so you can see when it is ready again.")
                .define("showJumpBarDuringCooldown", true);

        SPEC = builder.build();
    }

    private MountHudConfig() {
    }
}
