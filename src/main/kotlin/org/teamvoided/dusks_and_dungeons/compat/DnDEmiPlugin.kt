package org.teamvoided.dusks_and_dungeons.compat

import dev.emi.emi.api.EmiPlugin
import dev.emi.emi.api.EmiRegistry
import dev.emi.emi.api.recipe.EmiRecipe
import dev.emi.emi.api.recipe.EmiWorldInteractionRecipe
import dev.emi.emi.api.stack.EmiIngredient
import dev.emi.emi.api.stack.EmiStack
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.core.component.DataComponents
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.alchemy.Potion
import net.minecraft.world.item.alchemy.PotionContents
import net.minecraft.world.item.alchemy.Potions
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.Fluids
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.MODID
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.isDev
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.log
import org.teamvoided.dusks_and_dungeons.impl.BlockStrippingRegistryIml
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.init.DnDItems.EVIL_ITEMS
import org.teamvoided.dusks_and_dungeons.util.tag
import kotlin.jvm.optionals.getOrNull

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

        val axes: EmiIngredient = getAxes()
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

        val water = EmiStack.of(Fluids.WATER, 81_000)

        registry.addRecipeSafe(synthetic("world/unique", "$MODID/tinted_water_bottle")) { id ->
            basicWorld(
                EmiStack.of(DnDItems.TINTED_GLASS_BOTTLE), water,
                EmiStack.of(setPotion(ItemStack(DnDItems.TINTED_POTION), Potions.WATER.value())),
                id
            )
        }

    }

    fun getAxes(): EmiIngredient {
        return damagedTool(
            getPreferredTag(
                mutableListOf(
                    "minecraft:axes", "c:axes", "c:tools/axes", "fabric:axes", "forge:tools/axes"
                ),
                EmiStack.of(Items.IRON_AXE)
            ), 1
        )
    }

    fun damagedTool(tool: EmiIngredient, damage: Int): EmiIngredient {
        for (stack in tool.emiStacks) {
            val rawStack = stack.itemStack.copy()
            rawStack.damageValue = damage
            stack.remainder = EmiStack.of(rawStack)
        }
        return tool
    }


    fun getPreferredTag(candidates: MutableList<String>, fallback: EmiIngredient): EmiIngredient {
        for (id in candidates) {
            val potential = EmiIngredient.of(Registries.ITEM.tag(ResourceLocation.parse(id)))
            if (!potential.isEmpty) {
                return potential
            }
        }
        return fallback
    }


    fun subId(id: ResourceLocation): String = "${id.namespace}/${id.path}"
    fun subId(block: Block): String = subId(BuiltInRegistries.BLOCK.getKey(block))
    fun subId(item: Item): String = subId(BuiltInRegistries.ITEM.getKey(item))
    fun subId(fluid: Fluid): String = subId(BuiltInRegistries.FLUID.getKey(fluid))

    fun setPotion(stack: ItemStack, potion: Potion): ItemStack {
        stack.update(
            DataComponents.POTION_CONTENTS, PotionContents.EMPTY,
            BuiltInRegistries.POTION.wrapAsHolder(potion), PotionContents::withPotion
        )
        return stack
    }

    fun basicWorld(
        left: EmiIngredient, right: EmiIngredient, output: EmiStack, id: ResourceLocation, catalyst: Boolean = true,
    ): EmiRecipe {
        return EmiWorldInteractionRecipe.builder()
            .id(id)
            .leftInput(left)
            .rightInput(right, catalyst)
            .output(output)
            .build()
    }

    fun safely(name: String, runnable: Runnable) {
        try {
            runnable.run()
        } catch (e: Throwable) {
            log.warn("Exception thrown when reloading $name step in DnD EMI plugin", e)
        }
    }

    fun EmiRegistry.addRecipeSafe(id: ResourceLocation, supplier: (ResourceLocation) -> EmiRecipe) {
        try {
            addRecipe(supplier.invoke(id))
        } catch (e: Throwable) {
            log.warn("Exception thrown when parsing EMI recipe ($id)", e)
        }
    }

    fun synthetic(type: String, name: String): ResourceLocation = id("/$type/$name")

    fun hiddenFromRecipeViewers(): Set<Item> {
        return BuiltInRegistries.ITEM.getTag(ConventionalItemTags.HIDDEN_FROM_RECIPE_VIEWERS)
            .getOrNull()
            ?.stream()
            ?.map { it.value() }
            ?.toList()
            ?.toSet() ?: setOf()
    }

}