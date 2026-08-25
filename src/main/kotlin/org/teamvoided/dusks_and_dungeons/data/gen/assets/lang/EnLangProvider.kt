package org.teamvoided.dusks_and_dungeons.data.gen.assets.lang

import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import org.teamvoided.dusks_and_dungeons.data.registry.DnDAdvancements
import org.teamvoided.dusks_and_dungeons.data.registry.DnDDamageTypes
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags
import org.teamvoided.dusks_and_dungeons.util.HEAVY_CUBE_TOOLTIP
import org.teamvoided.dusks_and_dungeons.util.TINTED_TOOLTIP
import org.teamvoided.dusks_and_dungeons.util.getModHolders
import org.teamvoided.voidlib.devin.FabricOutput
import org.teamvoided.voidlib.devin.FutureProvider
import org.teamvoided.voidlib.devin.provider.DevinLangProvider

class EnLangProvider(o: FabricOutput, p: FutureProvider) : DevinLangProvider(o, p) {

    override fun generateTranslations(provider: HolderLookup.Provider, gen: TranslationBuilder) {
        getModHolders(BuiltInRegistries.ITEM).forEach { gen.add(it.value(), it.lang()) }
        getModHolders(BuiltInRegistries.BLOCK).forEach { trySafe(it) { gen.add(it.value(), it.lang()) } }
        getModHolders(BuiltInRegistries.CREATIVE_MODE_TAB).forEach { gen.add(it.key(), it.lang()) }
        getModHolders(BuiltInRegistries.MOB_EFFECT).forEach { gen.add(it.value(), it.lang()) }
        provider.getModHolders(Registries.BIOME).forEach { gen.add(it.key().location().toLanguageKey("biome"), it.lang()) }

        DnDItemTags.ITEM_TAGS.forEach { gen.add(it.translationKey, getLang(it.location)) }
        gen.advancement(DnDAdvancements.FALL, "Fall!", "Visit the golden and autumn biomes!")
        gen.advancement(DnDAdvancements.WOOF, "Woof", "Find and tame the Autumn Wolf")

        gen.damageType(
            DnDDamageTypes.THROWN_BRICK,
            "was bricked down",
            "was brought down by" to "with the certainty of",
            "was brought down with a cast brick from"
        )

        gen.add(HEAVY_CUBE_TOOLTIP, "This block contains custom state!")
        gen.add(TINTED_TOOLTIP, "This bottle is too dark to make out its contents.")
    }

    override fun doOverrides(lang: String): String {
        if (lang.startsWith("Dnd ")) {
            return lang.replace("Dnd ", "DnD ")
        }
        return lang
    }

}