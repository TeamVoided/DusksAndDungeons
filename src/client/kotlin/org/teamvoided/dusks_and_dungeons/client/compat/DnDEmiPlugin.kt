package org.teamvoided.dusks_and_dungeons.client.compat

import dev.emi.emi.api.EmiRegistry
import dev.emi.emi.api.stack.EmiIngredient
import dev.emi.emi.api.stack.EmiStack
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.alchemy.Potions
import net.minecraft.world.item.crafting.RecipeType
import net.minecraft.world.level.material.Fluids
import org.slf4j.Logger
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.MODID
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.log
import org.teamvoided.dusks_and_dungeons.client.compat.recipe.EmiCandelabraContentsRecipe
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.init.DnDItems.EVIL_ITEMS
import org.teamvoided.dusks_and_dungeons.recipe.CandelabraContentsRecipe
import org.teamvoided.dusks_and_dungeons.util.isDev
import org.teamvoided.voidlib.client.compat.emi.VEmiPlugin
import org.teamvoided.voidlib.client.compat.emi.basicWorld
import org.teamvoided.voidlib.client.compat.emi.getRecipes
import org.teamvoided.voidlib.client.compat.emi.subId

object DnDEmiPlugin : VEmiPlugin {

    override fun log(): Logger = log

    override fun modid(): String = MODID

    override fun register(registry: EmiRegistry) {
        safely("hide experimental") { handleExperimental(registry) }
        safely("world interaction") { addWorldInteraction(registry) }
        safely("special recipes") {
            for (recipe in getRecipes(registry, RecipeType.CRAFTING)) {
                if (recipe.value() is CandelabraContentsRecipe) {
                    addCandelabraRecipes(registry)
                }
            }
        }
    }

    fun addCandelabraRecipes(registry: EmiRegistry) {
        for (stack in EmiIngredient.of(DnDItemTags.CANDELABRAS).emiStacks) {
            registry.addRecipeSafe(synthetic("crafting/adding_candles", subId(stack.itemStack.item))) {
                EmiCandelabraContentsRecipe(stack, it)
            }
        }
    }

    fun handleExperimental(reg: EmiRegistry) {
        if (isDev()) {
            return
        }
        reg.removeEmiStacks { it.itemStack.item in EVIL_ITEMS }
    }

    fun addWorldInteraction(registry: EmiRegistry) {
        val water = EmiStack.of(Fluids.WATER, 81_000)
        val waterBottle = EmiStack.of(setPotion(ItemStack(DnDItems.TINTED_POTION), Potions.WATER.value()))
        val bottle = EmiStack.of(DnDItems.TINTED_GLASS_BOTTLE)
        registry.addRecipeSafe(synthetic("world/unique", "$MODID/tinted_water_bottle")) { id ->
            basicWorld(bottle, water, waterBottle, id)
        }
        val mud = EmiStack.of(Items.MUD)
        registry.addRecipeSafe(synthetic("world/unique", "$MODID/mud")) { id ->
            basicWorld(EmiStack.of(Items.DIRT), waterBottle.copy().setRemainder(bottle), mud, id, false)
        }
    }

}