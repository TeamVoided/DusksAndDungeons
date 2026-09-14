package org.teamvoided.dusks_and_dungeons.recipe

import net.minecraft.core.HolderLookup
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.CraftingBookCategory
import net.minecraft.world.item.crafting.CraftingInput
import net.minecraft.world.item.crafting.CustomRecipe
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.level.Level
import org.teamvoided.dusks_and_dungeons.block.candelabra.CandelabraContents
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags
import org.teamvoided.dusks_and_dungeons.init.DnDDataComponents
import org.teamvoided.dusks_and_dungeons.init.DnDRecipes.CANDELABRA_CONTENTS

class CandelabraContentsRecipe(category: CraftingBookCategory) : CustomRecipe(category) {

    override fun getSerializer(): RecipeSerializer<*> = CANDELABRA_CONTENTS

    override fun canCraftInDimensions(x: Int, y: Int): Boolean = x * y >= 2

    override fun matches(input: CraftingInput, level: Level): Boolean {
        var candelabra = ItemStack.EMPTY
        var candles = ItemStack.EMPTY

        for (stack in input.items()) {
            if (!stack.isEmpty) {
                if (stack.`is`(DnDItemTags.GOES_IN_CANDELABRA)) {
                    if (!candles.isEmpty) {
                        return false
                    }

                    candles = stack
                } else {
                    if (!stack.`is`(DnDItemTags.CANDELABRAS)) {
                        return false
                    }

                    if (!candelabra.isEmpty) {
                        return false
                    }

                    val contents = stack.getOrDefault(DnDDataComponents.CANDELABRA_CONTENTS, CandelabraContents.ONE)
                    if (contents != CandelabraContents.ONE) {
                        return false
                    }

                    candelabra = stack
                }
            }
        }

        return !candelabra.isEmpty && !candles.isEmpty
    }

    override fun assemble(input: CraftingInput, provider: HolderLookup.Provider): ItemStack {
        var candle = ItemStack.EMPTY
        var candelabra = ItemStack.EMPTY

        for (stack in input.items()) {
            if (!stack.isEmpty) {
                if (stack.`is`(DnDItemTags.GOES_IN_CANDELABRA)) {
                    candle = stack
                } else if (stack.`is`(DnDItemTags.CANDELABRAS)) {
                    candelabra = stack.copyWithCount(1)
                }
            }
        }

        if (candelabra.isEmpty) {
            return candelabra
        }

        candelabra.set(DnDDataComponents.CANDELABRA_CONTENTS, CandelabraContents.create(candle.copyWithCount(1)))
        return candelabra
    }

}