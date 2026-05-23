package org.teamvoided.dusks_and_dungeons.block

import com.mojang.serialization.MapCodec
import net.minecraft.world.level.block.BushBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.Mth
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import org.teamvoided.dusks_and_dungeons.block.not_blocks.TripleBlockSection

open class TripleTallPlantBlock(settings: Properties) : BushBlock(settings) {

    init {
        this.registerDefaultState(stateDefinition.any().setValue(SECTION, TripleBlockSection.BOTTOM))
    }

    override fun codec(): MapCodec<out BushBlock> = CODEC

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(SECTION)
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val blockPos = ctx.clickedPos
        val world = ctx.level
        return if (
            blockPos.y < world.maxBuildHeight - 2 &&
            world.getBlockState(blockPos.above()).canBeReplaced(ctx) &&
            world.getBlockState(blockPos.above(2)).canBeReplaced(ctx)
        ) super.getStateForPlacement(ctx)
        else null
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        val blockstate = state.getValue(SECTION)
        if (blockstate == TripleBlockSection.BOTTOM) return super.canSurvive(state, world, pos)
        else {
            val blockDown = world.getBlockState(pos.below())
            return blockDown.`is`(this) && blockDown.getValue(SECTION) == TripleBlockSection.getBelowSection(blockstate)
        }
    }

    override fun updateShape(
        state: BlockState, direction: Direction, neighborState: BlockState, world: LevelAccessor,
        pos: BlockPos, neighborPos: BlockPos,
    ): BlockState {
        return if (
            !state.canSurvive(world, pos) &&
            neighborUpdatesAboveAndBelow(state, direction, neighborState)
        ) {
            Blocks.AIR.defaultBlockState()
        } else super.updateShape(state, direction, neighborState, world, pos, neighborPos)
    }

    open fun neighborUpdatesAboveAndBelow(state: BlockState, direction: Direction, neighborState: BlockState): Boolean {
        val section = state.getValue(SECTION)
        return ((direction == Direction.UP && section != TripleBlockSection.TOP && !neighborState.`is`(this)) ||
                (direction == Direction.DOWN && section != TripleBlockSection.BOTTOM && !neighborState.`is`(this)))
    }

    override fun setPlacedBy(world: Level, pos: BlockPos, state: BlockState, placer: LivingEntity?, itemStack: ItemStack) {
        val blockPosMiddle = pos.above()
        val blockPosTop = pos.above(2)
        world.setBlock(
            blockPosMiddle,
            withWaterloggedState(world, blockPosMiddle, defaultBlockState().setValue(SECTION, TripleBlockSection.MIDDLE)),
            3
        )
        world.setBlock(
            blockPosTop,
            withWaterloggedState(world, blockPosTop, defaultBlockState().setValue(SECTION, TripleBlockSection.TOP)),
            3
        )
    }

    override fun playerWillDestroy(world: Level, pos: BlockPos, state: BlockState, player: Player): BlockState {
        if (!world.isClientSide) {
            breakOthers(world, pos, state, player)
        }
        return super.playerWillDestroy(world, pos, state, player)
    }

    override fun playerDestroy(
        world: Level, player: Player, pos: BlockPos,
        state: BlockState, blockEntity: BlockEntity?, stack: ItemStack,
    ) = super.playerDestroy(world, player, pos, Blocks.AIR.defaultBlockState(), blockEntity, stack)

    override fun getSeed(state: BlockState, pos: BlockPos): Long = Mth
        .getSeed(pos.x, pos.below(if (state.getValue(SECTION) == TripleBlockSection.BOTTOM) 0 else 1).y, pos.z)

    companion object {
        private val CODEC = simpleCodec(::TripleTallPlantBlock)
        val SECTION: EnumProperty<TripleBlockSection> = EnumProperty.create("section", TripleBlockSection::class.java)

        fun withWaterloggedState(world: LevelReader, pos: BlockPos, state: BlockState): BlockState =
            if (state.hasProperty(BlockStateProperties.WATERLOGGED)) state.setValue(BlockStateProperties.WATERLOGGED, world.isWaterAt(pos))
            else state

        fun breakOthers(world: Level, pos: BlockPos, state: BlockState, player: Player?) {
            val blockSection = state.getValue(SECTION)
            val breakPos: BlockPos
            val breakState: BlockState
            when (blockSection) {
                TripleBlockSection.TOP -> {
                    breakPos = pos.below(2)
                    breakState = world.getBlockState(breakPos)
                    breakOther(world, pos.below(), player)
                    breakOther(world, pos.below(2), player)
                }

                TripleBlockSection.MIDDLE -> {
                    breakPos = pos.below()
                    breakState = world.getBlockState(breakPos)
                    breakOther(world, pos.above(), player)
                    breakOther(world, pos.below(), player)
                }

                TripleBlockSection.BOTTOM -> {
                    breakPos = pos
                    breakState = world.getBlockState(breakPos)
                    breakOther(world, pos.above(2), player)
                    breakOther(world, pos.above(), player)
                }

                else -> throw MatchException(
                    "TripleTallPlantBlock cannot run breakOthers, SECTION state does not have a when: $blockSection",
                    null
                )
            }

            if (player == null) dropResources(breakState, world, breakPos)
            else if (!player.isCreative) {
                dropResources(breakState, world, breakPos, null, player, player.mainHandItem)
            }
        }

        private fun breakOther(world: Level, pos: BlockPos, player: Player?) {
            val blockState = world.getBlockState(pos)
            if (blockState.block is TripleTallPlantBlock) {
                val fluidState = blockState.fluidState
                val afterState =
                    if (!fluidState.isEmpty) fluidState.type.defaultFluidState().createLegacyBlock()
                    else Blocks.AIR.defaultBlockState()
                world.setBlock(pos, afterState, 3)
                world.levelEvent(player, 2001, pos, getId(blockState))
            }
        }
    }
}