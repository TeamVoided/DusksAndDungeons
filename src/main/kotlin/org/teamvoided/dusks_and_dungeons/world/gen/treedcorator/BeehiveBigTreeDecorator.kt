package org.teamvoided.dusks_and_dungeons.world.gen.treedcorator

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import net.minecraft.world.level.block.BeehiveBlock
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.entity.BeehiveBlockEntity.Occupant
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.core.Direction
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.log
import org.teamvoided.dusks_and_dungeons.init.DnDWorldgen
import kotlin.math.max
import kotlin.math.min

class BeehiveBigTreeDecorator(private val probability: Float) : TreeDecorator() {
    override fun type(): TreeDecoratorType<*> = DnDWorldgen.BEEHIVE_BIG_TREE_DECORATOR

    override fun place(placer: Context) {
        val randomGenerator = placer.random()
        if (!(randomGenerator.nextFloat() >= this.probability)) {
            val leafPos = placer.leaves().toList()
            val logPos = placer.logs().toList()
            val i = if (leafPos.isNotEmpty()) max((leafPos[0].y - 1).toDouble(), (logPos[0].y + 1).toDouble()).toInt()
            else min(
                (logPos[0].y + 1 + randomGenerator.nextInt(3)).toDouble(),
                logPos[logPos.size - 1].y.toDouble()
            ).toInt()
            val placementPos = logPos.filter { it.y >= i - 2 }.flatMap { pos ->
                if (pos == null) return
                SPAWN_DIRECTIONS.map(pos::relative)
            }
            if (placementPos.isNotEmpty()) {
                val finalPos = placementPos.shuffled()
                    .firstOrNull {
                        placer.isAir(it) && !placer.isAir(it.above()) && placer.isAir(it.relative(WORLDGEN_FACING))
                    }
                if (finalPos != null) {
                    placer.setBlock(
                        finalPos,
                        Blocks.BEE_NEST.defaultBlockState().setValue(BeehiveBlock.FACING, WORLDGEN_FACING)
                    )
                    placer.level().getBlockEntity(finalPos, BlockEntityType.BEEHIVE)
                        .ifPresent {
                            for (ignored in 0 until 2 + randomGenerator.nextInt(2)) {
                                it.storeBee(Occupant.create(randomGenerator.nextInt(599)))
                            }
                        }
                    return
                    /* Debugging of hive spawns
                    placementPos.filter {
                        placer.isAir(it) && !placer.isAir(it.up()) && !placer.isAir(it.offset(WORLDGEN_FACING))
                    }.forEach { placer.replace(it, Blocks.YELLOW_GLAZED_TERRACOTTA.defaultState) }
                    placementPos.filter {
                        placer.isAir(it) && !placer.isAir(it.up())
                                && placer.world.testBlockState(it.up()) { b -> b.block != Blocks.YELLOW_GLAZED_TERRACOTTA || b.block != Blocks.BEEHIVE }
                    }
                        .forEach {
                            placer.replace(it, Blocks.GREEN_STAINED_GLASS.defaultState)
                        }
                     */
                }
            }
            log.info("No beehive was placed by BeehiveBigTreeDecorator!")
        }
    }

    companion object {
        val CODEC: MapCodec<BeehiveBigTreeDecorator> = Codec.floatRange(0.0f, 1.0f)
            .fieldOf("probability")
            .xmap(::BeehiveBigTreeDecorator) { it.probability }

        private val WORLDGEN_FACING = Direction.SOUTH
        private val SPAWN_DIRECTIONS =
            Direction.Plane.HORIZONTAL.filter { it != WORLDGEN_FACING.opposite }.toTypedArray()
    }
}
