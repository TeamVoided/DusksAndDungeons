package org.teamvoided.dusks_and_dungeons.mixin.taglighting;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusks_and_dungeons.data.tags.c.CBlockTags;

// TODO move to taglighting
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @WrapOperation(
            method = "handleOnClimbable",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z")
    )
    boolean swapForTag(BlockState state, Block block, Operation<Boolean> original) {
        return state.is(CBlockTags.SCAFFOLDING) || original.call(state, block);
    }
}
