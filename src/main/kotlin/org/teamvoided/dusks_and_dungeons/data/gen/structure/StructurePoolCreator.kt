package org.teamvoided.dusks_and_dungeons.data.gen.structure

import com.mojang.datafixers.util.Pair
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.core.Holder
import net.minecraft.core.HolderGetter
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement
import net.minecraft.data.worldgen.Pools
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList
import net.minecraft.data.worldgen.ProcessorLists
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.MODID
import org.teamvoided.dusks_and_dungeons.data.structure.DnDStructurePools
import org.teamvoided.dusks_and_dungeons.data.structure.DnDStructureProcessorLists
import java.util.function.Function

@Suppress("MemberVisibilityCanBePrivate", "MagicNumber")
object StructurePoolCreator {
    fun bootstrap(c: BootstrapContext<StructureTemplatePool>) {
        val placedFeatures = c.lookup(Registries.PLACED_FEATURE)
        val structurePools = c.lookup(Registries.TEMPLATE_POOL)
        val procLists = c.lookup(Registries.PROCESSOR_LIST)

        val poolEmpty = structurePools.getOrThrow(Pools.EMPTY)
        val procEmpty = procLists.getOrThrow(ProcessorLists.EMPTY)

        generateAutumnRuins(c, structurePools, poolEmpty, procLists, procEmpty, placedFeatures)
    }

    fun generateAutumnRuins(
        c: BootstrapContext<StructureTemplatePool>,
        structurePools: HolderGetter<StructureTemplatePool>,
        poolEmpty: Holder<StructureTemplatePool>,
        procLists: HolderGetter<StructureProcessorList>,
        procEmpty: Holder.Reference<StructureProcessorList>,
        placedFeatures: HolderGetter<PlacedFeature>

    ) {
        val procDefault = procLists.getOrThrow(DnDStructureProcessorLists.AUTUMN_RUINS_DEFAULT)
        val default = "autumn_ruins/"
        val stone = default + "stone/stone_"
        c.register(
            DnDStructurePools.AUTUMN_RUINS_SINGLE,
            StructureTemplatePool(
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
                StructureTemplatePool.Projection.RIGID
            )
        )
    }


    private fun id(str: String) = "$MODID:$str"

    fun pairedSingle(
        str: String, processors: Holder<StructureProcessorList>, weight: Int = 1
    ): Pair<Function<StructureTemplatePool.Projection, out StructurePoolElement>, Int> =
        Pair(processedSingle(str, processors), weight)

    fun processedSingle(
        str: String, processors: Holder<StructureProcessorList>
    ): Function<StructureTemplatePool.Projection, out StructurePoolElement> =
        StructurePoolElement.single(id(str), processors)


    fun pairedLegacySingle(
        str: String, processors: Holder<StructureProcessorList>, weight: Int = 1
    ): Pair<Function<StructureTemplatePool.Projection, out StructurePoolElement>, Int> =
        Pair(processedLegacySingle(str, processors), weight)

    fun processedLegacySingle(
        str: String, processors: Holder<StructureProcessorList>
    ): Function<StructureTemplatePool.Projection, out StructurePoolElement> =
        StructurePoolElement.legacy(id(str), processors)


    fun pairedFeature(
        placedFeatures: Holder<PlacedFeature>, weight: Int = 1
    ): Pair<Function<StructureTemplatePool.Projection, out StructurePoolElement>, Int> =
        Pair(processedFeature(placedFeatures), weight)

    fun processedFeature(holder: Holder<PlacedFeature>): Function<StructureTemplatePool.Projection, out StructurePoolElement> =
        StructurePoolElement.feature(holder)

}
