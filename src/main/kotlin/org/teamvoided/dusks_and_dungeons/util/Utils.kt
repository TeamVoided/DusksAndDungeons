package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.util.Mth

object Utils {
    const val PI = Mth.PI
    const val DEG_TO_RAD = 0.017453292f
    const val RAD_TO_DEG = 57.295776f
    const val rotate30 = PI / 6f
    const val rotate45 = PI / 4f
    const val rotate60 = PI / 3f
    const val rotate90 = Mth.HALF_PI
    const val rotate120 = rotate90 + rotate30
    const val rotate135 = rotate90 + rotate45
    const val rotate150 = rotate90 + rotate60
    const val rotate180 = PI
    const val rotate210 = rotate180 + rotate30
    const val rotate225 = rotate180 + rotate45
    const val rotate240 = rotate180 + rotate60
    const val rotate270 = PI * (3f / 2f)
    const val rotate300 = rotate270 + rotate30
    const val rotate315 = rotate270 + rotate45
    const val rotate330 = rotate270 + rotate60
    const val rotate360 = Mth.TWO_PI
}