package org.teamvoided.dusks_and_dungeons.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags;

@Mixin(WallBlock.class)
public abstract class WallBlockMixin extends Block implements SimpleWaterloggedBlock {

    protected WallBlockMixin(BlockBehaviour.Properties settings) {
        super(settings);
    }

    @ModifyReturnValue(method = "connectsTo", at = @At("RETURN"))
    private boolean connectToWoodenOrRegular(boolean original, BlockState state) {
        if (original && state.is(BlockTags.WALLS)) {
            return state.is(DnDBlockTags.WOODEN_WALLS) == defaultBlockState().is(DnDBlockTags.WOODEN_WALLS);
        }
        return original;
    }
}