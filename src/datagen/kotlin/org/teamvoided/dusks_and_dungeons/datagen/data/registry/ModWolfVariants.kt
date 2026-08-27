package org.teamvoided.dusks_and_dungeons.datagen.data.registry

import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.animal.WolfVariant
import net.minecraft.world.level.biome.Biome
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.datagen.data.RegistryBootstrapper
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBiomeTags
import org.teamvoided.dusks_and_dungeons.data.registry.DnDWolfVariants

object ModWolfVariants : RegistryBootstrapper<WolfVariant> {

    override fun BootstrapContext<WolfVariant>.init() {
        register(DnDWolfVariants.AUTUMN, "wolf_autumn", DnDBiomeTags.SPAWNS_AUTUMN_WOLVES)
    }

    fun BootstrapContext<WolfVariant>.register(key: ResourceKey<WolfVariant>, name: String, tag: TagKey<Biome>) {
        register(
            key, WolfVariant(
                wolf(name), wolf(name, "_tame"), wolf(name, "_angry"),
                lookup(Registries.BIOME).getOrThrow(tag)
            )
        )
    }

    fun wolf(name: String, suffix: String = "") = id("entity/wolf/$name$suffix")

}
