# Metadata for Modrinth and CurseForge

## Common

| Field | Value |
|---|---|
| Name | `Better Mount HUD (Unofficial Port)` |
| Modrinth URL (slug) | `better-mount-hud-port` (free as of 2026-10-24) |
| Summary | from `summary.txt` (135 chars, no formatting, no title repeat — Modrinth rule) |
| Side | Client only (server: unsupported / not needed) |
| License | GPL-3.0-only (`GNU General Public License v3.0 only`), same as the original |
| Source | https://github.com/Roostyk/better-mount-hud-port (GitHub, required by GPL) |
| Issues | https://github.com/Roostyk/better-mount-hud-port/issues (GitHub Issues of the same repo) |
| Dependencies | none (required: none, optional: none) |
| Icon | `media/icon-512.png` (pixel art, scaled ×4 without blur) |

Why not "[NeoForge/Forge Port]" in the name: CurseForge moderation asks to keep loader names and
version numbers out of titles, and Modrinth asks titles to be "only the name of the project".
"(Unofficial Port)" shows that the project is not by the original author.

## Files

| File | Loader | Minecraft | Java | Version number |
|---|---|---|---|---|
| `neoforge-1.21.1/build/libs/bettermounthudport-1.0.0+1.21.1-neoforge.jar` | NeoForge | 1.21.1 | 21 | `1.0.0+1.21.1-neoforge` |
| `forge-1.20.1/build/libs/bettermounthudport-1.0.0+1.20.1-forge.jar` | Forge | 1.20.1 | 17 | `1.0.0+1.20.1-forge` |

Version display names: `1.0.0 (NeoForge 1.21.1)` and `1.0.0 (Forge 1.20.1)`. Release channel: Release.

Do not tick 1.21 for the NeoForge file and do not tick 1.20 / 1.20.2+ for the Forge file:
the jars only load on exactly 1.21.1 and 1.20.1.

## Modrinth

- Categories: `utility` (primary, same as the original), `transportation`.
- Loaders: NeoForge (file 1), Forge (file 2).
- Environment: client-side only.

## CurseForge

- Class: Mods.
- Main category: **Map and Information** (same as AppleSkin and Jade).
- Additional category: **Utility & QoL**.
- Per file: game version, loader (NeoForge / Forge), environment **Client**, Java version (21 / 17).

## Search keywords (keep them in the page text)

horse, mount, riding, hunger bar, food bar, experience bar, HUD, camel, NeoForge, Forge, 1.21.1, 1.20.1
