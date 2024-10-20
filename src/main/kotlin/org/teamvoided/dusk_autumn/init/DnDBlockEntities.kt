package org.teamvoided.dusk_autumn.init

import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import org.teamvoided.dusk_autumn.block.entity.BunnyGraveBlockEntity
import net.minecraft.datafixer.TypeReferences
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Util
import org.teamvoided.dusk_autumn.block.entity.CelestalBellBlockEntity
import org.teamvoided.dusk_autumn.block.entity.ChestOSoulsBlockEntity
import org.teamvoided.dusk_autumn.block.entity.HauntedBlockEntity
import org.teamvoided.dusk_autumn.block.entity.QuarterBlockPileBlockEntity
import org.teamvoided.dusk_autumn.init.blocks.DnDStoneBlocks

object DnDBlockEntities {
    fun init() {}
    val CELESTAL_BELL: BlockEntityType<CelestalBellBlockEntity> =
        register("celestal_bell", BlockEntityType.Builder.create(::CelestalBellBlockEntity, DnDBlocks.CELESTAL_BELL))

    val CHEST_O_SOULS: BlockEntityType<ChestOSoulsBlockEntity> =
        register("chest_o_souls", BlockEntityType.Builder.create(::ChestOSoulsBlockEntity, DnDBlocks.CHEST_O_SOULS))

    val QUARTER_BLOCK_PILE: BlockEntityType<QuarterBlockPileBlockEntity> = register(
        "quarter_block_pile",
        BlockEntityType.Builder.create(::QuarterBlockPileBlockEntity, DnDBlocks.QUARTER_BLOCK_PILE)
    )

    val BUNNY_GRAVE: BlockEntityType<BunnyGraveBlockEntity> =
        register("bunny_grave", BlockEntityType.Builder.create(::BunnyGraveBlockEntity, DnDStoneBlocks.BUNNY_GRAVE))

    val HAUNTED_BLOCK: BlockEntityType<HauntedBlockEntity> = register(
        "hauted_block", BlockEntityType.Builder.create(
            ::HauntedBlockEntity,
            DnDStoneBlocks.HAUNTED_GRAVESTONE,
            DnDStoneBlocks.SMALL_HAUNTED_GRAVESTONE,
            DnDStoneBlocks.HAUNTED_DEEPSLATE_GRAVESTONE,
            DnDStoneBlocks.SMALL_HAUNTED_DEEPSLATE_GRAVESTONE,
            DnDStoneBlocks.HAUNTED_TUFF_GRAVESTONE,
            DnDStoneBlocks.SMALL_HAUNTED_TUFF_GRAVESTONE,
            DnDStoneBlocks.HAUNTED_BLACKSTONE_GRAVESTONE,
            DnDStoneBlocks.SMALL_HAUNTED_BLACKSTONE_GRAVESTONE
        )
    )

    private fun <T : BlockEntity> register(id: String, builder: BlockEntityType.Builder<T>): BlockEntityType<T> {
        val type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id)
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, builder.build(type))
    }
}