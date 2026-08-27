package org.teamvoided.dusks_and_dungeons.mixin.accessors;

import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MultifaceBlock.class)
public interface MultifaceBlockAccessor {

    @Invoker("removeFace")
    static BlockState dnd_removeFace(BlockState blockState, BooleanProperty booleanProperty) {
        throw new UnsupportedOperationException();
    }

}