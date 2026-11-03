package org.teamvoided.dusks_and_dungeons.item

import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.util.Mth
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.item.Item
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.phys.Vec3
import org.teamvoided.dusks_and_dungeons.entity.ScarecrowEntity
import org.teamvoided.dusks_and_dungeons.init.DnDEntityTypes

class ScarecrowItem(properties: Properties) : Item(properties) {

    override fun useOn(context: UseOnContext): InteractionResult {
        if (context.clickedFace == Direction.DOWN) {
            return InteractionResult.FAIL
        }
        val level = context.level
        val ctx = BlockPlaceContext(context)
        val pos = ctx.clickedPos
        val stack = context.itemInHand
        val vec3d = Vec3.atBottomCenterOf(pos)
        val box = DnDEntityTypes.SCARECROW.dimensions.makeBoundingBox(vec3d.x(), vec3d.y(), vec3d.z())
        if (level.noCollision(null, box) && level.getEntities(null, box).isEmpty()) {
            if (level is ServerLevel) {
                val consumer = EntityType.createDefaultStackConfig<ScarecrowEntity>(level, stack, context.player)
                val scarecrow =
                    DnDEntityTypes.SCARECROW.create(level, consumer, pos, MobSpawnType.SPAWN_EGG, true, true)
                        ?: return InteractionResult.FAIL

                val yaw = Mth.floor((Mth.wrapDegrees(ctx.rotation - 180.0f) + 22.5f) / ROT_AMOUNT) * ROT_AMOUNT
                scarecrow.yRot = yaw
                level.addFreshEntityWithPassengers(scarecrow)
                level.playSound(
                    null,
                    scarecrow.x, scarecrow.y, scarecrow.z,
                    SoundEvents.ARMOR_STAND_PLACE, SoundSource.BLOCKS,
                    0.75f, 0.8f
                )
                scarecrow.gameEvent(GameEvent.ENTITY_PLACE, ctx.player)
            }

            stack.shrink(1)
            return InteractionResult.sidedSuccess(level.isClientSide)
        } else {
            return InteractionResult.FAIL
        }
    }

    companion object {

        const val ROT_AMOUNT: Float = 22.5f

    }
}