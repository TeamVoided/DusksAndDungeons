package org.teamvoided.dusks_and_dungeons.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.NyliumBlock;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDConfiguredFeature;

@Mixin(NyliumBlock.class)
public abstract class NyliumBlockMixin implements BonemealableBlock {
    @Shadow
    protected abstract void place(Registry<ConfiguredFeature<?, ?>> registry, ResourceKey<ConfiguredFeature<?, ?>> registryKey, ServerLevel world, ChunkGenerator chunkGenerator, RandomSource random, BlockPos pos);

    @Inject(method = "performBonemeal", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z", ordinal = 0), cancellable = true)
    private void vivionBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state, CallbackInfo ci,
                                @Local(ordinal = 1) BlockState blockState, @Local(ordinal = 1) BlockPos blockPos, @Local ChunkGenerator chunkGenerator, @Local(ordinal = 0) Registry<ConfiguredFeature<?, ?>> registry) {
        if (random.nextInt(24) == 0) {
            if (blockState.is(Blocks.CRIMSON_NYLIUM)) {
                this.place(registry, DnDConfiguredFeature.FAIRY_RING_CRIMSON, world, chunkGenerator, random, blockPos);
                ci.cancel();
            } else if (blockState.is(Blocks.WARPED_NYLIUM)) {
                this.place(registry, DnDConfiguredFeature.FAIRY_RING_WARPED, world, chunkGenerator, random, blockPos);
                ci.cancel();
            }
        }
    }
}