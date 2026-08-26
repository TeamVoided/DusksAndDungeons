package org.teamvoided.dusks_and_dungeons.init.events

import net.fabricmc.fabric.api.loot.v3.LootTableEvents
import net.fabricmc.fabric.api.loot.v3.LootTableSource
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder
import net.minecraft.core.HolderLookup
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Items
import net.minecraft.world.level.storage.loot.BuiltInLootTables.*
import net.minecraft.world.level.storage.loot.LootTable
import org.teamvoided.dusks_and_dungeons.api.PostDataLoadEvent
import org.teamvoided.dusks_and_dungeons.data.DnDLootTables.BARTERING_ADD_VIVIONS
import org.teamvoided.dusks_and_dungeons.data.DnDLootTables.SIMPLE_DUNGEON_ADD_SPOOKY
import org.teamvoided.dusks_and_dungeons.data.DnDLootTables.SNIFFER_ADD_MOONBERRY
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.item.throwable.ThrownItemDefinition
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.voidlib.helpers.mc.addNewPool
import org.teamvoided.voidlib.helpers.mc.addToExistingPools
import org.teamvoided.voidlib.helpers.mc.compost
import org.teamvoided.voidlib.helpers.mc.fuel

object DnDEvents {

    fun init() {

        // TODO(1.0) move?
        FabricBrewingRecipeRegistryBuilder.BUILD.register { builder ->
            builder.addContainer(DnDItems.TINTED_POTION)
            builder.addContainer(DnDItems.TINTED_SPLASH_POTION)
            builder.addContainer(DnDItems.TINTED_LINGERING_POTION)

            builder.addContainerRecipe(DnDItems.TINTED_POTION, Items.GUNPOWDER, DnDItems.TINTED_SPLASH_POTION)
            builder.addContainerRecipe(DnDItems.TINTED_SPLASH_POTION, Items.DRAGON_BREATH, DnDItems.TINTED_LINGERING_POTION)
        }

        LootTableEvents.MODIFY.register(::modifyLootTables)
        initTrades()
        initItemEvents()
        compostItems()
        createFuels()

        PostDataLoadEvent.DATA_LOADED.register { ThrownItemDefinition.refreshCache(it.registryAccess()) }
    }

    @Suppress("UNUSED_PARAMETER")
    fun modifyLootTables(
        key: ResourceKey<LootTable>, builder: LootTable.Builder, src: LootTableSource, lookup: HolderLookup.Provider,
    ): Any = when (key) {
        PIGLIN_BARTERING -> addToExistingPools(builder, BARTERING_ADD_VIVIONS)
        SNIFFER_DIGGING -> addToExistingPools(builder, SNIFFER_ADD_MOONBERRY)
        SIMPLE_DUNGEON -> addNewPool(builder, SIMPLE_DUNGEON_ADD_SPOOKY)
        else -> Unit
    }


    fun compostItems() {
        compost(DnDBlocks.ROOT_BLOCK, 0.65)
        DnDBlockLists.flowerbedBlocks.forEach { compost(it, 0.3) }

        compost(DnDBlocks.GOLDEN_MUSHROOM, 0.65)
        compost(DnDBlocks.GOLDEN_MUSHROOM_BLOCK, 0.85)
        compost(DnDBlocks.GOLDEN_MUSHROOM_STEM_BLOCK, 0.65)

        compost(DnDItems.LANTERN_PUMPKIN_SEEDS, 0.3)
        compost(DnDItems.MOSSKIN_PUMPKIN_SEEDS, 0.3)
        compost(DnDItems.GLOOM_PUMPKIN_SEEDS, 0.3)
        compost(DnDItems.PALE_PUMPKIN_SEEDS, 0.3)

        compost(DnDBlocks.LANTERN_PUMPKIN, 0.65)
        compost(DnDBlocks.MOSSKIN_PUMPKIN, 0.65)
        compost(DnDBlocks.GLOOM_PUMPKIN, 0.65)
        compost(DnDBlocks.PALE_PUMPKIN, 0.65)

        compost(DnDBlocks.CARVED_LANTERN_PUMPKIN, 0.65)
        compost(DnDBlocks.CARVED_MOSSKIN_PUMPKIN, 0.65)
        compost(DnDBlocks.CARVED_GLOOM_PUMPKIN, 0.65)
        compost(DnDBlocks.CARVED_PALE_PUMPKIN, 0.65)

        compost(DnDBlocks.SMALL_PUMPKIN, 0.45)
        compost(DnDBlocks.SMALL_LANTERN_PUMPKIN, 0.45)
        compost(DnDBlocks.SMALL_MOSSKIN_PUMPKIN, 0.45)
        compost(DnDBlocks.SMALL_GLOOM_PUMPKIN, 0.45)
        compost(DnDBlocks.SMALL_PALE_PUMPKIN, 0.45)

        compost(DnDBlocks.SMALL_CARVED_PUMPKIN, 0.45)
        compost(DnDBlocks.SMALL_CARVED_LANTERN_PUMPKIN, 0.45)
        compost(DnDBlocks.SMALL_CARVED_MOSSKIN_PUMPKIN, 0.45)
        compost(DnDBlocks.SMALL_CARVED_GLOOM_PUMPKIN, 0.45)
        compost(DnDBlocks.SMALL_CARVED_PALE_PUMPKIN, 0.45)
    }

    fun createFuels() {
        fuel(DnDItems.BIG_SCAFFOLDING, 300)
        fuel(DnDBlocks.MOLTEN_LAVASPONGE, 20000)
        fuel(DnDItems.GLOWING_LAVASPONGE, 20000)
        fuel(DnDItemTags.HOLLOW_LOGS_THAT_BURN, 300)
        fuel(DnDItemTags.LOG_PILES_THAT_BURN, 300)
        fuel(DnDItemTags.WOOD_WALLS_THAT_BURN, 300)
        fuel(DnDItemTags.PLANK_WALLS_THAT_BURN, 275)
        fuel(DnDItemTags.BOOKSHELVES_THAT_BURN, 300)
    }

}