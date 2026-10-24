# Better Mount HUD (Unofficial Port)

Keeps your hunger, experience and air visible while you ride a horse, camel, pig, strider or any other mount.

Unofficial NeoForge/Forge port of [Better Mount HUD](https://modrinth.com/mod/better-mount-hud)
by Lortseam ([source](https://github.com/Lortseam/better-mount-hud)), licensed under GPL-3.0.
See [NOTICE.md](NOTICE.md).

## Features

- The hunger bar stays visible while riding. The mount's hearts move above it.
- The experience bar and level stay visible while riding a jumping mount.
  The jump bar comes back while you hold the jump key.
- Air bubbles appear above the mount's hearts instead of covering them.
- The jump bar stays visible while the mount's jump is on cooldown (the camel's dash).
- Every change can be turned off in the config (Mods → Better Mount HUD → Config).
- Client-side only. Works on any server.

## Versions

| Folder | Minecraft | Loader |
|---|---|---|
| `neoforge-1.21.1` | 1.21.1 | NeoForge |
| `forge-1.20.1` | 1.20.1 | Forge |

## Building

Each folder is a separate Gradle project:

```
cd neoforge-1.21.1
gradlew build
```

The jar is in `build/libs/`.

## License

GPL-3.0, the same as the original mod. See [LICENSE](LICENSE).
