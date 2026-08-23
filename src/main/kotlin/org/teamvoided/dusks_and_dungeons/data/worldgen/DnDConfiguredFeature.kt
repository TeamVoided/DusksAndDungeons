package org.teamvoided.dusks_and_dungeons.data.worldgen

import net.minecraft.resources.ResourceKey
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

@Suppress("MemberVisibilityCanBePrivate")
object DnDConfiguredFeature {
    val OVERGROWN_COBBLESTONE_BOULDER = create("overgrown_cobblestone_boulder")
    val FAIRY_RING_WHITE = create("fairy_ring/white")
    val FAIRY_RING_RED = create("fairy_ring/red")
    val FAIRY_RING_BLUE = create("fairy_ring/blue")
    val FAIRY_RING_ORANGE = create("fairy_ring/orange")
    val FAIRY_RING_WILDFLOWER = create("fairy_ring/wildflower")
    val FAIRY_RING_COLD_WILDFLOWER = create("fairy_ring/cold_wildflower")

    @JvmField
    val FAIRY_RING_CRIMSON = create("fairy_ring/crimson")

    @JvmField
    val FAIRY_RING_WARPED = create("fairy_ring/warped")

    val CASCADE_TREE = create("tree/sapling/cascade_tree")
    val CASCADE_TREE_BEES = create("tree/sapling/cascade_tree_bees")
    val CASCADE_TREE_AUTUMN = create("tree/cascade_tree_autumn")
    val SYPIA_TALL = create("tree/sapling/sypia")
    val SYPIA_TALL_BEES = create("tree/sapling/sypia_tall_bees")
    val SYPIA_TALL_AUTUMN = create("tree/sypia_tall_autumn")
    val DARK_OAK_AUTUMN = create("tree/dark_oak_autumn")
    val ACACIA_AUTUMN = create("tree/acacia_autumn")
    val ACACIA_BUSH_AUTUMN = create("tree/acacia_bush_autumn")
    val DISK_PODZOL = create("disk/disk_podzol")
    val DISK_MUD = create("disk/disk_mud")
    val DISK_RED_SAND = create("disk/disk_red_sand")
    val AUTUMN_WOODS_VEGETATION = create("biome_vegetation/autumn_woods_vegetation")
    val AUTUMN_PASTURES_VEGETATION = create("biome_vegetation/autumn_pastures_vegetation")
    val GOLDEN_VEGETATION = create("biome_vegetation/golden_vegetation")
    val FLOWER_AUTUMN = create("flower/flower_autumn")
    val PATCH_ROSEBUSH = create("flower/patch_rosebush")
    val BLUE_PETALS = create("flower/blue_petals")
    val AUTUMN_FARMLAND = create("autumn_farmland")
    val AUTUMN_FARMLAND_CROPS = create("crops/autumn_farmland_crops")
    val CROPS_WILD_WHEAT = create("crops/wild_wheat")
    val CROPS_WHEAT = create("crops/wheat")
    val CROPS_CARROTS = create("crops/carrots")
    val CROPS_POTATOES = create("crops/potatoes")
    val CROPS_PUMPKIN = create("crops/pumpkins")
    val CROPS_BEETROOTS = create("crops/beetroots")
    val CROPS_GOLDEN_BEETROOTS = create("crops/golden_beetroots")

    val PATCH_PUMPKIN_LANTERN = create("patch_pumpkin/lantern")
    val PATCH_PUMPKIN_MOSSKIN = create("patch_pumpkin/mosskin")
    val PATCH_PUMPKIN_PALE = create("patch_pumpkin/pale")
    val PATCH_PUMPKIN_GLOOM = create("patch_pumpkin/gloom")
    val PATCH_PUMPKIN_EXTRA = create("patch_pumpkin/extra")
    val PATCH_PUMPKIN_LANTERN_EXTRA = create("patch_pumpkin/extra_lantern")
    val PATCH_PUMPKIN_MOSSKIN_EXTRA = create("patch_pumpkin/extra_mosskin")
    val PATCH_PUMPKIN_PALE_EXTRA = create("patch_pumpkin/extra_pale")
    val PATCH_PUMPKIN_GLOOM_EXTRA = create("patch_pumpkin/extra_gloom")

    val CRIMSON_WART_VEGETATION = create("crimson_wart_vegetation")
    val WARPED_WART_VEGETATION = create("warped_wart_vegetation")

    //val SPRUCE_BIG_FALLEN_TREE = create("fallen_tree/spruce_big")
    //val JUNGLE_BIG_FALLEN_TREE = create("fallen_tree/jungle_big")
    //val DARK_OAK_FALLEN_TREE = create("fallen_tree/dark_oak")
    //val CASCADE_FALLEN_TREE = create("fallen_tree/cascade")
    //val CRIMSON_FALLEN_STEM = create("fallen_stem/crimson")
    //val WARPED_FALLEN_STEM = create("fallen_stem/warped")

    val HUGE_GOLDEN_MUSHROOM = create("golden_mushroom/huge")
    val PATCH_GOLDEN_MUSHROOM = create("golden_mushroom/patch")
    val PATCH_GOLDEN_MUSHROOM_WITH_HUGE = create("golden_mushroom/patch_with_huge")

    //once i get rule tests make these convert blocks to their overgrown variants
    val OVERGROWTH_FLOOR_V = create("overgrowth/floor_vegetation")
    val OVERGROWTH_PATCH_FLOOR = create("overgrowth/floor")
    val OVERGROWTH_PATCH_FLOOR_B = create("overgrowth/floor_bonemeal")
    val OVERGROWTH_CEILING_V = create("overgrowth/ceiling_vegetation")
    val OVERGROWTH_PATCH_CEILING = create("overgrowth/ceiling")
    val OVERGROWTH_PATCH_CEILING_B = create("overgrowth/ceiling_bonemeal")
    val OVERGROWTH_TREE_DOWN = create("tree/verdant/down")
    val OVERGROWTH_TREE_NORTH = create("tree/verdant/north")
    val OVERGROWTH_TREE_SOUTH = create("tree/verdant/south")
    val OVERGROWTH_TREE_EAST = create("tree/verdant/east")
    val OVERGROWTH_TREE_WEST = create("tree/verdant/west")
    val OVERGROWTH_TREE_ROOTED = create("tree/verdant/rooted")
    val OVERGROWTH_CARPET_PATCH = create("overgrowth/carpet_patch")
    val OVERGROWTH_HANGING = create("overgrowth/hanging")
    val OVERGROWTH_HANGING_LEAVES = create("overgrowth/hanging_leaves")
    val OVERGROWTH_HANGING_BLOCKS = create("overgrowth/hanging_blocks")

    fun create(id: String): ResourceKey<ConfiguredFeature<*, *>> =
        ResourceKey.create(Registries.CONFIGURED_FEATURE, id(id))

}