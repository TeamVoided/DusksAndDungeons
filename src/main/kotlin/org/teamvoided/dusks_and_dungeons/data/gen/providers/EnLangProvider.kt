package org.teamvoided.dusks_and_dungeons.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDTabs.DUSKS_AND_DUNGEONS
import org.teamvoided.dusks_and_dungeons.init.DnDTabs.OVERLAY_BLOCKS
import org.teamvoided.dusks_and_dungeons.util.getModHolders
import org.teamvoided.voidlib.devin.FDOutput
import org.teamvoided.voidlib.devin.FutureLookup

class EnLangProvider(val output: FDOutput, r: FutureLookup) : FabricLanguageProvider(output, r) {
    val blocks = listOf(DnDBlocks.GOLDEN_BEETROOTS)
    override fun generateTranslations(lookup: HolderLookup.Provider, gen: TranslationBuilder) {
        getModHolders(BuiltInRegistries.ITEM).forEach { trySafe(it) { gen.add(it.value(), it.lang()) } }
//        getModHolders(BuiltInRegistries.BLOCK).forEach { trySafe(it) { gen.add(it.value(), it.lang()) } }
        listOf(DUSKS_AND_DUNGEONS, OVERLAY_BLOCKS).forEach { gen.add(it.key(), it.lang()) }
        getModHolders(BuiltInRegistries.MOB_EFFECT).forEach { gen.add(it.value(), it.lang()) }

        DnDItemTags.ITEM_TAGS.forEach { gen.add(it.translationKey, genLang(it.location)) }

//        blocks.forEach { gen.add(it.descriptionId, genLang(it.id)) }
//        DnDItems.ITEMS.forEach { gen.add(it.descriptionId, genLang(it.id)) }
    }


    fun <T : Any> Holder.Reference<T>.lang(): String = genLang(key().location())
    private fun genLang(id: ResourceLocation): String =
        id.path.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

    fun trySafe(reference: Holder.Reference<*>, fn: () -> Unit) {
        try {
            fn()
        } catch (e: Exception) {
            if (output.isStrictValidationEnabled) {
                LOGGER.warn("Exception found when gen lang entry for [${reference}]: ", e)
            }
        }
    }
}