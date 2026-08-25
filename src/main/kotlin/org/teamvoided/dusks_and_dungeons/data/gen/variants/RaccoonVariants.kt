package org.teamvoided.dusks_and_dungeons.data.gen.variants

import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceLocation
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.entity.RaccoonVariant
import org.teamvoided.dusks_and_dungeons.init.DnDRaccoonVariants

object RaccoonVariants {

    fun bootstrap(c: BootstrapContext<RaccoonVariant>) {
        c.register(DnDRaccoonVariants.DEFAULT, RaccoonVariant(raccoon("raccoon")))
    }

    fun raccoon(name: String): ResourceLocation = id("textures/entity/raccoon/$name.png")

}