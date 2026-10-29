package org.teamvoided.dusks_and_dungeons.mixin.directional_sculk.blockstates;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.SculkCatalystBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusks_and_dungeons.util.mixin.DirectionalSculk;

@Mixin(SculkCatalystBlockEntity.CatalystListener.class)
public class SculkCatalystBlockEntityMixin {

    @WrapOperation(method = "bloom", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;sendParticles(Lnet/minecraft/core/particles/ParticleOptions;DDDIDDDD)I"))
    private <T extends ParticleOptions> int bloomParticles(
            ServerLevel level,
            T particle, double x, double y, double z, int count, double deltaX, double deltaY, double deltaZ, double speed, Operation<Integer> original,
            ServerLevel ignored, BlockPos pos, BlockState state
    ) {
        var facing = state.getValue(DirectionalSculk.FACING);
        if (facing == Direction.UP) {
            return original.call(level, particle, x, y, z, count, deltaX, deltaY, deltaZ, speed);
        }
        var spawnPos = pos.getCenter();
        var delta = switch (facing) {
            case Direction.DOWN -> {
                spawnPos = spawnPos.add(0.0, -0.65, 0.0);
                yield new Vec3(deltaX, -deltaY, deltaZ);
            }
            case Direction.NORTH -> {
                spawnPos = spawnPos.add(0.0, 0.0, -0.65);
                yield new Vec3(deltaX, deltaZ, -deltaY);
            }
            case Direction.SOUTH -> {
                spawnPos = spawnPos.add(0.0, 0.0, 0.65);
                yield new Vec3(deltaX, deltaZ, deltaY);
            }
            case Direction.WEST -> {
                spawnPos = spawnPos.add(-0.65, 0.0, 0.0);
                yield new Vec3(-deltaY, deltaX, deltaZ);
            }
            case Direction.EAST -> {
                spawnPos = spawnPos.add(0.65, 0.0, 0.0);
                yield new Vec3(deltaY, deltaX, deltaZ);
            }
            default -> Vec3.ZERO;
        };
        return original.call(
                level, particle, spawnPos.x, spawnPos.y, spawnPos.z, count, delta.x, delta.y, delta.z, speed
        );
    }

}