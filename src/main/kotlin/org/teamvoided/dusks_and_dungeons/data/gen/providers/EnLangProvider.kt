package org.teamvoided.dusks_and_dungeons.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.world.level.block.Block
import net.minecraft.world.item.Item
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.init.DnDTabs.DUSKS_AND_DUNGEONS
import org.teamvoided.dusks_and_dungeons.init.DnDTabs.OVERLAY_BLOCKS
import org.teamvoided.voidlib.devin.FDOutput
import org.teamvoided.voidlib.devin.FutureLookup

@Suppress("MemberVisibilityCanBePrivate")
class EnLangProvider(o: FDOutput, r: FutureLookup) : FabricLanguageProvider(o, r) {
    val blocks = listOf(DnDBlocks.GOLDEN_BEETROOTS)
    override fun generateTranslations(lookup: HolderLookup.Provider, gen: TranslationBuilder) {
        DUSKS_AND_DUNGEONS.unwrapKey().get().let { gen.add(it, "Dusks and Dungeons") }
        OVERLAY_BLOCKS.unwrapKey().get().let { gen.add(it, "Rocky Blocks") }
        DnDItemTags.ITEM_TAGS.forEach { gen.add(it.translationKey, genLang(it.location)) }
        blocks.forEach { gen.add(it.descriptionId, genLang(it.id)) }
        DnDItems.ITEMS.forEach { gen.add(it.descriptionId, genLang(it.id)) }
    }

    private fun genLang(identifier: ResourceLocation): String =
        identifier.path.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

    val Item.id get() = BuiltInRegistries.ITEM.getKey(this)
    val Block.id get() = BuiltInRegistries.BLOCK.getKey(this)
}