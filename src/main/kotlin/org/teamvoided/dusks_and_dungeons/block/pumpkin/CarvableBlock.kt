package org.teamvoided.dusks_and_dungeons.block.pumpkin

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.phys.BlockHitResult
import org.teamvoided.dusks_and_dungeons.util.asEquipmentSlot
import org.teamvoided.dusks_and_dungeons.util.dropFromBlockInteractLootTable
import org.teamvoided.dusks_and_dungeons.util.isShears
import org.teamvoided.dusks_and_dungeons.util.key

interface CarvableBlock {

    fun getId(): ResourceLocation

    fun getCarvedBlockState(stack: ItemStack, state: BlockState, clickedDir: Direction): BlockState


    fun isCarvingTool(stack: ItemStack): Boolean = stack.isShears()

    fun getCarvingLootTable(): ResourceKey<LootTable> = crateKey(getId())

    fun getGameEvent(player: Player, stack: ItemStack): Holder<GameEvent>? = GameEvent.SHEAR

    fun getSoundEvent(player: Player, stack: ItemStack): SoundEvent? = SoundEvents.PUMPKIN_CARVE

    fun getCarvingDir(clickedDir: Direction, player: Player): Direction {
        return if (clickedDir.axis === Direction.Axis.Y) player.direction.opposite else clickedDir
    }

    fun tryCarve(
        stack: ItemStack, state: BlockState, level: Level, pos: BlockPos,
        player: Player, hand: InteractionHand, hit: BlockHitResult,
    ): Boolean {
        if (!isCarvingTool(stack)) return false

        if (level is ServerLevel) {
            val dir = getCarvingDir(hit.direction, player)
            dropFromBlockInteractLootTable(
                level, getCarvingLootTable(), pos, state, level.getBlockEntity(pos), stack, player
            ) { _, drops ->
                val item = ItemEntity(
                    level,
                    pos.x + 0.5 + dir.stepX * 0.65,
                    pos.y + 0.1,
                    pos.z + 0.5 + dir.stepZ * 0.65,
                    drops
                )
                val random = level.getRandom()
                item.setDeltaMovement(
                    0.05 * dir.stepX + random.nextDouble() * 0.02,
                    0.05,
                    0.05 * dir.stepZ + random.nextDouble() * 0.02
                )
                level.addFreshEntity(item)
            }
            getSoundEvent(player, stack)?.let { sound ->
                level.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0f, 1.0f)
            }
            level.setBlock(pos, getCarvedBlockState(stack, state, dir), 11)
            stack.hurtAndBreak(1, player, hand.asEquipmentSlot())
            getGameEvent(player, stack)?.let { event -> level.gameEvent(player, event, pos) }
            player.awardStat(Stats.ITEM_USED.get(stack.item))
        }

        return true
    }

    companion object {

        const val LOOT_PREFIX = "carving/"

        fun crateKey(id: ResourceLocation): ResourceKey<LootTable> {
            return Registries.LOOT_TABLE.key(id.withPrefix(LOOT_PREFIX))
        }

    }
}