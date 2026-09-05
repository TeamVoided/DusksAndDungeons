package org.teamvoided.dusks_and_dungeons.compat

import dev.emi.emi.api.EmiPlugin
import dev.emi.emi.api.EmiRegistry
import dev.emi.emi.api.recipe.EmiRecipe
import dev.emi.emi.api.recipe.EmiWorldInteractionRecipe
import dev.emi.emi.api.stack.EmiStack
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.alchemy.Potions
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.material.Fluids
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.MODID
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.isDev
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.log
import org.teamvoided.dusks_and_dungeons.block.pumpkin.CarvableBlock
import org.teamvoided.dusks_and_dungeons.impl.BlockStrippingRegistryIml
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.init.DnDItems.EVIL_ITEMS
import java.util.function.Function

object DnDEmiPlugin : EmiPlugin {

    override fun register(registry: EmiRegistry) {
        val hiddenItems = hiddenFromRecipeViewers() + EVIL_ITEMS.toSet()

        safely("hide experimental") { handleExperimental(registry) }
        safely("world interaction") { addWorldInteraction(registry, hiddenItems) }
    }

    fun handleExperimental(reg: EmiRegistry) {
        if (isDev()) {
            return
        }
        reg.removeEmiStacks { it.itemStack.item in EVIL_ITEMS }
    }

    fun addWorldInteraction(registry: EmiRegistry, hiddenItems: Set<Item>) {
        val axes = getAxes()
        for ((from, to) in BlockStrippingRegistryIml.BLOCK_STATE_MAP) {
            val input = EmiStack.of(from)
            val output = EmiStack.of(to)
            if (hiddenItems.contains(input.itemStack.item) || hiddenItems.contains(output.itemStack.item)) {
                continue
            }
            registry.addRecipeSafe(synthetic("world/stripping", subId(from))) { id ->
                basicWorld(input, axes, output, id)
            }
        }

        val shears = getShears()
        for (carved in BuiltInRegistries.ITEM.mapNotNull { if (it is BlockItem && it.block is CarvableBlock) it.block as CarvableBlock else null }) {
            val from = (carved as Block).asItem()
            val to = carved.getCarvedBlock().asItem()
            if (to == Items.AIR) {
                continue
            }

            val input = EmiStack.of(from)
            val output = EmiStack.of(to)
            if (hiddenItems.contains(input.itemStack.item) || hiddenItems.contains(output.itemStack.item)) {
                continue
            }
            registry.addRecipeSafe(synthetic("world/carving", subId(from))) { id ->
                EmiWorldInteractionRecipe.builder()
                    .id(id)
                    .leftInput(input)
                    .rightInput(shears, true)
                    .output(output)
                    // TODO add seed loot tables
                    .build()
            }
        }

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

    fun synthetic(type: String, name: String): ResourceLocation = id("/$type/$name")

    fun safely(name: String, runnable: Runnable) {
        try {
            runnable.run()
        } catch (e: Throwable) {
            log.warn("Exception thrown when reloading $name step in DnD EMI plugin", e)
        }
    }

    fun EmiRegistry.addRecipeSafe(id: ResourceLocation, supplier: Function<ResourceLocation, EmiRecipe>) {
        try {
            addRecipe(supplier.apply(id))
        } catch (e: Throwable) {
            log.warn("Exception thrown when parsing EMI recipe ($id)", e)
        }
    }

}