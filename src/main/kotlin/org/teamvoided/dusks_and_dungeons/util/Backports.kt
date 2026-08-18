package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntitySelector
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.storage.loot.LootParams
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.parameters.LootContextParams
import net.minecraft.world.phys.Vec3
import org.teamvoided.dusks_and_dungeons.init.DnDLootContext
import org.teamvoided.voidlib.helpers.mc.getLootTable
import java.util.function.BiConsumer
import java.util.function.Function
import java.util.function.Predicate


// All functions here have been backported from future versions and will become redundant on update

val CAN_BE_PICKED: Predicate<Entity> = EntitySelector.NO_SPECTATORS.and(Entity::isPickable)

fun getApproximateNearest(dx: Double, dy: Double, dz: Double): Direction {
    return getApproximateNearest(dx.toFloat(), dy.toFloat(), dz.toFloat())
}

fun getApproximateNearest(dx: Float, dy: Float, dz: Float): Direction {
    var result = Direction.NORTH
    var highestDot = Float.MIN_VALUE

    var dot: Float
    for (direction in Direction.entries) {
        dot = dx * direction.normal.x + dy * direction.normal.y + dz * direction.normal.z
        if (dot > highestDot) {
            highestDot = dot
            result = direction
        }
    }

    return result
}


fun InteractionHand.asEquipmentSlot(): EquipmentSlot {
    return if (this == InteractionHand.MAIN_HAND) EquipmentSlot.MAINHAND else EquipmentSlot.OFFHAND
}

// region LootTable
fun dropFromBlockInteractLootTable(
    level: ServerLevel,
    key: ResourceKey<LootTable>,
    interactedBlockPos: BlockPos,
    interactedBlockState: BlockState,
    interactedBlockEntity: BlockEntity?,
    tool: ItemStack?,
    interactingEntity: Entity?,
    consumer: BiConsumer<ServerLevel, ItemStack>,
): Boolean {
    return dropFromLootTable(
        level, key, { params ->
            params.withParameter(LootContextParams.BLOCK_STATE, interactedBlockState)
                .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(interactedBlockPos))
                .withOptionalParameter(LootContextParams.BLOCK_ENTITY, interactedBlockEntity)
                .withOptionalParameter(DnDLootContext.INTERACTING_ENTITY, interactingEntity)
                .withOptionalParameter(LootContextParams.TOOL, tool)
                .create(DnDLootContext.BLOCK_INTERACT)
        },
        consumer
    )
}

fun dropFromLootTable(
    level: ServerLevel,
    key: ResourceKey<LootTable>,
    paramsBuilder: Function<LootParams.Builder, LootParams>,
    consumer: BiConsumer<ServerLevel, ItemStack>,
): Boolean {
    val lootTable = level.getLootTable(key)
    val params = paramsBuilder.apply(LootParams.Builder(level))
    val drops = lootTable.getRandomItems(params)
    if (drops.isNotEmpty()) {
        drops.forEach { stack -> consumer.accept(level, stack) }
        return true
    } else {
        return false
    }
}
// endregion