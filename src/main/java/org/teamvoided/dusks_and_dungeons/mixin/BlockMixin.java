package org.teamvoided.dusks_and_dungeons.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.fabricmc.fabric.api.block.v1.FabricBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags;


// TODO move to Taglighting
@Mixin(Block.class)
public abstract class BlockMixin extends BlockBehaviour implements ItemLike, FabricBlock {

    protected BlockMixin(Properties settings) {
        super(settings);
    }

    @ModifyReturnValue(at = @At("RETURN"), method = "isExceptionForConnection")
    private static boolean cannotConnectTag(boolean original, BlockState state) {
        return state.is(DnDBlockTags.BLOCKS_CANNOT_CONNECT_TO) || original;
    }
}