package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.Explosion
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.LevelEvent
import net.minecraft.world.level.block.state.BlockState
import org.teamvoided.dusks_and_dungeons.util.setBlockAndUpdateFluid
import org.teamvoided.voidlib.helpers.mc.hasEnchantment

class FilledLavaspongeBlock(
    settings: Properties,
    val resultBlock: Block,
    val contactBlock: Block,
    val brokenResultBlock: Block? = null,
) : Block(settings) {

    fun convertBlock(level: Level, pos: BlockPos) {
        level.setBlockAndUpdateFluid(pos, resultBlock.defaultBlockState())
        level.levelEvent(LevelEvent.PARTICLES_WATER_EVAPORATING, pos, 0)
        level.playSound(
            null, pos,
            SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS,
            1.0f, (0.1f + level.getRandom().nextFloat() * 0.2f) * 0.7f
        )
    }

    override fun onPlace(state: BlockState, level: Level, pos: BlockPos, state2: BlockState, bl: Boolean) {
        super.onPlace(state, level, pos, state2, bl)
        for (dir in Direction.entries) {
            if (level.getBlockState(pos.relative(dir)).`is`(contactBlock)) {
                convertBlock(level, pos)
                break
            }
        }
    }

    override fun neighborChanged(
        state: BlockState, level: Level, pos: BlockPos, block: Block, pos2: BlockPos, bl: Boolean,
    ) {
        if (level.getBlockState(pos2).`is`(contactBlock)) {
            level.setBlockAndUpdateFluid(pos, resultBlock.defaultBlockState())
        }
        super.neighborChanged(state, level, pos, block, pos2, bl)
    }

    override fun stepOn(level: Level, pos: BlockPos, state: BlockState, entity: Entity) {
        if (!entity.isSteppingCarefully && entity is LivingEntity) {
            entity.hurt(level.damageSources().hotFloor(), 1.0f)
        }

        super.stepOn(level, pos, state, entity)
    }


    override fun spawnAfterBreak(
        state: BlockState, level: ServerLevel, pos: BlockPos, stack: ItemStack, bl: Boolean,
    ) {
        super.spawnAfterBreak(state, level, pos, stack, bl)
        if (brokenResultBlock != null && !stack.hasEnchantment(Enchantments.SILK_TOUCH)) {
            level.setBlockAndUpdateFluid(pos, brokenResultBlock.defaultBlockState())
        }
    }

    override fun wasExploded(level: Level, pos: BlockPos, explosion: Explosion) {
        super.wasExploded(level, pos, explosion)
        if (brokenResultBlock != null) {
            level.setBlockAndUpdateFluid(pos, brokenResultBlock.defaultBlockState())
        }
    }

}