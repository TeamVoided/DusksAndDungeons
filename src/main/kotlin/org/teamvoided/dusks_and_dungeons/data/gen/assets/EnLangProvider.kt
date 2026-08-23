package org.teamvoided.dusks_and_dungeons.data.gen.assets

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.advancements.Advancement
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.damagesource.DamageType
import org.teamvoided.dusks_and_dungeons.data.gen.data.registry.DamageTypes
import org.teamvoided.dusks_and_dungeons.data.registry.DnDAdvancements
import org.teamvoided.dusks_and_dungeons.data.registry.DnDAdvancements.description
import org.teamvoided.dusks_and_dungeons.data.registry.DnDAdvancements.title
import org.teamvoided.dusks_and_dungeons.data.registry.DnDDamageTypes
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


        gen.damageTranslaion(
            DnDDamageTypes.THROWN_BRICK,
            "was bricked down",
            ("was brought down by" to "with the certainty of"),
            "was brought down with a cast brick from"
        )
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

    private fun TranslationBuilder.defaultDamageTranslaion(
        key: ResourceKey<DamageType>,
        message: String,
        transition: String = "by",
        tryingToEscape: String = "whilst trying to escape",
        using: String = "using"
    ) {
        val key0 = key.location().path
        this.add("death.attack.$key0", "%s $message")
        this.add("death.attack.$key0.item", "%s $message $transition %s $using %s")
        this.add("death.attack.$key0.player", "%s $message $tryingToEscape %s")
    }

    private fun TranslationBuilder.damageTranslaion(
        key: ResourceKey<DamageType>,
        message: String,
        messageItem: String,
        messageAttacker: String = messageItem
    ) = this.damageTranslaion(key, message, (messageItem to "using"), messageAttacker)

    private fun TranslationBuilder.damageTranslaion(
        key: ResourceKey<DamageType>,
        message: String,
        messageItem: Pair<String, String>,
        messageAttacker: String = messageItem.first
    ) {
        val key0 = key.location().path
        this.add("death.attack.$key0", "%s $message")
        this.add("death.attack.$key0.item", "%s ${messageItem.first} %s ${messageItem.second} %s")
        this.add("death.attack.$key0.player", "%s $messageAttacker %s")
    }


    private fun TranslationBuilder.damageTranslaion(
        direct: ResourceKey<DamageType>,
        indirect: ResourceKey<DamageType>,
        message: String,
        messageItem: String,
        messageAttacker: String = messageItem
    ) {
        this.directDamageTranslaion(direct, message, messageAttacker)
        this.indirectDamageTranslaion(indirect, message, messageItem)
    }


    private fun TranslationBuilder.directDamageTranslaion(
        key: ResourceKey<DamageType>,
        message: String,
        messageAttacker: String
    ) {
        val key0 = key.location().path
        this.add("death.attack.$key0", "%s $message")
        this.add("death.attack.$key0.player", "%s $messageAttacker %s")
    }

    private fun TranslationBuilder.indirectDamageTranslaion(
        key: ResourceKey<DamageType>,
        message: String,
        messageItem: String
    ) {
        val key0 = key.location().path
        this.add("death.attack.$key0", "%s $message")
        this.add("death.attack.$key0.item", "%s $messageItem %s using %s")
    }
}