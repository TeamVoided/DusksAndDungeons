package org.teamvoided.dusks_and_dungeons.data.gen.fancy_name_pack

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import java.util.concurrent.CompletableFuture

@Suppress("MemberVisibilityCanBePrivate")
class FancyNameTranslationProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricLanguageProvider(o, r) {

    val crimsonBricks = listOf(
        DnDBlocks.CRACKED_RED_NETHER_BRICKS,
        DnDBlocks.RED_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_RED_NETHER_BRICKS,
        DnDBlocks.RED_NETHER_BRICK_PILLAR,
        DnDBlocks.CRACKED_MIXED_RED_NETHER_BRICKS,
        DnDBlocks.MIXED_RED_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_MIXED_RED_NETHER_BRICKS,
        DnDBlocks.MIXED_RED_NETHER_BRICK_PILLAR,
    ) + DnDBlocks.POLISHED_RED_NETHER_BRICKS.collect() + DnDBlocks.MIXED_RED_NETHER_BRICKS.collect()
    val warpedBricks = listOf(
        DnDBlocks.CRACKED_BLUE_NETHER_BRICKS,
        DnDBlocks.BLUE_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_BLUE_NETHER_BRICKS,
        DnDBlocks.BLUE_NETHER_BRICK_PILLAR,
        DnDBlocks.CRACKED_MIXED_BLUE_NETHER_BRICKS,
        DnDBlocks.MIXED_BLUE_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_MIXED_BLUE_NETHER_BRICKS,
        DnDBlocks.MIXED_BLUE_NETHER_BRICK_PILLAR,
    ) + DnDBlocks.POLISHED_BLUE_NETHER_BRICKS.collect() +
            DnDBlocks.MIXED_BLUE_NETHER_BRICKS.collect() + DnDBlocks.BLUE_NETHER_BRICKS.collect()
    val ashenBricks = listOf(
        DnDBlocks.CRACKED_GRAY_NETHER_BRICKS,
        DnDBlocks.GRAY_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_GRAY_NETHER_BRICKS,
        DnDBlocks.GRAY_NETHER_BRICK_PILLAR,
        DnDBlocks.CRACKED_MIXED_GRAY_NETHER_BRICKS,
        DnDBlocks.MIXED_GRAY_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_MIXED_GRAY_NETHER_BRICKS,
        DnDBlocks.MIXED_GRAY_NETHER_BRICK_PILLAR,
    ) + DnDBlocks.MIXED_GRAY_NETHER_BRICKS.collect() +
            DnDBlocks.POLISHED_GRAY_NETHER_BRICKS.collect() + DnDBlocks.GRAY_NETHER_BRICKS.collect()


    override fun generateTranslations(lookup: HolderLookup.Provider, gen: TranslationBuilder) {
        crimsonBricks.forEach { gen.add(it.translationKey, genLang(it.id).replace("Red", "Crimson")) }
        warpedBricks.forEach { gen.add(it.translationKey, genLang(it.id).replace("Blue", "Warped")) }
        ashenBricks.forEach { gen.add(it.translationKey, genLang(it.id).replace("Gray", "Ashen")) }
    }

    private fun genLang(identifier: Identifier): String =
        identifier.path.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

    val Item.id get() = Registries.ITEM.getId(this)
    val Block.id get() = Registries.BLOCK.getId(this)

}
