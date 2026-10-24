# Media plan

## Icon

Ready: `media/icon-512.png` (the in-game icon, scaled ×4 without blur). It reads well at small size:
one big shape (a horseshoe), one accent (red heart), one bar (green XP).

If you want a new one, prompt for an image generator:

> Minecraft-style pixel art icon, 512x512, a grey iron horseshoe in the center with a small red pixel heart
> inside it, a bright green experience bar along the bottom, dark slate rounded-square background with a
> thin light border, flat colors, crisp hard pixel edges, no text, no logos, high contrast, readable at 64x64.

Tips: no text on the icon; at most 3 colors besides the background; check it at 64×64 before uploading.
Do not use the original mod's icon or the Minecraft logo.

## Screenshots (gallery)

Ready as drafts (flat test world): `media/before-after.png`, `media/1-horse-idle.png`,
`media/2-horse-charging-jump.png`, `media/8-camel-dash-cooldown.png`, `media/11-pig-low-air.png`.
They work for the first release. For better ones, re-shoot in a pretty world:

- Settings: English language (the tutorial hint showed Cyrillic keys), GUI scale 3, FOV 70,
  turn off the tutorial hints (set `tutorialStep:none` in `options.txt` while the game is closed).
- World: plains or savanna at sunset; a horse with horse armor looks best.
- Shaders: optional (Complementary Reimagined or BSL). The HUD is not affected by shaders, only the background.
- Shots, all from the first-person view with the HUD visible:
  1. Horse, survival, half hunger, level 30: the main "everything visible" shot.
  2. Same spot, mod config off: the "vanilla" shot for a before/after pair.
  3. Holding jump: the jump bar is back.
  4. Camel right after a dash: the cooldown bar.
  5. Air bubbles: hard to film for real (mounts float), keep the ready `media/11-pig-low-air.png`.
  6. The config screen.

## Main GIF (top of the page, replaces the before/after image)

5–8 seconds, 800 px wide, loop:
1. Player walks up to a horse, the HUD shows hunger and XP.
2. Player mounts: in **vanilla** hunger and XP vanish (caption "Vanilla").
3. Cut: same moment **with the mod**, everything stays (caption "Better Mount HUD").
Record with OBS, cut and convert to GIF with ScreenToGif or ezgif.com (keep it under ~5 MB).
Upload to the Modrinth gallery and paste its link into the description.

## Short video (YouTube Shorts / TikTok, 15–25 s, vertical)

- 0–3 s: text "Minecraft hides your hunger when you ride a horse 😤", player mounts, the bar vanishes.
- 3–8 s: rides across a field, hunger hits zero, health starts to drop — "wait, why am I dying?"
- 8–10 s: text "Fix:", mod name on screen.
- 10–20 s: same ride with the mod: hunger, XP and level visible, eats while riding, jumps (jump bar appears and goes away).
- 20–25 s: "Client-side, works on any server. NeoForge 1.21.1 / Forge 1.20.1. Link in bio."
Title idea: "This tiny mod fixes Minecraft's worst horse problem".
