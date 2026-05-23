package org.teamvoided.dusks_and_dungeons.data.gen.variants

import net.minecraft.registry.BootstrapContext
import net.minecraft.util.Identifier
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.entity.RaccoonVariant
import org.teamvoided.dusks_and_dungeons.init.DnDRaccoonVariants

object RaccoonVariants {

    fun bootstrap(c: BootstrapContext<RaccoonVariant>) {
        c.register(DnDRaccoonVariants.DEFAULT, RaccoonVariant(raccoon("raccoon")))
    }

    fun raccoon(name: String): Identifier = id("textures/entity/raccoon/$name.png")

}