package org.teamvoided.dusks_and_dungeons.init

import net.minecraft.core.Registry
import net.minecraft.core.component.DataComponents
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.food.Foods
import net.minecraft.world.item.*
import net.minecraft.world.item.Item.Properties
import net.minecraft.world.item.alchemy.PotionContents
import net.minecraft.world.item.component.DyedItemColor
import net.minecraft.world.item.component.ItemAttributeModifiers
import net.minecraft.world.level.block.DispenserBlock
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.init.misc.DnDToolMaterials
import org.teamvoided.dusks_and_dungeons.item.DnDFoodComponents
import org.teamvoided.dusks_and_dungeons.item.PlaceInFluidBlockItem
import org.teamvoided.dusks_and_dungeons.item.ScarecrowItem
import org.teamvoided.dusks_and_dungeons.item.TripleTallBlockItem
import org.teamvoided.dusks_and_dungeons.item.potion.CustomGlassBottleItem
import org.teamvoided.dusks_and_dungeons.item.potion.TintedLingeringPotionItem
import org.teamvoided.dusks_and_dungeons.item.potion.TintedPotionItem
import org.teamvoided.dusks_and_dungeons.item.potion.TintedSplashPotionItem
import org.teamvoided.dusks_and_dungeons.util.ensureUnique
import org.teamvoided.dusks_and_dungeons.util.getModEntries
import org.teamvoided.dusks_and_dungeons.util.tellWitnessesThatIWasMurdered
import org.teamvoided.voidlib.helpers.item.EquipableItem


object DnDItems {

    val ITEMS get() = getModEntries(BuiltInRegistries.ITEM)

    val EVIL_ITEMS = mutableSetOf<Item>()

    val CASCADE_DOOR = register("cascade_door", DoubleHighBlockItem(DnDBlocks.CASCADE_DOOR, Properties()))
    val BLUE_DOOR = register("blue_door", DoubleHighBlockItem(DnDBlocks.BLUE_DOOR, Properties()))
    val CASCADE_SIGN = register(
        "cascade_sign", SignItem(countSettings(16), DnDBlocks.CASCADE_SIGN, DnDBlocks.CASCADE_WALL_SIGN)
    )
    val CASCADE_HANGING_SIGN = register(
        "cascade_hanging_sign",
        HangingSignItem(DnDBlocks.CASCADE_HANGING_SIGN, DnDBlocks.CASCADE_WALL_HANGING_SIGN, countSettings(16))
    )

    val SYPIA_DOOR = register("sypia_door", DoubleHighBlockItem(DnDBlocks.SYPIA_DOOR, Properties()))
    val SYPIA_SIGN = register(
        "sypia_sign", SignItem(countSettings(16), DnDBlocks.SYPIA_SIGN, DnDBlocks.SYPIA_WALL_SIGN)
    )
    val SYPIA_HANGING_SIGN = register(
        "sypia_hanging_sign",
        HangingSignItem(DnDBlocks.SYPIA_HANGING_SIGN, DnDBlocks.SYPIA_WALL_HANGING_SIGN, countSettings(16))
    )

    val VERDANT_DOOR = register("verdant_door", DoubleHighBlockItem(DnDBlocks.VERDANT_DOOR, Properties()))
        .tellWitnessesThatIWasMurdered()
    val VERDANT_SIGN = register(
        "verdant_sign", SignItem(countSettings(16), DnDBlocks.VERDANT_SIGN, DnDBlocks.VERDANT_WALL_SIGN)
    ).tellWitnessesThatIWasMurdered()
    val VERDANT_HANGING_SIGN = register(
        "verdant_hanging_sign",
        HangingSignItem(DnDBlocks.VERDANT_HANGING_SIGN, DnDBlocks.VERDANT_WALL_HANGING_SIGN, countSettings(16))
    ).tellWitnessesThatIWasMurdered()

    val FARMERS_HAT = register(
        "farmers_hat", EquipableItem(
            countSettings(1).component(DataComponents.DYED_COLOR, DyedItemColor(0xb26c20, true))
        )
    )
    val SCARECROW_ITEM = register("scarecrow", ScarecrowItem(countSettings(16))).tellWitnessesThatIWasMurdered()
    val WILD_WHEAT = register("wild_wheat", DoubleHighBlockItem(DnDBlocks.WILD_WHEAT, Properties()))

    val LANTERN_PUMPKIN_SEEDS =
        register("lantern_pumpkin_seeds", ItemNameBlockItem(DnDBlocks.LANTERN_PUMPKIN_STEM, Properties()))
    val MOSSKIN_PUMPKIN_SEEDS =
        register("mosskin_pumpkin_seeds", ItemNameBlockItem(DnDBlocks.MOSSKIN_PUMPKIN_STEM, Properties()))
    val PALE_PUMPKIN_SEEDS =
        register("pale_pumpkin_seeds", ItemNameBlockItem(DnDBlocks.PALE_PUMPKIN_STEM, Properties()))
    val GLOOM_PUMPKIN_SEEDS =
        register("gloom_pumpkin_seeds", ItemNameBlockItem(DnDBlocks.GLOOM_PUMPKIN_STEM, Properties()))

