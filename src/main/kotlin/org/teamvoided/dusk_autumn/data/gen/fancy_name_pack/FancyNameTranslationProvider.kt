package org.teamvoided.dusk_autumn.data.gen.fancy_name_pack

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.init.DuskBlocks
import java.util.concurrent.CompletableFuture

@Suppress("MemberVisibilityCanBePrivate")
class FancyNameTranslationProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricLanguageProvider(o, r) {

    val ashenBricks = listOf(
        DuskBlocks.GREY_NETHER_BRICKS,
        DuskBlocks.CRACKED_GREY_NETHER_BRICKS,
        DuskBlocks.GREY_NETHER_BRICK_STAIRS,
        DuskBlocks.GREY_NETHER_BRICK_SLAB,
        DuskBlocks.GREY_NETHER_BRICK_WALL,
        DuskBlocks.GREY_NETHER_BRICK_FENCE,
        DuskBlocks.CHISELED_GREY_NETHER_BRICKS,
        DuskBlocks.GREY_NETHER_BRICK_PILLAR,
        DuskBlocks.POLISHED_GREY_NETHER_BRICKS,
        DuskBlocks.POLISHED_GREY_NETHER_BRICK_STAIRS,
        DuskBlocks.POLISHED_GREY_NETHER_BRICK_SLAB,
        DuskBlocks.POLISHED_GREY_NETHER_BRICK_WALL,
    )

    override fun generateTranslations(lookup: HolderLookup.Provider, gen: TranslationBuilder) {

        ashenBricks.forEach { gen.add(it.translationKey, genLang(it.id).replace("Grey", "Ashen")) }

    }

    private fun genLang(identifier: Identifier): String =
        identifier.path.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

    val Item.id get() = Registries.ITEM.getId(this)
    val Block.id get() = Registries.BLOCK.getId(this)

}
