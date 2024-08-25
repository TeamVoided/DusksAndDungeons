package org.teamvoided.dusk_autumn.data.worldgen

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.gen.feature.ConfiguredFeature
import org.teamvoided.dusk_autumn.DuskAutumns.id

@Suppress("MemberVisibilityCanBePrivate")
object DnDConfiguredFeature {
    val OVERGROWN_COBBLESTONE_BOULDER = create("overgrown_cobblestone_boulder")
    val CASCADE_TREE = create("cascade_tree")
    val CASCADE_TREE_BEES = create("cascade_tree_bees")
    val CASCADE_TREE_AUTUMN = create("cascade_tree_autumn")

    //    val CASCADE_TREE_WETLANDS = create("cascade_tree_wetlands")
    val GOLDEN_BIRCH_TALL = create("golden_birch_tall")
    val GOLDEN_BIRCH_TALL_BEES = create("golden_birch_tall_bees")
    val GOLDEN_BIRCH_TALL_AUTUMN = create("golden_birch_tall_autumn")

    //    val GOLDEN_BIRCH_TALL_WETLANDS = create("golden_birch_tall_wetlands")
    val DARK_OAK_AUTUMN = create("dark_oak_autumn")

    //    val DARK_OAK_AUTUMN_WETLANDS = create("dark_oak_autumn_wetlands")
    val ACACIA_AUTUMN = create("acacia_autumn")
    val ACACIA_BUSH_AUTUMN = create("acacia_bush_autumn")
    val DISK_PODZOL = create("disk_podzol")
    val DISK_MUD = create("disk_mud")
    val DISK_RED_SAND = create("disk_red_sand")
    val PATCH_PUMPKIN_EXTRA = create("patch_pumpkin_extra")
    val AUTUMN_WOODS_VEGETATION = create("autumn_woods_vegetation")
    val AUTUMN_PASTURES_VEGETATION = create("autumn_pastures_vegetation")

    //    val AUTUMN_WETLANDS_VEGETATION = create("autumn_wetlands_vegetation")
    val FLOWER_AUTUMN = create("flower_autumn")
    val PATCH_ROSEBUSH = create("patch_rosebush")
    val BLUE_PETALS = create("blue_petals")
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

    val ROCKY_OVERWORLD_ORE = create("rocky_overworld_ore")
    val SLATED_OVERWORLD_ORE = create("slated_overworld_ore")
    val BLACKSTONE_NETHER_ORE = create("blackstone_nether_ore")


    fun create(id: String): RegistryKey<ConfiguredFeature<*, *>> =
        RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, id(id))

}