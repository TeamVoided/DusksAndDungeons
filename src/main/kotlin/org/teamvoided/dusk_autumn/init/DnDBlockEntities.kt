package org.teamvoided.dusk_autumn.init

import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.datafixer.TypeReferences
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Util
import org.teamvoided.dusk_autumn.block.entity.CelestalBellBlockEntity

object DnDBlockEntities {
    fun init() {}
    val CELESTAL_BELL: BlockEntityType<CelestalBellBlockEntity> =
        register("celestal_bell", BlockEntityType.Builder.create(::CelestalBellBlockEntity, DnDBlocks.CELESTAL_BELL))

    private fun <T : BlockEntity> register(id: String, builder: BlockEntityType.Builder<T>): BlockEntityType<T> {
        val type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id)
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, builder.build(type))
    }

}