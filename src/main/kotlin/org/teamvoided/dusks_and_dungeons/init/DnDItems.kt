package org.teamvoided.dusks_and_dungeons.init

import net.minecraft.core.Registry
import net.minecraft.core.component.DataComponents
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.world.food.Foods
import net.minecraft.world.item.*
import net.minecraft.world.item.Item.Properties
import net.minecraft.world.item.alchemy.PotionContents
import net.minecraft.world.item.component.DyedItemColor
import net.minecraft.world.level.block.DispenserBlock
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.init.misc.DnDToolMaterials
import org.teamvoided.dusks_and_dungeons.item.DnDFoods
import org.teamvoided.dusks_and_dungeons.item.PlaceInFluidBlockItem.Companion.placeInLavaItem
import org.teamvoided.dusks_and_dungeons.item.ScarecrowItem
import org.teamvoided.dusks_and_dungeons.item.TripleTallBlockItem
import org.teamvoided.dusks_and_dungeons.item.potion.CustomGlassBottleItem
import org.teamvoided.dusks_and_dungeons.item.potion.TintedLingeringPotionItem
import org.teamvoided.dusks_and_dungeons.item.potion.TintedPotionItem
import org.teamvoided.dusks_and_dungeons.item.potion.TintedSplashPotionItem
import org.teamvoided.dusks_and_dungeons.util.ensureUnique
import org.teamvoided.dusks_and_dungeons.util.getModEntries
import org.teamvoided.dusks_and_dungeons.util.key
import org.teamvoided.dusks_and_dungeons.util.tellWitnessesThatIWasMurdered
import org.teamvoided.voidlib.helpers.item.EquipableItem
import java.util.function.Consumer
import java.util.function.Function
import net.minecraft.world.item.ItemNameBlockItem as NameBlockItem


object DnDItems {

    val ITEMS get() = getModEntries(BuiltInRegistries.ITEM)

    val EVIL_ITEMS = mutableSetOf<Item>()

    val CASCADE_DOOR = register("cascade_door", { DoubleHighBlockItem(DnDBlocks.CASCADE_DOOR, it) })
    val BLUE_DOOR = register("blue_door", { DoubleHighBlockItem(DnDBlocks.BLUE_DOOR, it) })
    val CASCADE_SIGN = register("cascade_sign", { SignItem(it, DnDBlocks.CASCADE_SIGN, DnDBlocks.CASCADE_WALL_SIGN) }) {
        it.stacksTo(16)
    }
    val CASCADE_HANGING_SIGN = register(
        "cascade_hanging_sign",
        { HangingSignItem(DnDBlocks.CASCADE_HANGING_SIGN, DnDBlocks.CASCADE_WALL_HANGING_SIGN, it) }
    ) {
        it.stacksTo(16)
    }

    val SYPIA_DOOR = register("sypia_door", { DoubleHighBlockItem(DnDBlocks.SYPIA_DOOR, it) })
    val SYPIA_SIGN = register("sypia_sign", { SignItem(it, DnDBlocks.SYPIA_SIGN, DnDBlocks.SYPIA_WALL_SIGN) }) {
        it.stacksTo(16)
    }
    val SYPIA_HANGING_SIGN = register(
        "sypia_hanging_sign", { HangingSignItem(DnDBlocks.SYPIA_HANGING_SIGN, DnDBlocks.SYPIA_WALL_HANGING_SIGN, it) }
    ) {
        it.stacksTo(16)
    }

    val VERDANT_DOOR = register("verdant_door", { DoubleHighBlockItem(DnDBlocks.VERDANT_DOOR, it) })
    val VERDANT_SIGN = register("verdant_sign", { SignItem(it, DnDBlocks.VERDANT_SIGN, DnDBlocks.VERDANT_WALL_SIGN) }) {
        it.stacksTo(16)
    }
    val VERDANT_HANGING_SIGN = register(
        "verdant_hanging_sign",
        { HangingSignItem(DnDBlocks.VERDANT_HANGING_SIGN, DnDBlocks.VERDANT_WALL_HANGING_SIGN, it) }
    ) {
        it.stacksTo(16)
    }

    val FARMERS_HAT = register("farmers_hat", ::EquipableItem) {
        it.stacksTo(1).component(DataComponents.DYED_COLOR, DyedItemColor(0xb26c20, true))
    }
    val SCARECROW_ITEM = register("scarecrow", ::ScarecrowItem) {
        it.stacksTo(16)
    }
        .tellWitnessesThatIWasMurdered()
    val WILD_WHEAT = register("wild_wheat", { DoubleHighBlockItem(DnDBlocks.WILD_WHEAT, it) })

    val LANTERN_PUMPKIN_SEEDS = register("lantern_pumpkin_seeds", { NameBlockItem(DnDBlocks.LANTERN_PUMPKIN_STEM, it) })
    val MOSSKIN_PUMPKIN_SEEDS = register("mosskin_pumpkin_seeds", { NameBlockItem(DnDBlocks.MOSSKIN_PUMPKIN_STEM, it) })
    val PALE_PUMPKIN_SEEDS = register("pale_pumpkin_seeds", { NameBlockItem(DnDBlocks.PALE_PUMPKIN_STEM, it) })
    val GLOOM_PUMPKIN_SEEDS = register("gloom_pumpkin_seeds", { NameBlockItem(DnDBlocks.GLOOM_PUMPKIN_STEM, it) })

