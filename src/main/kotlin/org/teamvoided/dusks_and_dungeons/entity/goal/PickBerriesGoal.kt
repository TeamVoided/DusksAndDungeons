package org.teamvoided.dusks_and_dungeons.entity.goal

import net.minecraft.block.*
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal
import net.minecraft.entity.mob.PathAwareEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.world.GameRules
import net.minecraft.world.WorldView
import net.minecraft.world.event.GameEvent

open class PickBerriesGoal(mob: PathAwareEntity, speed: Double, range: Int, maxYDifference: Int) :
    MoveToTargetPosGoal(mob, speed, range, maxYDifference) {
    protected var timer: Int = 0

    override fun getDesiredSquaredDistanceToTarget(): Double = 2.0

    override fun shouldResetPath(): Boolean = tryingTime % 100 == 0

    override fun isTargetPos(world: WorldView, pos: BlockPos?): Boolean {
        val blockState = world.getBlockState(pos)
        return blockState.isOf(Blocks.SWEET_BERRY_BUSH) && blockState.get(SweetBerryBushBlock.AGE) >= 2 || CaveVines.hasBerries(blockState)
    }

    override fun tick() {
        if (hasReached()) {
            if (timer >= 40) {
                pickFromTargetPos()
            } else {
                ++timer
            }
        } else if (!hasReached() && mob.random.nextFloat() < 0.05f) {
            mob.playSound(SoundEvents.ENTITY_FOX_SNIFF, 1.0f, 1.0f)
        }

        super.tick()
    }

    protected fun pickFromTargetPos() {
        if (mob.world.gameRules.getBooleanValue(GameRules.DO_MOB_GRIEFING)) {
            val blockState: BlockState = mob.world.getBlockState(targetPos)
            if (blockState.isOf(Blocks.SWEET_BERRY_BUSH)) {
                pickSweetBerries(blockState)
            } else if (CaveVines.hasBerries(blockState)) {
                pickGlowBerries(blockState)
            }
        }
    }

    private fun pickGlowBerries(state: BlockState) = CaveVines.pickBerries(mob, state, mob.world, targetPos)

    private fun pickSweetBerries(state: BlockState) {
        val i = state.get(SweetBerryBushBlock.AGE) as Int
        state.with(SweetBerryBushBlock.AGE, 1)
        var j: Int = 1 + mob.world.random.nextInt(2) + (if (i == 3) 1 else 0)
        val itemStack: ItemStack = mob.getEquippedStack(EquipmentSlot.MAINHAND)
        if (itemStack.isEmpty) {
            mob.equipStack(EquipmentSlot.MAINHAND, ItemStack(Items.SWEET_BERRIES))
            --j
        }

        if (j > 0) {
            Block.dropStack(mob.world, targetPos, ItemStack(Items.SWEET_BERRIES, j))
        }

        mob.playSound(SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, 1.0f, 1.0f)
        mob.world.setBlockState(targetPos, state.with(SweetBerryBushBlock.AGE, 1), 2)
        mob.world.emitGameEvent(GameEvent.BLOCK_CHANGE, targetPos, GameEvent.Context.create(mob))
    }

    override fun canStart(): Boolean = !mob.isSleeping && super.canStart()

    override fun start() {
        timer = 0
//        mob.setSitting(false)
        super.start()
    }
}
