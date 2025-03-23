package org.teamvoided.dusks_and_dungeons.init

import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.datafixer.TypeReferences
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Util
import org.teamvoided.dusks_and_dungeons.block.entity.*

object DnDBlockEntities {
    // region ☢ Experimental ☢
    val CELESTAL_BELL: BlockEntityType<CelestalBellBlockEntity> =
        register("celestal_bell", BlockEntityType.Builder.create(::CelestalBellBlockEntity, DnDBlocks.CELESTAL_BELL))

    val CHEST_O_SOULS: BlockEntityType<ChestOSoulsBlockEntity> =
        register("chest_o_souls", BlockEntityType.Builder.create(::ChestOSoulsBlockEntity, DnDBlocks.CHEST_O_SOULS))

    val QUARTER_BLOCK_PILE: BlockEntityType<QuarterBlockPileBlockEntity> = register(
        "quarter_block_pile",
        BlockEntityType.Builder.create(::QuarterBlockPileBlockEntity, DnDBlocks.QUARTER_BLOCK_PILE)
    )

    val BUNNY_GRAVE: BlockEntityType<BunnyGraveBlockEntity> =
        register("bunny_grave", BlockEntityType.Builder.create(::BunnyGraveBlockEntity, DnDBlocks.BUNNY_GRAVE))

    val HAUNTED_BLOCK: BlockEntityType<HauntedBlockEntity> = register(
        "hauted_block", BlockEntityType.Builder.create(
            ::HauntedBlockEntity,
//            DnDBlocks.HAUNTED_GRAVESTONE,
//            DnDBlocks.SMALL_HAUNTED_GRAVESTONE,
//            DnDBlocks.HAUNTED_DEEPSLATE_GRAVESTONE,
//            DnDBlocks.SMALL_HAUNTED_DEEPSLATE_GRAVESTONE,
//            DnDBlocks.HAUNTED_TUFF_GRAVESTONE,
//            DnDBlocks.SMALL_HAUNTED_TUFF_GRAVESTONE,
//            DnDBlocks.HAUNTED_BLACKSTONE_GRAVESTONE,
//            DnDBlocks.SMALL_HAUNTED_BLACKSTONE_GRAVESTONE
        )
    )
    val HAUNTED_GRAVESTONE_BLOCK: BlockEntityType<HauntedGravestoneBlockEntity> = register(
        "haunted_gravestone_block", BlockEntityType.Builder.create(
            ::HauntedGravestoneBlockEntity,
            DnDBlocks.HAUNTED_GRAVESTONE,
            DnDBlocks.SMALL_HAUNTED_GRAVESTONE,
            DnDBlocks.HAUNTED_DEEPSLATE_GRAVESTONE,
            DnDBlocks.SMALL_HAUNTED_DEEPSLATE_GRAVESTONE,
            DnDBlocks.HAUNTED_TUFF_GRAVESTONE,
            DnDBlocks.SMALL_HAUNTED_TUFF_GRAVESTONE,
            DnDBlocks.HAUNTED_BLACKSTONE_GRAVESTONE,
            DnDBlocks.SMALL_HAUNTED_BLACKSTONE_GRAVESTONE
        )
    )
    // endregion
    fun init() {}

    private fun <T : BlockEntity> register(id: String, builder: BlockEntityType.Builder<T>): BlockEntityType<T> {
        val type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id)
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, builder.build(type))
    }
}