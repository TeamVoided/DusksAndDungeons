/*
 * TODO(cleanup)
 */

package org.teamvoided.dusks_and_dungeons.init.misc

import net.minecraft.core.registries.BuiltInRegistries
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

object VVAliases {

    fun init() {
        alias("dripstone_slab")
        alias("quartz_bricks_slab")
        alias("cracked_polished_blackstone_bricks_slab")
        alias("cracked_deepslate_bricks_slab")
        alias("smooth_basalt_slab")
        alias("end_stone_slab")
        alias("packed_mud_slab")
        alias("calcite_slab")
        alias("cracked_deepslate_tiles_slab")
        alias("obsidian_slab")
        alias("snow_slab")
        alias("cracked_nether_bricks_slab")
        alias("cracked_stone_bricks_slab")

        aliasStairs("cracked_deepslate_bricks_stair")
        aliasStairs("cracked_nether_bricks_stair")
        aliasStairs("quartz_bricks_stair")
        aliasStairs("dripstone_stair")
        aliasStairs("smooth_basalt_stair")
        aliasStairs("end_stone_stair")
        aliasStairs("cut_red_sandstone_stair")
        aliasStairs("packed_mud_stair")
        aliasStairs("calcite_stair")
        aliasStairs("cracked_stone_bricks_stair")
        aliasStairs("cracked_deepslate_tiles_stair")
        aliasStairs("cracked_polished_blackstone_bricks_stair")
        aliasStairs("smooth_stone_stair")
        aliasStairs("snow_stair")
        aliasStairs("obsidian_stair")
        aliasStairs("cut_sandstone_stair")

        alias("smooth_basalt_wall")
        alias("stone_wall")
        alias("cracked_nether_bricks_wall")
        alias("smooth_stone_wall")
        alias("polished_andesite_wall")
        alias("dark_prismarine_wall")
        alias("cut_sandstone_wall")
        alias("packed_mud_wall")
        alias("brick_fence")
        alias("snow_wall")
        alias("end_stone_wall")
        alias("cracked_polished_blackstone_bricks_wall")
        alias("calcite_wall")
        alias("dripstone_wall")
        alias("quartz_wall")
        alias("purpur_wall")
        alias("polished_diorite_wall")
        alias("smooth_red_sandstone_wall")
        alias("smooth_quartz_wall")
        alias("quartz_bricks_wall")
        alias("prismarine_bricks_wall")
        alias("obsidian_wall")
        alias("smooth_sandstone_wall")
        alias("cut_red_sandstone_wall")
        alias("polished_granite_wall")
        alias("cracked_stone_bricks_wall")
        alias("cracked_deepslate_bricks_wall")
        alias("cracked_deepslate_tiles_wall")

        alias("infested_cracked_deepslate_tiles")
        alias("infested_deepslate_bricks")
        alias("infested_deepslate_tiles")
        alias("infested_cracked_deepslate_bricks")
        alias("infested_polished_deepslate")
        alias("infested_mossy_cobblestone")
        alias("infested_cobbled_deepslate")

        alias("redstone_lantern")
    }

    fun aliasStairs(name: String) {
        alias(name, name + "s")
    }

    fun alias(name: String) = alias(name, name)

    fun alias(oldName: String, newName: String) {
        val oldId = id("voided_variance", oldName)
        val newId = id(newName.replace("_bricks_", "_brick_").replace("_tiles_", "_tile_"))
        BuiltInRegistries.BLOCK.addAlias(oldId, newId)
        BuiltInRegistries.ITEM.addAlias(oldId, newId)
    }

}