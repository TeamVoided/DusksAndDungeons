package org.teamvoided.dusks_and_dungeons.mixin.wii.connect;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusks_and_dungeons.block.not_blocks.BlockConnection;

@Mixin(IronBarsBlock.class)
public class IronBarsBlockMixin {

    @WrapOperation(method = "updateShape", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/IronBarsBlock;attachsTo(Lnet/minecraft/world/level/block/state/BlockState;Z)Z"))
    boolean updateShapeWithConnections(IronBarsBlock instance, BlockState state, boolean bl, Operation<Boolean> original, @Local(argsOnly = true) Direction dir) {
        return original.call(instance, state, bl) || (state.getBlock() instanceof BlockConnection connection && connection.allowBarsToConnect(state, dir.getOpposite()));
    }

    @ModifyExpressionValue(method = "getStateForPlacement", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/world/level/block/IronBarsBlock;attachsTo(Lnet/minecraft/world/level/block/state/BlockState;Z)Z"))
    boolean placementWithConnectionsN(boolean original, @Local(ordinal = 0) BlockState state) {
        return original || state.getBlock() instanceof BlockConnection connection && connection.allowBarsToConnect(state, Direction.SOUTH);
    }

    @ModifyExpressionValue(method = "getStateForPlacement", at = @At(value = "INVOKE", ordinal = 1, target = "Lnet/minecraft/world/level/block/IronBarsBlock;attachsTo(Lnet/minecraft/world/level/block/state/BlockState;Z)Z"))
    boolean placementWithConnectionsS(boolean original, @Local(ordinal = 1) BlockState state) {
        return original || state.getBlock() instanceof BlockConnection connection && connection.allowBarsToConnect(state, Direction.NORTH);
    }

    @ModifyExpressionValue(method = "getStateForPlacement", at = @At(value = "INVOKE", ordinal = 2, target = "Lnet/minecraft/world/level/block/IronBarsBlock;attachsTo(Lnet/minecraft/world/level/block/state/BlockState;Z)Z"))
    boolean placementWithConnectionsW(boolean original, @Local(ordinal = 2) BlockState state) {
        return original || state.getBlock() instanceof BlockConnection connection && connection.allowBarsToConnect(state, Direction.EAST);
    }

    @ModifyExpressionValue(method = "getStateForPlacement", at = @At(value = "INVOKE", ordinal = 3, target = "Lnet/minecraft/world/level/block/IronBarsBlock;attachsTo(Lnet/minecraft/world/level/block/state/BlockState;Z)Z"))
    boolean placementWithConnectionsE(boolean original, @Local(ordinal = 3) BlockState state) {
        return original || state.getBlock() instanceof BlockConnection connection && connection.allowBarsToConnect(state, Direction.WEST);
    }

}