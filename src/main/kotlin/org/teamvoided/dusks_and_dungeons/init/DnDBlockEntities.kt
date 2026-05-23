package org.teamvoided.dusks_and_dungeons.init

import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.util.datafix.fixes.References
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.Registry
import net.minecraft.Util

object DnDBlockEntities {
    fun init() {}
    private fun <T : BlockEntity> register(id: String, builder: BlockEntityType.Builder<T>): BlockEntityType<T> {
        val type = Util.fetchChoiceType(References.BLOCK_ENTITY, id)
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, builder.build(type))
    }
}