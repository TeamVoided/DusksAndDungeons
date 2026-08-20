package org.teamvoided.dusks_and_dungeons.data.gen.data.litho.worldgen_modifiers

import dev.worldgen.lithostitched.api.util.InjectionType
import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.level.Level
import org.teamvoided.dusks_and_dungeons.data.litho.DnDWorldgenModifiers
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDSurfaceRules

object WorldgenModifiers {

    fun init(c: BootstrapContext<WorldgenModifier>) = c.boostrap()

    fun BootstrapContext<WorldgenModifier>.boostrap() {
        register(
            DnDWorldgenModifiers.DUSKS_AND_DUNGEONS_BIOMES_RULES, WorldgenModifier.builder()
                .addSurfaceRule(
                    Level.OVERWORLD,
                    InjectionType.PREPEND,
                    DnDSurfaceRules.overworld()
                )
        )
    }

}