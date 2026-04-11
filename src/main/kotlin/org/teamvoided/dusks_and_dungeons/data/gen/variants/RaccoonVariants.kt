package org.teamvoided.dusks_and_dungeons.data.gen.variants

import net.minecraft.registry.BootstrapContext
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons
import org.teamvoided.dusks_and_dungeons.entity.RaccoonVariant
import org.teamvoided.dusks_and_dungeons.init.DnDRaccoonVariants

object RaccoonVariants {

    fun bootstrap(c: BootstrapContext<RaccoonVariant>) {
        c.register(DnDRaccoonVariants.DEFAULT, RaccoonVariant(DusksAndDungeons.id("textures/entity/raccoon.png")))
    }
}