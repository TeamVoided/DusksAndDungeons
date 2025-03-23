package org.teamvoided.dusks_and_dungeons.init

import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.AttributeModifiersComponent
import net.minecraft.component.type.DyedColorComponent
import net.minecraft.item.*
import net.minecraft.item.Item.Settings
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.block.DnDPumpkinBlock.Companion.setSeeds
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.GLOOM_PUMPKIN
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.LANTERN_PUMPKIN
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.MOSSKIN_PUMPKIN
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.PALE_PUMPKIN
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.SMALL_GLOOM_PUMPKIN
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.SMALL_LANTERN_PUMPKIN
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.SMALL_MOSSKIN_PUMPKIN
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.SMALL_PALE_PUMPKIN
import org.teamvoided.dusks_and_dungeons.item.DnDFoodComponents
import org.teamvoided.dusks_and_dungeons.item.ScarecrowItem
import org.teamvoided.dusks_and_dungeons.item.TripleTallBlockItem
import org.teamvoided.voidlib.helpers.item.EquipableItem


@Suppress("unused", "MemberVisibilityCanBePrivate")
object DnDItems {
    val ITEMS = mutableListOf<Item>()
    val EVIL_ITEMS = mutableSetOf<Item>()

    val CASCADE_DOOR = register("cascade_door", TallBlockItem(DnDBlocks.CASCADE_DOOR, Settings()))
    val BLUE_DOOR = register("blue_door", TallBlockItem(DnDBlocks.BLUE_DOOR, Settings()))
    val CASCADE_SIGN = register(
        "cascade_sign", SignItem(countSettings(16), DnDBlocks.CASCADE_SIGN, DnDBlocks.CASCADE_WALL_SIGN)
    )
    val CASCADE_HANGING_SIGN = register(
        "cascade_hanging_sign",
        HangingSignItem(DnDBlocks.CASCADE_HANGING_SIGN, DnDBlocks.CASCADE_WALL_HANGING_SIGN, countSettings(16))
    )
    val FARMERS_HAT = register(
        "farmers_hat", EquipableItem(
            countSettings(1).component(DataComponentTypes.DYED_COLOR, DyedColorComponent(0xb26c20, true))
        )
    )
    val SCARECROW_ITEM = register("scarecrow", ScarecrowItem(countSettings(16)))
    val WILD_WHEAT = register("wild_wheat", TallBlockItem(DnDBlocks.WILD_WHEAT, Settings()))

    val LANTERN_PUMPKIN_SEEDS =
        register("lantern_pumpkin_seeds", AliasedBlockItem(DnDBlocks.LANTERN_PUMPKIN_STEM, Settings()))
    val MOSSKIN_PUMPKIN_SEEDS =
        register("mosskin_pumpkin_seeds", AliasedBlockItem(DnDBlocks.MOSSKIN_PUMPKIN_STEM, Settings()))
    val PALE_PUMPKIN_SEEDS =
        register("pale_pumpkin_seeds", AliasedBlockItem(DnDBlocks.PALE_PUMPKIN_STEM, Settings()))
    val GLOOM_PUMPKIN_SEEDS =
        register("gloom_pumpkin_seeds", AliasedBlockItem(DnDBlocks.GLOOM_PUMPKIN_STEM, Settings()))

    val CORN_KERNELS = register("corn_kernels", AliasedBlockItem(DnDBlocks.CORN_CROP, Settings()))
    val CORN_STALK = register("corn_stalk", TripleTallBlockItem(DnDBlocks.CORN, Settings()))
    val CORN = register("corn", Item(Settings().food(DnDFoodComponents.CORN)))
    val CORN_SYRUP_BOTTLE = register(
        "corn_syrup_bottle", HoneyBottleItem(
            Settings().recipeRemainder(Items.GLASS_BOTTLE).food(FoodComponents.HONEY_BOTTLE).maxCount(16)
        )
    )

    val GOLDEN_BEETROOT = register(
        "golden_beetroot",
        AliasedBlockItem(DnDBlocks.GOLDEN_BEETROOTS, Settings().food(DnDFoodComponents.GOLDEN_BEETROOT))
    )

    val MOONBERRY_VINELET = register("moonberry_vinelet", AliasedBlockItem(DnDBlocks.MOONBERRY_VINELET, Settings()))
    val MOONBERRIES = register("moonberries", Item((Settings()).food(DnDFoodComponents.MOONBERRIES)))

    @JvmField
    val BLACKSTONE_SWORD = register(
        "blackstone_sword", SwordItem(
            ToolMaterials.STONE, attributeSettings(
                SwordItem.createAttributes(ToolMaterials.STONE, 3, -2.4f)
            )
        )
    )
    val BLACKSTONE_PICKAXE = register(
        "blackstone_pickaxe", PickaxeItem(
            ToolMaterials.STONE, attributeSettings(
                PickaxeItem.createAttributes(ToolMaterials.STONE, 1.0f, -2.8f)
            )
        )
    )

    @JvmField
    val BLACKSTONE_AXE = register(
        "blackstone_axe", AxeItem(
            ToolMaterials.STONE, attributeSettings(
                AxeItem.createAttributes(ToolMaterials.STONE, 7.0f, -3.2f)
            )
        )
    )
    val BLACKSTONE_SHOVEL = register(
        "blackstone_shovel", ShovelItem(
            ToolMaterials.STONE, attributeSettings(
                ShovelItem.createAttributes(ToolMaterials.STONE, 1.5f, -3.0f)
            )
        )
    )
    val BLACKSTONE_HOE = register(
        "blackstone_hoe", HoeItem(
            ToolMaterials.STONE, attributeSettings(
                HoeItem.createAttributes(ToolMaterials.STONE, -1.0f, -2.0f)
            )
        )
    )

    fun init() {
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

    fun attributeSettings(comp: AttributeModifiersComponent): Settings = Settings().attributeModifiersComponent(comp)
    fun countSettings(count: Int): Settings = Settings().maxCount(count)

    // TODO move to voidlib
    @JvmField
    val CUSTOM_STATS = listOf(id("base_block_range"), id("base_entity_range"))
}
