package org.teamvoided.dusk_autumn.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.data.tags.DuskItemTags
import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.init.DuskItemGroups.DUSK_AUTUMN_TAB
import org.teamvoided.dusk_autumn.init.DuskItemGroups.getKey
import org.teamvoided.dusk_autumn.init.DuskItems
import org.teamvoided.dusk_autumn.item.DuskItemLists
import java.util.concurrent.CompletableFuture

@Suppress("MemberVisibilityCanBePrivate")
class EnglishTranslationProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricLanguageProvider(o, r) {


    val itemTags = listOf(
        DuskItemTags.CASCADE_LOGS,
        DuskItemTags.LEAF_PILES,
        DuskItemTags.NETHER_BRICKS
    )
    val items = listOf(
        DuskBlocks.BLUE_PETALS.asItem(),
        DuskBlocks.CASCADE_SAPLING.asItem(),
        DuskBlocks.CASCADE_LEAVES.asItem(),
        DuskBlocks.GOLDEN_BIRCH_SAPLING.asItem(),
        DuskBlocks.GOLDEN_BIRCH_LEAVES.asItem(),
        DuskBlocks.BIG_CHAIN.asItem(),
        DuskBlocks.BIG_LANTERN.asItem(),
        DuskBlocks.BIG_SOUL_LANTERN.asItem(),
        DuskBlocks.ROOT_BLOCK.asItem(),
        DuskItems.FARMERS_HAT,
        DuskItems.WILD_WHEAT,
        DuskItems.GOLDEN_BEETROOT,
        DuskItems.BLUE_DOOR,
    ) + DuskItemLists.cascadeWood +
            DuskItemLists.cascadeSigns +
//            DuskItemLists.pineWood +
            DuskItemLists.netherBrickStuff +
            DuskItemLists.redNetherBrickStuff +
            DuskItemLists.mixedNetherBrickStuff +
            DuskItemLists.blackstoneTools +
            DuskItemLists.overgrownCobblestone +
            DuskItemLists.overgrownStoneBricks +
            DuskItemLists.logPiles +
            DuskItemLists.leafPiles +
            DuskItemLists.moonberry +
            DuskItemLists.overlayBlocks
    val blocks = listOf(
        DuskBlocks.GOLDEN_BEETROOTS
    )

    override fun generateTranslations(lookup: HolderLookup.Provider, gen: TranslationBuilder) {
        getKey(DUSK_AUTUMN_TAB)?.let { gen.add(it, "Dusk Items") }
        itemTags.forEach { gen.add(it.translationKey, genLang(it.id)) }
        items.forEach { gen.add(it.translationKey, genLang(it.id)) }
        blocks.forEach { gen.add(it.translationKey, genLang(it.id)) }
    }

    private fun genLang(identifier: Identifier): String =
        identifier.path.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

    val Item.id get() = Registries.ITEM.getId(this)
    val Block.id get() = Registries.BLOCK.getId(this)

}
