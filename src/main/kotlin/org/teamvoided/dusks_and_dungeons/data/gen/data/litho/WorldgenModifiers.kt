package org.teamvoided.dusks_and_dungeons.data.gen.data.litho

import dev.worldgen.lithostitched.api.util.InjectionType
import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.level.Level
import org.teamvoided.dusks_and_dungeons.data.gen.data.RegistryBootstrapper
import org.teamvoided.dusks_and_dungeons.data.gen.data.litho.modifiers.DnDSurfaceRules
import org.teamvoided.dusks_and_dungeons.data.litho.DnDWorldgenModifiers

object WorldgenModifiers : RegistryBootstrapper<WorldgenModifier> {

    override fun BootstrapContext<WorldgenModifier>.init() {
        register(
            DnDWorldgenModifiers.DUSKS_AND_DUNGEONS_BIOMES_RULES,
            WorldgenModifier.builder()
                .addSurfaceRule(Level.OVERWORLD, InjectionType.PREPEND, DnDSurfaceRules.overworld())
        )
    }

}