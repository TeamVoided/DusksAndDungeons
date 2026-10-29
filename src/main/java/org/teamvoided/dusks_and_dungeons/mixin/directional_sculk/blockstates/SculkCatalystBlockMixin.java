package org.teamvoided.dusks_and_dungeons.mixin.directional_sculk.blockstates;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SculkCatalystBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teamvoided.dusks_and_dungeons.util.mixin.DirectionalSculk;

@Mixin(SculkCatalystBlock.class)
public class SculkCatalystBlockMixin extends Block {

    public SculkCatalystBlockMixin(Properties settings) {
        super(settings);
    }

    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/SculkCatalystBlock;registerDefaultState(Lnet/minecraft/world/level/block/state/BlockState;)V"))
    public void addDefaultState(SculkCatalystBlock block, BlockState state, Operation<Void> original) {
        original.call(block, state.setValue(DirectionalSculk.FACING, Direction.UP));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockState state = super.getStateForPlacement(ctx);
        return state == null ? null : DirectionalSculk.getPlacementState(state, ctx);
    }

    @Override
    protected @NotNull BlockState rotate(BlockState state, Rotation rotation) {
        return DirectionalSculk.rotate(state, rotation);
    }

    @Override
    protected @NotNull BlockState mirror(BlockState state, Mirror mirror) {
        return DirectionalSculk.mirror(state, mirror);
    }

    @Inject(method = "createBlockStateDefinition", at = @At("RETURN"))
    public void addDirectionalProperties(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(DirectionalSculk.FACING);
    }

}