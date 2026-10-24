/*
 * Better Mount HUD (Unofficial Port)
 * Copyright (C) 2026 Roostyk
 *
 * Unofficial port of Better Mount HUD by Lortseam
 * (https://github.com/Lortseam/better-mount-hud), licensed under GPL-3.0.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package com.roostyk.bettermounthudport;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;

/**
 * Client-only entry point. Keeps hunger, experience and air visible while riding a mount.
 */
@Mod(value = BetterMountHudPort.MOD_ID, dist = Dist.CLIENT)
public final class BetterMountHudPort {
    public static final String MOD_ID = "bettermounthudport";

    public BetterMountHudPort(ModContainer container) {
        container.registerConfig(ModConfig.Type.CLIENT, MountHudConfig.SPEC);
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        NeoForge.EVENT_BUS.register(MountHudRenderer.class);
    }
}
