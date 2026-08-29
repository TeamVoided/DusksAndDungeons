package org.teamvoided.dusks_and_dungeons.data.worldgen

import net.minecraft.core.registries.Registries
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.key

object DnDPlacedFeature {

    val OVERGROWN_BOULDER = key("boulder/overgrown_cobblestone")
    val OVERGROWN_CAVE_BOULDER = key("boulder/overgrown_cave")
    val ORE_LAPIS_EXTRA = key("ore_lapis_extra")
    val CASCADE_TREE = key("tree/cascade_tree")
    val CASCADE_TREE_BEES = key("tree/cascade_tree_bees")
    val CASCADE_TREE_AUTUMN = key("tree/cascade_tree_autumn")
    val SYPIA_TALL = key("tree/sypia_tall")
    val SYPIA_TALL_BEES = key("tree/sypia_tall_bees")
    val SYPIA_TALL_AUTUMN = key("tree/sypia_tall_autumn")
    val DARK_OAK_AUTUMN = key("tree/dark_oak_autumn")
    val ACACIA_AUTUMN = key("tree/acacia_autumn")
    val ACACIA_BUSH_AUTUMN = key("tree/acacia_bush_autumn")
    val PATCH_GRASS_AUTUMN_PLAIN = key("patch_grass_autumn_plain")
    val PATCH_TALL_GRASS_AUTUMN_PLAIN = key("patch_tall_grass_autumn_plain")
    val PATCH_PUMPKIN_EXTRA = key("pumpkin/pumpkin_extra")
    val PATCH_LANTERN_PUMPKIN_EXTRA = key("pumpkin/lantern_extra")
    val PATCH_MOSSKIN_PUMPKIN_EXTRA = key("pumpkin/mosskin_extra")
    val PATCH_GLOOM_PUMPKIN_EXTRA = key("pumpkin/gloom_extra")
    val DISK_MUD = key("disk_mud")
    val AUTUMN_WOODS_VEGETATION = key("autumn_woods_vegetation")
    val AUTUMN_PASTURES_VEGETATION = key("autumn_pastures_vegetation")
    val GOLDEN_WOODS_VEGETATION = key("golden_woods_vegetation")
    val GOLDEN_PASTURES_VEGETATION = key("golden_pastures_vegetation")
    val FLOWER_AUTUMN = key("flower_autumn")
    val PATCH_ROSEBUSH = key("patch_rosebush")
    val ORANGE_PETALS = key("orange_petals")
    val AUTUMN_FARMLANDS = key("autumn_farmlands")
    val CROPS_WILD_WHEAT = key("crops/wild_wheat")
    val WILD_WHEAT_FIELD = key("wild_wheat_field")

    val OVERGROWTH_TREE_ROOTED = key("cave/overgrowth_tree_rooted")
    val OVERGROWTH_HANGING = key("cave/overgrowth_hanging")
    val OVERGROWTH_CAVES_FLOOR_VEGETATION = key("cave/overgrowth_floor_vegetation")
    val OVERGROWTH_CAVES_CEILING_VEGETATION = key("cave/overgrowth_ceiling_vegetation")
    val OVERGROWTH_TREE_CAVE_1 = key("cave/verdant_tree_1")
    val OVERGROWTH_TREE_CAVE_2 = key("cave/verdant_tree_2")
    val OVERGROWTH_TREE_CAVE_3 = key("cave/verdant_tree_3")
    val VERDANT_DOWN = key("tree/verdant")


    val FAIRY_RING_RED = key("fairy_ring_red")

    val GOLDEN_MUSHROOM_CAVE = key("golden_mushroom/cave") //overworld not surface
    val GOLDEN_MUSHROOM_SURFACE = key("golden_mushroom/surface") //other forests
    val GOLDEN_MUSHROOM_HUGE_PATCH = key("golden_mushroom/common") //dark forest and mushroom biomes

    val CRIMSON_WART = key("crimson_warts")
    val WARPED_WART = key("warped_warts")

    val PILE_CORN = key("pile_corn")

    fun key(id: String) = Registries.PLACED_FEATURE.key(id(id))

}