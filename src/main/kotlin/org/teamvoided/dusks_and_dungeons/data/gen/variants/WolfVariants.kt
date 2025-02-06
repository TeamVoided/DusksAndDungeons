package org.teamvoided.dusks_and_dungeons.data.gen.variants

import net.minecraft.entity.passive.WolfVariant
import net.minecraft.registry.BootstrapContext
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.world.biome.Biome
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.data.variants.DnDWolfVariants
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBiomeTags

object WolfVariants {
    fun bootstrap(c: BootstrapContext<WolfVariant>) {
        c.register(DnDWolfVariants.AUTUMN, "wolf_autumn", DnDBiomeTags.SPAWNS_AUTUMN_WOLVES)
    }

    fun BootstrapContext<WolfVariant>.register(key: RegistryKey<WolfVariant>, name: String, tag: TagKey<Biome>) =
        this.register(
            key, WolfVariant(
                wolf(name), wolf(name, "_tame"), wolf(name, "_angry"),
                this.getRegistryLookup(RegistryKeys.BIOME).getTagOrThrow(tag)
            )
        )

    fun wolf(name: String, suffix: String = "") = id("entity/wolf/$name$suffix")
}
