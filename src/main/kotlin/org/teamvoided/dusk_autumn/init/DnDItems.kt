package org.teamvoided.dusk_autumn.init

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.dispenser.DispenserBlock
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.DyedColorComponent
import net.minecraft.item.*
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import org.teamvoided.dusk_autumn.init.blocks.DnDFloraBlocks
import org.teamvoided.dusk_autumn.init.blocks.DnDWoodBlocks
import org.teamvoided.dusk_autumn.item.*
import org.teamvoided.dusk_autumn.util.tellWitnessesThatIWasMurdered


@Suppress("unused", "MemberVisibilityCanBePrivate")
object DnDItems {
    val ITEMS = mutableListOf<Item>()
    val EVIL_ITEMS = mutableSetOf<Item>()

    val CASCADE_DOOR = register("cascade_door", TallBlockItem(DnDWoodBlocks.CASCADE_DOOR, Item.Settings()))
    val BLUE_DOOR = register("blue_door", TallBlockItem(DnDWoodBlocks.BLUE_DOOR, Item.Settings()))
    val CASCADE_SIGN = register(
        "cascade_sign",
        SignItem(Item.Settings().maxCount(16), DnDWoodBlocks.CASCADE_SIGN, DnDWoodBlocks.CASCADE_WALL_SIGN)
    )
    val CASCADE_HANGING_SIGN = register(
        "cascade_hanging_sign",
        HangingSignItem(
            DnDWoodBlocks.CASCADE_HANGING_SIGN, DnDWoodBlocks.CASCADE_WALL_HANGING_SIGN,
            Item.Settings().maxCount(16)
        )
    )
    val GALLERY_MAPLE_DOOR =
        register("gallery_maple_door", TallBlockItem(DnDWoodBlocks.GALLERY_MAPLE_DOOR, Item.Settings())).tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_SIGN = register(
        "gallery_maple_sign",
        SignItem(Item.Settings().maxCount(16), DnDWoodBlocks.GALLERY_MAPLE_SIGN, DnDWoodBlocks.GALLERY_MAPLE_WALL_SIGN)
    ).tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_HANGING_SIGN = register(
        "gallery_maple_hanging_sign",
        HangingSignItem(
            DnDWoodBlocks.GALLERY_MAPLE_HANGING_SIGN, DnDWoodBlocks.GALLERY_MAPLE_WALL_HANGING_SIGN,
            Item.Settings().maxCount(16)
        )
    ).tellWitnessesThatIWasMurdered()
    val BONEWOOD_DOOR = register("bonewood_door", TallBlockItem(DnDWoodBlocks.BONEWOOD_DOOR, Item.Settings())).tellWitnessesThatIWasMurdered()
    val WITHERING_BONEWOOD_DOOR =
        register("withering_bonewood_door", TallBlockItem(DnDWoodBlocks.WITHERING_BONEWOOD_DOOR, Item.Settings())).tellWitnessesThatIWasMurdered()

    val FARMERS_HAT = register(
        "farmers_hat", HeadDecorationItem(
            Item.Settings().maxCount(1).component(DataComponentTypes.DYED_COLOR, DyedColorComponent(0xb26c20, true))
        )
    )
    val SCARECROW_ITEM = register(
        "scarecrow", ScarecrowItem(
            Item.Settings().maxCount(16)
        )
    )
    val DIE_ITEM = register(
        "die", DiceItem(
            Item.Settings().maxCount(16).component(DataComponentTypes.DYED_COLOR, DyedColorComponent(0xFFFFFF, true))
        )
    )

    val WATER_FERN = register("water_fern", WaterPlaceableBlockItem(DnDFloraBlocks.WATER_FERN, Item.Settings())).tellWitnessesThatIWasMurdered()
    val WILD_WHEAT = register("wild_wheat", TallBlockItem(DnDFloraBlocks.WILD_WHEAT, Item.Settings()))
    val GOLDEN_BEETROOT = register(
        "golden_beetroot",
        AliasedBlockItem(
            DnDFloraBlocks.GOLDEN_BEETROOTS, Item.Settings().food(DnDFoodComponents.GOLDEN_BEETROOT)
        )
    )
    val MOONBERRY_VINELET =
        register("moonberry_vinelet", AliasedBlockItem(DnDFloraBlocks.MOONBERRY_VINELET, Item.Settings()))
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

    val FREEZE_ROD = register("freeze_rod", Item(Item.Settings())).tellWitnessesThatIWasMurdered()
    val CHILL_CHARGE = register("chill_charge", ChillChargeItem(Item.Settings())).tellWitnessesThatIWasMurdered()
//    val ICE_SWORD = register(
//        "ice_sword", FunnyIceSword(
//            Item.Settings().rarity(Rarity.EPIC)
//                .component(DataComponentTypes.TOOL, FunnyIceSword.createToolComponent())
//                .attributeModifiersComponent(FunnyIceSword.createAttributes())
//        )
//    )

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
