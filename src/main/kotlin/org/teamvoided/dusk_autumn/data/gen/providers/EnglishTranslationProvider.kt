package org.teamvoided.dusk_autumn.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.data.tags.DnDItemTags
import org.teamvoided.dusk_autumn.init.DnDTabs.DUSKS_AND_DUNGEONS_EXCEPT_DEBUG
import org.teamvoided.dusk_autumn.init.DnDTabs.getKey
import org.teamvoided.dusk_autumn.init.DnDItems
import org.teamvoided.dusk_autumn.init.DnDTabs.DUSKS_AND_DUNGEONS
import org.teamvoided.dusk_autumn.init.DnDTabs.OVERLAY_BLOCKS
import org.teamvoided.dusk_autumn.init.blocks.DnDFloraBlocks
import java.util.concurrent.CompletableFuture

@Suppress("MemberVisibilityCanBePrivate")
class EnglishTranslationProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricLanguageProvider(o, r) {
    val blocks = listOf(
        DnDFloraBlocks.GOLDEN_BEETROOTS
    )

    override fun generateTranslations(lookup: HolderLookup.Provider, gen: TranslationBuilder) {
        getKey(DUSKS_AND_DUNGEONS)?.let { gen.add(it, "Dusks and Dungeons") }
        getKey(OVERLAY_BLOCKS)?.let { gen.add(it, "Rocky Blocks") }
        DnDItemTags.ITEM_TAGS.forEach { gen.add(it.translationKey, genLang(it.id)) }
        blocks.forEach { gen.add(it.translationKey, genLang(it.id)) }
        DnDItems.ITEMS.forEach { gen.add(it.translationKey, genLang(it.id)) }
    }

    private fun genLang(identifier: Identifier): String =
        identifier.path.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

    val Item.id get() = Registries.ITEM.getId(this)
    val Block.id get() = Registries.BLOCK.getId(this)
}