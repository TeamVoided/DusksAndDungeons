package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.PinkPetalsBlock
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.particle.ColorableParticleEffect
import kotlin.math.abs

class FlowerbedParticleBlock(settings: Properties) : PinkPetalsBlock(settings) {


    override fun animateTick(state: BlockState, world: Level, pos: BlockPos, random: RandomSource) {
        super.animateTick(state, world, pos, random)

        if (random.nextInt(3) == 0) addFlowerParticle(state, world, pos, random)
        //if (random.nextInt(1 + MAX_PETAL_AMOUNT + state.get(AMOUNT)) == 0) {
        //    if (!world.isDay || world.getLightLevel(pos) <= 13) {
        //        val x: Double = pos.x + ((random.nextDouble() * PARTICLE_HORIZONTAL_RANGE) - PARTICLE_VERTICAL_RANGE)
        //        val y: Double = pos.y + (random.nextDouble() * PARTICLE_VERTICAL_RANGE)
        //        val z: Double = pos.z + ((random.nextDouble() * PARTICLE_HORIZONTAL_RANGE) - PARTICLE_VERTICAL_RANGE)
        //        //add ambient particles
        //    }
        //}
    }

    //sweet berry bush
    override fun entityInside(state: BlockState, world: Level, pos: BlockPos, entity: Entity) {
        if (entity is LivingEntity) {
            if (!world.isClientSide && entity.y < pos.y + 0.25 && (entity.xOld != entity.x || entity.zOld != entity.z)) {
                val x = abs(entity.x - entity.xOld)
                val z = abs(entity.z - entity.zOld)
                if (x >= 0.003 || z >= 0.003) {
                    addFlowerParticle(state, world, pos, world.random)
                }
            }
        }
    }

    //override fun canPlantOnTop(floor: BlockState, world: BlockView, pos: BlockPos): Boolean =
    //    floor.isIn(DnDBlockTags.VIVIONBED_PLACEABLE)

    companion object {
        const val PARTICLE_HORIZONTAL_RANGE = 5
        const val PARTICLE_VERTICAL_RANGE = 5

        fun addFlowerParticle(state: BlockState, world: Level, pos: BlockPos, random: RandomSource) {
            val petalCount = state.getValue(AMOUNT)
            if (petalCount == MAX_FLOWERS) {
                addParticle(world, pos, random)
            } else {
                val x = random.nextDouble()
                if (petalCount > 3 || x <= 0.5) {
                    val z = random.nextDouble()
                    if (petalCount > 2 || z <= 0.5) {
                        val dir = state.getValue(FACING)
                        addParticle(rotatePartPos(x, z, dir), rotatePartPos(z, x, dir), world, pos, random)
                    }
                }
            }
        }

        fun rotatePartPos(a: Double, b: Double, horDir: Direction): Double {
            return when (horDir) {
                Direction.NORTH -> a
                Direction.WEST -> b
                Direction.SOUTH -> 1 - a
                Direction.EAST -> 1 - b
                else -> error("flowerbed particle should only be horizontally rotated")
            }
        }

        fun addParticle(world: Level, pos: BlockPos, random: RandomSource) =
            addParticle(random.nextDouble(), random.nextDouble(), world, pos, random)

        fun addParticle(x: Double, z: Double, world: Level, pos: BlockPos, random: RandomSource) =
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