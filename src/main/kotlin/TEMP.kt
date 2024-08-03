//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package net.minecraft.item

open class ToolItem(val material: ToolMaterial, settings: Settings) : Item(
    settings.maxDamage(
        material.durability
    )
) {
    override fun getEnchantability(): Int {
        return material.enchantability
    }

    override fun canRepair(stack: ItemStack, ingredient: ItemStack): Boolean {
        return material.repairIngredient.test(ingredient) || super.canRepair(stack, ingredient)
    }
}