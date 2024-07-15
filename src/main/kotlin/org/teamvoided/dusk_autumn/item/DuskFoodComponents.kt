package org.teamvoided.dusk_autumn.item

import net.minecraft.item.FoodComponent

@Suppress("HasPlatformType", "MagicNumber")
object DuskFoodComponents {
    val GOLDEN_BEETROOT  = FoodComponent.Builder().hunger(5).saturation(1.6f).build()
    val MOONBERRIES  = FoodComponent.Builder().hunger(4).saturation(0.3f).build()
}
