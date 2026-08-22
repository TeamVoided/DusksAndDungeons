package org.teamvoided.dusks_and_dungeons.data.gen.assets

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.advancements.Advancement
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import org.teamvoided.dusks_and_dungeons.data.registry.DnDAdvancements
import org.teamvoided.dusks_and_dungeons.data.registry.DnDAdvancements.description
import org.teamvoided.dusks_and_dungeons.data.registry.DnDAdvancements.title
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags
import org.teamvoided.dusks_and_dungeons.util.getModHolders
import org.teamvoided.voidlib.devin.FDOutput
import org.teamvoided.voidlib.devin.FutureLookup

class EnLangProvider(val output: FDOutput, r: FutureLookup) : FabricLanguageProvider(output, r) {

    override fun generateTranslations(lookup: HolderLookup.Provider, gen: TranslationBuilder) {
        getModHolders(BuiltInRegistries.ITEM).forEach { gen.add(it.value(), it.lang()) }
        getModHolders(BuiltInRegistries.BLOCK).forEach { trySafe(it) { gen.add(it.value(), it.lang()) } }
        getModHolders(BuiltInRegistries.CREATIVE_MODE_TAB).forEach { gen.add(it.key(), it.lang()) }
        getModHolders(BuiltInRegistries.MOB_EFFECT).forEach { gen.add(it.value(), it.lang()) }

        DnDItemTags.ITEM_TAGS.forEach { gen.add(it.translationKey, genLang(it.location)) }
        gen.advancement(DnDAdvancements.FALL, "Fall!", "Visit the golden and autumn biomes!")
        gen.advancement(DnDAdvancements.WOOF, "Woof", "Find and tame the Autumn Wolf")
    }


    fun <T : Any> Holder.Reference<T>.lang(): String = genLang(key().location())
    fun genLang(id: ResourceLocation): String = genLang(id.path)
    fun genLang(id: String): String {
        val lang = id.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }
        return doOverrides(lang)
    }

    fun doOverrides(lang: String): String {
        if (lang.startsWith("Dnd ")) {
            return lang.replace("Dnd ", "DnD ")
        }
        return lang
    }

    fun TranslationBuilder.advancement(key: ResourceKey<Advancement>, title: String, description: String) {
        add(title(key).string, title)
        add(description(key).string, description)
    }

    fun trySafe(reference: Holder.Reference<*>, fn: () -> Unit) {
        try {
            fn()
        } catch (e: Exception) {
            if (e.message?.startsWith("Existing translation key found") != true || output.isStrictValidationEnabled) {
                LOGGER.warn("Exception found when gen lang entry for [${reference}]: ", e)
            }
        }
    }

}