package org.teamvoided.dusks_and_dungeons.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {

    @Unique
    private final BlockEntityType<?> dusks_and_dungeons$me = (BlockEntityType<?>) (Object) this;

    @ModifyExpressionValue(method = "supports", at = @At(value = "INVOKE", target = "Ljava/util/Set;contains(Ljava/lang/Object;)Z"))
    private boolean supports(boolean original, BlockState state) {
        return (BlockEntityType.DECORATED_POT.equals(dusks_and_dungeons$me) && state.isOf(DnDBlocks.INSTANCE.getPOT_O_SCREAMS())) || original;
    }
}
