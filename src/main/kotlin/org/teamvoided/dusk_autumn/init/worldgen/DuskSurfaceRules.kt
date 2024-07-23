package org.teamvoided.dusk_autumn.init.worldgen

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.util.math.VerticalSurfaceType
import net.minecraft.world.gen.YOffset
import net.minecraft.world.gen.noise.NoiseParametersKeys
import net.minecraft.world.gen.surfacebuilder.SurfaceRules.*

object DuskSurfaceRules {
    val podzol = sequence(
        condition(
            water(-1, 0),
            sequence(
                condition(
                    ON_FLOOR, block(Blocks.PODZOL)
                ),
                block(Blocks.DIRT)
            )
        )
    )
    val mycelium = sequence(
        condition(
            water(-1, 0),
            sequence(
                condition(
                    ON_FLOOR, block(Blocks.MYCELIUM)
                ),
                block(Blocks.DIRT)
            )
        )
    )

    fun overworld(): MaterialRule {
        //Sorted like the vanilla surface rule locations https://minecraft.wiki/w/World_generation#Surface
        //Surface rule sequence 1: Floor

        val mushroomIslandSurface = condition(
            biome(
                DuskBiomes.AUTUMN_WOODS,
                DuskBiomes.AUTUMN_PASTURES,
                DuskBiomes.AUTUMN_CASCADES,
                DuskBiomes.AUTUMN_WETLANDS
            ), sequence(
                condition(
                    DEEP_UNDER_FLOOR, sequence(
                        condition(
                            surfaceNoiseThreshold(1.0),
                            mycelium
                        )
                    )
                ),
                condition(
                    UNDER_FLOOR, sequence(
                        condition(
                            surfaceSecondaryNoiseThreshold(-0.75, 0.75),
                            block(Blocks.COARSE_DIRT)
                        )
                    )
                ),
                condition(
                    stoneDepth(0, false, 2, VerticalSurfaceType.FLOOR), sequence(
                        condition(
                            surfaceSecondaryNoiseThreshold(-2.0, 2.0),
                            podzol
                        )
                    )
                ),
                condition(
                    UNDER_CEILING, sequence(
                        condition(
                            surfaceNoiseThreshold(0.75),
                            block(Blocks.COARSE_DIRT)
                        )
                    )
                ),
                condition(
                    stoneDepth(0, true, 6, VerticalSurfaceType.CEILING), sequence(
                        condition(
                            surfaceSecondaryNoiseThreshold(1.0),
                            block(Blocks.COARSE_DIRT)
                        )
                    )
                ),
                condition(
                    UNDER_FLOOR, mycelium
                )
            )
        )
        val onFloorInDeepWater = condition(
            DEEP_UNDER_FLOOR, condition(
                water(-6, 0),
                sequence(
                    mushroomIslandSurface
                )
            )
        )

        val surface = condition(
            abovePreliminarySurface(),
            sequence(
//                swampWater
//                woodedBadlands,
//                badlands,
//                onFloorAndWater,
                onFloorInDeepWater,
//                snowyCherryGrove,
//                stonyShore,
//                deepSand,
//                sandstoneDesert,
//                sandOcean
            )
        )

        // Return a surface-only sequence of surface rules
        return condition(
            aboveY(YOffset.fixed(-55), 0),
            surface
        )
    }

    private fun block(block: Block): MaterialRule = block(block.defaultState)

    private fun surfaceSecondaryNoiseThreshold(min: Double): MaterialCondition =
        noiseThreshold(NoiseParametersKeys.SURFACE_SECONDARY, min / 8.25)

    private fun surfaceSecondaryNoiseThreshold(min: Double, max: Double): MaterialCondition =
        noiseThreshold(NoiseParametersKeys.SURFACE_SECONDARY, min / 8.25, max / 8.25)

    private fun surfaceNoiseThreshold(min: Double): MaterialCondition =
        noiseThreshold(NoiseParametersKeys.SURFACE, min / 8.25)

    fun surfaceNoiseThreshold(min: Double, max: Double): MaterialCondition =
        noiseThreshold(NoiseParametersKeys.SURFACE, min / 8.25, max / 8.25)

}