package org.teamvoided.dusk_autumn.item

import net.minecraft.entity.player.HungerConstants
import net.minecraft.item.FoodComponent
import java.util.*

@Suppress("HasPlatformType", "MagicNumber")
object DnDFoodComponents {
    val GOLDEN_BEETROOT = FoodComponent.Builder().hunger(5).saturation(1.6f).build()
    val MOONBERRIES = FoodComponent.Builder().hunger(4).saturation(0.3f).build()
    val CORN = FoodComponent.Builder().hunger(4).saturation(0.6f).build()

//    val ROCK_CANDY_8 = FoodComponent.Builder().hunger(8).saturation(0.8f).build() //full block
//    val ROCK_CANDY_6 = FoodComponent.Builder().hunger(6).saturation(0.6f).build() //stair and wall block
//    val ROCK_CANDY_4 = FoodComponent.Builder().hunger(4).saturation(0.4f).build() //slab block

    val ROCK_CANDY_8 = foodComponent(8, 0.8f, 3f) //full block
    val ROCK_CANDY_6 = foodComponent(6, 0.6f, 2f) //stair and wall block
    val ROCK_CANDY_4 = foodComponent(4, 0.4f, 1.6f) //slab block
    val ROCK_CANDY_2 = foodComponent(2, 0.2f, 0.8f) //shard item
    //YOU CANT CUSTOMIZE EAT TIME AAAAAAAAAAAAAAA

    fun foodComponent(hunger: Int, saturation: Float, eatSeconds: Float): FoodComponent {
        val saturationButComponent: Float = HungerConstants.calculateSaturation(hunger, saturation)
        return FoodComponent(hunger, saturationButComponent, false, eatSeconds, Optional.empty(), listOf());
    }
    //1.6
}