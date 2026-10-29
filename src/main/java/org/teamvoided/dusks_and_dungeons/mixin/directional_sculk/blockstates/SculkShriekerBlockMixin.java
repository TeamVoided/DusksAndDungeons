package org.teamvoided.dusks_and_dungeons.mixin.directional_sculk.blockstates;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SculkShriekerBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SculkShriekerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teamvoided.dusks_and_dungeons.util.mixin.DirectionalSculk;

@Mixin(SculkShriekerBlock.class)
public class SculkShriekerBlockMixin extends Block {

    public SculkShriekerBlockMixin(Properties settings) {
        super(settings);
    }

    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/SculkShriekerBlock;registerDefaultState(Lnet/minecraft/world/level/block/state/BlockState;)V"))
    public void addDefaultState(SculkShriekerBlock block, BlockState state, Operation<Void> original) {
        original.call(block, state.setValue(DirectionalSculk.FACING, Direction.UP));
    }

    @ModifyReturnValue(method = "getCollisionShape", at = @At("RETURN"))
    public VoxelShape directionalCollisionShape(VoxelShape ignored, BlockState state) {
        return DirectionalSculk.getShape(state);
    }

    @ModifyReturnValue(method = "getOcclusionShape", at = @At("RETURN"))
    public VoxelShape directionalOcclusionShape(VoxelShape ignored, BlockState state) {
        return DirectionalSculk.getShape(state);
    }

    @Inject(method = "stepOn", at = @At("HEAD"), cancellable = true)
    public void onSteppedOnIfUp(Level level, BlockPos pos, BlockState state, Entity entity, CallbackInfo ci) {
        if (DirectionalSculk.isUp(state)) {
            return;
        }
        super.stepOn(level, pos, state, entity);
        ci.cancel();
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!DirectionalSculk.isUp(state) && level instanceof ServerLevel sLevel) {
            var player = SculkShriekerBlockEntity.tryGetPlayer(entity);
            if (player != null && !DirectionalSculk.isCreativeFlying(entity)) {
                sLevel.getBlockEntity(pos, BlockEntityType.SCULK_SHRIEKER).ifPresent(shrieker -> shrieker.tryShriek(sLevel, player));
            }
        }
        super.entityInside(state, level, pos, entity);
    }

    @ModifyReturnValue(method = "getStateForPlacement", at = @At("RETURN"))
    public BlockState addDirectionalPlacement(@Nullable BlockState original, BlockPlaceContext ctx) {
        return original == null ? null : DirectionalSculk.getPlacementState(original, ctx);
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