package org.teamvoided.dusks_and_dungeons.data.worldgen

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.key

object DnDConfiguredFeature {

    val OVERGROWN_COBBLESTONE_BOULDER = key("overgrown_cobblestone_boulder")
    val FAIRY_RING_WHITE = key("fairy_ring/white")
    val FAIRY_RING_RED = key("fairy_ring/red")
    val FAIRY_RING_BLUE = key("fairy_ring/blue")
    val FAIRY_RING_ORANGE = key("fairy_ring/orange")
    val FAIRY_RING_WILDFLOWER = key("fairy_ring/wildflower")
    val FAIRY_RING_COLD_WILDFLOWER = key("fairy_ring/cold_wildflower")

    @JvmField
    val FAIRY_RING_CRIMSON = key("fairy_ring/crimson")

    @JvmField
    val FAIRY_RING_WARPED = key("fairy_ring/warped")

    val CASCADE_TREE = key("tree/sapling/cascade_tree")
    val CASCADE_TREE_BEES = key("tree/sapling/cascade_tree_bees")
    val CASCADE_TREE_AUTUMN = key("tree/cascade_tree_autumn")
    val SYPIA_TALL = key("tree/sapling/sypia")
    val SYPIA_TALL_BEES = key("tree/sapling/sypia_tall_bees")
    val SYPIA_TALL_AUTUMN = key("tree/sypia_tall_autumn")
    val DARK_OAK_AUTUMN = key("tree/dark_oak_autumn")
    val ACACIA_AUTUMN = key("tree/acacia_autumn")
    val ACACIA_BUSH_AUTUMN = key("tree/acacia_bush_autumn")
    val DISK_PODZOL = key("disk/disk_podzol")
    val DISK_MUD = key("disk/disk_mud")
    val DISK_RED_SAND = key("disk/disk_red_sand")
    val AUTUMN_WOODS_VEGETATION = key("biome_vegetation/autumn_woods_vegetation")
    val AUTUMN_PASTURES_VEGETATION = key("biome_vegetation/autumn_pastures_vegetation")
    val GOLDEN_VEGETATION = key("biome_vegetation/golden_vegetation")
    val FLOWER_AUTUMN = key("flower/flower_autumn")
    val PATCH_ROSEBUSH = key("flower/patch_rosebush")
    val BLUE_PETALS = key("flower/blue_petals")
    val AUTUMN_FARMLAND = key("autumn_farmland")
    val AUTUMN_FARMLAND_CROPS = key("crops/autumn_farmland_crops")
    val CROPS_WILD_WHEAT = key("crops/wild_wheat")
    val CROPS_WHEAT = key("crops/wheat")
    val CROPS_CARROTS = key("crops/carrots")
    val CROPS_POTATOES = key("crops/potatoes")
    val CROPS_PUMPKIN = key("crops/pumpkins")
    val CROPS_BEETROOTS = key("crops/beetroots")
    val CROPS_GOLDEN_BEETROOTS = key("crops/golden_beetroots")

    val PATCH_PUMPKIN_LANTERN = key("patch_pumpkin/lantern")
    val PATCH_PUMPKIN_MOSSKIN = key("patch_pumpkin/mosskin")
    val PATCH_PUMPKIN_PALE = key("patch_pumpkin/pale")
    val PATCH_PUMPKIN_GLOOM = key("patch_pumpkin/gloom")
    val PATCH_PUMPKIN_EXTRA = key("patch_pumpkin/extra")
    val PATCH_PUMPKIN_LANTERN_EXTRA = key("patch_pumpkin/extra_lantern")
    val PATCH_PUMPKIN_MOSSKIN_EXTRA = key("patch_pumpkin/extra_mosskin")
    val PATCH_PUMPKIN_PALE_EXTRA = key("patch_pumpkin/extra_pale")
    val PATCH_PUMPKIN_GLOOM_EXTRA = key("patch_pumpkin/extra_gloom")

    val CRIMSON_WART_VEGETATION = key("crimson_wart_vegetation")
    val WARPED_WART_VEGETATION = key("warped_wart_vegetation")

    //val SPRUCE_BIG_FALLEN_TREE = create("fallen_tree/spruce_big")
    //val JUNGLE_BIG_FALLEN_TREE = create("fallen_tree/jungle_big")
    //val DARK_OAK_FALLEN_TREE = create("fallen_tree/dark_oak")
    //val CASCADE_FALLEN_TREE = create("fallen_tree/cascade")
    //val CRIMSON_FALLEN_STEM = create("fallen_stem/crimson")
    //val WARPED_FALLEN_STEM = create("fallen_stem/warped")

    val HUGE_GOLDEN_MUSHROOM = key("golden_mushroom/huge")
    val PATCH_GOLDEN_MUSHROOM = key("golden_mushroom/patch")
    val PATCH_GOLDEN_MUSHROOM_WITH_HUGE = key("golden_mushroom/patch_with_huge")

    //once i get rule tests make these convert blocks to their overgrown variants
    val OVERGROWTH_FLOOR_V = key("overgrowth/floor_vegetation")
    val OVERGROWTH_PATCH_FLOOR = key("overgrowth/floor")
    val OVERGROWTH_PATCH_FLOOR_BONEMEAL = key("overgrowth/floor_bonemeal")
    val OVERGROWTH_CEILING_V = key("overgrowth/ceiling_vegetation")
    val OVERGROWTH_PATCH_CEILING = key("overgrowth/ceiling")
    val OVERGROWTH_PATCH_CEILING_BONEMEAL = key("overgrowth/ceiling_bonemeal")
    val OVERGROWTH_TREE_DOWN = key("tree/verdant/down")
    val OVERGROWTH_TREE_NORTH = key("tree/verdant/north")
    val OVERGROWTH_TREE_SOUTH = key("tree/verdant/south")
    val OVERGROWTH_TREE_EAST = key("tree/verdant/east")
    val OVERGROWTH_TREE_WEST = key("tree/verdant/west")
    val OVERGROWTH_TREE_ROOTED = key("tree/verdant/rooted")
    val OVERGROWTH_TREE_PATCH = key("tree/verdant/patch")
    val OVERGROWTH_LEAF_DECORATORS = key("tree/verdant/leaf_decorator")
    val OVERGROWTH_CARPET_PATCH = key("overgrowth/carpet_patch")
    val OVERGROWTH_HANGING = key("overgrowth/hanging")
    val OVERGROWTH_HANGING_LEAVES = key("overgrowth/hanging_leaves")
    val OVERGROWTH_HANGING_BLOCKS = key("overgrowth/hanging_blocks")

    val PILE_CORN = key("pile_corn")

    fun key(id: String): ResourceKey<ConfiguredFeature<*, *>> = Registries.CONFIGURED_FEATURE.key(id(id))

}