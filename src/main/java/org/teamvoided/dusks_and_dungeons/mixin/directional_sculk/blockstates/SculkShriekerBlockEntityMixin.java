package org.teamvoided.dusks_and_dungeons.mixin.directional_sculk.blockstates;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.SpawnUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SculkShriekerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusks_and_dungeons.util.mixin.DirectionalSculk;

import java.util.Optional;


@Mixin(SculkShriekerBlockEntity.class)
public class SculkShriekerBlockEntityMixin extends BlockEntity {

    public SculkShriekerBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @WrapOperation(
            method = "trySummonWarden",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/SpawnUtil;trySpawnMob(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/entity/MobSpawnType;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;IIILnet/minecraft/util/SpawnUtil$Strategy;)Ljava/util/Optional;")
    )
    public <T extends Entity> Optional<T> trySpawnWardenBelow(EntityType<T> type, MobSpawnType spawnType, ServerLevel level, BlockPos pos, int spawnAttempts, int spawnRangeXZ, int spawnRangeY, SpawnUtil.Strategy strategy, Operation<Optional<T>> original) {
        return original.call(type, spawnType, level, DirectionalSculk.getWardenSpawnPos(level, pos, getBlockState()), spawnAttempts, spawnRangeXZ, spawnRangeY, strategy);
    }

}