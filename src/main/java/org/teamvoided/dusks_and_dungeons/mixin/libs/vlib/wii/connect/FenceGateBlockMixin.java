package org.teamvoided.dusks_and_dungeons.mixin.libs.vlib.wii.connect;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.voidlib.api.BlockConnection;

// TODO(lib) move to voidlib
@Mixin(FenceGateBlock.class)
public abstract class FenceGateBlockMixin {

    @ModifyExpressionValue(method = "updateShape", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/world/level/block/FenceGateBlock;isWall(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    boolean updateShapeInWallCheck(boolean original, BlockState gate, Direction dir, BlockState state) {
        return original || (state.getBlock() instanceof BlockConnection connection && connection.allowGateInWallState(state, dir.getOpposite()));
    }

    @ModifyExpressionValue(method = "updateShape", at = @At(value = "INVOKE", ordinal = 1, target = "Lnet/minecraft/world/level/block/FenceGateBlock;isWall(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    boolean updateShapeInWallCheckOpposite(boolean original, BlockState gate, Direction dir, BlockState offestState, LevelAccessor level, BlockPos pos) {
        var state = level.getBlockState(pos.relative(dir.getOpposite()));
        return original || (state.getBlock() instanceof BlockConnection connection && connection.allowGateInWallState(state, dir));
    }

    @WrapOperation(method = "getStateForPlacement", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/world/level/block/FenceGateBlock;isWall(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    boolean getStateForPlacementInWallCheckW(FenceGateBlock block, BlockState state, Operation<Boolean> call) {
        var original = call.call(block, state);
        return original || (state.getBlock() instanceof BlockConnection connection && connection.allowGateInWallState(state, Direction.EAST));
    }

    @WrapOperation(method = "getStateForPlacement", at = @At(value = "INVOKE", ordinal = 1, target = "Lnet/minecraft/world/level/block/FenceGateBlock;isWall(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    boolean getStateForPlacementInWallCheckE(FenceGateBlock block, BlockState state, Operation<Boolean> call) {
        var original = call.call(block, state);
        return original || (state.getBlock() instanceof BlockConnection connection && connection.allowGateInWallState(state, Direction.WEST));
    }

    @WrapOperation(method = "getStateForPlacement", at = @At(value = "INVOKE", ordinal = 2, target = "Lnet/minecraft/world/level/block/FenceGateBlock;isWall(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    boolean getStateForPlacementInWallCheckN(FenceGateBlock block, BlockState state, Operation<Boolean> call) {
        var original = call.call(block, state);
        return original || (state.getBlock() instanceof BlockConnection connection && connection.allowGateInWallState(state, Direction.SOUTH));
    }

    @WrapOperation(method = "getStateForPlacement", at = @At(value = "INVOKE", ordinal = 3, target = "Lnet/minecraft/world/level/block/FenceGateBlock;isWall(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    boolean getStateForPlacementInWallCheckS(FenceGateBlock block, BlockState state, Operation<Boolean> call) {
        var original = call.call(block, state);
        return original || (state.getBlock() instanceof BlockConnection connection && connection.allowGateInWallState(state, Direction.NORTH));
    }

}