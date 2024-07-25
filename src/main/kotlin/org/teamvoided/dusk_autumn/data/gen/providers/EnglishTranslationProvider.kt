package org.teamvoided.dusk_autumn.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.init.DuskItemGroups.DUSK_AUTUMN_TAB
import org.teamvoided.dusk_autumn.init.DuskItemGroups.getKey
import org.teamvoided.dusk_autumn.init.DuskItems
import java.util.concurrent.CompletableFuture

@Suppress("MemberVisibilityCanBePrivate")
class EnglishTranslationProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricLanguageProvider(o, r) {

    val items = listOf(
        DuskBlocks.CASCADE_SAPLING.asItem(),
        DuskBlocks.CASCADE_LEAVES.asItem(),
        DuskBlocks.CASCADE_LOG.asItem(),
        DuskBlocks.STRIPPED_CASCADE_LOG.asItem(),
        DuskBlocks.CASCADE_PLANKS.asItem(),
        DuskItems.CASCADE_DOOR,
        DuskBlocks.CASCADE_TRAPDOOR.asItem(),
        DuskItems.BLUE_DOOR,
        DuskItems.CASCADE_SIGN,
        DuskItems.CASCADE_HANGING_SIGN,

        DuskBlocks.GOLDEN_BIRCH_SAPLING.asItem(),
        DuskBlocks.GOLDEN_BIRCH_LEAVES.asItem(),

        DuskBlocks.BLUE_PETALS.asItem(),

        DuskBlocks.OAK_LEAF_PILE.asItem(),
        DuskBlocks.SPRUCE_LEAF_PILE.asItem(),
        DuskBlocks.BIRCH_LEAF_PILE.asItem(),
        DuskBlocks.JUNGLE_LEAF_PILE.asItem(),
        DuskBlocks.ACACIA_LEAF_PILE.asItem(),
        DuskBlocks.DARK_OAK_LEAF_PILE.asItem(),
        DuskBlocks.MANGROVE_LEAF_PILE.asItem(),
        DuskBlocks.CHERRY_LEAF_PILE.asItem(),
        DuskBlocks.AZALEA_LEAF_PILE.asItem(),
        DuskBlocks.FLOWERING_AZALEA_LEAF_PILE.asItem(),
        DuskBlocks.CASCADE_LEAF_PILE.asItem(),
        DuskBlocks.GOLDEN_BIRCH_LEAF_PILE.asItem(),

        DuskBlocks.ROCKY_GRASS.asItem(),

        DuskItems.FARMERS_HAT,
        DuskItems.WILD_WHEAT,
        DuskItems.GOLDEN_BEETROOT,
        DuskBlocks.MOONBERRY_VINE.asItem(),
        DuskItems.MOONBERRY_VINELET,
        DuskItems.MOONBERRIES,

    )
    val blocks = listOf(
        DuskBlocks.GOLDEN_BEETROOTS
    )

    override fun generateTranslations(lookup: HolderLookup.Provider, gen: TranslationBuilder) {
        items.forEach { gen.add(it.translationKey, genLang(it.id)) }
        blocks.forEach { gen.add(it.translationKey, genLang(it.id)) }


        getKey(DUSK_AUTUMN_TAB)?.let { gen.add(it, "Dusk Items") }
    }

    private fun genLang(identifier: Identifier): String =
        identifier.path.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

    val Item.id get() = Registries.ITEM.getId(this)
    val Block.id get() = Registries.BLOCK.getId(this)

}
