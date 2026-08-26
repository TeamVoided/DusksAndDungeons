package org.teamvoided.dusks_and_dungeons.mixin.directional_sculk.blockstates;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SculkShriekerBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SculkShriekerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.dusks_and_dungeons.util.mixin.DirectionalSculk;
import org.teamvoided.dusks_and_dungeons.util.mixin.SculkDirectionalStuff;

@Mixin(SculkShriekerBlock.class)
public class SculkShriekerBlockMixin extends Block {

    public SculkShriekerBlockMixin(Properties settings) {
        super(settings);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void addDefaultState(Properties settings, CallbackInfo ci) {
        registerDefaultState(defaultBlockState().setValue(BlockStateProperties.FACING, Direction.UP));
    }

    @ModifyReturnValue(method = "getCollisionShape", at = @At("RETURN"))
    public VoxelShape directionalCollisionShape(VoxelShape original, BlockState state) {
        return DirectionalSculk.getShape(state);
    }

    @ModifyReturnValue(method = "getOcclusionShape", at = @At("RETURN"))
    public VoxelShape directionalOcclusionShape(VoxelShape original, BlockState state) {
        return DirectionalSculk.getShape(state);
    }


    @Inject(method = "stepOn", at = @At("HEAD"), cancellable = true)
    public void onSteppedOnIfUp(Level world, BlockPos pos, BlockState state, Entity entity, CallbackInfo ci) {
        if (SculkDirectionalStuff.isNotUp(state)) {
            super.stepOn(world, pos, state, entity);
            ci.cancel();
        }
    }

    @Override
    protected void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        if (SculkDirectionalStuff.isNotUp(state) && world instanceof ServerLevel serverWorld) {
            ServerPlayer serverPlayerEntity = SculkShriekerBlockEntity.tryGetPlayer(entity);
            if (serverPlayerEntity != null && SculkDirectionalStuff.noCreativeFlightAnnoyance(serverPlayerEntity)) {
                serverWorld.getBlockEntity(pos, BlockEntityType.SCULK_SHRIEKER).ifPresent((blockEntity) -> blockEntity.tryShriek(serverWorld, serverPlayerEntity));
            }
        }
        super.entityInside(state, world, pos, entity);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return SculkDirectionalStuff.getPlacementState(super.getStateForPlacement(ctx), ctx);
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return SculkDirectionalStuff.spin(state, rotation);
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return SculkDirectionalStuff.spin(state, mirror);
    }

    @Inject(method = "createBlockStateDefinition", at = @At("TAIL"))
    public void addDirectionalProperties(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(DirectionalSculk.FACING);
    }
}