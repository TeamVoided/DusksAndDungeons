package org.teamvoided.dusks_and_dungeons.init

import net.minecraft.core.BlockPos
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.block.entity.CandelabraBlockEntity
import org.teamvoided.dusks_and_dungeons.util.register

object DnDBlockEntities {

    val CANDELABRA = register("candelabra", ::CandelabraBlockEntity, DnDBlocks.IRON_CANDELABRA)

    fun init() {
        BlockEntityType.BRUSHABLE_BLOCK.addSupportedBlock(DnDBlocks.SUSPICIOUS_RED_SAND)
    }

    internal fun <T : BlockEntity> register(
        id: String, factory: (BlockPos, BlockState) -> T, vararg blocks: Block,
    ): BlockEntityType<T> {
        return register(id, BlockEntityType.Builder.of(factory, *blocks))
    }

    internal fun <T : BlockEntity> register(name: String, builder: BlockEntityType.Builder<T>): BlockEntityType<T> {
        return BuiltInRegistries.BLOCK_ENTITY_TYPE.register(id(name), builder.build())
    }

}