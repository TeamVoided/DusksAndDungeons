package org.teamvoided.dusk_autumn.item

import net.minecraft.item.FoodComponent
import net.minecraft.item.FoodComponents

class DuskFoodComponents: FoodComponents(){
    companion object{
        val GOLDEN_BEETROOT: FoodComponent = FoodComponent.Builder().hunger(5).saturationModifier(1.6f).build()
        val MOONBERRIES = FoodComponent.Builder().hunger(4).saturationModifier(0.3f).build()
    }
}