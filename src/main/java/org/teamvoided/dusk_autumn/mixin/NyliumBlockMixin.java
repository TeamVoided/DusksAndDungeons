package org.teamvoided.dusk_autumn.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.NyliumBlock;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teamvoided.dusk_autumn.data.worldgen.DnDConfiguredFeature;

@Mixin(NyliumBlock.class)
public abstract class NyliumBlockMixin implements Fertilizable {
    @Shadow
    protected abstract void generate(Registry<ConfiguredFeature<?, ?>> registry, RegistryKey<ConfiguredFeature<?, ?>> registryKey, ServerWorld world, ChunkGenerator chunkGenerator, RandomGenerator random, BlockPos pos);

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", ordinal = 0), method = "fertilize", cancellable = true)
    private void vivionBonemeal(ServerWorld world, RandomGenerator random, BlockPos pos, BlockState state, CallbackInfo ci,
                                @Local(ordinal = 1) BlockState blockState, @Local(ordinal = 1) BlockPos blockPos, @Local ChunkGenerator chunkGenerator, @Local(ordinal = 0) Registry<ConfiguredFeature<?, ?>> registry) {
        if (random.nextInt(24) == 0) {
            if (blockState.isOf(Blocks.CRIMSON_NYLIUM)) {
                this.generate(registry, DnDConfiguredFeature.FAIRY_RING_CRIMSON, world, chunkGenerator, random, blockPos);
                ci.cancel();
            } else if (blockState.isOf(Blocks.WARPED_NYLIUM)) {
                this.generate(registry, DnDConfiguredFeature.FAIRY_RING_WARPED, world, chunkGenerator, random, blockPos);
                ci.cancel();
            }
        }
    }
}