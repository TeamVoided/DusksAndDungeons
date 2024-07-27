package org.teamvoided.dusk_autumn.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.init.DuskItemGroups
import org.teamvoided.dusk_autumn.init.DuskItemGroups.DUSK_AUTUMN_TAB
import org.teamvoided.dusk_autumn.init.DuskItemGroups.getKey
import org.teamvoided.dusk_autumn.init.DuskItems
import java.util.concurrent.CompletableFuture

@Suppress("MemberVisibilityCanBePrivate")
class EnglishTranslationProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricLanguageProvider(o, r) {

    val items = listOf(
        DuskBlocks.BLUE_PETALS.asItem(),
        DuskBlocks.CASCADE_SAPLING.asItem(),
        DuskBlocks.CASCADE_LEAVES.asItem(),
        DuskBlocks.GOLDEN_BIRCH_SAPLING.asItem(),
        DuskBlocks.GOLDEN_BIRCH_LEAVES.asItem(),
        DuskBlocks.BIG_CHAIN.asItem(),
        DuskBlocks.MIXED_NETHER_BRICKS.asItem(),
        DuskBlocks.ROOT_BLOCK.asItem(),
        DuskItems.FARMERS_HAT,
        DuskItems.WILD_WHEAT,
        DuskItems.GOLDEN_BEETROOT,
        DuskItems.BLUE_DOOR,
    ) + DuskItemGroups.cascadeWood +
            DuskItemGroups.cascadeSigns +
            DuskItemGroups.netherBrickStuff +
            DuskItemGroups.redNetherBrickStuff +
            DuskItemGroups.overgrownCobblestone +
            DuskItemGroups.overgrownStoneBricks +
            DuskItemGroups.leafPiles +
            DuskItemGroups.moonberry+ 
            DuskItemGroups.overlayBlocks
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
