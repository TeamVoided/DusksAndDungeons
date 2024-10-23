package org.teamvoided.dusk_autumn.init

import net.minecraft.block.dispenser.DispenserBlock
import org.teamvoided.dusk_autumn.item.TripleTallBlockItem
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.AttributeModifiersComponent
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
import org.teamvoided.dusk_autumn.util.shh
import org.teamvoided.dusk_autumn.util.tellWitnessesThatIWasMurdered


@Suppress("unused", "MemberVisibilityCanBePrivate")
object DnDItems {
    val ITEMS = mutableListOf<Item>()
    val EVIL_ITEMS = mutableSetOf<Item>()

    val CASCADE_DOOR = register("cascade_door", TallBlockItem(DnDWoodBlocks.CASCADE_DOOR, Item.Settings()))
    val BLUE_DOOR = register("blue_door", TallBlockItem(DnDWoodBlocks.BLUE_DOOR, Item.Settings()))
    val CASCADE_SIGN = register(
        "cascade_sign", SignItem(CountSettings(16), DnDWoodBlocks.CASCADE_SIGN, DnDWoodBlocks.CASCADE_WALL_SIGN)
    )
    val CASCADE_HANGING_SIGN = register(
        "cascade_hanging_sign",
        HangingSignItem(DnDWoodBlocks.CASCADE_HANGING_SIGN, DnDWoodBlocks.CASCADE_WALL_HANGING_SIGN, CountSettings(16))
    )
    val GALLERY_MAPLE_DOOR =
        register("gallery_maple_door", TallBlockItem(DnDWoodBlocks.GALLERY_MAPLE_DOOR, Item.Settings()))
            .tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_SIGN = register(
        "gallery_maple_sign",
        SignItem(CountSettings(16), DnDWoodBlocks.GALLERY_MAPLE_SIGN, DnDWoodBlocks.GALLERY_MAPLE_WALL_SIGN)
    ).tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_HANGING_SIGN = register(
        "gallery_maple_hanging_sign", HangingSignItem(
            DnDWoodBlocks.GALLERY_MAPLE_HANGING_SIGN, DnDWoodBlocks.GALLERY_MAPLE_WALL_HANGING_SIGN, CountSettings(16)
        )
    ).tellWitnessesThatIWasMurdered()
    val BONEWOOD_DOOR = register("bonewood_door", TallBlockItem(DnDWoodBlocks.BONEWOOD_DOOR, Item.Settings()))
        .tellWitnessesThatIWasMurdered()
    val WITHERING_BONEWOOD_DOOR =
        register("withering_bonewood_door", TallBlockItem(DnDWoodBlocks.WITHERING_BONEWOOD_DOOR, Item.Settings()))
            .tellWitnessesThatIWasMurdered()

    val FARMERS_HAT = register(
        "farmers_hat", HeadDecorationItem(
            CountSettings(1).component(DataComponentTypes.DYED_COLOR, DyedColorComponent(0xb26c20, true))
        )
    )
    val WITCH_HAT = register("witch_hat", HeadDecorationItem(CountSettings(1))).shh()

    @JvmField
    val VILE_WITCH_HAT = register("vile_witch_hat", HeadDecorationItem(CountSettings(1))).shh()
    val SCARECROW_ITEM = register("scarecrow", ScarecrowItem(CountSettings(16))).tellWitnessesThatIWasMurdered()
    val DIE_ITEM = register(
        "die", DiceItem(
            CountSettings(16).component(DataComponentTypes.DYED_COLOR, DyedColorComponent(0xFFFFFF, true))
        )
    ).tellWitnessesThatIWasMurdered()

    val WATER_FERN = register("water_fern", WaterPlaceableBlockItem(DnDFloraBlocks.WATER_FERN, Item.Settings()))
        .tellWitnessesThatIWasMurdered()
    val WILD_WHEAT = register("wild_wheat", TallBlockItem(DnDFloraBlocks.WILD_WHEAT, Item.Settings()))

