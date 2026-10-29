package org.teamvoided.dusks_and_dungeons.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SnowyDirtBlock.class)
public class SnowyDirtBlockMixin {

    @ModifyExpressionValue(method = "updateShape", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/SnowyDirtBlock;isSnowySetting(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private static boolean fullFaceUpdateShape(boolean original, BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor level, BlockPos blockPos, BlockPos pos2) {
        return original && blockState2.isFaceSturdy(level, pos2, Direction.DOWN);
    }

    @ModifyExpressionValue(method = "getStateForPlacement", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/SnowyDirtBlock;isSnowySetting(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private static boolean fullFaceGetStateForPlacement(boolean original, BlockPlaceContext ctx, @Local BlockState state) {
        return original && state.isFaceSturdy(ctx.getLevel(), ctx.getClickedPos().above(), Direction.DOWN);
    }

}