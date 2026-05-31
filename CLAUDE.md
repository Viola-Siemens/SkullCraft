# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build and run

- Requires Java 21 toolchain as configured in `build.gradle`.
- Common Gradle commands:
	- `./gradlew build`
	- `./gradlew check`
	- `./gradlew test`
	- `./gradlew runClient`
	- `./gradlew runServer`
	- `./gradlew runGameTestServer`
	- `./gradlew runData`
	- `./gradlew clean`
	- `./gradlew tasks --all`
- There is currently no `src/test` test suite in the repository, so `test`/`check` are mainly useful for compile-level verification unless tests are added later.
- NeoGradle defines client/server/data/game test run configurations in `build.gradle`; generated resources are sourced from `src/generated/resources`.

## High-level architecture

This is a NeoForge 1.21.3 mod centered on three things:

1. Large-scale registration of custom skull blocks/items for many mob families.
2. A functional block/entity/menu pair for the Skull Charger.
3. Mixin-based extension of vanilla skull behavior so custom skulls participate in rendering, drops, persistence, note block instruments, and wearable head visuals.

Read these files first when orienting yourself:

- `src/main/java/com/hexagram2021/skullcraft/SkullCraft.java`
- `src/main/java/com/hexagram2021/skullcraft/common/SCContent.java`
- `src/main/java/com/hexagram2021/skullcraft/common/register/SCBlocks.java`
- `src/main/java/com/hexagram2021/skullcraft/common/register/SCItems.java`
- `src/main/java/com/hexagram2021/skullcraft/client/ClientEventSubscriber.java`
- `src/main/java/com/hexagram2021/skullcraft/common/block/entity/SkullChargerBlockEntity.java`
- `src/main/java/com/hexagram2021/skullcraft/common/crafting/SkullChargerMenu.java`
- `src/main/resources/skullcraft.mixins.json`

## Bootstrap and registration flow

- `SkullCraft` is the mod entrypoint.
- Its constructor wires common setup, the tags-updated hook, client config registration, and delegates all content registration to `SCContent.modConstruction(...)`.
- `SCContent` is the central registration hub for blocks, items, data components, loot modifier codec, menu types, block entities, village content, creative tab, sound registration, capabilities, and default item component tweaks.
- `SCContent.init()` is a key integration point: it mutates `BlockEntityType.SKULL.validBlocks` so all custom skull blocks are recognized by vanilla skull systems.
- `SkullCraft.tagsUpdated(...)` calls into `Villages.addAllStructuresToPool(...)` after server data load, so village integration is finalized after tags/data are available.

## Code structure

- `src/main/java/com/hexagram2021/skullcraft/common/register`
	- Deferred-register based content registration and registry keys.
	- `SCBlocks` and `SCItems` are the main content maps.
	- `SCDataComponents` registers modern item data components such as skull scale.
	- `SCContainerTypes`, `SCBlockEntities`, `SCCreativeModeTabs`, `SCEnchantments`, `SCEnchantmentTags`, `SCItemTags` hold the rest of the registry surface.
	- `SCNoteBlockInstruments` is declaration-only; actual values are injected by mixin.
- `src/main/java/com/hexagram2021/skullcraft/common/block`
	- Custom skull block implementations grouped by morphology/family (`human`, `cube`, `small_cube`, `cow`, `piglin`, `horse`, `warden`, `hoglin`).
	- `SkullChargerBlock` is the main functional machine block.
- `src/main/java/com/hexagram2021/skullcraft/common/block/entity`
	- `SkullChargerBlockEntity` contains the machine-side inventory and logic.
- `src/main/java/com/hexagram2021/skullcraft/common/crafting`
	- `SkullChargerMenu` handles the menu/container flow.
- `src/main/java/com/hexagram2021/skullcraft/common/components`
	- Item data model for skull-specific components, including scale serialization.
- `src/main/java/com/hexagram2021/skullcraft/common/loot`
	- Global loot modifier support for preserving skull data on drops.
- `src/main/java/com/hexagram2021/skullcraft/common/world`
	- Village professions, POIs, trades, and structure-pool injection logic.
- `src/main/java/com/hexagram2021/skullcraft/client`
	- Client-only event wiring for model layers, skull models, texture mapping, and menu screen registration.
- `src/main/java/com/hexagram2021/skullcraft/client/model`
	- One model class per skull family plus small rendering helper interfaces.
- `src/main/java/com/hexagram2021/skullcraft/client/screen`
	- `SkullChargerScreen`.
- `src/main/java/com/hexagram2021/skullcraft/client/config`
	- Client-only config.
- `src/main/java/com/hexagram2021/skullcraft/mixin`
	- The non-obvious vanilla integration layer; check here before changing skull persistence, rendering, player equipment visuals, note block behavior, or mob/player head drops.

## Important implementation patterns

- Custom skull support is cross-cutting, not localized to one class.
	- Registration lives in `SCBlocks`/`SCItems`.
	- Vanilla skull recognition is extended in `SCContent.init()`.
	- Persistence and drop behavior are split across mixins, block loot tables, and the global loot modifier.
	- If you change skull data behavior, inspect all of those areas together.
- Skull families are implemented end-to-end.
	- Adding a new skull type usually means touching block registration, item registration, client model registration, texture mapping, and resources for the same family.
- The mod bridges item data components and block-entity NBT.
	- Item-side data uses `SCDataComponents` plus vanilla item components like enchantments and repair cost.
	- Block-entity persistence still uses explicit tags declared in `SkullCraft` and handled through mixins.
- Village content is split across code and data.
	- Java registration/trade logic lives in `common/world/Villages.java`.
	- Structures, pools, and related data live under `src/main/resources/data/skullcraft/...` and `src/main/resources/data/neoforge/...`.
	- Pool mutation relies on the accessor mixin `StructureTemplatePoolAccess`.
- There is no dedicated custom networking layer.
	- The Skull Charger UI uses menu/container synchronization patterns rather than custom payloads.

## Resources and data

- Assets live under `src/main/resources/assets/skullcraft`.
- Data-driven content lives under `src/main/resources/data/skullcraft`.
- NeoForge-specific data such as global loot modifier indexes also appears under `src/main/resources/data/neoforge`.
- `runData` writes to `src/generated/resources`, and that directory is already added to the main resources source set.
- Enchantments are data-driven JSON definitions; Java mostly provides keys/tags rather than imperative enchantment implementations.

## Current repository-specific caveats

- `src/main/resources/skullcraft.mixins.json` still declares `"compatibilityLevel": "JAVA_17"`, but the Gradle toolchain is Java 21.
- Running `./gradlew tasks --all` currently reports a NeoGradle deprecation warning in `build.gradle` for `Run.getProgramArguments()`; prefer `getArguments()` if touching run configuration.
- The repository already contains many local modifications in the working tree, so be careful not to overwrite unrelated user changes while editing.
