package org.teamvoided.dusks_and_dungeons.item

import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundSource
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.InteractionResult
import net.minecraft.core.Direction
import net.minecraft.util.Mth
import net.minecraft.world.phys.Vec3
import net.minecraft.world.level.gameevent.GameEvent
import org.teamvoided.dusks_and_dungeons.entity.ScarecrowEntity
import org.teamvoided.dusks_and_dungeons.init.DnDEntityTypes

class ScarecrowItem(settings: Properties) : Item(settings) {
    override fun useOn(context: UseOnContext): InteractionResult {
        val direction = context.clickedFace
        if (direction == Direction.DOWN) {
            return InteractionResult.FAIL
        } else {
            val world = context.level
            val itemPlacementContext = BlockPlaceContext(context)
            val blockPos = itemPlacementContext.clickedPos
            val itemStack = context.itemInHand
            val vec3d = Vec3.atBottomCenterOf(blockPos)
            val box = DnDEntityTypes.SCARECROW.dimensions.makeBoundingBox(vec3d.x(), vec3d.y(), vec3d.z())
            if (world.noCollision(null as Entity?, box) && world.getEntities(null as Entity?, box).isEmpty()) {
                if (world is ServerLevel) {
                    val consumer =
                        EntityType.createDefaultStackConfig<ScarecrowEntity>(world, itemStack, context.player)
                    val scarecrowEntity =
                        DnDEntityTypes.SCARECROW.create(world, consumer, blockPos, MobSpawnType.SPAWN_EGG, true, true)
                            ?: return InteractionResult.FAIL

                    val yaw =
                        Mth.floor((Mth.wrapDegrees(context.rotation - 180.0f) + 22.5f) / TURN) * TURN
                    scarecrowEntity.moveTo(
                        scarecrowEntity.x,
                        scarecrowEntity.y,
                        scarecrowEntity.z,
                        yaw,
                        0.0f
                    )
                    world.addFreshEntityWithPassengers(scarecrowEntity)
                    world.playSound(
                        null as Player?,
                        scarecrowEntity.x,
                        scarecrowEntity.y,
                        scarecrowEntity.z,
                        SoundEvents.ARMOR_STAND_PLACE,
                        SoundSource.BLOCKS,
                        0.75f,
                        0.8f
                    )
                    scarecrowEntity.gameEvent(GameEvent.ENTITY_PLACE, context.player)
                }

                itemStack.shrink(1)
                return InteractionResult.sidedSuccess(world.isClientSide)
            } else {
                return InteractionResult.FAIL
            }
        }
    }

    companion object {
        const val TURN: Float = 22.5f
    }
}