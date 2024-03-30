package org.teamvoided.dusk_autumn.world.gen.treedcorator

import com.mojang.serialization.Codec
import net.minecraft.block.BeehiveBlock
import net.minecraft.block.Blocks
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.EntityType
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.Registries
import net.minecraft.util.math.Direction
import net.minecraft.world.gen.treedecorator.TreeDecorator
import net.minecraft.world.gen.treedecorator.TreeDecoratorType
import org.teamvoided.dusk_autumn.init.DuskWorldgen
import kotlin.math.max
import kotlin.math.min

class BeehiveBigTreeDecorator(private val probability: Float) : TreeDecorator() {
    override fun getType(): TreeDecoratorType<*> = DuskWorldgen.BEEHIVE_BIG_TREE_DECORATOR

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
            val placementPos = logPos.filter { it.y >= i - 2 }.flatMap { pos ->
                if (pos == null) return
                SPAWN_DIRECTIONS.map(pos::offset)
            }
            if (placementPos.isNotEmpty()) {
                val finalPos =
                    placementPos.shuffled()
                        .firstOrNull {
                            placer.isAir(it) && !placer.isAir(it.up()) && placer.isAir(it.offset(WORLDGEN_FACING))
                        }
                if (finalPos != null) {
                    placer.replace(
                        finalPos,
                        Blocks.BEE_NEST.defaultState.with(BeehiveBlock.FACING, WORLDGEN_FACING)
                    )
                    placer.world.getBlockEntity(finalPos, BlockEntityType.BEEHIVE)
                        .ifPresent {
                            (0 until 2 + randomGenerator.nextInt(2)).forEach { _ ->
                                val nbtCompound = NbtCompound()
                                nbtCompound.putString("id", Registries.ENTITY_TYPE.getId(EntityType.BEE).toString())
                                it.addBee(nbtCompound, randomGenerator.nextInt(599), false)
                            }
                        }
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