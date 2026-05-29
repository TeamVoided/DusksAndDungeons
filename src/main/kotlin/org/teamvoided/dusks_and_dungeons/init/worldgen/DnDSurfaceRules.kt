package org.teamvoided.dusks_and_dungeons.init.worldgen

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.Noises
import net.minecraft.world.level.levelgen.SurfaceRules.*

object DnDSurfaceRules {
    val podzol = sequence(
        ifTrue(
            waterBlockCheck(-1, 0),
            sequence(
                ifTrue(
                    ON_FLOOR, block(Blocks.PODZOL)
                ),
                block(Blocks.DIRT)
            )
        )
    )

    val autumnBiomes = isBiome(
        DnDBiomes.AUTUMN_WOODS,
        DnDBiomes.AUTUMN_PASTURES,
        DnDBiomes.AUTUMN_CASCADES,
    )

    fun overworld(): RuleSource {
        val autumnMud = ifTrue(
            autumnBiomes, ifTrue(
                mudRegionThreshold(0.0),
                ifTrue(
                    not(yBlockCheck(VerticalAnchor.absolute(65), 0)),
                    sequence(
                        ifTrue(
                            UNDER_FLOOR,
                            ifTrue(
                                mudThreshold(0.0),
                                block(Blocks.MUD)
                            )
                        ),
                        ifTrue(
                            ON_FLOOR,
                            ifTrue(
                                not(yBlockCheck(VerticalAnchor.absolute(63), 0)),
                                block(Blocks.MUD)
                            )
                        )
                    )
                )
            )
        )
        val defaultAutumnPasturesSurface = ifTrue(
            isBiome(DnDBiomes.AUTUMN_PASTURES), sequence(
//                condition(
//                    ON_FLOOR, sequence(
//                        condition(
//                            surfaceNoiseThreshold(1.0),
//                            podzol
//                        )
//                    )
//                ),
                ifTrue(
                    UNDER_FLOOR, sequence(
                        ifTrue(
                            surfaceSecondaryNoiseThreshold(1.25),
                            block(Blocks.COARSE_DIRT)
                        )
                    )
                )
            )
        )
        val defaultAutumnWoodsSurface = ifTrue(
            isBiome(DnDBiomes.AUTUMN_WOODS), sequence(
                ifTrue(
                    UNDER_FLOOR, sequence(
                        ifTrue(
                            surfaceNoiseThreshold(-0.75, 0.75),
                            block(Blocks.COARSE_DIRT)
                        )
                    )
                ),
//                condition(
//                    ON_FLOOR, sequence(
//                        condition(
//                            surfaceNoiseThreshold(-2.0, 2.0),
//                            podzol
//                        )
//                    )
//                )
            )
        )

        val surface = ifTrue(
            abovePreliminarySurface(),
            sequence(
//                autumnMud,
                ifTrue(
                    waterBlockCheck(-6, 0),
                    sequence(
                        defaultAutumnWoodsSurface,
                        defaultAutumnPasturesSurface
                    )
                )
            )
        )

        // Return a surface-only sequence of surface rules
        return ifTrue(
            yBlockCheck(VerticalAnchor.absolute(-55), 0),
            surface
        )
    }

    private fun block(block: Block): RuleSource = state(block.defaultBlockState())
    fun mudThreshold(min: Double): ConditionSource {
        return noiseCondition(Noises.SWAMP, min, Double.MAX_VALUE)
    }

    fun mudRegionThreshold(min: Double): ConditionSource {
        return noiseCondition(Noises.PACKED_ICE, min, Double.MAX_VALUE)
    }

    private fun surfaceSecondaryNoiseThreshold(min: Double): ConditionSource =
        noiseCondition(Noises.SURFACE_SECONDARY, min / 8.25)

    private fun surfaceSecondaryNoiseThreshold(min: Double, max: Double): ConditionSource =
        noiseCondition(Noises.SURFACE_SECONDARY, min / 8.25, max / 8.25)

    private fun surfaceNoiseThreshold(min: Double): ConditionSource =
        noiseCondition(Noises.SURFACE, min / 8.25)

    fun surfaceNoiseThreshold(min: Double, max: Double): ConditionSource =
        noiseCondition(Noises.SURFACE, min / 8.25, max / 8.25)

}