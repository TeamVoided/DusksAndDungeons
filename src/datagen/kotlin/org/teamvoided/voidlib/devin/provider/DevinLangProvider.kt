package org.teamvoided.voidlib.devin.provider

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.core.Holder
import net.minecraft.resources.ResourceLocation
import org.teamvoided.voidlib.devin.FabricOutput
import org.teamvoided.voidlib.devin.FutureProvider

abstract class DevinLangProvider(val output: FabricOutput, p: FutureProvider, langCode: String = "en_us") :
    FabricLanguageProvider(output, langCode, p) {

    fun <T : Any> Holder.Reference<T>.lang(): String = getLang(key().location())

    fun getLang(id: ResourceLocation): String = getLang(id.path)

    open fun getLang(id: String): String {
        val lang = id.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }
        return doOverrides(lang)
    }

    open fun doOverrides(lang: String): String = lang

    open fun trySafe(reference: Holder.Reference<*>, fn: () -> Unit) {
        try {
            fn()
        } catch (e: Exception) {
            if (e.message?.startsWith("Existing translation key found") != true || output.isStrictValidationEnabled) {
                LOGGER.warn("Exception found when gen lang entry for [${reference}]: ", e)
            }
        }
    }

}