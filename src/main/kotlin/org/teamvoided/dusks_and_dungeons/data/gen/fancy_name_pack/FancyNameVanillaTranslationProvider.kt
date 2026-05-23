package org.teamvoided.dusks_and_dungeons.data.gen.fancy_name_pack

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.item.Item
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
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
        nether.forEach { gen.add(it.descriptionId, genLang(it.id).replace("Nether", "Crimson")) }
        crimsonBricks.forEach { gen.add(it.descriptionId, genLang(it.id).replace("Red", "Crimson")) }
    }

    private fun genLang(identifier: ResourceLocation): String =
        identifier.path.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

    val Item.id get() = BuiltInRegistries.ITEM.getKey(this)
    val Block.id get() = BuiltInRegistries.BLOCK.getKey(this)

}
