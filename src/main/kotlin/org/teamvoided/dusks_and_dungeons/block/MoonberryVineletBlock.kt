package org.teamvoided.dusks_and_dungeons.block

import com.mojang.serialization.MapCodec
import net.minecraft.world.level.ItemLike
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.CropBlock
import net.minecraft.world.level.block.MultifaceBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems

class MoonberryVineletBlock(settings: Properties) : CropBlock(settings) {
    override fun codec(): MapCodec<MoonberryVineletBlock> = CODEC
    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(AGE)
    }

    override fun getShape(state: BlockState, world: BlockGetter, pos: BlockPos, context: CollisionContext)
            : VoxelShape = SHAPE[getAge(state)]

    override fun getAgeProperty(): IntegerProperty = AGE
    override fun getMaxAge(): Int = MAX_AGE
    override fun getBaseSeedId(): ItemLike = DnDItems.MOONBERRY_VINELET
    override fun getStateForAge(age: Int): BlockState {
        return if (age == MAX_AGE) DnDBlocks.MOONBERRY_VINE.defaultBlockState()
            .setValue(MultifaceBlock.getFaceProperty(Direction.DOWN), true)
            .setValue(MoonberryVineBlock.BERRIES, 1)
        else super.getStateForAge(age)
    }

    override fun randomTick(state: BlockState, world: ServerLevel, pos: BlockPos, random: RandomSource) {
        if (random.nextInt(3) != 0 && world.isNight) super.randomTick(state, world, pos, random)
    }

    override fun getBonemealAgeIncrease(world: Level): Int = if (world.isNight) BONE_MEAL_AGE_INCREASE else 0

    companion object {
        val CODEC = simpleCodec(::MoonberryVineletBlock)

        const val MAX_AGE: Int = 3
        val AGE = BlockStateProperties.AGE_2
        private val SHAPE = arrayOf(
            box(5.0, -1.0, 5.0, 11.0, 1.0, 11.0),
            box(3.0, -1.0, 3.0, 13.0, 1.0, 13.0),
            box(0.0, -1.0, 0.0, 16.0, 1.0, 16.0)
        )
        private const val BONE_MEAL_AGE_INCREASE = 1
    }
}