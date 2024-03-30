package org.teamvoided.dusk_autumn.world.gen.treedcorator

import com.mojang.serialization.Codec
import net.minecraft.block.BeehiveBlock
import net.minecraft.block.BlockState
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
import java.util.stream.Collectors
import java.util.stream.Stream
import kotlin.math.max
import kotlin.math.min

class BeehiveTreeDecoratorNotNull(private val probability: Float) : TreeDecorator() {
    override fun getType(): TreeDecoratorType<*> {
        return DuskWorldgen.BEEHIVE_TREE_DECORATOR_NOT_NULL
    }

    override fun generate(placer: Placer) {
        val randomGenerator = placer.random
        if (!(randomGenerator.nextFloat() >= this.probability)) {
            val list: List<BlockPos> = placer.leafPositions
            val list2: List<BlockPos> = placer.logPositions
            val i = if (list.isNotEmpty()) max((list[0].y - 1).toDouble(), (list2[0].y + 1).toDouble())
                .toInt() else min(
                (list2[0].y + 1 + randomGenerator.nextInt(3)).toDouble(),
                list2[list2.size - 1].y.toDouble()
            )
                .toInt()
            val list3: List<BlockPos> =
                list2.stream().filter { pos: BlockPos -> pos.y == i }.flatMap<BlockPos> { pos: BlockPos ->
                    val var10000 = Stream.of(*SPAWN_DIRECTIONS)
                    Objects.requireNonNull(pos)
                    var10000.map(pos::offset)
                }.collect(Collectors.toList())
            if (list3.isNotEmpty()) {
                Collections.shuffle(list3)
                val optional = list3.stream().filter { pos: BlockPos ->
                    placer.isAir(pos) && placer.isAir(
                        pos!!.offset(WORLDGEN_FACING)
                    )
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
        val CODEC: Codec<BeehiveTreeDecoratorNotNull> = Codec.floatRange(0.0f, 1.0f).fieldOf("probability").xmap(
            { probability: Float ->
                BeehiveTreeDecoratorNotNull(
                    probability
                )
            },
            { decorator: BeehiveTreeDecoratorNotNull -> decorator.probability }).codec()
        private val WORLDGEN_FACING = Direction.SOUTH
        private val SPAWN_DIRECTIONS =
            Direction.Type.HORIZONTAL.stream().filter { direction: Direction -> direction != WORLDGEN_FACING.opposite }
                .toArray { i: Int ->
                    arrayOfNulls<Direction>(
                        i
                    )
                }
    }
}