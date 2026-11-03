package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.util.Mth

// TODO(1.0) either move this to a separate file or just move to companion objects for use cases
@Deprecated("Marked for removal in 1.0")
object Utils {

    const val DEG_TO_RAD = Mth.DEG_TO_RAD
    const val RAD_TO_DEG = Mth.RAD_TO_DEG
    const val ROTATE_30 = Mth.PI / 6f
    const val ROTATE_45 = Mth.PI / 4f
    const val ROTATE_60 = Mth.PI / 3f
    const val ROTATE_90 = Mth.HALF_PI
    const val ROTATE_120 = ROTATE_90 + ROTATE_30
    const val ROTATE_135 = ROTATE_90 + ROTATE_45
    const val ROTATE_150 = ROTATE_90 + ROTATE_60
    const val ROTATE_180 = Mth.PI
    const val ROTATE_210 = ROTATE_180 + ROTATE_30
    const val ROTATE_225 = ROTATE_180 + ROTATE_45
    const val ROTATE_240 = ROTATE_180 + ROTATE_60
    const val ROTATE_270 = Mth.PI * (3f / 2f)
    const val ROTATE_300 = ROTATE_270 + ROTATE_30
    const val ROTATE_315 = ROTATE_270 + ROTATE_45
    const val ROTATE_330 = ROTATE_270 + ROTATE_60
    const val ROTATE_360 = Mth.TWO_PI

}