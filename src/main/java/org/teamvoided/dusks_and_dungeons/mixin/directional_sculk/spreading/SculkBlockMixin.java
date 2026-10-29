package org.teamvoided.dusks_and_dungeons.mixin.directional_sculk.spreading;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.SculkBlock;
import net.minecraft.world.level.block.SculkSpreader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.dusks_and_dungeons.util.mixin.DirectionalSculk;

@Mixin(SculkBlock.class)
public class SculkBlockMixin {

    @Inject(method = "attemptUseCharge", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/SculkBlock;canPlaceGrowth(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;)Z"), cancellable = true)
    private void randomGrowth(SculkSpreader.ChargeCursor cursor, LevelAccessor level, BlockPos pos, RandomSource random, SculkSpreader spreader, boolean spread, CallbackInfoReturnable<Integer> cir) {
        int charge = cursor.getCharge();
        int cost = spreader.growthSpawnCost();
        DirectionalSculk.tryUseChargeSpreadRewrite(level, cursor.getPos(), charge, cost, random, spreader.isWorldGeneration());
        cir.setReturnValue(Math.max(0, charge - cost));
    }

}