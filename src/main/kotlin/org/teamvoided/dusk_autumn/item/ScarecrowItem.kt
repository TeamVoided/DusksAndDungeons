package org.teamvoided.dusk_autumn.item

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemUsageContext
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.math.Direction
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import net.minecraft.world.event.GameEvent
import org.teamvoided.dusk_autumn.entity.ScarecrowEntity
import org.teamvoided.dusk_autumn.init.DnDEntities

class ScarecrowItem(settings: Settings) : Item(settings) {
    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        val direction = context.side
        if (direction == Direction.DOWN) {
            return ActionResult.FAIL
        } else {
            val world = context.world
            val itemPlacementContext = ItemPlacementContext(context)
            val blockPos = itemPlacementContext.blockPos
            val itemStack = context.stack
            val vec3d = Vec3d.ofBottomCenter(blockPos)
            val box = DnDEntities.SCARECROW.dimensions.getBoxAt(vec3d.getX(), vec3d.getY(), vec3d.getZ())
            if (world.isSpaceEmpty(null as Entity?, box) && world.getOtherEntities(null as Entity?, box).isEmpty()) {
                if (world is ServerWorld) {
                    val consumer =
                        EntityType.createDefaultStackSpawnConfig<ScarecrowEntity>(world, itemStack, context.player)
                    val scarecrowEntity =
                        DnDEntities.SCARECROW.create(world, consumer, blockPos, SpawnReason.SPAWN_EGG, true, true)
                            ?: return ActionResult.FAIL

                    val yaw =
                        MathHelper.floor((MathHelper.wrapDegrees(context.playerYaw - 180.0f) + 22.5f) / TURN) * TURN
                    scarecrowEntity.refreshPositionAndAngles(
                        scarecrowEntity.x,
                        scarecrowEntity.y,
                        scarecrowEntity.z,
                        yaw,
                        0.0f
                    )
                    world.spawnEntityAndPassengers(scarecrowEntity)
                    world.playSound(
                        null as PlayerEntity?,
                        scarecrowEntity.x,
                        scarecrowEntity.y,
                        scarecrowEntity.z,
                        SoundEvents.ENTITY_ARMOR_STAND_PLACE,
                        SoundCategory.BLOCKS,
                        0.75f,
                        0.8f
                    )
                    scarecrowEntity.emitGameEvent(GameEvent.ENTITY_PLACE, context.player)
                }

                itemStack.decrement(1)
                return ActionResult.success(world.isClient)
            } else {
                return ActionResult.FAIL
            }
        }
    }

    companion object {
        const val TURN: Float = 22.5f
    }
}