    val CORN_KERNELS = register("corn_kernels", { NameBlockItem(DnDBlocks.CORN_CROP, it) })
    val CORN_STALK = register("corn_stalk", { TripleTallBlockItem(DnDBlocks.CORN, it) })
    val CORN = register("corn", ::Item) {
        it.food(DnDFoods.CORN)
    }
    val CORN_SYRUP_BOTTLE = register("corn_syrup_bottle", ::HoneyBottleItem) {
        it.craftRemainder(Items.GLASS_BOTTLE)
            .food(Foods.HONEY_BOTTLE)
            .stacksTo(16)
    }

    val GOLDEN_BEETROOT = register("golden_beetroot", { NameBlockItem(DnDBlocks.GOLDEN_BEETROOTS, it) }) {
        it.food(DnDFoods.GOLDEN_BEETROOT)
    }

    val MOONBERRY_VINELET = register("moonberry_vinelet", { NameBlockItem(DnDBlocks.MOONBERRY_VINELET, it) })
    val MOONBERRIES = register("moonberries", ::Item) {
        it.food(DnDFoods.MOONBERRIES)
    }

    val BIG_SCAFFOLDING = register("big_scaffolding", { ScaffoldingBlockItem(DnDBlocks.BIG_SCAFFOLDING, it) })

    val BRITTLE_LAVASPONGE = register("brittle_lavasponge", { placeInLavaItem(DnDBlocks.BRITTLE_LAVASPONGE, it) })
        .tellWitnessesThatIWasMurdered()

    val FUSED_LAVASPONGE = register("fused_lavasponge", { BlockItem(DnDBlocks.FUSED_LAVASPONGE, it) }) {
        it.fireResistant()
    }
        .tellWitnessesThatIWasMurdered()

    val LAVASPONGE = register("lavasponge", { placeInLavaItem(DnDBlocks.LAVASPONGE, it) }) {
        it.fireResistant()
    }
        .tellWitnessesThatIWasMurdered()

    // LAVASPONGE has to be before GLOWING_LAVASPONGE or
    // the `.craftRemainder(DnDBlocks.LAVASPONGE.asItem())` will cause it to return air, which will brick the game
    val GLOWING_LAVASPONGE = register("glowing_lavasponge", { BlockItem(DnDBlocks.GLOWING_LAVASPONGE, it) }) {
        it.fireResistant()
            .craftRemainder(DnDBlocks.LAVASPONGE.asItem())
    }
        .tellWitnessesThatIWasMurdered()

    // region Blackstone Tools
    @JvmField
    val BLACKSTONE_SWORD = register("blackstone_sword", { SwordItem(DnDToolMaterials.BLACKSTONE, it) }) {
        it.attributes(SwordItem.createAttributes(DnDToolMaterials.BLACKSTONE, 3, -2.4f))
    }
    val BLACKSTONE_PICKAXE = register("blackstone_pickaxe", { PickaxeItem(DnDToolMaterials.BLACKSTONE, it) }) {
        it.attributes(PickaxeItem.createAttributes(DnDToolMaterials.BLACKSTONE, 1.0f, -2.8f))
    }

    @JvmField
    val BLACKSTONE_AXE = register("blackstone_axe", { AxeItem(DnDToolMaterials.BLACKSTONE, it) }) {
        it.attributes(AxeItem.createAttributes(DnDToolMaterials.BLACKSTONE, 7.0f, -3.2f))
    }
    val BLACKSTONE_SHOVEL = register("blackstone_shovel", { ShovelItem(DnDToolMaterials.BLACKSTONE, it) }) {
        it.attributes(HoeItem.createAttributes(DnDToolMaterials.BLACKSTONE, 1.5f, -3.0f))
    }
    val BLACKSTONE_HOE = register("blackstone_hoe", { HoeItem(DnDToolMaterials.BLACKSTONE, it) }) {
        it.attributes(HoeItem.createAttributes(DnDToolMaterials.BLACKSTONE, -1.0f, -2.0f))
    }
    // endregion

    val TINTED_POTION = register("tinted_potion", ::TintedPotionItem, ::potion)
    val TINTED_GLASS_BOTTLE = register("tinted_glass_bottle", { CustomGlassBottleItem(TINTED_POTION, it) })
    val TINTED_SPLASH_POTION = register("tinted_splash_potion", ::TintedSplashPotionItem, ::potion)
    val TINTED_LINGERING_POTION = register("tinted_lingering_potion", ::TintedLingeringPotionItem, ::potion)

    fun init() {
        DispenserBlock.registerProjectileBehavior(TINTED_SPLASH_POTION)
        DispenserBlock.registerProjectileBehavior(TINTED_LINGERING_POTION)
    }

    fun register(name: String, item: Function<Properties, Item>, properties: Consumer<Properties>): Item {
        val builder = Properties()
        properties.accept(builder)
        return register(name, item, builder)
    }

    fun register(name: String, item: Function<Properties, Item>, properties: Properties = Properties()): Item {
        val id = Registries.ITEM.key(id(name))
        ensureUnique(id, BuiltInRegistries.ITEM)
//        properties.setId(id) // 1.21.11 code
        return Registry.register(BuiltInRegistries.ITEM, id.location(), item.apply(properties))
    }

    fun potion(props: Properties) {
        props
            .stacksTo(1)
            .component(DataComponents.POTION_CONTENTS, PotionContents.EMPTY)
    }

    // TODO(lib) clean up & move to voidlib
    @JvmField
    val CUSTOM_STATS = listOf(id("base_block_range"), id("base_entity_range"))
}
