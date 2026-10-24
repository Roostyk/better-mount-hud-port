/*
 * Better Mount HUD [NeoForge/Forge Port]
 * Copyright (C) 2026 Roostyk
 * Unofficial port of Better Mount HUD by Lortseam, licensed under GPL-3.0.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 */
package com.roostyk.bettermounthudport;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;

/**
 * Entry point. The mod only changes the HUD, so everything happens on the client.
 */
@Mod(BetterMountHudPort.MOD_ID)
public final class BetterMountHudPort {
    public static final String MOD_ID = "bettermounthudport";

    public BetterMountHudPort() {
        // mods.toml sets clientSideOnly, so servers never load the mod. This check is a second guard.
        if (FMLEnvironment.dist == Dist.CLIENT) {
            ClientSetup.init();
        }
    }
}
