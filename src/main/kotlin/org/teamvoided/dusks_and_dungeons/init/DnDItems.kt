package org.teamvoided.dusks_and_dungeons.init

import net.minecraft.block.dispenser.DispenserBlock
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.AttributeModifiersComponent
import net.minecraft.component.type.DyedColorComponent
import net.minecraft.item.*
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.block.DnDPumpkinBlock.Companion.setSeeds
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.LANTERN_PUMPKIN
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.MOSSKIN_PUMPKIN
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.GLOOM_PUMPKIN
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.PALE_PUMPKIN
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.SMALL_LANTERN_PUMPKIN
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.SMALL_MOSSKIN_PUMPKIN
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.SMALL_GLOOM_PUMPKIN
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.SMALL_PALE_PUMPKIN
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDWoodBlocks
import org.teamvoided.dusks_and_dungeons.item.*
import org.teamvoided.dusks_and_dungeons.util.shh
import org.teamvoided.dusks_and_dungeons.util.tellWitnessesThatIWasMurdered
import org.teamvoided.voidlib.helpers.item.EquipableItem


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
    val FARMERS_HAT = register(
        "farmers_hat", EquipableItem(
            CountSettings(1).component(DataComponentTypes.DYED_COLOR, DyedColorComponent(0xb26c20, true))
        )
    )
    val WILD_WHEAT = register("wild_wheat", TallBlockItem(DnDBlocks.WILD_WHEAT, Item.Settings()))

    val LANTERN_PUMPKIN_SEEDS =
        register("lantern_pumpkin_seeds", AliasedBlockItem(DnDBlocks.LANTERN_PUMPKIN_STEM, Item.Settings()))
    val MOSSKIN_PUMPKIN_SEEDS =
        register("mosskin_pumpkin_seeds", AliasedBlockItem(DnDBlocks.MOSSKIN_PUMPKIN_STEM, Item.Settings()))
    val PALE_PUMPKIN_SEEDS =
        register("pale_pumpkin_seeds", AliasedBlockItem(DnDBlocks.PALE_PUMPKIN_STEM, Item.Settings()))
    val GLOOM_PUMPKIN_SEEDS =
        register("gloom_pumpkin_seeds", AliasedBlockItem(DnDBlocks.GLOOM_PUMPKIN_STEM, Item.Settings()))

    val CORN_KERNELS = register("corn_kernels", AliasedBlockItem(DnDBlocks.CORN_CROP, Item.Settings()))
    val CORN_STALK = register("corn_stalk", TripleTallBlockItem(DnDBlocks.CORN, Item.Settings()))
    val CORN = register("corn", Item((Item.Settings()).food(DnDFoodComponents.CORN)))
    val CORN_SYRUP_BOTTLE = register(
        "corn_syrup_bottle", HoneyBottleItem(
            Item.Settings().recipeRemainder(Items.GLASS_BOTTLE).food(FoodComponents.HONEY_BOTTLE).maxCount(16)
        )
    )

    val GOLDEN_BEETROOT = register(
        "golden_beetroot",
        AliasedBlockItem(DnDBlocks.GOLDEN_BEETROOTS, Item.Settings().food(DnDFoodComponents.GOLDEN_BEETROOT))
    )

    val MOONBERRY_VINELET =
        register("moonberry_vinelet", AliasedBlockItem(DnDBlocks.MOONBERRY_VINELET, Item.Settings()))
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

    // ☢ Experimental ☢
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

    val WITCH_HAT = register("witch_hat", EquipableItem(CountSettings(1))).shh().tellWitnessesThatIWasMurdered()

    @JvmField
    val VILE_WITCH_HAT = register("vile_witch_hat", EquipableItem(CountSettings(1))).shh().tellWitnessesThatIWasMurdered()
    val SCARECROW_ITEM = register("scarecrow", ScarecrowItem(CountSettings(16))).tellWitnessesThatIWasMurdered()
    val DIE_ITEM = register(
        "die", DiceItem(
            CountSettings(16).component(DataComponentTypes.DYED_COLOR, DyedColorComponent(0xFFFFFF, true))
        )
    ).tellWitnessesThatIWasMurdered()

    val WATER_FERN = register("water_fern", WaterPlaceableBlockItem(DnDBlocks.WATER_FERN, Item.Settings()))
        .tellWitnessesThatIWasMurdered()

    val FREEZE_ROD = register("freeze_rod", Item(Item.Settings())).tellWitnessesThatIWasMurdered()
    val CHILL_CHARGE = register("chill_charge", ChillChargeItem(Item.Settings())).tellWitnessesThatIWasMurdered()

    //    val ICE_SWORD = register(
//        "ice_sword", FunnyIceSword(
//            Item.Settings().rarity(Rarity.EPIC)
//                .component(DataComponentTypes.TOOL, FunnyIceSword.createToolComponent())
//                .attributeModifiersComponent(FunnyIceSword.createAttributes())
//        )
//    )
    val WEB_WEAVER =
        register("web_weaver", BowItem(Item.Settings().maxDamage(404))).shh().tellWitnessesThatIWasMurdered()
    val HARVESTER_SCYTHE = register(
        "harvester_scythe", HarvesterScytheItem(AttributeSettings(HarvesterScytheItem.makeAttributes()))
    ).shh().tellWitnessesThatIWasMurdered()
    val BROOM = register("broom", BroomItem(CountSettings(1))).shh().tellWitnessesThatIWasMurdered()

    fun init() {
        DispenserBlock.registerBehavior(CHILL_CHARGE)

        LANTERN_PUMPKIN.setSeeds(LANTERN_PUMPKIN_SEEDS)
        SMALL_LANTERN_PUMPKIN.setSeeds(LANTERN_PUMPKIN_SEEDS)
        MOSSKIN_PUMPKIN.setSeeds(MOSSKIN_PUMPKIN_SEEDS)
        SMALL_MOSSKIN_PUMPKIN.setSeeds(MOSSKIN_PUMPKIN_SEEDS)
        GLOOM_PUMPKIN.setSeeds(GLOOM_PUMPKIN_SEEDS)
        SMALL_GLOOM_PUMPKIN.setSeeds(GLOOM_PUMPKIN_SEEDS)
        PALE_PUMPKIN.setSeeds(PALE_PUMPKIN_SEEDS)
        SMALL_PALE_PUMPKIN.setSeeds(PALE_PUMPKIN_SEEDS)

//        BLOCK_ITEMS.forEach(::register)
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
