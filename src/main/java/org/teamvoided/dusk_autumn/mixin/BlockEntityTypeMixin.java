package org.teamvoided.dusk_autumn.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusk_autumn.init.DnDBlocks;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {

    @Unique
    private final BlockEntityType<?> dusk_autumn$me = (BlockEntityType<?>) (Object) this;

    @ModifyExpressionValue(method = "supports", at = @At(value = "INVOKE", target = "Ljava/util/Set;contains(Ljava/lang/Object;)Z"))
    private boolean supports(boolean original, BlockState state) {
        return (BlockEntityType.DECORATED_POT.equals(dusk_autumn$me) && state.isOf(DnDBlocks.INSTANCE.getPOT_O_SCREAMS())) || original;
    }
}
