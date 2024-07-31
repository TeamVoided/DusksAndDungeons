package org.teamvoided.dusk_autumn.data.gen.fancy_name_pack

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.init.DnDBlocks
import java.util.concurrent.CompletableFuture

@Suppress("MemberVisibilityCanBePrivate")
class FancyNameTranslationProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricLanguageProvider(o, r) {

    val ashenBricks = listOf(
        DnDBlocks.GRAY_NETHER_BRICKS,
        DnDBlocks.CRACKED_GRAY_NETHER_BRICKS,
        DnDBlocks.GRAY_NETHER_BRICK_STAIRS,
        DnDBlocks.GRAY_NETHER_BRICK_SLAB,
        DnDBlocks.GRAY_NETHER_BRICK_WALL,
        DnDBlocks.GRAY_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_GRAY_NETHER_BRICKS,
        DnDBlocks.GRAY_NETHER_BRICK_PILLAR,
        DnDBlocks.POLISHED_GRAY_NETHER_BRICKS,
        DnDBlocks.POLISHED_GRAY_NETHER_BRICK_STAIRS,
        DnDBlocks.POLISHED_GRAY_NETHER_BRICK_SLAB,
        DnDBlocks.POLISHED_GRAY_NETHER_BRICK_WALL,
    )

    override fun generateTranslations(lookup: HolderLookup.Provider, gen: TranslationBuilder) {

        ashenBricks.forEach { gen.add(it.translationKey, genLang(it.id).replace("Grey", "Ashen")) }

    }

    private fun genLang(identifier: Identifier): String =
        identifier.path.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

    val Item.id get() = Registries.ITEM.getId(this)
    val Block.id get() = Registries.BLOCK.getId(this)

}
