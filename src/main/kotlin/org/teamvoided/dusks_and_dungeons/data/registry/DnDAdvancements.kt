package org.teamvoided.dusks_and_dungeons.data.registry

import net.minecraft.advancements.Advancement
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component.translatable
import net.minecraft.network.chat.MutableComponent
import net.minecraft.resources.ResourceKey
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.key
import org.teamvoided.dusks_and_dungeons.util.toLangKey

object DnDAdvancements {

    val FALL = key("adventure/fall")
    val WOOF = key("husbandry/woof")

    fun key(id: String) = Registries.ADVANCEMENT.key(id(id))


    fun title(key: ResourceKey<Advancement>): MutableComponent {
        return translatable("advancements.${key.toLangKey()}.title")
    }

    fun description(key: ResourceKey<Advancement>): MutableComponent {
        return translatable("advancements.${key.toLangKey()}.description")
    }

}

