package org.teamvoided.dusk_autumn.data.gen.fancy_name_pack

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.blocks.DnDNetherBrickBlocks
import java.util.concurrent.CompletableFuture

@Suppress("MemberVisibilityCanBePrivate")
class FancyNameTranslationProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricLanguageProvider(o, r) {

    val crimsonBricks = listOf(
        DnDNetherBrickBlocks.CRACKED_RED_NETHER_BRICKS,
        DnDNetherBrickBlocks.RED_NETHER_BRICK_FENCE,
        DnDNetherBrickBlocks.CHISELED_RED_NETHER_BRICKS,
        DnDNetherBrickBlocks.RED_NETHER_BRICK_PILLAR,
        DnDNetherBrickBlocks.POLISHED_RED_NETHER_BRICKS,
        DnDNetherBrickBlocks.POLISHED_RED_NETHER_BRICK_STAIRS,
        DnDNetherBrickBlocks.POLISHED_RED_NETHER_BRICK_SLAB,
        DnDNetherBrickBlocks.POLISHED_RED_NETHER_BRICK_WALL,
        DnDNetherBrickBlocks.MIXED_NETHER_BRICKS,
        DnDNetherBrickBlocks.CRACKED_MIXED_NETHER_BRICKS,
        DnDNetherBrickBlocks.MIXED_NETHER_BRICK_STAIRS,
        DnDNetherBrickBlocks.MIXED_NETHER_BRICK_SLAB,
        DnDNetherBrickBlocks.MIXED_NETHER_BRICK_WALL,
        DnDNetherBrickBlocks.MIXED_NETHER_BRICK_FENCE,
        DnDNetherBrickBlocks.CHISELED_MIXED_NETHER_BRICKS,
        DnDNetherBrickBlocks.MIXED_NETHER_BRICK_PILLAR,
    )
    val warpedBricks = listOf(
        DnDNetherBrickBlocks.BLUE_NETHER_BRICKS,
        DnDNetherBrickBlocks.CRACKED_BLUE_NETHER_BRICKS,
        DnDNetherBrickBlocks.BLUE_NETHER_BRICK_STAIRS,
        DnDNetherBrickBlocks.BLUE_NETHER_BRICK_SLAB,
        DnDNetherBrickBlocks.BLUE_NETHER_BRICK_WALL,
        DnDNetherBrickBlocks.BLUE_NETHER_BRICK_FENCE,
        DnDNetherBrickBlocks.CHISELED_BLUE_NETHER_BRICKS,
        DnDNetherBrickBlocks.BLUE_NETHER_BRICK_PILLAR,
        DnDNetherBrickBlocks.POLISHED_BLUE_NETHER_BRICKS,
        DnDNetherBrickBlocks.POLISHED_BLUE_NETHER_BRICK_STAIRS,
        DnDNetherBrickBlocks.POLISHED_BLUE_NETHER_BRICK_SLAB,
        DnDNetherBrickBlocks.POLISHED_BLUE_NETHER_BRICK_WALL,
        DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICKS,
        DnDNetherBrickBlocks.CRACKED_MIXED_BLUE_NETHER_BRICKS,
        DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_STAIRS,
        DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_SLAB,
        DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_WALL,
        DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_FENCE,
        DnDNetherBrickBlocks.CHISELED_MIXED_BLUE_NETHER_BRICKS,
        DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_PILLAR,
    )
    val ashenBricks = listOf(
        DnDNetherBrickBlocks.GRAY_NETHER_BRICKS,
        DnDNetherBrickBlocks.CRACKED_GRAY_NETHER_BRICKS,
        DnDNetherBrickBlocks.GRAY_NETHER_BRICK_STAIRS,
        DnDNetherBrickBlocks.GRAY_NETHER_BRICK_SLAB,
        DnDNetherBrickBlocks.GRAY_NETHER_BRICK_WALL,
        DnDNetherBrickBlocks.GRAY_NETHER_BRICK_FENCE,
        DnDNetherBrickBlocks.CHISELED_GRAY_NETHER_BRICKS,
        DnDNetherBrickBlocks.GRAY_NETHER_BRICK_PILLAR,
        DnDNetherBrickBlocks.POLISHED_GRAY_NETHER_BRICKS,
        DnDNetherBrickBlocks.POLISHED_GRAY_NETHER_BRICK_STAIRS,
        DnDNetherBrickBlocks.POLISHED_GRAY_NETHER_BRICK_SLAB,
        DnDNetherBrickBlocks.POLISHED_GRAY_NETHER_BRICK_WALL,
        DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICKS,
        DnDNetherBrickBlocks.CRACKED_MIXED_GRAY_NETHER_BRICKS,
        DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_STAIRS,
        DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_SLAB,
        DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_WALL,
        DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_FENCE,
        DnDNetherBrickBlocks.CHISELED_MIXED_GRAY_NETHER_BRICKS,
        DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_PILLAR,
    )

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
