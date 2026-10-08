package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.world.level.block.state.properties.IntegerProperty

object DnDBlockStateProperties {

    val CANDLES: IntegerProperty = IntegerProperty.create("candles", 1, 5)
    val COMPOSITE_SHAPE: IntegerProperty = IntegerProperty.create("shape", 0, 255)

}