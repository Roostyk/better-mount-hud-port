/*
 * Better Mount HUD [NeoForge/Forge Port]
 * Copyright (C) 2026 Roostyk
 * Unofficial port of Better Mount HUD by Lortseam, licensed under GPL-3.0.
 * See the LICENSE file for the full license text.
 */
package com.roostyk.bettermounthudport.devtest;

import com.mojang.logging.LogUtils;
import com.roostyk.bettermounthudport.BetterMountHudPort;
import com.roostyk.bettermounthudport.MountHudConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Screenshot;
import net.minecraft.client.gui.screens.AccessibilityOnboardingScreen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.LevelSettings;
import net.minecraft.world.level.WorldDataConfiguration;
import net.minecraft.world.level.levelgen.WorldOptions;
import net.minecraft.world.level.levelgen.presets.WorldPresets;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import org.slf4j.Logger;

/**
 * Dev-only scripted HUD test. Runs only with -Dbettermounthudport.devtest=true (the hudTest run).
 * Creates a flat world, rides a horse and a camel, and saves a screenshot after each step.
 */
@EventBusSubscriber(modid = BetterMountHudPort.MOD_ID, value = Dist.CLIENT)
public final class HudDevTest {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final boolean ENABLED = Boolean.getBoolean("bettermounthudport.devtest");

    private static boolean worldRequested;
    private static int ticksInWorld;

    private HudDevTest() {
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        if (!ENABLED) {
            return;
        }
        Minecraft mc = Minecraft.getInstance();
        if (!worldRequested && (mc.screen instanceof TitleScreen || mc.screen instanceof AccessibilityOnboardingScreen)) {
            worldRequested = true;
            mc.options.onboardAccessibility = false;
            mc.options.pauseOnLostFocus = false;
            createWorld(mc);
            return;
        }
        if (mc.player == null || mc.level == null || mc.screen != null) {
            return;
        }
        runStep(mc, ticksInWorld++);
    }

    private static void createWorld(Minecraft mc) {
        String name = "hudtest-" + System.currentTimeMillis();
        LevelSettings settings = new LevelSettings(name, GameType.SURVIVAL, false, Difficulty.PEACEFUL,
                true, new GameRules(), WorldDataConfiguration.DEFAULT);
        mc.createWorldOpenFlows().createFreshLevel(name, settings, WorldOptions.defaultWithRandomSeed(),
                registries -> registries.registryOrThrow(Registries.WORLD_PRESET)
                        .getHolderOrThrow(WorldPresets.FLAT).value().createWorldDimensions(),
                mc.screen);
    }

    private static void runStep(Minecraft mc, int t) {
        switch (t) {
            case 40 -> {
                command(mc, "gamerule sendCommandFeedback false");
                command(mc, "gamerule doDaylightCycle false");
                command(mc, "time set noon");
                command(mc, "xp add @s 27 levels");
                command(mc, "xp add @s 40 points");
                command(mc, "summon minecraft:horse ~2 ~ ~ {Tame:1b,SaddleItem:{id:\"minecraft:saddle\",count:1}}");
            }
            case 60 -> command(mc, "ride @s mount @e[type=minecraft:horse,limit=1,sort=nearest]");
            case 100 -> shot(mc, "1-horse-idle");
            case 110 -> mc.options.keyJump.setDown(true);
            case 125 -> shot(mc, "2-horse-charging-jump");
            case 126 -> mc.options.keyJump.setDown(false);
            case 160 -> shot(mc, "3-horse-after-jump");
            case 165 -> {
                MountHudConfig.SHOW_FOOD.set(false);
                MountHudConfig.SHOW_EXPERIENCE.set(false);
            }
            case 175 -> shot(mc, "4-horse-config-off-vanilla");
            case 180 -> {
                MountHudConfig.SHOW_FOOD.set(true);
                MountHudConfig.SHOW_EXPERIENCE.set(true);
                command(mc, "gamemode creative");
            }
            case 200 -> shot(mc, "5-horse-creative");
            case 205 -> mc.options.hideGui = true;
            case 215 -> shot(mc, "6-horse-f1-hidden");
            case 220 -> {
                mc.options.hideGui = false;
                command(mc, "gamemode survival");
                command(mc, "ride @s dismount");
                command(mc, "kill @e[type=minecraft:horse]");
                command(mc, "summon minecraft:camel ~2 ~ ~ {SaddleItem:{id:\"minecraft:saddle\",count:1}}");
            }
            case 240 -> command(mc, "ride @s mount @e[type=minecraft:camel,limit=1,sort=nearest]");
            case 300 -> shot(mc, "7-camel-idle");
            case 305 -> mc.options.keyJump.setDown(true);
            case 315 -> mc.options.keyJump.setDown(false);
            case 325 -> shot(mc, "8-camel-dash-cooldown");
            case 420 -> shot(mc, "9-camel-ready");
            case 430 -> {
                command(mc, "ride @s dismount");
                command(mc, "kill @e[type=minecraft:camel]");
                command(mc, "summon minecraft:pig ~2 ~ ~ {Saddle:1b}");
            }
            case 450 -> command(mc, "ride @s mount @e[type=minecraft:pig,limit=1,sort=nearest]");
            case 490 -> shot(mc, "10-pig");
            case 500 -> {
                LOGGER.info("[HudDevTest] Finished, closing the game.");
                mc.stop();
            }
            default -> {
            }
        }
    }

    private static void command(Minecraft mc, String command) {
        LOGGER.info("[HudDevTest] /{}", command);
        mc.player.connection.sendCommand(command);
    }

    private static void shot(Minecraft mc, String name) {
        Screenshot.grab(mc.gameDirectory, name + ".png", mc.getMainRenderTarget(),
                message -> LOGGER.info("[HudDevTest] Screenshot {}: {}", name, message.getString()));
    }
}
