package org.teamvoided.dusks_and_dungeons.data.gen.data.structure

import com.mojang.datafixers.util.Pair
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.Pools
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool.Projection
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.data.gen.data.RegistryBootstrapper
import org.teamvoided.dusks_and_dungeons.data.structure.DnDStructurePools
import org.teamvoided.dusks_and_dungeons.data.structure.DnDStructureProcessorLists
import java.util.function.Function

object StructurePoolCreator : RegistryBootstrapper<StructureTemplatePool> {

    override fun BootstrapContext<StructureTemplatePool>.init() {
//        val placedFeatures = lookup(Registries.PLACED_FEATURE)
        val templatePools = lookup(Registries.TEMPLATE_POOL)
        val processorLists = lookup(Registries.PROCESSOR_LIST)

        val poolEmpty = templatePools.getOrThrow(Pools.EMPTY)
//        val procEmpty = processorLists.getOrThrow(ProcessorLists.EMPTY)

        val autumnRuins = processorLists.getOrThrow(DnDStructureProcessorLists.AUTUMN_RUINS_DEFAULT)
        val ruin = "autumn_ruins/"
        val stoneRuin = "${ruin}stone/stone_"
        register(
            DnDStructurePools.AUTUMN_RUINS_SINGLE,
            StructureTemplatePool(
                poolEmpty,
                listOf(
                    legacy(ruin + "well_1", autumnRuins, 10),
                    legacy(ruin + "well_2", autumnRuins, 10),
                    legacy(stoneRuin + "remain_1", autumnRuins, 1),
                    legacy(stoneRuin + "remain_2", autumnRuins, 1),
                    legacy(stoneRuin + "remain_3", autumnRuins, 1),
                    legacy(stoneRuin + "remain_4", autumnRuins, 1),
                    legacy(stoneRuin + "rubble_1", autumnRuins, 3),
                    legacy(stoneRuin + "rubble_2", autumnRuins, 3),
                    legacy(stoneRuin + "ruin_1", autumnRuins, 3),
                    legacy(stoneRuin + "ruin_2", autumnRuins, 3),
                    legacy(stoneRuin + "ruin_3", autumnRuins, 3),
                    legacy(stoneRuin + "watch_1", autumnRuins, 1),
                    legacy(stoneRuin + "watch_2", autumnRuins, 2),
                ),
                Projection.RIGID
            )
        )
    }

    typealias WeightedPool = Pair<Function<Projection, out StructurePoolElement>, Int>

    fun legacy(path: String, processors: Holder<StructureProcessorList>, weight: Int = 1): WeightedPool {
        return Pair(StructurePoolElement.legacy(id(path).toString(), processors), weight)
    }

    fun single(path: String, processors: Holder<StructureProcessorList>, weight: Int = 1): WeightedPool {
        return Pair(StructurePoolElement.single(id(path).toString(), processors), weight)
    }

    fun feature(feature: Holder<PlacedFeature>, weight: Int = 1): WeightedPool {
        return Pair(StructurePoolElement.feature(feature), weight)
    }

}