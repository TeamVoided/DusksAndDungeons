package org.teamvoided.dusks_and_dungeons.datagen.packs

import net.minecraft.core.HolderLookup
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.util.block.getId
import org.teamvoided.voidlib.devin.FabricOutput
import org.teamvoided.voidlib.devin.FutureProvider
import org.teamvoided.voidlib.devin.provider.VLangProvider

class FancyNameLangProvider(o: FabricOutput, p: FutureProvider) : VLangProvider(o, p) {

    val crimsonBricks = listOf(
        DnDBlocks.CRACKED_RED_NETHER_BRICKS,
        DnDBlocks.RED_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_RED_NETHER_BRICKS,
        DnDBlocks.RED_NETHER_BRICK_PILLAR,
    ) + DnDBlocks.POLISHED_RED_NETHER_BRICKS.list

    val warpedBricks = listOf(
        DnDBlocks.CRACKED_BLUE_NETHER_BRICKS,
        DnDBlocks.BLUE_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_BLUE_NETHER_BRICKS,
        DnDBlocks.BLUE_NETHER_BRICK_PILLAR,
    ) + DnDBlocks.POLISHED_BLUE_NETHER_BRICKS.list + DnDBlocks.BLUE_NETHER_BRICKS.list

    val ashenBricks = listOf(
        DnDBlocks.CRACKED_GRAY_NETHER_BRICKS,
        DnDBlocks.GRAY_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_GRAY_NETHER_BRICKS,
        DnDBlocks.GRAY_NETHER_BRICK_PILLAR,
    ) + DnDBlocks.POLISHED_GRAY_NETHER_BRICKS.list + DnDBlocks.GRAY_NETHER_BRICKS.list


    override fun generateTranslations(lookup: HolderLookup.Provider, gen: TranslationBuilder) {
        crimsonBricks.forEach { gen.add(it.descriptionId, getLang(getId(it))) }
        warpedBricks.forEach { gen.add(it.descriptionId, getLang(getId(it))) }
        ashenBricks.forEach { gen.add(it.descriptionId, getLang(getId(it))) }
    }

    override fun doOverrides(lang: String): String {
        return lang
            .replace("Red", "Crimson")
            .replace("Blue", "Warped")
            .replace("Gray", "Ashen")
    }

}