    val LANTERN_PUMPKIN_SEEDS =
        register("lantern_pumpkin_seeds", AliasedBlockItem(DnDFloraBlocks.LANTERN_PUMPKIN_STEM, Item.Settings())).shh()
    val MOSSKIN_PUMPKIN_SEEDS =
        register("mosskin_pumpkin_seeds", AliasedBlockItem(DnDFloraBlocks.MOSSKIN_PUMPKIN_STEM, Item.Settings())).shh()
    val PALE_PUMPKIN_SEEDS =
        register("pale_pumpkin_seeds", AliasedBlockItem(DnDFloraBlocks.PALE_PUMPKIN_STEM, Item.Settings())).shh()
    val GLOOM_PUMPKIN_SEEDS =
        register("gloom_pumpkin_seeds", AliasedBlockItem(DnDFloraBlocks.GLOOM_PUMPKIN_STEM, Item.Settings())).shh()

    val CORN_KERNELS = register("corn_kernels", AliasedBlockItem(DnDFloraBlocks.CORN_CROP, Item.Settings())).shh()
    val CORN_STALK = register("corn_stalk", TripleTallBlockItem(DnDFloraBlocks.CORN, Item.Settings())).shh()
    val CORN = register("corn", Item((Item.Settings()).food(DnDFoodComponents.CORN))).shh()
    val CORN_SYRUP_BOTTLE = register(
        "corn_syrup_bottle", HoneyBottleItem(
            Item.Settings().recipeRemainder(Items.GLASS_BOTTLE).food(FoodComponents.HONEY_BOTTLE).maxCount(16)
        )
    ).shh()

    val GOLDEN_BEETROOT = register(
        "golden_beetroot",
        AliasedBlockItem(DnDFloraBlocks.GOLDEN_BEETROOTS, Item.Settings().food(DnDFoodComponents.GOLDEN_BEETROOT))
    )

    val MOONBERRY_VINELET =
        register("moonberry_vinelet", AliasedBlockItem(DnDFloraBlocks.MOONBERRY_VINELET, Item.Settings()))
    val MOONBERRIES = register("moonberries", Item((Item.Settings()).food(DnDFoodComponents.MOONBERRIES)))

    @JvmField
    val BLACKSTONE_SWORD = register(
        "blackstone_sword", SwordItem(
            ToolMaterials.STONE, AttributeSettings(
                SwordItem.createAttributes(ToolMaterials.STONE, 3, -2.4f)
            )
        )
    )
    val BLACKSTONE_PICKAXE = register(
        "blackstone_pickaxe", PickaxeItem(
            ToolMaterials.STONE, AttributeSettings(
                PickaxeItem.createAttributes(ToolMaterials.STONE, 1.0f, -2.8f)
            )
        )
    )

    @JvmField
    val BLACKSTONE_AXE = register(
        "blackstone_axe", AxeItem(
            ToolMaterials.STONE, AttributeSettings(
                AxeItem.createAttributes(ToolMaterials.STONE, 7.0f, -3.2f)
            )
        )
    )
    val BLACKSTONE_SHOVEL = register(
        "blackstone_shovel", ShovelItem(
            ToolMaterials.STONE, AttributeSettings(
                ShovelItem.createAttributes(ToolMaterials.STONE, 1.5f, -3.0f)
            )
        )
    )
    val BLACKSTONE_HOE = register(
        "blackstone_hoe", HoeItem(
            ToolMaterials.STONE, AttributeSettings(
                HoeItem.createAttributes(ToolMaterials.STONE, -1.0f, -2.0f)
            )
        )
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
    val WEB_WEAVER = register("web_weaver", BowItem(Item.Settings().maxDamage(404))).shh()
    val HARVESTER_SCYTHE = register(
        "harvester_scythe", HarvesterScytheItem(AttributeSettings(HarvesterScytheItem.makeAttributes()))
    ).shh()
    val BROOM = register("broom", BroomItem(CountSettings(1))).shh()

    fun init() {
        DispenserBlock.registerBehavior(CHILL_CHARGE)
    }

    fun register(id: String, item: Item): Item {
        val regItem = Registry.register(Registries.ITEM, id(id), item)
        ITEMS.add(regItem)
        return regItem
    }

    private fun createRegistryKey(name: String): RegistryKey<ItemGroup> {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, id(name))
    }

    @Suppress("FunctionName")
    fun AttributeSettings(comp: AttributeModifiersComponent): Item.Settings =
        Item.Settings().attributeModifiersComponent(comp)

    @Suppress("FunctionName")
    fun CountSettings(count: Int): Item.Settings = Item.Settings().maxCount(count)

    @JvmField
    val CUSTOM_STATS = listOf(id("base_block_range"), id("base_entity_range"))
}
