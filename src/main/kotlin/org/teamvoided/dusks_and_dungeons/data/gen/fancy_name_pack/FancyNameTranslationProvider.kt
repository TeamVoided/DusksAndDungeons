package org.teamvoided.dusks_and_dungeons.data.gen.fancy_name_pack

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDNetherBrickBlocks
import java.util.concurrent.CompletableFuture

@Suppress("MemberVisibilityCanBePrivate")
class FancyNameTranslationProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricLanguageProvider(o, r) {

    val crimsonBricks = listOf(
        DnDNetherBrickBlocks.CRACKED_RED_NETHER_BRICKS,
        DnDNetherBrickBlocks.RED_NETHER_BRICK_FENCE,
        DnDNetherBrickBlocks.CHISELED_RED_NETHER_BRICKS,
        DnDNetherBrickBlocks.RED_NETHER_BRICK_PILLAR,
        DnDNetherBrickBlocks.CRACKED_MIXED_RED_NETHER_BRICKS,
        DnDNetherBrickBlocks.MIXED_RED_NETHER_BRICK_FENCE,
        DnDNetherBrickBlocks.CHISELED_MIXED_RED_NETHER_BRICKS,
        DnDNetherBrickBlocks.MIXED_RED_NETHER_BRICK_PILLAR,
    ) + DnDNetherBrickBlocks.POLISHED_RED_NETHER_BRICKS.collect() + DnDNetherBrickBlocks.MIXED_RED_NETHER_BRICKS.collect()
    val warpedBricks = listOf(
        DnDNetherBrickBlocks.CRACKED_BLUE_NETHER_BRICKS,
        DnDNetherBrickBlocks.BLUE_NETHER_BRICK_FENCE,
        DnDNetherBrickBlocks.CHISELED_BLUE_NETHER_BRICKS,
        DnDNetherBrickBlocks.BLUE_NETHER_BRICK_PILLAR,
        DnDNetherBrickBlocks.CRACKED_MIXED_BLUE_NETHER_BRICKS,
        DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_FENCE,
        DnDNetherBrickBlocks.CHISELED_MIXED_BLUE_NETHER_BRICKS,
        DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_PILLAR,
    ) + DnDNetherBrickBlocks.POLISHED_BLUE_NETHER_BRICKS.collect() +
            DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICKS.collect() + DnDNetherBrickBlocks.BLUE_NETHER_BRICKS.collect()
    val ashenBricks = listOf(
        DnDNetherBrickBlocks.CRACKED_GRAY_NETHER_BRICKS,
        DnDNetherBrickBlocks.GRAY_NETHER_BRICK_FENCE,
        DnDNetherBrickBlocks.CHISELED_GRAY_NETHER_BRICKS,
        DnDNetherBrickBlocks.GRAY_NETHER_BRICK_PILLAR,
        DnDNetherBrickBlocks.CRACKED_MIXED_GRAY_NETHER_BRICKS,
        DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_FENCE,
        DnDNetherBrickBlocks.CHISELED_MIXED_GRAY_NETHER_BRICKS,
        DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_PILLAR,
    ) + DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICKS.collect() +
            DnDNetherBrickBlocks.POLISHED_GRAY_NETHER_BRICKS.collect() + DnDNetherBrickBlocks.GRAY_NETHER_BRICKS.collect()


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
