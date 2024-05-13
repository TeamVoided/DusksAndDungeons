package org.teamvoided.dusk_autumn.block

import com.mojang.serialization.MapCodec
import net.minecraft.block.*
import net.minecraft.item.ItemConvertible
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.StateManager
import net.minecraft.state.property.IntProperty
import net.minecraft.state.property.Properties
import net.minecraft.state.property.Property
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.random.RandomGenerator
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.init.DuskItems

class MoonberryVineletBlock(settings: Settings) : CropBlock(settings) {
    override fun getCodec(): MapCodec<MoonberryVineletBlock> = CODEC

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(*arrayOf<Property<*>>(AGE))
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return SHAPE[getAge(state)]
    }

    override fun getAgeProperty(): IntProperty {
        return AGE
    }

    override fun getMaxAge(): Int {
        return MAX_AGE
    }

    override fun getSeedsItem(): ItemConvertible {
        return DuskItems.MOONBERRY_VINELET
    }

    override fun withAge(age: Int): BlockState {
        return if (age == MAX_AGE) DuskBlocks.MOONBERRY_VINE.defaultState.with(
            AbstractLichenBlock.getProperty(Direction.DOWN),
            true
        ).with(MoonberryVineBlock.BERRIES, 1)
        else super.withAge(
            age
        )
    }

    override fun randomTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: RandomGenerator) {
        if (random.nextInt(3) != 0 && world.isNight) {
            super.randomTick(state, world, pos, random)
        }
    }

    override fun getGrowthAmount(world: World): Int {
        return if (world.isNight) BONE_MEAL_AGE_INCREASE else 0
    }

    companion object {
        val CODEC: MapCodec<MoonberryVineletBlock> = createCodec(::MoonberryVineletBlock)

        const val MAX_AGE: Int = 3
        val AGE: IntProperty = Properties.AGE_2
        private val SHAPE =
            arrayOf(
                createCuboidShape(5.0, -1.0, 5.0, 11.0, 1.0, 11.0),
                createCuboidShape(3.0, -1.0, 3.0, 13.0, 1.0, 13.0),
                createCuboidShape(0.0, -1.0, 0.0, 16.0, 1.0, 16.0)
            )
        private const val BONE_MEAL_AGE_INCREASE = 1
    }
}