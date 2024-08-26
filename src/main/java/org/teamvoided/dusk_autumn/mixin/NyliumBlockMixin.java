package org.teamvoided.dusk_autumn.mixin;

import net.minecraft.block.*;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.NetherConfiguredFeatures;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.dusk_autumn.data.tags.DnDBlockTags;
import org.teamvoided.dusk_autumn.data.worldgen.DnDConfiguredFeature;

@Debug(export = true)
@Mixin(NyliumBlock.class)
public abstract class NyliumBlockMixin extends Block implements Fertilizable {

    protected NyliumBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(at = @At("HEAD"), method = "fertilize", cancellable = true)
    private void vivionBonemeal(ServerWorld world, RandomGenerator random, BlockPos pos, BlockState state, CallbackInfo ci) {
        if (random.nextInt(24) == 0) {
            BlockState blockState = world.getBlockState(pos);
            BlockPos blockPos = pos.up();
            ChunkGenerator chunkGenerator = world.getChunkManager().getChunkGenerator();
            Registry<ConfiguredFeature<?, ?>> registry = world.getRegistryManager().get(RegistryKeys.CONFIGURED_FEATURE);
            if (blockState.isOf(Blocks.CRIMSON_NYLIUM)) {
                this.generate(registry, DnDConfiguredFeature.FAIRY_RING_CRIMSON, world, chunkGenerator, random, blockPos);
                ci.cancel();
            } else if (blockState.isOf(Blocks.WARPED_NYLIUM)) {
                this.generate(registry, DnDConfiguredFeature.FAIRY_RING_WARPED, world, chunkGenerator, random, blockPos);
                ci.cancel();
            }
        }
    }

    @Unique
    private void generate(Registry<ConfiguredFeature<?, ?>> registry, RegistryKey<ConfiguredFeature<?, ?>> registryKey, ServerWorld world, ChunkGenerator chunkGenerator, RandomGenerator random, BlockPos pos) {
        registry.getHolder(registryKey).ifPresent((registryKeyHolder) -> {
            (registryKeyHolder.value()).generate(world, chunkGenerator, random, pos);
        });
    }
}