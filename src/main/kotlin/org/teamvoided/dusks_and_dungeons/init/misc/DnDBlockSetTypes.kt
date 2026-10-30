package org.teamvoided.dusks_and_dungeons.init.misc

import net.fabricmc.fabric.api.`object`.builder.v1.block.type.BlockSetTypeBuilder
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.properties.BlockSetType
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import java.util.function.UnaryOperator

object DnDBlockSetTypes {

    val CASCADE_BLOCK_SET_TYPE = registryCopy("cascade", BlockSetType.CHERRY)
    val SYPIA_BLOCK_SET_TYPE = registryCopy("sypia", BlockSetType.BIRCH)
    val VERDANT_BLOCK_SET_TYPE = registryCopy("verdant", BlockSetType.BAMBOO)

    val WOOL = register("wool") { builder ->
        builder
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

    val MOSS = register("moss") { builder ->
        builder
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

    fun init() = Unit

    fun register(name: String, creator: UnaryOperator<BlockSetTypeBuilder>): BlockSetType {
        return register(name, creator.apply(BlockSetTypeBuilder()))
    }

    fun registryCopy(name: String, set: BlockSetType): BlockSetType {
        return register(name, BlockSetTypeBuilder.copyOf(set))
    }

    fun register(name: String, set: BlockSetTypeBuilder): BlockSetType {
        return set.register(id(name))
    }

}