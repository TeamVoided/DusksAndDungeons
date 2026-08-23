package org.teamvoided.dusks_and_dungeons.data.gen.assets.lang

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder
import net.minecraft.advancements.Advancement
import net.minecraft.resources.ResourceKey
import net.minecraft.world.damagesource.DamageType
import org.teamvoided.dusks_and_dungeons.data.registry.DnDAdvancements
import org.teamvoided.dusks_and_dungeons.util.toLangKey

fun TranslationBuilder.advancement(key: ResourceKey<Advancement>, title: String, description: String) {
    add(DnDAdvancements.title(key).string, title)
    add(DnDAdvancements.description(key).string, description)
}

fun TranslationBuilder.defaultDamageType(key: ResourceKey<DamageType>, message: String) {
    val lang = key.toLangKey()
    add("death.attack.$lang", "%s $message")
    add("death.attack.$lang.item", "%s $message by %s using %s")
    add("death.attack.$lang.player", "%s $message whilst trying to escape %s")
}

fun TranslationBuilder.damageType(
    key: ResourceKey<DamageType>,
    message: String,
    messageItem: String,
    messageAttacker: String,
) {
    damageType(key, message, (messageItem to "using"), messageAttacker)
}

fun TranslationBuilder.damageType(
    key: ResourceKey<DamageType>, message: String, messageItem: Pair<String, String>, messageAttacker: String,
) {
    val lang = key.toLangKey()
    add("death.attack.$lang", "%s $message")
    add("death.attack.$lang.item", "%s ${messageItem.first} %s ${messageItem.second} %s")
    add("death.attack.$lang.player", "%s $messageAttacker %s")
}

