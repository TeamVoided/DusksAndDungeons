package org.teamvoided.dusks_and_dungeons.compat.recipe

import dev.emi.emi.api.recipe.EmiPatternCraftingRecipe
import dev.emi.emi.api.stack.EmiIngredient
import dev.emi.emi.api.stack.EmiStack
import dev.emi.emi.api.widget.GeneratedSlotWidget
import dev.emi.emi.api.widget.SlotWidget
import net.minecraft.resources.ResourceLocation
import org.teamvoided.dusks_and_dungeons.block.candelabra.CandelabraContents
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags
import org.teamvoided.dusks_and_dungeons.init.DnDDataComponents.CANDELABRA_CONTENTS
import java.util.*

@Suppress("MISSING_DEPENDENCY_SUPERCLASS_WARNING")
class EmiCandelabraContentsRecipe(val candelabra: EmiStack, id: ResourceLocation) :
    EmiPatternCraftingRecipe(listOf(candelabra, CANDLES), candelabra, id) {

    override fun getInputWidget(slot: Int, x: Int, y: Int): SlotWidget {
        if (slot == 0) {
            return SlotWidget(candelabra, x, y)
        } else if (slot == 1) {
            return SlotWidget(CANDLES, x, y)
        }
        return SlotWidget(EmiStack.EMPTY, x, y)
    }

    override fun getOutputWidget(x: Int, y: Int): SlotWidget {
        return GeneratedSlotWidget({ r -> getFull(r) }, unique, x, y)
    }

    fun getFull(random: Random): EmiStack {
        val stack = candelabra.itemStack.copy()
        val allCandles = CANDLES.emiStacks
        stack[CANDELABRA_CONTENTS] = CandelabraContents.create(allCandles[random.nextInt(allCandles.size)].itemStack)

        return EmiStack.of(stack)
    }

    companion object {

        val CANDLES: EmiIngredient = EmiIngredient.of(DnDItemTags.GOES_IN_CANDELABRA)

    }
}
