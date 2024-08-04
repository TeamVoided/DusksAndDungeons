package org.teamvoided.dusk_autumn.data.gen.fancy_name_pack

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.Item
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import java.util.concurrent.CompletableFuture

@Suppress("MemberVisibilityCanBePrivate", "unused")
class FancyNameVanillaTranslationProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricLanguageProvider(o, r) {

    val crimsonBricks = listOf(
        Blocks.RED_NETHER_BRICKS,
        Blocks.RED_NETHER_BRICK_STAIRS,
        Blocks.RED_NETHER_BRICK_SLAB,
        Blocks.RED_NETHER_BRICK_WALL,
    )
    val nether = listOf(
        Blocks.NETHER_WART,
        Blocks.NETHER_WART_BLOCK
    )

    override fun generateTranslations(lookup: HolderLookup.Provider, gen: TranslationBuilder) {
        nether.forEach { gen.add(it.translationKey, genLang(it.id).replace("Nether", "Crimson")) }
        crimsonBricks.forEach { gen.add(it.translationKey, genLang(it.id).replace("Red", "Crimson")) }
    }

    private fun genLang(identifier: Identifier): String =
        identifier.path.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

    val Item.id get() = Registries.ITEM.getId(this)
    val Block.id get() = Registries.BLOCK.getId(this)

}
