package org.teamvoided.dusks_and_dungeons.mixin.datagen;

import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(OverworldBiomes.class)
public interface OverworldBiomesAccessor {

    @Invoker("globalOverworldGeneration")
    static void dnd_globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        throw new UnsupportedOperationException();
    }

}