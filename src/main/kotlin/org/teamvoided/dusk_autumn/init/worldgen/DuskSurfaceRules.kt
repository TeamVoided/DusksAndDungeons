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

    val autumnBiomes = biome(
        DuskBiomes.AUTUMN_WOODS,
        DuskBiomes.AUTUMN_PASTURES,
        DuskBiomes.AUTUMN_CASCADES,
        DuskBiomes.AUTUMN_WETLANDS
    )

    fun overworld(): MaterialRule {
        val autumnMud = condition(
            autumnBiomes, condition(
                mudRegionThreshold(0.0),
                condition(
                    not(aboveY(YOffset.fixed(65), 0)),
                    sequence(
                        condition(
                            UNDER_FLOOR,
                            condition(
                                mudThreshold(0.0),
                                block(Blocks.MUD)
                            )
                        ),
                        condition(
                            ON_FLOOR,
                            condition(
                                not(aboveY(YOffset.fixed(63), 0)),
                                block(Blocks.MUD)
                            )
                        )
                    )
                )
            )
        )
        val defaultAutumnPasturesSurface = condition(
            biome(DuskBiomes.AUTUMN_PASTURES), sequence(
                condition(
                    ON_FLOOR, sequence(
                        condition(
                            surfaceNoiseThreshold(1.0),
                            podzol
                        )
                    )
                ),
                condition(
                    UNDER_FLOOR, sequence(
                        condition(
                            surfaceSecondaryNoiseThreshold(1.25),
                            block(Blocks.COARSE_DIRT)
                        )
                    )
                )
            )
        )
        val defaultAutumnWoodsSurface = condition(
            biome(DuskBiomes.AUTUMN_WOODS), sequence(
                condition(
                    UNDER_FLOOR, sequence(
                        condition(
                            surfaceNoiseThreshold(-0.75, 0.75),
                            block(Blocks.COARSE_DIRT)
                        )
                    )
                ),
                condition(
                    ON_FLOOR, sequence(
                        condition(
                            surfaceNoiseThreshold(-2.0, 2.0),
                            podzol
                        )
                    )
                )
            )
        )

        val surface = condition(
            abovePreliminarySurface(),
            sequence(
                autumnMud,
                condition(
                    water(-6, 0),
                    sequence(
                        defaultAutumnWoodsSurface,
                        defaultAutumnPasturesSurface
                    )
                )
            )
        )

        // Return a surface-only sequence of surface rules
        return condition(
            aboveY(YOffset.fixed(-55), 0),
            surface
        )
    }

    private fun block(block: Block): MaterialRule = block(block.defaultState)
    fun mudThreshold(min: Double): MaterialCondition {
        return noiseThreshold(NoiseParametersKeys.SURFACE_SWAMP, min, Double.MAX_VALUE)
    }

    fun mudRegionThreshold(min: Double): MaterialCondition {
        return noiseThreshold(NoiseParametersKeys.PACKED_ICE, min, Double.MAX_VALUE)
    }

    private fun surfaceSecondaryNoiseThreshold(min: Double): MaterialCondition =
        noiseThreshold(NoiseParametersKeys.SURFACE_SECONDARY, min / 8.25)

    private fun surfaceSecondaryNoiseThreshold(min: Double, max: Double): MaterialCondition =
        noiseThreshold(NoiseParametersKeys.SURFACE_SECONDARY, min / 8.25, max / 8.25)

    private fun surfaceNoiseThreshold(min: Double): MaterialCondition =
        noiseThreshold(NoiseParametersKeys.SURFACE, min / 8.25)

    fun surfaceNoiseThreshold(min: Double, max: Double): MaterialCondition =
        noiseThreshold(NoiseParametersKeys.SURFACE, min / 8.25, max / 8.25)

}