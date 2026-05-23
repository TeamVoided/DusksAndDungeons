package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.block.BlockState
import net.minecraft.block.PinkPetalsBlock
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.BlockView
import net.minecraft.world.World
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.particle.ColorableParticleEffect
import kotlin.math.abs

class FlowerbedParticleBlock(settings: Settings) : PinkPetalsBlock(settings) {


    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: RandomGenerator) {
        super.randomDisplayTick(state, world, pos, random)

        if (random.nextInt(3) == 0) addFlowerParticle(state, world, pos, random)
        if (random.nextInt(5) == 0) {
            if (!world.isDay || world.getLightLevel(pos) <= 13) {
                val x: Double = pos.x + ((random.nextDouble() * PARTICLE_HORIZONTAL_RANGE) - PARTICLE_VERTICAL_RANGE)
                val y: Double = pos.y + (random.nextDouble() * PARTICLE_VERTICAL_RANGE)
                val z: Double = pos.z + ((random.nextDouble() * PARTICLE_HORIZONTAL_RANGE) - PARTICLE_VERTICAL_RANGE)
                //add ambient particles
            }
        }
    }

    //sweet berry bush
    override fun onEntityCollision(state: BlockState, world: World, pos: BlockPos, entity: Entity) {
        if (entity is LivingEntity) {
            if (!world.isClient && (entity.lastRenderX != entity.x || entity.lastRenderZ != entity.z)) {
                val x = abs(entity.x - entity.lastRenderX)
                val z = abs(entity.z - entity.lastRenderZ)
                if (x >= 0.003 || z >= 0.003) {
                    addFlowerParticle(state, world, pos, world.random)
                }
            }
        }
    }


    override fun canPlantOnTop(floor: BlockState, world: BlockView, pos: BlockPos): Boolean =
        floor.isIn(DnDBlockTags.VIVIONBED_PLACEABLE)

    companion object {
        const val PARTICLE_HORIZONTAL_RANGE = 5
        const val PARTICLE_VERTICAL_RANGE = 5

        fun addFlowerParticle(state: BlockState, world: World, pos: BlockPos, random: RandomGenerator) {
            val petalCount = state.get(AMOUNT)
            if (petalCount == MAX_PETAL_AMOUNT) {
                addParticle(world, pos, random)
            } else {
                val x = random.nextDouble()
                if (petalCount > 3 || x <= 0.5) {
                    val z = random.nextDouble()
                    if (petalCount > 2 || z <= 0.5) {
                        val dir = state.get(FACING)
                        addParticle(rotatePartPos(x, z, dir), rotatePartPos(x, z, dir, true), world, pos, random)
                    }
                }
            }
        }

        fun rotatePartPos(x: Double, z: Double, horDir: Direction, getZ: Boolean = false): Double {
            return if (!getZ) {
                when (horDir) {
                    Direction.NORTH -> x
                    Direction.WEST -> z
                    Direction.SOUTH -> 1 - x
                    Direction.EAST -> 1 - z
                    else -> error("flowerbed particle should only be horizontally rotated")
                }
            } else {
                when (horDir) {
                    Direction.NORTH -> z
                    Direction.WEST -> x
                    Direction.SOUTH -> 1 - z
                    Direction.EAST -> 1 - x
                    else -> error("flowerbed particle should only be horizontally rotated")
                }
            }
        }

        fun addParticle(world: World, pos: BlockPos, random: RandomGenerator) =
            addParticle(random.nextDouble(), random.nextDouble(), world, pos, random)

        fun addParticle(x: Double, z: Double, world: World, pos: BlockPos, random: RandomGenerator) =
            world.addParticle(
                ColorableParticleEffect(0xAAFFAA),
                pos.x + x,
                pos.y + (random.nextDouble() * 0.7 - 0.1),
                pos.z + z,
                (random.nextDouble() - random.nextDouble()) * 0.125,
                (random.nextDouble() * -0.1) - 0.1,
                (random.nextDouble() - random.nextDouble()) * 0.125
            )
    }
}