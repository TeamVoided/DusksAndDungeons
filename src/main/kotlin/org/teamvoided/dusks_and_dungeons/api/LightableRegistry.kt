package org.teamvoided.dusks_and_dungeons.api

import net.minecraft.world.item.FireChargeItem
import net.minecraft.world.item.FlintAndSteelItem
import net.minecraft.world.level.block.CampfireBlock
import net.minecraft.world.level.block.CandleBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import org.teamvoided.dusks_and_dungeons.impl.LightableRegistryImpl
import java.util.function.Predicate

object LightableRegistry {

    /**
     * Registers a condition to allow Lighter items like [FlintAndSteelItem] and [FireChargeItem] to change the state to be lit.
     * The Condition needs to check if the block has the [BlockStateProperties.LIT], or the game will crash.
     *
     * As an example of how the predicate should look like see : [CampfireBlock.canLight], [CandleBlock.canLight]
     *
     */
    fun resisterLightCondition(condition: Predicate<BlockState>) {
        LightableRegistryImpl.register(condition)
    }

}