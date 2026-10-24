/*
 * Better Mount HUD [NeoForge/Forge Port]
 * Copyright (C) 2026 Roostyk
 * Unofficial port of Better Mount HUD by Lortseam, licensed under GPL-3.0.
 * See the LICENSE file for the full license text.
 */
package com.roostyk.bettermounthudport;

import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.network.NetworkConstants;

/**
 * Client-only setup. Kept in its own class so the server never loads client classes.
 */
final class ClientSetup {
    private ClientSetup() {
    }

    // ModLoadingContext.get() is deprecated in late 47.x builds, but it is the only way that works
    // on every Forge 47 version this mod supports (47.1 and newer).
    @SuppressWarnings("removal")
    static void init() {
        ModLoadingContext context = ModLoadingContext.get();
        // Client-only mod: servers do not need it, so never show the server as incompatible.
        // mods.toml also sets clientSideOnly, but older Forge 47 builds ignore that field.
        context.registerExtensionPoint(IExtensionPoint.DisplayTest.class,
                () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (remote, isServer) -> true));
        context.registerConfig(ModConfig.Type.CLIENT, MountHudConfig.SPEC);
        context.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((minecraft, parent) -> new MountHudConfigScreen(parent)));
        MinecraftForge.EVENT_BUS.register(MountHudRenderer.class);
    }
}
