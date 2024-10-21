package org.teamvoided.dusk_autumn.item

import net.minecraft.entity.player.HungerConstants
import net.minecraft.item.FoodComponent
import java.util.*

@Suppress("HasPlatformType", "MagicNumber")
object DnDFoodComponents {
    val GOLDEN_BEETROOT = FoodComponent.Builder().hunger(5).saturation(1.6f).build()
    val MOONBERRIES = FoodComponent.Builder().hunger(4).saturation(0.3f).build()
    val CORN = FoodComponent.Builder().hunger(4).saturation(0.6f).build()

    fun foodComponent(hunger: Int, saturation: Float, eatSeconds: Float): FoodComponent {
        val saturationButComponent: Float = HungerConstants.calculateSaturation(hunger, saturation)
        return FoodComponent(hunger, saturationButComponent, false, eatSeconds, Optional.empty(), listOf());
    }
    //1.6
}