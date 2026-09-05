package org.teamvoided.dusks_and_dungeons.compat

import dev.emi.emi.api.recipe.EmiRecipe
import dev.emi.emi.api.recipe.EmiWorldInteractionRecipe
import dev.emi.emi.api.stack.EmiIngredient
import dev.emi.emi.api.stack.EmiStack
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.core.component.DataComponents
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.alchemy.Potion
import net.minecraft.world.item.alchemy.PotionContents
import net.minecraft.world.level.block.Block
import org.teamvoided.dusks_and_dungeons.util.tag
import kotlin.jvm.optionals.getOrNull

val AXE_TAGS = mutableListOf(
    "minecraft:axes",
    "c:axes",
    "c:tools/axes",
    "fabric:axes",
    "forge:tools/axes"
).map { Registries.ITEM.tag(ResourceLocation.parse(it)) }

val SHEARS_TAGS = mutableListOf(
    "c:tools/shear",
    "c:tools/shears",
).map { Registries.ITEM.tag(ResourceLocation.parse(it)) }

fun getAxes(): EmiIngredient = damagedTool(getPreferredTag(AXE_TAGS, EmiStack.of(Items.IRON_AXE)), 1)
fun getShears(): EmiIngredient = damagedTool(getPreferredTag(SHEARS_TAGS, EmiStack.of(Items.SHEARS)), 1)

fun damagedTool(tool: EmiIngredient, damage: Int): EmiIngredient {
    for (stack in tool.emiStacks) {
        val rawStack = stack.itemStack.copy()
        rawStack.damageValue = damage
        stack.remainder = EmiStack.of(rawStack)
    }
    return tool
}

fun getPreferredTag(candidates: List<TagKey<Item>>, fallback: EmiIngredient): EmiIngredient {
    for (tag in candidates) {
        val ingredient = EmiIngredient.of(tag)
        if (!ingredient.isEmpty) {
            return ingredient
        }
    }
    return fallback
}


fun subId(id: ResourceLocation): String = "${id.namespace}/${id.path}"
fun subId(block: Block): String = subId(BuiltInRegistries.BLOCK.getKey(block))
fun subId(item: Item): String = subId(BuiltInRegistries.ITEM.getKey(item))
//fun subId(fluid: Fluid): String = subId(BuiltInRegistries.FLUID.getKey(fluid))

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


fun hiddenFromRecipeViewers(): Set<Item> {
    return BuiltInRegistries.ITEM.getTag(ConventionalItemTags.HIDDEN_FROM_RECIPE_VIEWERS)
        .getOrNull()
        ?.stream()
        ?.map { it.value() }
        ?.toList()
        ?.toSet() ?: setOf()
}