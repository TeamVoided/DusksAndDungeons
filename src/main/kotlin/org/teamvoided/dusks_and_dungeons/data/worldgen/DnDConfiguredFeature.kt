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

    //    val CASCADE_TREE_WETLANDS = create("cascade_tree_wetlands")
    val GOLDEN_BIRCH_TALL = create("tree/sapling/golden_birch_tall")
    val GOLDEN_BIRCH_TALL_BEES = create("tree/sapling/golden_birch_tall_bees")
    val GOLDEN_BIRCH_TALL_AUTUMN = create("tree/golden_birch_tall_autumn")

    //    val GOLDEN_BIRCH_TALL_WETLANDS = create("golden_birch_tall_wetlands")
    val DARK_OAK_AUTUMN = create("tree/dark_oak_autumn")

    //    val DARK_OAK_AUTUMN_WETLANDS = create("dark_oak_autumn_wetlands")
    val ACACIA_AUTUMN = create("tree/acacia_autumn")
    val ACACIA_BUSH_AUTUMN = create("tree/acacia_bush_autumn")
    val DISK_PODZOL = create("disk/disk_podzol")
    val DISK_MUD = create("disk/disk_mud")
    val DISK_RED_SAND = create("disk/disk_red_sand")
    val PATCH_PUMPKIN_LANTERN = create("patch_pumpkin/lantern")
    val PATCH_PUMPKIN_MOSSKIN = create("patch_pumpkin/mosskin")
    val PATCH_PUMPKIN_PALE = create("patch_pumpkin/pale")
    val PATCH_PUMPKIN_GLOOM = create("patch_pumpkin/gloom")
    val PATCH_PUMPKIN_EXTRA = create("patch_pumpkin/extra")
    val PATCH_PUMPKIN_LANTERN_EXTRA = create("patch_pumpkin/extra_lantern")
    val PATCH_PUMPKIN_MOSSKIN_EXTRA = create("patch_pumpkin/extra_mosskin")
    val PATCH_PUMPKIN_PALE_EXTRA = create("patch_pumpkin/extra_pale")
    val PATCH_PUMPKIN_GLOOM_EXTRA = create("patch_pumpkin/extra_gloom")
    val AUTUMN_WOODS_VEGETATION = create("biome_vegetation/autumn_woods_vegetation")
    val AUTUMN_PASTURES_VEGETATION = create("biome_vegetation/autumn_pastures_vegetation")
    val GOLDEN_VEGETATION = create("biome_vegetation/golden_vegetation")

    //    val AUTUMN_WETLANDS_VEGETATION = create("biome_vegetation/autumn_wetlands_vegetation")
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

    val OAK_FALLEN_TREE = create("fallen_tree/oak")
    val SPRUCE_FALLEN_TREE = create("fallen_tree/spruce")
    val SPRUCE_BIG_FALLEN_TREE = create("fallen_tree/spruce_big")
    val BIRCH_FALLEN_TREE = create("fallen_tree/birch")
    val BIRCH_TALL_FALLEN_TREE = create("fallen_tree/birch_tall")
    val JUNGLE_FALLEN_TREE = create("fallen_tree/jungle")
    val JUNGLE_BIG_FALLEN_TREE = create("fallen_tree/jungle_big")
    val ACACIA_FALLEN_TREE = create("fallen_tree/acacia")
    val DARK_OAK_FALLEN_TREE = create("fallen_tree/dark_oak")
    val CHERRY_FALLEN_TREE = create("fallen_tree/cherry")
    val CASCADE_FALLEN_TREE = create("fallen_tree/cascade")
    val CRIMSON_FALLEN_STEM = create("fallen_stem/crimson")
    val WARPED_FALLEN_STEM = create("fallen_stem/warped")

    val ROCKY_OVERWORLD_ORE = create("overlay/rocky_overworld_ore")
    val SLATED_OVERWORLD_ORE = create("overlay/slated_overworld_ore")
    val BLACKSTONE_NETHER_ORE = create("overlay/blackstone_nether_ore")

    val HUGE_GOLDEN_MUSHROOM = create("huge_golden_mushroom")
    val PATCH_GOLDEN_MUSHROOM = create("patch_golden_mushroom")


    fun create(id: String): ResourceKey<ConfiguredFeature<*, *>> =
        ResourceKey.create(Registries.CONFIGURED_FEATURE, id(id))

}