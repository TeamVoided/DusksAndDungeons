package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries
import net.minecraft.block.Block
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.DyedColorComponent
import net.minecraft.item.*
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.item.DuskFoodComponents
import org.teamvoided.dusk_autumn.item.FarmersHatItem


@Suppress("unused", "MemberVisibilityCanBePrivate")
object DuskItems {
    val CASCADE_DOOR = register("cascade_door", TallBlockItem(DuskBlocks.CASCADE_DOOR, Item.Settings()))
    val BLUE_DOOR = register("blue_door", TallBlockItem(DuskBlocks.BLUE_DOOR, Item.Settings()))
    val CASCADE_SIGN = register(
        "cascade_sign",
        SignItem(Item.Settings().maxCount(16), DuskBlocks.CASCADE_SIGN, DuskBlocks.CASCADE_WALL_SIGN)
    )
    val CASCADE_HANGING_SIGN = register(
        "cascade_hanging_sign",
        HangingSignItem(
            DuskBlocks.CASCADE_HANGING_SIGN, DuskBlocks.CASCADE_WALL_HANGING_SIGN,
            Item.Settings().maxCount(16)
        )
    )

    val FARMERS_HAT = register(
        "farmers_hat",
        FarmersHatItem(
            Item.Settings().maxCount(1).component(DataComponentTypes.DYED_COLOR, DyedColorComponent(0xb26c20, true))
        )
    )
    val WILD_WHEAT = register("wild_wheat", TallBlockItem(DuskBlocks.WILD_WHEAT, Item.Settings()))
    val GOLDEN_BEETROOT = register(
        "golden_beetroot",
        AliasedBlockItem(
            DuskBlocks.GOLDEN_BEETROOTS, Item.Settings().food(DuskFoodComponents.GOLDEN_BEETROOT)
        )
    )
    val MOONBERRY_VINELET =
        register("moonberry_vinelet", AliasedBlockItem(DuskBlocks.MOONBERRY_VINELET, Item.Settings()))
    val MOONBERRIES = register("moonberries", Item((Item.Settings()).food(DuskFoodComponents.MOONBERRIES)))

//add void util compat
    //???

    fun init() {}

    fun register(id: String, item: Item): Item = Registry.register(Registries.ITEM, id(id), item)

    fun BlockItem(block: Block) = BlockItem(block, Item.Settings())
    private fun createRegistryKey(name: String): RegistryKey<ItemGroup> {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, id(name))
    }
}