package org.teamvoided.dusks_and_dungeons.block

import net.fabricmc.fabric.api.`object`.builder.v1.block.type.BlockSetTypeBuilder
import net.fabricmc.fabric.api.`object`.builder.v1.block.type.WoodTypeBuilder
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.properties.BlockSetType
import net.minecraft.world.level.block.state.properties.WoodType
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id


// TODO split DnDBlockSetTypes to new file
object DnDWoodTypes {
    fun init() = Unit

    val CASCADE_BLOCK_SET_TYPE: BlockSetType = BlockSetTypeBuilder.copyOf(BlockSetType.CHERRY).register(id("cascade"))
    val CASCADE_WOOD_TYPE = registerWoodType("cascade", WoodType.CHERRY, CASCADE_BLOCK_SET_TYPE)

    val SYPIA_BLOCK_SET_TYPE: BlockSetType = BlockSetTypeBuilder.copyOf(BlockSetType.BIRCH).register(id("sypia"))
    val SYPIA_WOOD_TYPE = registerWoodType("sypia", WoodType.BIRCH, SYPIA_BLOCK_SET_TYPE)

    val VERDANT_BLOCK_SET_TYPE: BlockSetType = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(id("verdant"))
    @JvmField
    val VERDANT_WOOD_TYPE: WoodType = registerWoodType("verdant", WoodType.OAK, VERDANT_BLOCK_SET_TYPE)

    val WOOL = create("wool") {
        it
            .openableByHand(true)
            .openableByWindCharge(true)
            .buttonActivatedByArrows(true)
            .pressurePlateActivationRule(BlockSetType.PressurePlateSensitivity.EVERYTHING)
            .soundGroup(SoundType.WOOL)
            .doorCloseSound(SoundEvents.CHERRY_WOOD_DOOR_CLOSE)
            .doorOpenSound(SoundEvents.CHERRY_WOOD_DOOR_OPEN)
            .trapdoorCloseSound(SoundEvents.CHERRY_WOOD_TRAPDOOR_CLOSE)
            .trapdoorOpenSound(SoundEvents.CHERRY_WOOD_TRAPDOOR_OPEN)
            .pressurePlateClickOffSound(SoundEvents.WOOL_HIT)
            .pressurePlateClickOnSound(SoundEvents.WOOL_FALL)
            .buttonClickOffSound(SoundEvents.CHERRY_WOOD_BUTTON_CLICK_OFF)
            .buttonClickOnSound(SoundEvents.CHERRY_WOOD_BUTTON_CLICK_ON)

    }
    val MOSS = create("moss") {
        it
            .openableByHand(true)
            .openableByWindCharge(true)
            .buttonActivatedByArrows(true)
            .pressurePlateActivationRule(BlockSetType.PressurePlateSensitivity.EVERYTHING)
            .soundGroup(SoundType.MOSS_CARPET)
            .doorCloseSound(SoundEvents.CHERRY_WOOD_DOOR_CLOSE)
            .doorOpenSound(SoundEvents.CHERRY_WOOD_DOOR_OPEN)
            .trapdoorCloseSound(SoundEvents.CHERRY_WOOD_TRAPDOOR_CLOSE)
            .trapdoorOpenSound(SoundEvents.CHERRY_WOOD_TRAPDOOR_OPEN)
            .pressurePlateClickOffSound(SoundEvents.MOSS_CARPET_HIT)
            .pressurePlateClickOnSound(SoundEvents.MOSS_CARPET_FALL)
            .buttonClickOffSound(SoundEvents.CHERRY_WOOD_BUTTON_CLICK_OFF)
            .buttonClickOnSound(SoundEvents.CHERRY_WOOD_BUTTON_CLICK_ON)

    }

    fun create(name: String, fn: (builder: BlockSetTypeBuilder) -> BlockSetTypeBuilder): BlockSetType =
        fn(BlockSetTypeBuilder()).build(id(name))

    @Suppress("SameParameterValue")
    private fun registerWoodType(id: String, woodType: WoodType, blockSet: BlockSetType): WoodType =
        WoodTypeBuilder.copyOf(woodType).register(id(id), blockSet)
}