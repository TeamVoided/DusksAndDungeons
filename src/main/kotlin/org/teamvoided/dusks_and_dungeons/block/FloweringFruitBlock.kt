package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.BlockTags
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.util.block.symmetricalBox
import org.teamvoided.dusks_and_dungeons.util.isShears

class FloweringFruitBlock(properties: Properties) : Block(properties), BonemealableBlock,
    SimpleWaterloggedBlock {

    init {
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(NO_AGING, false))
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(AGE, NO_AGING)
    }

    override fun useItemOn(
        itemStack: ItemStack,
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        blockHitResult: BlockHitResult
    ): ItemInteractionResult {
        if (itemStack.isShears() && !state.getValue(NO_AGING)) {
            val state2 = state.cycle(NO_AGING)
            level.setBlock(pos, state2, 2)
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state2))
            itemStack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand))
            return ItemInteractionResult.sidedSuccess(level.isClientSide)
        } else return super.useItemOn(itemStack, state, level, pos, player, hand, blockHitResult)
    }

    override fun getShape(
        state: BlockState,
        blockGetter: BlockGetter,
        blockPos: BlockPos,
        cc: CollisionContext
    ): VoxelShape {
        val vec3 = state.getOffset(blockGetter, blockPos)
        return (SHAPE_PER_AGE[state.getValue(AGE)] ?: super.getShape(state, blockGetter, blockPos, cc))
            .move(vec3.x, vec3.y, vec3.z)
    }

    override fun canSurvive(state: BlockState, levelReader: LevelReader, blockPos: BlockPos): Boolean = levelReader.getBlockState(blockPos.above()).`is`(BlockTags.LEAVES)

    override fun updateShape(
        state: BlockState,
        direction: Direction,
        state2: BlockState,
        world: LevelAccessor,
        blockPos: BlockPos,
        blockPos2: BlockPos
    ): BlockState {
        return if (direction == Direction.UP && !state.canSurvive(world, blockPos)) Blocks.AIR.defaultBlockState()
        else super.updateShape(state, direction, state2, world, blockPos, blockPos2)
    }

    override fun isValidBonemealTarget(levelReader: LevelReader, blockPos: BlockPos, state: BlockState): Boolean =
        isRandomlyTicking(state)

    override fun isBonemealSuccess(level: Level, r: RandomSource, blockPos: BlockPos, state: BlockState): Boolean = true

    override fun performBonemeal(
        serverLevel: ServerLevel,
        r: RandomSource,
        blockPos: BlockPos,
        state: BlockState
    ) {
        advanceAge(serverLevel, blockPos, state)
    }

    override fun isRandomlyTicking(state: BlockState): Boolean = !state.getValue(NO_AGING)

    override fun randomTick(state: BlockState, serverLevel: ServerLevel, blockPos: BlockPos, r: RandomSource) {
        if (r.nextInt(5) == 0) advanceAge(serverLevel, blockPos, state)
    }

    fun advanceAge(serverLevel: ServerLevel, blockPos: BlockPos, state: BlockState) {
        if (state.getValue(AGE) != MAX_AGE) serverLevel.setBlock(blockPos, state.cycle(AGE), 2)
        else serverLevel.setBlock(blockPos, Blocks.EMERALD_BLOCK.defaultBlockState(), 2)
    }

    companion object {
        val AGE: IntegerProperty = BlockStateProperties.AGE_3
        val NO_AGING: BooleanProperty = BlockStateProperties.PERSISTENT
        const val MAX_AGE: Int = 3

        val SHAPE_1 = symmetricalBox(4.0, 8.0, 16.0)
        val SHAPE_2 = symmetricalBox(3.0, 6.0, 16.0)
        val SHAPE_3 = symmetricalBox(2.0, 4.0, 16.0)
        val SHAPE_PER_AGE = AGE.possibleValues.associateWith {
            when (it) {
                0, 1 -> SHAPE_1
                2 -> SHAPE_2
                3 -> SHAPE_3
                else -> Shapes.block()
            }
        }
    }
}
