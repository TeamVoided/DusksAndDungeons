package org.teamvoided.dusk_autumn.datagen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.init.DuskItems

@Suppress("MemberVisibilityCanBePrivate")
class EnglishTranslationProvider(o: FabricDataOutput) : FabricLanguageProvider(o) {

    val items = listOf(
        DuskItems.CASCADE_SAPLING,
        DuskItems.CASCADE_LOG,
        DuskItems.STRIPPED_CASCADE_LOG,
        DuskItems.CASCADE_PLANKS,
        DuskItems.CASCADE_DOOR,
        DuskItems.CASCADE_TRAPDOOR,
        DuskItems.BLUE_DOOR,

        DuskItems.GOLDEN_BIRCH_SAPLING,
        DuskItems.GOLDEN_BIRCH_LEAVES,

        DuskItems.BLUE_PETALS,

        DuskItems.OAK_LEAF_PILE,
        DuskItems.SPRUCE_LEAF_PILE,
        DuskItems.BIRCH_LEAF_PILE,
        DuskItems.JUNGLE_LEAF_PILE,
        DuskItems.ACACIA_LEAF_PILE,
        DuskItems.DARK_OAK_LEAF_PILE,
        DuskItems.MANGROVE_LEAF_PILE,
        DuskItems.CHERRY_LEAF_PILE,
        DuskItems.AZALEA_LEAF_PILE,
        DuskItems.FLOWERING_AZALEA_LEAF_PILE,
        DuskItems.CASCADE_LEAF_PILE,
        DuskItems.GOLDEN_BIRCH_LEAF_PILE
    )
    val blocks = listOf<Block>()
    override fun generateTranslations(gen: TranslationBuilder) {
        items.forEach { gen.add(it.translationKey, genLang(it.id)) }
        blocks.forEach { gen.add(it.translationKey, genLang(it.id)) }
    }

    private fun genLang(identifier: Identifier): String =
        identifier.path.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

    val Item.id get() = Registries.ITEM.getId(this)
    val Block.id get() = Registries.BLOCK.getId(this)
}