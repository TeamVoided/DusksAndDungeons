package org.teamvoided.dusks_and_dungeons.item

import net.minecraft.world.food.FoodConstants
import net.minecraft.world.food.FoodProperties
import java.util.*

@Suppress("HasPlatformType", "MagicNumber")
object DnDFoodComponents {
    val GOLDEN_BEETROOT = FoodProperties.Builder().nutrition(5).saturationModifier(1.6f).build()
    val MOONBERRIES = FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build()
    val CORN = FoodProperties.Builder().nutrition(4).saturationModifier(0.6f).build()

    fun foodComponent(hunger: Int, saturation: Float, eatSeconds: Float): FoodProperties {
        val saturationButComponent: Float = FoodConstants.saturationByModifier(hunger, saturation)
        return FoodProperties(hunger, saturationButComponent, false, eatSeconds, Optional.empty(), listOf());
    }
    //1.6
}