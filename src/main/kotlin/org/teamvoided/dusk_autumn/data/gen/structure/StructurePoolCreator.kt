package org.teamvoided.dusk_autumn.data.gen.structure

import com.mojang.datafixers.util.Pair
import net.minecraft.registry.BootstrapContext
import net.minecraft.registry.Holder
import net.minecraft.registry.HolderProvider
import net.minecraft.registry.RegistryKeys
import net.minecraft.structure.pool.StructurePool
import net.minecraft.structure.pool.StructurePoolElement
import net.minecraft.structure.pool.StructurePools
import net.minecraft.structure.processor.StructureProcessorList
import net.minecraft.structure.processor.StructureProcessorLists
import net.minecraft.world.gen.feature.PlacedFeature
import org.teamvoided.dusk_autumn.DuskAutumns.MODID
import org.teamvoided.dusk_autumn.data.structure.DnDStructurePools
import org.teamvoided.dusk_autumn.data.structure.DnDStructureProcessorLists
import java.util.function.Function

@Suppress("MemberVisibilityCanBePrivate", "MagicNumber")
object StructurePoolCreator {
    fun bootstrap(c: BootstrapContext<StructurePool>) {
        val placedFeatures = c.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val structurePools = c.getRegistryLookup(RegistryKeys.STRUCTURE_POOL)
        val procLists = c.getRegistryLookup(RegistryKeys.STRUCTURE_PROCESSOR_LIST)

        val poolEmpty = structurePools.getHolderOrThrow(StructurePools.EMPTY)
        val procEmpty = procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)

        generateAutumnRuins(c, structurePools, poolEmpty, procLists, procEmpty, placedFeatures)
    }

    fun generateAutumnRuins(
        c: BootstrapContext<StructurePool>,
        structurePools: HolderProvider<StructurePool>,
        poolEmpty: Holder<StructurePool>,
        procLists: HolderProvider<StructureProcessorList>,
        procEmpty: Holder.Reference<StructureProcessorList>,
        placedFeatures: HolderProvider<PlacedFeature>

    ) {
        val procDefault = procLists.getHolderOrThrow(DnDStructureProcessorLists.AUTUMN_RUINS_DEFAULT)
        val default = "autumn_ruins/"
        val stone = default + "stone/stone_"
        c.register(
            DnDStructurePools.AUTUMN_RUINS_SINGLE,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedLegacySingle(default + "well_1", procDefault, 10),
                    pairedLegacySingle(default + "well_2", procDefault, 10),
                    pairedLegacySingle(stone + "remain_1", procDefault, 1),
                    pairedLegacySingle(stone + "remain_2", procDefault, 1),
                    pairedLegacySingle(stone + "remain_3", procDefault, 1),
                    pairedLegacySingle(stone + "remain_4", procDefault, 1),
                    pairedLegacySingle(stone + "rubble_1", procDefault, 3),
                    pairedLegacySingle(stone + "rubble_2", procDefault, 3),
                    pairedLegacySingle(stone + "ruin_1", procDefault, 3),
                    pairedLegacySingle(stone + "ruin_2", procDefault, 3),
                    pairedLegacySingle(stone + "ruin_3", procDefault, 3),
                    pairedLegacySingle(stone + "watch_1", procDefault, 1),
                    pairedLegacySingle(stone + "watch_2", procDefault, 2),
                ),
                StructurePool.Projection.RIGID
            )
        )
    }


    private fun id(str: String) = "$MODID:$str"

    fun pairedSingle(
        str: String, processors: Holder<StructureProcessorList>, weight: Int = 1
    ): Pair<Function<StructurePool.Projection, out StructurePoolElement>, Int> =
        Pair(processedSingle(str, processors), weight)

    fun processedSingle(
        str: String, processors: Holder<StructureProcessorList>
    ): Function<StructurePool.Projection, out StructurePoolElement> =
        StructurePoolElement.ofProcessedSingle(id(str), processors)


    fun pairedLegacySingle(
        str: String, processors: Holder<StructureProcessorList>, weight: Int = 1
    ): Pair<Function<StructurePool.Projection, out StructurePoolElement>, Int> =
        Pair(processedLegacySingle(str, processors), weight)

    fun processedLegacySingle(
        str: String, processors: Holder<StructureProcessorList>
    ): Function<StructurePool.Projection, out StructurePoolElement> =
        StructurePoolElement.ofProcessedLegacySingle(id(str), processors)


    fun pairedFeature(
        placedFeatures: Holder<PlacedFeature>, weight: Int = 1
    ): Pair<Function<StructurePool.Projection, out StructurePoolElement>, Int> =
        Pair(processedFeature(placedFeatures), weight)

    fun processedFeature(holder: Holder<PlacedFeature>): Function<StructurePool.Projection, out StructurePoolElement> =
        StructurePoolElement.ofFeature(holder)

}
