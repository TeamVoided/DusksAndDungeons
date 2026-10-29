package org.teamvoided.dusks_and_dungeons.mixin.directional_sculk.blockstates;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.SculkSensorBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teamvoided.dusks_and_dungeons.util.mixin.DirectionalSculk;

import static net.minecraft.world.level.block.SculkSensorBlock.canActivate;
import static org.teamvoided.dusks_and_dungeons.util.mixin.DirectionalSculk.isCalibrated;
import static org.teamvoided.dusks_and_dungeons.util.mixin.DirectionalSculk.isUp;

@Mixin(SculkSensorBlock.class)
public class SculkSensorBlockMixin extends Block implements SimpleWaterloggedBlock {

    public SculkSensorBlockMixin(Properties settings) {
        super(settings);
    }

    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/SculkSensorBlock;registerDefaultState(Lnet/minecraft/world/level/block/state/BlockState;)V"))
    public void addDefaultState(SculkSensorBlock block, BlockState state, Operation<Void> original) {
        original.call(block, isCalibrated(asBlock()) ? state : state.setValue(DirectionalSculk.FACING, Direction.UP));
    }

    @Inject(method = "stepOn", at = @At("HEAD"), cancellable = true)
    public void onSteppedOnIfUp(Level world, BlockPos pos, BlockState state, Entity entity, CallbackInfo ci) {
        if (isCalibrated(asBlock()) || DirectionalSculk.isUp(state)) {
            return;
        }
        super.stepOn(world, pos, state, entity);
        ci.cancel();
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (isCalibrated(asBlock()) || DirectionalSculk.isUp(state)) {
            return;
        }
        if (level instanceof ServerLevel sLevel) {
            dnd$triggerSensorInside(state, sLevel, pos, entity);
        }
        super.entityInside(state, level, pos, entity);
    }

    @Unique
    private static void dnd$triggerSensorInside(BlockState state, ServerLevel level, BlockPos pos, Entity entity) {
        if (!canActivate(state) || entity.getType() == EntityType.WARDEN || DirectionalSculk.isCreativeFlying(entity)) {
            return;
        }
        if (level.getBlockEntity(pos) instanceof SculkSensorBlockEntity sensor) {
            if (sensor.getVibrationUser().canReceiveVibration(level, pos, GameEvent.STEP, GameEvent.Context.of(state))) {
                sensor.getListener().forceScheduleVibration(level, GameEvent.STEP, GameEvent.Context.of(entity), entity.position());
            }
        }
    }

    @ModifyReturnValue(method = "getDirectSignal", at = @At("RETURN"))
    public int addDirectionalRedstone(int original, BlockState state, BlockGetter level, BlockPos pos, Direction dir) {
        if (isCalibrated(asBlock()) || isUp(state)) {
            return original;
        }
        return dir == state.getValue(DirectionalSculk.FACING) ? state.getSignal(level, pos, dir) : 0;
    }

    @ModifyReturnValue(method = "getShape", at = @At("RETURN"))
    public VoxelShape getDirectionalShape(VoxelShape original, BlockState state) {
        return isCalibrated(asBlock()) ? original : DirectionalSculk.getShape(state);
    }

    @Inject(method = "animateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/Direction;getRandom(Lnet/minecraft/util/RandomSource;)Lnet/minecraft/core/Direction;", shift = At.Shift.AFTER), cancellable = true)
    public void addDirectionalRandomDisplayTick(BlockState state, Level world, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (isCalibrated(asBlock())) {
            return;
        }
        var facing = state.getValue(DirectionalSculk.FACING);
        if (facing == Direction.UP) {
            return;
        }
        var dir = Direction.getRandom(random);
        if (dir != facing && dir != facing.getOpposite()) {
            double x = 0.5 + (dir.getStepX() == 0 ? 0.5 - random.nextDouble() : dir.getStepX() * 0.6);
            double y = 0.25;
            double z = 0.5 + (dir.getStepZ() == 0 ? 0.5 - random.nextDouble() : dir.getStepZ() * 0.6);
            double yVel = random.nextFloat() * 0.04;
            var posFacing = new Vec3(pos.getX(), pos.getY(), pos.getY());
            var velFacing = switch (facing) {
                case Direction.DOWN -> {
                    posFacing = posFacing.add(x, 1 - y, z);
                    yield new Vec3(0.0, -yVel, 0.0);
                }
                case Direction.NORTH -> {
                    posFacing = posFacing.add(x, z, 1 - y);
                    yield new Vec3(0.0, 0.0, -yVel);
                }
                case Direction.SOUTH -> {
                    posFacing = posFacing.add(x, z, y);
                    yield new Vec3(0.0, 0.0, yVel);
                }
                case Direction.WEST -> {
                    posFacing = posFacing.add(1 - y, x, z);
                    yield new Vec3(-yVel, 0.0, 0.0);
                }
                case Direction.EAST -> {
                    posFacing = posFacing.add(y, x, z);
                    yield new Vec3(yVel, 0.0, 0.0);
                }
                default -> Vec3.ZERO;
            };
            world.addParticle(
                    DustColorTransitionOptions.SCULK_TO_REDSTONE,
                    posFacing.x, posFacing.y, posFacing.z,
                    velFacing.x, velFacing.y, velFacing.z
            );
        }
        ci.cancel();
    }

    @ModifyReturnValue(method = "getStateForPlacement", at = @At("RETURN"))
    public BlockState addDirectionalPlacement(BlockState original, BlockPlaceContext ctx) {
        return isCalibrated(asBlock()) ? original : DirectionalSculk.getPlacementState(original, ctx);
    }

    @Override
    protected @NotNull BlockState rotate(BlockState state, Rotation rotation) {
        return isCalibrated(asBlock()) ? super.rotate(state, rotation) : DirectionalSculk.rotate(state, rotation);
    }

    @Override
    protected @NotNull BlockState mirror(BlockState state, Mirror mirror) {
        return isCalibrated(asBlock()) ? super.mirror(state, mirror) : DirectionalSculk.mirror(state, mirror);
    }

    @Inject(method = "createBlockStateDefinition", at = @At("RETURN"))
    public void addDirectionalProperties(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci) {
        if (isCalibrated(asBlock())) {
            return;
        }
        builder.add(DirectionalSculk.FACING);
    }

}