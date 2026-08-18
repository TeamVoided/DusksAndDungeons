package org.teamvoided.dusks_and_dungeons.util

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.world.item.ItemStack


fun ItemStack.isShears() = `is`(ConventionalItemTags.SHEAR_TOOLS)