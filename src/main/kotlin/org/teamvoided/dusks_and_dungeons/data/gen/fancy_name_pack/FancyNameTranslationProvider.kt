package org.teamvoided.dusks_and_dungeons.data.gen.fancy_name_pack

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.world.level.block.Block
import net.minecraft.world.item.Item
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
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
    ) + DnDBlocks.POLISHED_RED_NETHER_BRICKS.list + DnDBlocks.MIXED_RED_NETHER_BRICKS.list
    val warpedBricks = listOf(
        DnDBlocks.CRACKED_BLUE_NETHER_BRICKS,
        DnDBlocks.BLUE_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_BLUE_NETHER_BRICKS,
        DnDBlocks.BLUE_NETHER_BRICK_PILLAR,
        DnDBlocks.CRACKED_MIXED_BLUE_NETHER_BRICKS,
        DnDBlocks.MIXED_BLUE_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_MIXED_BLUE_NETHER_BRICKS,
        DnDBlocks.MIXED_BLUE_NETHER_BRICK_PILLAR,
    ) + DnDBlocks.POLISHED_BLUE_NETHER_BRICKS.list +
            DnDBlocks.MIXED_BLUE_NETHER_BRICKS.list + DnDBlocks.BLUE_NETHER_BRICKS.list
    val ashenBricks = listOf(
        DnDBlocks.CRACKED_GRAY_NETHER_BRICKS,
        DnDBlocks.GRAY_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_GRAY_NETHER_BRICKS,
        DnDBlocks.GRAY_NETHER_BRICK_PILLAR,
        DnDBlocks.CRACKED_MIXED_GRAY_NETHER_BRICKS,
        DnDBlocks.MIXED_GRAY_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_MIXED_GRAY_NETHER_BRICKS,
        DnDBlocks.MIXED_GRAY_NETHER_BRICK_PILLAR,
    ) + DnDBlocks.MIXED_GRAY_NETHER_BRICKS.list +
            DnDBlocks.POLISHED_GRAY_NETHER_BRICKS.list + DnDBlocks.GRAY_NETHER_BRICKS.list


    override fun generateTranslations(lookup: HolderLookup.Provider, gen: TranslationBuilder) {
        crimsonBricks.forEach { gen.add(it.descriptionId, genLang(it.id).replace("Red", "Crimson")) }
        warpedBricks.forEach { gen.add(it.descriptionId, genLang(it.id).replace("Blue", "Warped")) }
        ashenBricks.forEach { gen.add(it.descriptionId, genLang(it.id).replace("Gray", "Ashen")) }
    }

    private fun genLang(identifier: ResourceLocation): String =
        identifier.path.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

    val Item.id get() = BuiltInRegistries.ITEM.getKey(this)
    val Block.id get() = BuiltInRegistries.BLOCK.getKey(this)

}
