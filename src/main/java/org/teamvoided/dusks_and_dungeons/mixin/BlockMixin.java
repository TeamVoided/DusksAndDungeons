package org.teamvoided.dusks_and_dungeons.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.fabricmc.fabric.api.block.v1.FabricBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags;

@Mixin(Block.class)
public abstract class BlockMixin extends AbstractBlock implements ItemConvertible, FabricBlock {

    protected BlockMixin(Settings settings) {
        super(settings);
    }

    @ModifyReturnValue(at = @At("RETURN"), method = "cannotConnect")
    private static boolean cannotConnectTag(boolean original, BlockState state) {
        return state.isIn(DnDBlockTags.BLOCKS_CANNOT_CONNECT_TO) || original;
    }
}