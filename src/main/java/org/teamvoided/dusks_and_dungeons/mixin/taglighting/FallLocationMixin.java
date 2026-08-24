package org.teamvoided.dusks_and_dungeons.mixin.taglighting;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.damagesource.FallLocation;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusks_and_dungeons.data.tags.c.CBlockTags;

// TODO(lib) move to taglighting
@Mixin(FallLocation.class)
public abstract class FallLocationMixin {

    @ModifyExpressionValue(
            method = "blockToFallLocation",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z", ordinal = 6)
    )
    private static boolean swapForTag(boolean original, BlockState state) {
        return state.is(CBlockTags.SCAFFOLDING);
    }
}
