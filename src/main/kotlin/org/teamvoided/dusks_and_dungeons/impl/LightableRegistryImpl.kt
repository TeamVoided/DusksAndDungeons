package org.teamvoided.dusks_and_dungeons.impl

import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.log
import java.util.function.Predicate

object LightableRegistryImpl {

    internal val CONDITIONS = mutableSetOf<Predicate<BlockState>>()

    fun register(condition: Predicate<BlockState>) {
        if (condition.test(Blocks.AIR.defaultBlockState())) {
            error("Condition was too loose and allowed air to be lit. Condition is required to check for property: ${BlockStateProperties.LIT}")
        }

        if (!CONDITIONS.add(condition)) {
            log.warn("Double registered a condition!")
        }
    }

    @JvmStatic
    fun canLight(state: BlockState): Boolean {
        for (predicate in CONDITIONS) {
            if (predicate.test(state)) {
                return true
            }
        }
        return false
    }

}