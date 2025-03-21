package org.teamvoided.voidlib.devin.extensions.model

import net.minecraft.data.client.model.BlockStateModelGenerator
import org.teamvoided.dusks_and_dungeons.util.datagen.slab
import org.teamvoided.dusks_and_dungeons.util.datagen.stairs
import org.teamvoided.dusks_and_dungeons.util.datagen.wall
import org.teamvoided.voidlib.consortium.block.set.AbstractBlockSet
import org.teamvoided.voidlib.consortium.block.set.BlockSet

fun BlockStateModelGenerator.createBlockSet(set: AbstractBlockSet) {
    if (set is BlockSet) this.registerSimpleCubeAll(set.parent)
    this.stairs(set.stairs, set.parent)
    this.slab(set.slab, set.parent)
    this.wall(set.wall, set.parent)
}
