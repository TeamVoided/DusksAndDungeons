package org.teamvoided.dusk_autumn.datagen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.block.Blocks
import net.minecraft.data.server.loot_table.VanillaBlockLootTableGenerator.JUNGLE_SAPLING_DROP_CHANCES
import org.teamvoided.dusk_autumn.init.DuskBlocks

class BlockLootTableProvider(o: FabricDataOutput) : FabricBlockLootTableProvider(o) {


    val dropsItSelf = listOf(
        DuskBlocks.CASCADE_PLANKS
    )

    override fun generate() {
        dropsItSelf.forEach(::addDrop)

        add(DuskBlocks.CASCADE_DOOR, ::doorDrops)

        add(DuskBlocks.OAK_LEAF_PILE) { oakLeavesDrops(it, Blocks.OAK_SAPLING, *LEAVES_SAPLING_DROP_CHANCES) }
        add(DuskBlocks.JUNGLE_LEAF_PILE) { leavesDrops(it, Blocks.JUNGLE_SAPLING, *JUNGLE_SAPLING_DROP_CHANCES) }
        add(DuskBlocks.MANGROVE_LEAF_PILE, ::mangroveLeavesDrops)

        add(DuskBlocks.BLUE_PETALS, ::method_49358)

        add(DuskBlocks.POTTED_VIOLET_DAISY, ::pottedPlantDrops)
    }
}