    val CORN_KERNELS = register("corn_kernels", ItemNameBlockItem(DnDBlocks.CORN_CROP, Properties()))
    val CORN_STALK = register("corn_stalk", TripleTallBlockItem(DnDBlocks.CORN, Properties()))
    val CORN = register("corn", Item(Properties().food(DnDFoodComponents.CORN)))
    val CORN_SYRUP_BOTTLE = register(
        "corn_syrup_bottle", HoneyBottleItem(
            Properties().craftRemainder(Items.GLASS_BOTTLE).food(Foods.HONEY_BOTTLE).stacksTo(16)
        )
    )

    val GOLDEN_BEETROOT = register(
        "golden_beetroot",
        ItemNameBlockItem(DnDBlocks.GOLDEN_BEETROOTS, Properties().food(DnDFoodComponents.GOLDEN_BEETROOT))
    )

    val MOONBERRY_VINELET = register("moonberry_vinelet", ItemNameBlockItem(DnDBlocks.MOONBERRY_VINELET, Properties()))
    val MOONBERRIES = register("moonberries", Item((Properties()).food(DnDFoodComponents.MOONBERRIES)))

    val BIG_SCAFFOLDING = register("big_scaffolding", ScaffoldingBlockItem(DnDBlocks.BIG_SCAFFOLDING, Properties()))

    val BRITTLE_LAVASPONGE = register(
        "brittle_lavasponge",
        PlaceInFluidBlockItem(PlaceInFluidBlockItem.LAVA, DnDBlocks.BRITTLE_LAVASPONGE, Properties())
    )
        .tellWitnessesThatIWasMurdered()

    val FUSED_LAVASPONGE = register(
        "fused_lavasponge", BlockItem(DnDBlocks.FUSED_LAVASPONGE, Properties().fireResistant())
    )
        .tellWitnessesThatIWasMurdered()

    val LAVASPONGE = register(
        "lavasponge",
        PlaceInFluidBlockItem(PlaceInFluidBlockItem.LAVA, DnDBlocks.LAVASPONGE, Properties().fireResistant())
    )
        .tellWitnessesThatIWasMurdered()

    // LAVASPONGE has to be before GLOWING_LAVASPONGE or
    // the `.craftRemainder(DnDBlocks.LAVASPONGE.asItem())` will cause it to return air, which will brick the game
    val GLOWING_LAVASPONGE = register(
        "glowing_lavasponge",
        BlockItem(
            DnDBlocks.GLOWING_LAVASPONGE,
            Properties().fireResistant().craftRemainder(DnDBlocks.LAVASPONGE.asItem())
        )
    )
        .tellWitnessesThatIWasMurdered()

    val RACCOON_SPAWN_EGG = register(
        "raccoon_spawn_egg",
        SpawnEggItem(
            DnDEntities.RACCOON,
            0x536174,
            0x191d22,
            Properties()
        )
    ).tellWitnessesThatIWasMurdered()

    // region Blackstone Tools
    @JvmField
    val BLACKSTONE_SWORD = register(
        "blackstone_sword",
        SwordItem(DnDToolMaterials.BLACKSTONE, attributeSettings(SwordItem.createAttributes(Tiers.STONE, 3, -2.4f)))
    )

    val BLACKSTONE_PICKAXE = register(
        "blackstone_pickaxe",
        PickaxeItem(
            DnDToolMaterials.BLACKSTONE,
            attributeSettings(PickaxeItem.createAttributes(Tiers.STONE, 1.0f, -2.8f))
        )
    )

    @JvmField
    val BLACKSTONE_AXE = register(
        "blackstone_axe",
        AxeItem(DnDToolMaterials.BLACKSTONE, attributeSettings(AxeItem.createAttributes(Tiers.STONE, 7.0f, -3.2f)))
    )
    val BLACKSTONE_SHOVEL = register(
        "blackstone_shovel",
        ShovelItem(
            DnDToolMaterials.BLACKSTONE,
            attributeSettings(ShovelItem.createAttributes(Tiers.STONE, 1.5f, -3.0f))
        )
    )
    val BLACKSTONE_HOE = register(
        "blackstone_hoe",
        HoeItem(DnDToolMaterials.BLACKSTONE, attributeSettings(HoeItem.createAttributes(Tiers.STONE, -1.0f, -2.0f)))
    )
    // endregion

    val TINTED_POTION = register("tinted_potion", TintedPotionItem(potionProps()))
    val TINTED_GLASS_BOTTLE = register("tinted_glass_bottle", CustomGlassBottleItem(TINTED_POTION, Properties()))
    val TINTED_SPLASH_POTION = register("tinted_splash_potion", TintedSplashPotionItem(potionProps()))
    val TINTED_LINGERING_POTION = register("tinted_lingering_potion", TintedLingeringPotionItem(potionProps()))

    fun init() {
        DispenserBlock.registerProjectileBehavior(TINTED_SPLASH_POTION)
        DispenserBlock.registerProjectileBehavior(TINTED_LINGERING_POTION)
    }

    fun register(name: String, item: Item): Item {
        val id = id(name)
        ensureUnique(id, BuiltInRegistries.ITEM)
        return Registry.register(BuiltInRegistries.ITEM, id, item)
    }

    fun potionProps(): Properties {
        return Properties().stacksTo(1).component(DataComponents.POTION_CONTENTS, PotionContents.EMPTY)
    }

    fun attributeSettings(comp: ItemAttributeModifiers): Properties = Properties().attributes(comp)
    fun countSettings(count: Int): Properties = Properties().stacksTo(count)

    // TODO move to voidlib
    @JvmField
    val CUSTOM_STATS = listOf(id("base_block_range"), id("base_entity_range"))
}
