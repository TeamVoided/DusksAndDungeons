package org.teamvoided.voidlib.devin.extensions.model

import net.minecraft.data.models.BlockModelGenerators
import org.teamvoided.dusks_and_dungeons.datagen.old.util.slab
import org.teamvoided.dusks_and_dungeons.datagen.old.util.stairs
import org.teamvoided.dusks_and_dungeons.datagen.old.util.wall
import org.teamvoided.voidlib.consortium.block.set.AbstractBlockSet
import org.teamvoided.voidlib.consortium.block.set.BlockSet

fun BlockModelGenerators.createBlockSet(set: AbstractBlockSet) {
    if (set is BlockSet) this.createTrivialCube(set.parent)
    this.stairs(set.stairs, set.parent)
    this.slab(set.slab, set.parent)
    this.wall(set.wall, set.parent)
}

fun BlockModelGenerators.createHeadlessBlockSet(set: AbstractBlockSet) {
    this.stairs(set.stairs, set.parent)
    this.slab(set.slab, set.parent)
    this.wall(set.wall, set.parent)
}
