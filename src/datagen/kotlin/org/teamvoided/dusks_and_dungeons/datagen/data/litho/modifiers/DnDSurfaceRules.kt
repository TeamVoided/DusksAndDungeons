package org.teamvoided.dusks_and_dungeons.datagen.data.litho.modifiers

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.Noises
import net.minecraft.world.level.levelgen.SurfaceRules.*
import net.minecraft.world.level.levelgen.VerticalAnchor
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDBiomes

object DnDSurfaceRules {

    val COARSE_DIRT = block(Blocks.COARSE_DIRT)
    val PODZOL = block(Blocks.PODZOL)
    val MUD = block(Blocks.MUD)

    fun overworld(): RuleSource {
        val autumnCascadesSurface = ifTrue(
            isBiome(
                DnDBiomes.AUTUMN_WOODS,
                DnDBiomes.AUTUMN_PASTURES,
                DnDBiomes.AUTUMN_CASCADES,
                DnDBiomes.GOLDEN_WOODS,
                DnDBiomes.GOLDEN_PASTURES
            ),
            ifTrue(
                not(waterBlockCheck(-1, 0)),
                ifTrue(
                    UNDER_FLOOR,
                    MUD
                )
            )
        )

        val autumnPasturesSurface = ifTrue(
            isBiome(DnDBiomes.AUTUMN_PASTURES, DnDBiomes.AUTUMN_CASCADES),
            ifTrue(
                UNDER_FLOOR,
                ifTrue(surfaceSecondaryNoiseAbove(-0.35, 0.35), COARSE_DIRT)
            )
        )

        val autumnWoodsSurface = ifTrue(
            isBiome(DnDBiomes.AUTUMN_WOODS),
            sequence(
                ifTrue(
                    ON_FLOOR,
                    ifTrue(surfaceSecondaryNoiseAbove(1.75), ifTrue(waterBlockCheck(0, 0), PODZOL))
                ),
                ifTrue(
                    UNDER_FLOOR,
                    ifTrue(surfaceNoiseAbove(-0.75, 0.75), COARSE_DIRT)
                )
            )
        )

        val surface = ifTrue(
            abovePreliminarySurface(),
            sequence(
                autumnCascadesSurface,
                ifTrue(
                    waterBlockCheck(-6, 0),
                    sequence(
                        autumnWoodsSurface,
                        autumnPasturesSurface
                    )
                )
            )
        )

        // Return a surface-only sequence of surface rules
        return ifTrue(yBlockCheck(VerticalAnchor.aboveBottom(11), 0), surface)
    }

    // region Helpers
    fun block(block: Block): RuleSource = state(block.defaultBlockState())
    fun surfaceNoiseAbove(x: Double): ConditionSource = noiseCondition(Noises.SURFACE, x / 8.25)
    fun surfaceNoiseAbove(x: Double, z: Double): ConditionSource = noiseCondition(Noises.SURFACE, x / 8.25, z / 8.25)
    fun surfaceSecondaryNoiseAbove(min: Double): ConditionSource = noiseCondition(Noises.SURFACE_SECONDARY, min / 8.25)
    fun surfaceSecondaryNoiseAbove(x: Double, z: Double): ConditionSource =
        noiseCondition(Noises.SURFACE_SECONDARY, x / 8.25, z / 8.25)
    // endregion

}