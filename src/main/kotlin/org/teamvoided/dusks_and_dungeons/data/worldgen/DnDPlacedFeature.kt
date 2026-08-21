package org.teamvoided.dusks_and_dungeons.data.worldgen

import net.minecraft.resources.ResourceKey
import net.minecraft.core.registries.Registries
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons

@Suppress("HasPlatformType", "MemberVisibilityCanBePrivate")
object DnDPlacedFeature {
    val OVERGROWN_COBBLESTONE_BOULDER = create("overgrown_cobblestone_boulder")
    val ORE_LAPIS_EXTRA = create("ore_lapis_extra")
    val CASCADE_TREE = create("cascade_tree")
    val CASCADE_TREE_BEES = create("cascade_tree_bees")
    val CASCADE_TREE_AUTUMN = create("cascade_tree_autumn")
    val SYPIA_TALL = create("sypia_tall")
    val SYPIA_TALL_BEES = create("sypia_tall_bees")
    val SYPIA_TALL_AUTUMN = create("sypia_tall_autumn")
    val DARK_OAK_AUTUMN = create("dark_oak_autumn")
    val ACACIA_AUTUMN = create("acacia_autumn")
    val ACACIA_BUSH_AUTUMN = create("acacia_bush_autumn")
    val PATCH_GRASS_AUTUMN_PLAIN = create("patch_grass_autumn_plain")
    val PATCH_TALL_GRASS_AUTUMN_PLAIN = create("patch_tall_grass_autumn_plain")
    val PATCH_PUMPKIN_EXTRA = create("patch_pumpkin_extra")
    val PATCH_LANTERN_PUMPKIN_EXTRA = create("patch_lantern_pumpkin_extra")
    val DISK_PODZOL = create("disk_podzol")
    val DISK_MUD = create("disk_mud")
    val DISK_RED_SAND = create("disk_red_sand")
    val AUTUMN_WOODS_VEGETATION = create("autumn_woods_vegetation")
    val AUTUMN_PASTURES_VEGETATION = create("autumn_pastures_vegetation")
    val GOLDEN_WOODS_VEGETATION = create("golden_woods_vegetation")
    val GOLDEN_PASTURES_VEGETATION = create("golden_pastures_vegetation")
    val FLOWER_AUTUMN = create("flower_autumn")
    val PATCH_ROSEBUSH = create("patch_rosebush")
    val BLUE_PETALS = create("blue_petals")
    val AUTUMN_FARMLANDS = create("autumn_farmlands")
    val CROPS_WILD_WHEAT = create("crops/wild_wheat")

    val OVERGROWTH_TREE_ROOTED = create("cave/overgrowth_tree_rooted")
    val OVERGROWTH_CAVES_FLOOR_VEGETATION = create("cave/overgrowth_floor_vegetation")
    val OVERGROWTH_CAVES_CEILING_VEGETATION = create("cave/overgrowth_ceiling_vegetation")


    val FAIRY_RING_RED = create("fairy_ring_red")

    val ROCKY_ORE_UPPER = create("rocky_ore_upper")
    val ROCKY_ORE_LOWER = create("rocky_ore_lower")
    val SLATED_ORE = create("slated_ore")
    val BLACKSTONED_ORE = create("blackstoned_ore")

    val GOLDEN_MUSHROOM_NORMAL = create("golden_mushroom_normal") //other forests
    val GOLDEN_MUSHROOM_COMMON = create("golden_mushroom_common") //dark forest and mushroom island


    fun create(id: String) = ResourceKey.create(Registries.PLACED_FEATURE, DusksAndDungeons.id(id))
}