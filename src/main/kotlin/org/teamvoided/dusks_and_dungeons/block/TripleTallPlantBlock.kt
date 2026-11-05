package org.teamvoided.dusks_and_dungeons.block

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.Mth
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.BushBlock
import net.minecraft.world.level.block.LevelEvent
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.EnumProperty
import org.teamvoided.dusks_and_dungeons.block.extra.TripleBlockSection

open class TripleTallPlantBlock(properties: Properties) : BushBlock(properties) {

    override fun codec(): MapCodec<out TripleTallPlantBlock> = CODEC

    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(SECTION, TripleBlockSection.BOTTOM)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(SECTION)
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val pos = ctx.clickedPos
        val level = ctx.level
        return if (
            pos.y < level.maxBuildHeight - 2
            && level.getBlockState(pos.above()).canBeReplaced(ctx)
            && level.getBlockState(pos.above(2)).canBeReplaced(ctx)
        )
            super.getStateForPlacement(ctx)
        else
            null
    }

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        val section = state.getValue(SECTION)
        if (section == TripleBlockSection.BOTTOM) {
            return super.canSurvive(state, level, pos)
        } else {
            val stateBelow = level.getBlockState(pos.below())
            return stateBelow.`is`(this) && stateBelow.getValue(SECTION) == TripleBlockSection.getBelowSection(section)
        }
    }

    override fun updateShape(
        state: BlockState, dir: Direction, neighborState: BlockState,
        level: LevelAccessor, pos: BlockPos, neighborPos: BlockPos,
    ): BlockState {
        return if (!state.canSurvive(level, pos) && neighborUpdatesAboveAndBelow(state, dir, neighborState))
            Blocks.AIR.defaultBlockState()
        else
            super.updateShape(state, dir, neighborState, level, pos, neighborPos)
    }

    open fun neighborUpdatesAboveAndBelow(state: BlockState, dir: Direction, neighborState: BlockState): Boolean {
        val section = state.getValue(SECTION)
        return (dir == Direction.UP && section != TripleBlockSection.TOP && !neighborState.`is`(this))
                || (dir == Direction.DOWN && section != TripleBlockSection.BOTTOM && !neighborState.`is`(this))
    }

    override fun setPlacedBy(
        level: Level, pos: BlockPos, state: BlockState, entity: LivingEntity?, itemStack: ItemStack,
    ) {
        val middlePos = pos.above()
        val topPos = pos.above(2)
        level.setBlock(
            middlePos,
            copyWaterloggedFrom(level, middlePos, defaultBlockState().setValue(SECTION, TripleBlockSection.MIDDLE)),
            UPDATE_ALL
        )
        level.setBlock(
            topPos,
            copyWaterloggedFrom(level, topPos, defaultBlockState().setValue(SECTION, TripleBlockSection.TOP)),
            UPDATE_ALL
        )
    }

    override fun playerWillDestroy(level: Level, pos: BlockPos, state: BlockState, player: Player): BlockState {
        if (!level.isClientSide) {
            breakOthers(level, pos, state, player)
        }
        return super.playerWillDestroy(level, pos, state, player)
    }

    override fun playerDestroy(
        level: Level, player: Player, pos: BlockPos, state: BlockState, blockEntity: BlockEntity?, tool: ItemStack,
    ) {
        super.playerDestroy(level, player, pos, Blocks.AIR.defaultBlockState(), blockEntity, tool)
    }

    override fun getSeed(state: BlockState, pos: BlockPos): Long {
        return Mth.getSeed(
            pos.x,
            pos.below(if (state.getValue(SECTION) == TripleBlockSection.BOTTOM) 0 else 1).y,
            pos.z
        )
    }

    companion object {

        private val CODEC = simpleCodec(::TripleTallPlantBlock)

        val SECTION: EnumProperty<TripleBlockSection> = EnumProperty.create("section", TripleBlockSection::class.java)

        fun copyWaterloggedFrom(world: LevelReader, pos: BlockPos, state: BlockState): BlockState {
            return if (state.hasProperty(BlockStateProperties.WATERLOGGED))
                state.setValue(BlockStateProperties.WATERLOGGED, world.isWaterAt(pos))
            else
                state
        }

        fun breakOthers(level: Level, pos: BlockPos, state: BlockState, player: Player?) {
            val section = state.getValue(SECTION)
            val breakPos: BlockPos
            val breakState: BlockState
            when (section) {
                TripleBlockSection.TOP -> {
                    breakPos = pos.below(2)
                    breakState = level.getBlockState(breakPos)
                    breakOther(level, pos.below(), player)
                    breakOther(level, pos.below(2), player)
                }

                TripleBlockSection.MIDDLE -> {
                    breakPos = pos.below()
                    breakState = level.getBlockState(breakPos)
                    breakOther(level, pos.above(), player)
                    breakOther(level, pos.below(), player)
                }

                TripleBlockSection.BOTTOM -> {
                    breakPos = pos
                    breakState = level.getBlockState(breakPos)
                    breakOther(level, pos.above(2), player)
                    breakOther(level, pos.above(), player)
                }

                else -> throw MatchException(
                    "TripleTallPlantBlock cannot run breakOthers, SECTION state does not have a when: $section",
                    null
                )
            }

            if (player == null) {
                dropResources(breakState, level, breakPos)
            } else if (!player.isCreative) {
                dropResources(breakState, level, breakPos, null, player, player.mainHandItem)
            }
        }

        fun breakOther(level: Level, pos: BlockPos, player: Player?) {
            val state = level.getBlockState(pos)
            if (state.block is TripleTallPlantBlock) {
                val fluidState = state.fluidState
                val remainderState =
                    if (!fluidState.isEmpty) fluidState.type.defaultFluidState().createLegacyBlock()
                    else Blocks.AIR.defaultBlockState()
                level.setBlock(pos, remainderState, UPDATE_ALL)
                level.levelEvent(player, LevelEvent.PARTICLES_DESTROY_BLOCK , pos, getId(state))
            }
        }

    }
}