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
        DuskItems.CASCADE_SAPLING,
        DuskItems.CASCADE_LEAVES,
        DuskItems.CASCADE_LOG,
        DuskItems.STRIPPED_CASCADE_LOG,
        DuskItems.CASCADE_PLANKS,
        DuskItems.CASCADE_DOOR,
        DuskItems.CASCADE_TRAPDOOR,
        DuskItems.BLUE_DOOR,
        DuskItems.CASCADE_SIGN,
        DuskItems.CASCADE_HANGING_SIGN,

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
        DuskItems.GOLDEN_BIRCH_LEAF_PILE,

        DuskItems.FARMERS_HAT,
        DuskItems.WILD_WHEAT,
        DuskItems.GOLDEN_BEETROOT,
        DuskItems.MOONBERRY_VINE,
        DuskItems.MOONBERRY_VINELET,
        DuskItems.MOONBERRIES,

        DuskItems.VOLCANIC_SAND,
        DuskItems.SUSPICIOUS_VOLCANIC_SAND,
        DuskItems.VOLCANIC_SANDSTONE,
        DuskItems.VOLCANIC_SANDSTONE_STAIRS,
        DuskItems.VOLCANIC_SANDSTONE_SLAB,
        DuskItems.VOLCANIC_SANDSTONE_WALL,
        DuskItems.CUT_VOLCANIC_SANDSTONE,
        DuskItems.CUT_VOLCANIC_SANDSTONE_SLAB,
        DuskItems.CHISELED_VOLCANIC_SANDSTONE,
        DuskItems.SMOOTH_VOLCANIC_SANDSTONE,
        DuskItems.SMOOTH_VOLCANIC_SANDSTONE_STAIRS,
        DuskItems.SMOOTH_VOLCANIC_SANDSTONE_SLAB
    )
    val blocks = listOf(
        DuskBlocks.GOLDEN_BEETROOTS,
        DuskBlocks.GOLDEN_CARROTS
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