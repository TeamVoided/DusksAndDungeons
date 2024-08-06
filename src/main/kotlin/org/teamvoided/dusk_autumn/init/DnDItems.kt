package org.teamvoided.dusk_autumn.init

import net.minecraft.block.Block
import net.minecraft.block.dispenser.DispenserBlock
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.DyedColorComponent
import net.minecraft.item.*
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Rarity
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.item.ChillChargeItem
import org.teamvoided.dusk_autumn.item.DnDFoodComponents
import org.teamvoided.dusk_autumn.item.HeadDecorationItem
import org.teamvoided.dusk_autumn.item.FunnyIceSword


@Suppress("unused", "MemberVisibilityCanBePrivate")
object DnDItems {
    val ITEMS = mutableListOf<Item>()

    val CASCADE_DOOR = register("cascade_door", TallBlockItem(DnDBlocks.CASCADE_DOOR, Item.Settings()))
    val BLUE_DOOR = register("blue_door", TallBlockItem(DnDBlocks.BLUE_DOOR, Item.Settings()))
    val CASCADE_SIGN = register(
        "cascade_sign",
        SignItem(Item.Settings().maxCount(16), DnDBlocks.CASCADE_SIGN, DnDBlocks.CASCADE_WALL_SIGN)
    )
    val CASCADE_HANGING_SIGN = register(
        "cascade_hanging_sign",
        HangingSignItem(
            DnDBlocks.CASCADE_HANGING_SIGN, DnDBlocks.CASCADE_WALL_HANGING_SIGN,
            Item.Settings().maxCount(16)
        )
    )
    val BONEWOOD_DOOR = register("bonewood_door", TallBlockItem(DnDBlocks.BONEWOOD_DOOR, Item.Settings()))
    val WITHERING_BONEWOOD_DOOR =
        register("withering_bonewood_door", TallBlockItem(DnDBlocks.WITHERING_BONEWOOD_DOOR, Item.Settings()))

    val FARMERS_HAT = register(
        "farmers_hat",
        HeadDecorationItem(
            Item.Settings().maxCount(1).component(DataComponentTypes.DYED_COLOR, DyedColorComponent(0xb26c20, true))
        )
    )
    val WILD_WHEAT = register("wild_wheat", TallBlockItem(DnDBlocks.WILD_WHEAT, Item.Settings()))
    val GOLDEN_BEETROOT = register(
        "golden_beetroot",
        AliasedBlockItem(
            DnDBlocks.GOLDEN_BEETROOTS, Item.Settings().food(DnDFoodComponents.GOLDEN_BEETROOT)
        )
    )
    val MOONBERRY_VINELET =
        register("moonberry_vinelet", AliasedBlockItem(DnDBlocks.MOONBERRY_VINELET, Item.Settings()))
    val MOONBERRIES = register("moonberries", Item((Item.Settings()).food(DnDFoodComponents.MOONBERRIES)))

    @JvmField
    val BLACKSTONE_SWORD = register(
        "blackstone_sword", (SwordItem(
            ToolMaterials.STONE,
            (Item.Settings()).attributeModifiersComponent(
                SwordItem.createAttributes(
                    ToolMaterials.STONE,
                    3,
                    -2.4f
                )
            )
        ))
    )
    val BLACKSTONE_PICKAXE = register(
        "blackstone_pickaxe", (PickaxeItem(
            ToolMaterials.STONE,
            (Item.Settings()).attributeModifiersComponent(
                PickaxeItem.createAttributes(
                    ToolMaterials.STONE,
                    1.0f,
                    -2.8f
                )
            )
        ))
    )

    @JvmField
    val BLACKSTONE_AXE = register(
        "blackstone_axe", (AxeItem(
            ToolMaterials.STONE,
            (Item.Settings()).attributeModifiersComponent(
                AxeItem.createAttributes(
                    ToolMaterials.STONE,
                    7.0f,
                    -3.2f
                )
            )
        ))
    )
    val BLACKSTONE_SHOVEL = register(
        "blackstone_shovel", (ShovelItem(
            ToolMaterials.STONE,
            (Item.Settings()).attributeModifiersComponent(
                ShovelItem.createAttributes(
                    ToolMaterials.STONE,
                    1.5f,
                    -3.0f
                )
            )
        ))
    )
    val BLACKSTONE_HOE = register(
        "blackstone_hoe", (HoeItem(
            ToolMaterials.STONE,
            (Item.Settings()).attributeModifiersComponent(
                HoeItem.createAttributes(
                    ToolMaterials.STONE,
                    -1.0f,
                    -2.0f
                )
            )
        ))
    )

    val FREEZE_ROD = register("freeze_rod", Item(Item.Settings()))
    val CHILL_CHARGE = register("chill_charge", ChillChargeItem(Item.Settings()))
    val ICE_SWORD = register(
        "ice_sword", FunnyIceSword(
            Item.Settings().rarity(Rarity.EPIC)
                .component(DataComponentTypes.TOOL, FunnyIceSword.createToolComponent())
                .attributeModifiersComponent(FunnyIceSword.createAttributes())
        )
    )

    fun init() {
        DispenserBlock.registerBehavior(CHILL_CHARGE)
    }

    fun register(id: String, item: Item): Item {
        val regItem = Registry.register(Registries.ITEM, id(id), item)
        ITEMS.add(regItem)
        return regItem
    }

    fun BlockItem(block: Block) = BlockItem(block, Item.Settings())
    private fun createRegistryKey(name: String): RegistryKey<ItemGroup> {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, id(name))
    }
}