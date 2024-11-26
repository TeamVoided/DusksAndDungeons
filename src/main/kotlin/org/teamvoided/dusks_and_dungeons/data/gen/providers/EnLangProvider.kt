package org.teamvoided.dusks_and_dungeons.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.init.DnDTabs.DUSKS_AND_DUNGEONS
import org.teamvoided.dusks_and_dungeons.init.DnDTabs.OVERLAY_BLOCKS
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDFloraBlocks
import org.teamvoided.voidlib.devin.FDOutput
import org.teamvoided.voidlib.devin.FutureLookup

@Suppress("MemberVisibilityCanBePrivate")
class EnLangProvider(o: FDOutput, r: FutureLookup) : FabricLanguageProvider(o, r) {
    val blocks = listOf(DnDFloraBlocks.GOLDEN_BEETROOTS)
    override fun generateTranslations(lookup: HolderLookup.Provider, gen: TranslationBuilder) {
        DUSKS_AND_DUNGEONS.key.get().let { gen.add(it, "Dusks and Dungeons") }
        OVERLAY_BLOCKS.key.get().let { gen.add(it, "Rocky Blocks") }
        DnDItemTags.ITEM_TAGS.forEach { gen.add(it.translationKey, genLang(it.id)) }
        blocks.forEach { gen.add(it.translationKey, genLang(it.id)) }
        DnDItems.ITEMS.forEach { gen.add(it.translationKey, genLang(it.id)) }
    }

    private fun genLang(identifier: Identifier): String =
        identifier.path.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

    val Item.id get() = Registries.ITEM.getId(this)
    val Block.id get() = Registries.BLOCK.getId(this)
}