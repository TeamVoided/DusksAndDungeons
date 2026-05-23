package org.teamvoided.dusks_and_dungeons.block

import net.fabricmc.fabric.api.`object`.builder.v1.block.type.BlockSetTypeBuilder
import net.fabricmc.fabric.api.`object`.builder.v1.block.type.WoodTypeBuilder
import net.minecraft.world.level.block.state.properties.BlockSetType
import net.minecraft.world.level.block.state.properties.WoodType
import net.minecraft.world.level.block.SoundType
import net.minecraft.sounds.SoundEvents
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id


object DnDWoodTypes {
    fun init() = Unit
    val CASCADE_BLOCK_SET_TYPE: BlockSetType =
        BlockSetTypeBuilder()
            .openableByHand(true)
            .openableByWindCharge(true)
            .buttonActivatedByArrows(true)
            .soundGroup(SoundType.CHERRY_WOOD)
            .doorOpenSound(SoundEvents.CHERRY_WOOD_DOOR_OPEN)
            .doorCloseSound(SoundEvents.CHERRY_WOOD_DOOR_CLOSE)
            .trapdoorOpenSound(SoundEvents.CHERRY_WOOD_TRAPDOOR_OPEN)
            .trapdoorCloseSound(SoundEvents.CHERRY_WOOD_TRAPDOOR_CLOSE)
            .pressurePlateClickOnSound(SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON)
            .pressurePlateClickOffSound(SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF)
            .buttonClickOnSound(SoundEvents.CHERRY_WOOD_BUTTON_CLICK_ON)
            .buttonClickOffSound(SoundEvents.CHERRY_WOOD_BUTTON_CLICK_OFF)
            .pressurePlateActivationRule(BlockSetType.PressurePlateSensitivity.EVERYTHING)
            .register(id("cascade"))
    val CASCADE_WOOD_TYPE = registerWoodType("cascade", WoodType.CHERRY, CASCADE_BLOCK_SET_TYPE)

    @Suppress("SameParameterValue")
    private fun registerWoodType(id: String, woodType: WoodType, blockSet: BlockSetType): WoodType =
        WoodTypeBuilder.copyOf(woodType).register(id(id), blockSet)
}