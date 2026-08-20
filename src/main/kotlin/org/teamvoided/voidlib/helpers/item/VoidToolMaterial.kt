@file:Suppress("unused")

package org.teamvoided.voidlib.helpers.item

import net.minecraft.tags.BlockTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Tier
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.block.Block

@JvmRecord
data class VoidToolMaterial(
    val durability: Int,
    val miningSpeedMultiplier: Float,
    val attackDamage: Float,
    val incorrectForDropsBlocks: TagKey<Block>,
    val enchantability: Int,
    val repairIngredient: Ingredient,
) : Tier {

    constructor(
        durability: Int, miningSpeedMultiplier: Float, attackDamage: Float,
        incorrectForDropsBlocks: TagKey<Block>, enchantability: Int, repairTag: TagKey<Item>,
    ) : this(
        durability, miningSpeedMultiplier, attackDamage,
        incorrectForDropsBlocks, enchantability, Ingredient.of(repairTag)
    )

    override fun getUses(): Int = durability
    override fun getSpeed(): Float = miningSpeedMultiplier
    override fun getAttackDamageBonus(): Float = attackDamage
    override fun getIncorrectBlocksForDrops(): TagKey<Block> = incorrectForDropsBlocks
    override fun getEnchantmentValue(): Int = enchantability
    override fun getRepairIngredient(): Ingredient = repairIngredient

    companion object {

        fun weaponOnly(
            durability: Int, attackDamage: Float, enchantability: Int, repairTag: TagKey<Item>,
        ): VoidToolMaterial {
            return VoidToolMaterial(
                durability, 6F, attackDamage,
                BlockTags.INCORRECT_FOR_IRON_TOOL, enchantability, repairTag
            )
        }

        fun copyOf(material: Tier, repairTag: TagKey<Item>): VoidToolMaterial {
            return VoidToolMaterial(
                material.uses, material.speed, material.attackDamageBonus,
                material.incorrectBlocksForDrops, material.enchantmentValue, repairTag,
            )
        }

    }
}