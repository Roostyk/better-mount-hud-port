> **Unofficial port.** This is a NeoForge/Forge port of [Better Mount HUD](https://modrinth.com/mod/better-mount-hud) by **Lortseam** ([original source](https://github.com/Lortseam/better-mount-hud)), licensed under GPL-3.0. The port is maintained by Roostyk and is not affiliated with or endorsed by the original author. Please report problems with this port [here](https://github.com/Roostyk/better-mount-hud-port/issues), not to the original project. Source code of the port: [GitHub](https://github.com/Roostyk/better-mount-hud-port).

**Stop guessing how hungry you are on horseback.** Hunger, experience and air stay on screen while you ride a horse, camel, pig or any other mount.

![Vanilla HUD vs. Better Mount HUD while riding a horse](https://raw.githubusercontent.com/Roostyk/better-mount-hud-port/main/publish/media/before-after.png)

## Features

- 🍖 **Hunger stays visible while riding.** The mount's hearts move up above it, so nothing overlaps.
- ✨ **Experience bar and level stay visible** on jumping mounts (horses, donkeys, mules, camels). The jump bar comes back while you hold the jump key.
- 🫧 **Air bubbles sit above the mount's hearts** instead of covering them.
- 🐫 **Jump bar during cooldown** (optional): see when the camel's dash is ready again.
- ⚙️ **Every change can be turned off** in the config.
- 💻 **Client-side only.** Install it just on your game. It works on any server, including vanilla ones.
- 🪶 No dependencies and no mixins: tiny, safe to add to any modpack.

The HUD behaves like vanilla in creative mode and when the HUD is hidden (F1).

## Compatibility

| Minecraft | Loader |
|---|---|
| 1.21.1 | NeoForge 21.1+ |
| 1.20.1 | Forge 47.1+ |

- Tested together with **AppleSkin** (NeoForge 1.21.1): no overlap.
- Other mods that move the hunger or health bars (Overflowing Bars, Raised and similar) have not been tested yet. If something overlaps, please [open an issue](https://github.com/Roostyk/better-mount-hud-port/issues) with a screenshot.

## Configuration

- **NeoForge:** Mods → Better Mount HUD (Unofficial Port) → Config.
- **Forge:** Mods → Better Mount HUD (Unofficial Port) → Config.
- File: `config/bettermounthudport-client.toml`.

| Option | Default | What it does |
|---|---|---|
| Show Hunger While Riding | On | Keeps the hunger bar visible while riding |
| Show Experience While Riding | On | Shows the experience bar and level instead of the jump bar |
| Show Jump Bar During Cooldown | On | Keeps the jump bar visible while the jump is on cooldown |

Translations: English, Russian, Ukrainian, German, French, Spanish, Portuguese (Brazil), Polish, Chinese (Simplified), Japanese, Korean.

## FAQ

**Is there a Fabric version?**
Yes, use the original [Better Mount HUD](https://modrinth.com/mod/better-mount-hud) by Lortseam.

**Can I use it in my modpack?**
Yes, no permission needed.

**Do I need to install it on the server?**
No. It only changes your HUD. Servers ignore it.

**Will you support other Minecraft versions?**
Maybe. Tell us which version you need in the [issues](https://github.com/Roostyk/better-mount-hud-port/issues).

**Why "unofficial"?**
The original author makes the Fabric version only. This port is a separate project with the same behavior, rewritten for NeoForge and Forge.

## License

GPL-3.0, the same as the original mod. Source code: [GitHub](https://github.com/Roostyk/better-mount-hud-port).
