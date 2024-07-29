package org.teamvoided.dusk_autumn.data.gen.providers

import net.minecraft.entity.decoration.painting.PaintingVariant
import net.minecraft.entity.passive.WolfVariant
import net.minecraft.entity.passive.WolfVariants
import net.minecraft.registry.BootstrapContext
import net.minecraft.registry.HolderSet
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.world.biome.Biome
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.data.DuskWolfVariants
import org.teamvoided.dusk_autumn.data.gen.providers.WolfVariants.registerWolfVariant
import org.teamvoided.dusk_autumn.data.tags.DuskBiomeTags

object WolfVariants {
    fun bootstrap(c: BootstrapContext<WolfVariant>) {
        c.registerWolfVariant(DuskWolfVariants.AUTUMN, "wolf_autumn", DuskBiomeTags.SPAWNS_AUTUMN_WOLVES)
    }

    fun BootstrapContext<WolfVariant>.registerWolfVariant(
        registryKey: RegistryKey<WolfVariant>,
        name: String,
        tagKey: TagKey<Biome>
    ) {
        val default = id("entity/wolf/$name")
        val tamed = id("entity/wolf/" + name + "_tame")
        val angy = id("entity/wolf/" + name + "_angry")
        this.register(
            registryKey,
            WolfVariant(default, tamed, angy, this.getRegistryLookup(RegistryKeys.BIOME).getTagOrThrow(tagKey))
        )
    }
    fun getKey(name: String): RegistryKey<WolfVariant> {
        return RegistryKey.of(RegistryKeys.WOLF_VARIANT, Identifier.ofDefault(name))
    }
}