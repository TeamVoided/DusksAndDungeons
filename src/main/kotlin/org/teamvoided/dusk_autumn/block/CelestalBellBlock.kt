package org.teamvoided.dusk_autumn.block

import net.minecraft.block.BellBlock
import net.minecraft.block.entity.BellBlockEntity
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.sound.SoundCategory
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World
import net.minecraft.world.event.GameEvent
import org.teamvoided.dusk_autumn.init.DnDSoundEvents

class CelestalBellBlock(settings: Settings) : BellBlock(settings) {
    override fun ring(entity: Entity?, world: World, pos: BlockPos, direction: Direction?): Boolean {
        var direction2 = direction
        val blockEntity = world.getBlockEntity(pos)
        if (!world.isClient && blockEntity is BellBlockEntity) {
            if (direction == null) {
                direction2 = world.getBlockState(pos).get(FACING) as Direction
            }

            blockEntity.activate(direction2)
            world.playSound(
                null as PlayerEntity?,
                pos,
                DnDSoundEvents.BLOCK_CELESTAL_BELL_USE,
                SoundCategory.BLOCKS,
                2.0f,
                1.0f
            )
            world.emitGameEvent(entity, GameEvent.BLOCK_CHANGE, pos)
            return true
        } else {
            return false
        }
    }
}