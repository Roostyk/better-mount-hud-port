# Чек-лист публикации — Better Mount HUD (Unofficial Port)

Тексты для копирования: `description.md`, `summary.txt`, `changelog.md`. Поля и теги: `meta.md`.
Картинки: папка `media/`.

## Шаг 0. Исходники на GitHub — готово

Код лежит в https://github.com/Roostyk/better-mount-hud-port (так требует лицензия GPL).
В коммитах стоит скрытый адрес GitHub, настоящая почта не видна.

## Шаг 1. Modrinth

1. modrinth.com → **Create a project** (кнопка «+» вверху):
   - Name: `Better Mount HUD (Unofficial Port)`
   - URL: `better-mount-hud-port`
   - Summary: из `summary.txt`
   - Visibility: Public
2. В настройках проекта:
   - **Description:** вставь весь `description.md`;
   - **Icon:** `media/icon-512.png`;
   - **Tags:** `Utility` (главный), `Transportation`;
   - **Environment:** Client-side only;
   - **License:** `GPL-3.0-only`;
   - **Links:** Source и Issues со ссылкой на GitHub.
3. **Gallery:** загрузи `before-after.png` (отметь как Featured), `1-horse-idle.png`,
   `2-horse-charging-jump.png`, `8-camel-dash-cooldown.png`, `11-pig-low-air.png`.
   Вверху описания сейчас стоит картинка «до/после» из GitHub. Когда будет гифка, загрузи её в галерею
   и замени ссылку на картинку в строке `![...](...)` в начале описания.
4. **Versions → Create a version**, файл 1:
   - файл: `neoforge-1.21.1\build\libs\bettermounthudport-1.0.0+1.21.1-neoforge.jar`;
   - Version number: `1.0.0+1.21.1-neoforge`, Name: `1.0.0 (NeoForge 1.21.1)`;
   - Channel: Release; Loaders: **NeoForge**; Game versions: **только 1.21.1**;
   - Changelog: из `changelog.md`; Dependencies: никаких.
5. Файл 2, то же самое:
   - файл: `forge-1.20.1\build\libs\bettermounthudport-1.0.0+1.20.1-forge.jar`;
   - Version number: `1.0.0+1.20.1-forge`, Name: `1.0.0 (Forge 1.20.1)`;
   - Loaders: **Forge**; Game versions: **только 1.20.1**.
6. **Submit for review.** Модерация обычно занимает от нескольких часов до пары дней.
7. Монетизация: в настройках аккаунта Modrinth (раздел про доход и выплаты) проверь, что выплаты подключены.

## Шаг 2. CurseForge

1. authors.curseforge.com → **Create a project** → Minecraft → Class **Mods**:
   - Name: `Better Mount HUD (Unofficial Port)`;
   - Summary: из `summary.txt`;
   - Main category: **Map and Information**; дополнительная: **Utility & QoL**;
   - Logo: `media/icon-512.png`;
   - License: **GNU General Public License version 3 (GPLv3)**.
2. **Description:** вставь `description.md`. Редактор CurseForge поддерживает Markdown:
   выбери Markdown перед вставкой. Если нет, вставь текст и поправь заголовки вручную.
3. В настройках проекта укажи ссылки **Source** и **Issues** на GitHub.
4. **Upload file**, файл 1:
   - jar NeoForge; Display name `1.0.0 (NeoForge 1.21.1)`; Release type **Release**;
   - Game version **1.21.1**, Mod loader **NeoForge**, Environment **Client**, Java **21**;
   - Changelog из `changelog.md`; Related projects: никаких.
5. Файл 2: jar Forge, `1.0.0 (Forge 1.20.1)`, Game version **1.20.1**, **Forge**, **Client**, Java **17**.
6. Отправь на модерацию. Оригинала на CurseForge нет, поэтому наш мод там единственный «Better Mount HUD».
   Это хорошо для поиска, но модератор может спросить про права. Ответ: лицензия GPL-3.0
   разрешает порты, автор указан в первой строке описания, исходники открыты.
7. Монетизация: authors.curseforge.com → раздел Rewards/Payments: проверь, что участие в программе включено.

## Шаг 3. После публикации

- Напиши мне ссылки на обе страницы: я внесу их в `portfolio.md`.
- По желанию напиши автору оригинала (issue в его репозитории или Discord), черновик ниже.
- Через 1–2 недели посмотрим скачивания и решим, делать ли другие версии Minecraft.

### Черновик сообщения автору оригинала (по желанию)

> Hi Lortseam! Thanks for Better Mount HUD. Several people asked for a Forge version in your issues
> (#6, #12), so I made an unofficial NeoForge 1.21.1 / Forge 1.20.1 port. It's a rewrite under GPL-3.0,
> credits you and links to your project at the top of the page, and asks users to report port bugs to me,
> not to you. Source: https://github.com/Roostyk/better-mount-hud-port. If you'd like anything changed (name, wording, links), just tell me.
