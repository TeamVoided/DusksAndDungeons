package org.teamvoided.dusk_autumn.block

import net.minecraft.block.Block
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.particle.ParticleTypes
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.MathHelper
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.block.entity.HauntedBlockEntity
import org.teamvoided.dusk_autumn.init.DnDBlockEntities
import org.teamvoided.dusk_autumn.mixin.BlockWithEntityAccessor

open class HauntedGravestoneBlock(settings: Settings) : GravestoneBlock(settings), BlockEntityProvider {

    init {
        defaultState = stateManager.defaultState
            .with(Properties.WATERLOGGED, false)
            .with(CENTERED, true)
            .with(FACING, Direction.NORTH)
            .with(IS_ACTIVE, false)
    }

    override fun createBlockEntity(pos: BlockPos?, state: BlockState?): BlockEntity {
        return HauntedBlockEntity(pos, state)
    }

    override fun <T : BlockEntity?> getTicker(
        world: World,
        state: BlockState?,
        type: BlockEntityType<T>?
    ): BlockEntityTicker<T>? {
        return if (world.isClient()) null else BlockWithEntityAccessor.checkType(
            type,
            DnDBlockEntities.HAUNTED_BLOCK,
            HauntedBlockEntity::serverTick
        )
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(IS_ACTIVE)
    }

    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: RandomGenerator) {
        if (state.get(IS_ACTIVE)) {
//            world.playSound(); TODO
            repeat(random.rangeInclusive(3, 5)) {
                world.addParticle(
                    ParticleTypes.SOUL,
                    pos.x + 0.5 + MathHelper.nextDouble(random, -.5, .5),
                    pos.y + 0.5 + MathHelper.nextDouble(random, -.5, .5),
                    pos.z + 0.5 + MathHelper.nextDouble(random, -.5, .5),
                    MathHelper.nextDouble(random, -0.03, 0.03),
                    MathHelper.nextDouble(random, 0.0, 0.2),
                    MathHelper.nextDouble(random, -0.03, 0.03)
                )
            }
        }
    }

    companion object {
        val IS_ACTIVE: BooleanProperty = BooleanProperty.of("is_active")
    }
}