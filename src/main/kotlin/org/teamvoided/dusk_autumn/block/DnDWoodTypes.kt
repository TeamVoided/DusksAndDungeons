package org.teamvoided.dusk_autumn.block

import net.fabricmc.fabric.api.`object`.builder.v1.block.type.BlockSetTypeBuilder
import net.fabricmc.fabric.api.`object`.builder.v1.block.type.WoodTypeBuilder
import net.minecraft.block.BlockSetType
import net.minecraft.block.WoodType
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.sound.SoundEvents
import org.teamvoided.dusk_autumn.DuskAutumns.id


object DnDWoodTypes {
    fun init() = Unit
    val CASCADE_BLOCK_SET_TYPE: BlockSetType =
        BlockSetTypeBuilder()
            .openableByHand(true)
            .openableByWindCharge(true)
            .buttonActivatedByArrows(true)
            .soundGroup(BlockSoundGroup.CHERRY_WOOD)
            .doorOpenSound(SoundEvents.BLOCK_CHERRY_WOOD_DOOR_OPEN)
            .doorCloseSound(SoundEvents.BLOCK_CHERRY_WOOD_DOOR_CLOSE)
            .trapdoorOpenSound(SoundEvents.BLOCK_CHERRY_WOOD_TRAPDOOR_OPEN)
            .trapdoorCloseSound(SoundEvents.BLOCK_CHERRY_WOOD_TRAPDOOR_CLOSE)
            .pressurePlateClickOnSound(SoundEvents.BLOCK_CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON)
            .pressurePlateClickOffSound(SoundEvents.BLOCK_CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF)
            .buttonClickOnSound(SoundEvents.BLOCK_CHERRY_WOOD_BUTTON_CLICK_ON)
            .buttonClickOffSound(SoundEvents.BLOCK_CHERRY_WOOD_BUTTON_CLICK_OFF)
            .pressurePlateActivationRule(BlockSetType.PressurePlateSensitivity.EVERYTHING)
            .register(id("cascade"))
    val CASCADE_WOOD_TYPE = registerWoodType("cascade", WoodType.CHERRY, CASCADE_BLOCK_SET_TYPE)

    val GALLERY_MAPLE_BLOCK_SET_TYPE: BlockSetType = BlockSetTypeBuilder().register(id("gallery_maple"))
    val GALLERY_MAPLE_WOOD_TYPE = registerWoodType("gallery_maple", WoodType.MANGROVE, GALLERY_MAPLE_BLOCK_SET_TYPE)

    //val PINE_BLOCK_SET_TYPE: BlockSetType = BlockSetTypeBuilder().register(id("pine"))
    //val PINE_WOOD_TYPE = registerWoodType("pine", WoodType.SPRUCE, PINE_BLOCK_SET_TYPE)

    val BONEWOOD_BLOCK_SET_TYPE: BlockSetType = BlockSetTypeBuilder().register(id("bonewood"))
    val BONEWOOD_WOOD_TYPE = registerWoodType("bonewood", WoodType.SPRUCE, BONEWOOD_BLOCK_SET_TYPE)
    val WITHERING_BONEWOOD_BLOCK_SET_TYPE: BlockSetType = BlockSetTypeBuilder().register(id("withering_bonewood"))
    val WITHERING_BONEWOOD_WOOD_TYPE =
        registerWoodType("withering_bonewood", BONEWOOD_WOOD_TYPE, WITHERING_BONEWOOD_BLOCK_SET_TYPE)

    private fun registerWoodType(id: String, woodType: WoodType, blockSet: BlockSetType): WoodType =
        WoodTypeBuilder.copyOf(woodType).register(id(id), blockSet)
}