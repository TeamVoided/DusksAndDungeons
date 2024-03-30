package org.teamvoided.dusk_autumn.world.gen.treedcorator

import com.mojang.serialization.Codec
import net.minecraft.block.BeehiveBlock
import net.minecraft.block.Blocks
import net.minecraft.block.entity.BeehiveBlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.EntityType
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.Registries
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.gen.treedecorator.TreeDecorator
import net.minecraft.world.gen.treedecorator.TreeDecoratorType
import org.teamvoided.dusk_autumn.init.DuskWorldgen
import java.util.*
import kotlin.math.max
import kotlin.math.min

class BeehiveBigTreeDecorator(private val probability: Float) : TreeDecorator() {
    override fun getType(): TreeDecoratorType<*> {
        return DuskWorldgen.BEEHIVE_TREE_DECORATOR_NOT_NULL
    }

    override fun generate(placer: Placer) {
        val randomGenerator = placer.random
        if (!(randomGenerator.nextFloat() >= this.probability)) {
            val leafPos = placer.leafPositions.toList()
            val logPos = placer.logPositions.toList()
            val i = if (leafPos.isNotEmpty()) max((leafPos[0].y - 1).toDouble(), (logPos[0].y + 1).toDouble()).toInt()
            else min(
                (logPos[0].y + 1 + randomGenerator.nextInt(3)).toDouble(),
                logPos[logPos.size - 1].y.toDouble()
            ).toInt()
            val placementPos = logPos.filter { it.y == i }.flatMap { pos ->
                if (pos == null) return
                SPAWN_DIRECTIONS.map(pos::offset)
            }
            if (placementPos.isNotEmpty()) {
                val optional = placementPos.shuffled().stream().filter { pos: BlockPos ->
                    placer.isAir(pos) && placer.isAir(pos.offset(WORLDGEN_FACING))
                }.findFirst()
                if (!optional.isEmpty) {
                    placer.replace(
                        optional.get(),
                        Blocks.BEE_NEST.defaultState.with(BeehiveBlock.FACING, WORLDGEN_FACING)
                    )
                    placer.world.getBlockEntity(optional.get(), BlockEntityType.BEEHIVE)
                        .ifPresent { blockEntity: BeehiveBlockEntity ->
                            val i = 2 + randomGenerator.nextInt(2)
                            for (j in 0 until i) {
                                val nbtCompound = NbtCompound()
                                nbtCompound.putString("id", Registries.ENTITY_TYPE.getId(EntityType.BEE).toString())
                                blockEntity.addBee(nbtCompound, randomGenerator.nextInt(599), false)
                            }
                        }
                }
            }
        }
    }

    companion object {
        val CODEC: Codec<BeehiveBigTreeDecorator> = Codec.floatRange(0.0f, 1.0f)
            .fieldOf("probability").xmap(::BeehiveBigTreeDecorator) { it.probability }.codec()

        private val WORLDGEN_FACING = Direction.SOUTH
        private val SPAWN_DIRECTIONS =
            Direction.Type.HORIZONTAL.filter { it != WORLDGEN_FACING.opposite }.toTypedArray()
    }
}