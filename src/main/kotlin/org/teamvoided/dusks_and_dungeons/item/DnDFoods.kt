package org.teamvoided.dusks_and_dungeons.item

import net.minecraft.world.food.FoodProperties

object DnDFoods {

    val GOLDEN_BEETROOT = food(5, 1.6f)
    val MOONBERRIES = food(4, 0.3f)
    val CORN = food(4, 0.6f)

    fun food(nutrition: Int, saturation: Float): FoodProperties {
        return FoodProperties.Builder()
            .nutrition(nutrition)
            .saturationModifier(saturation)
            .build()
    }

}