package org.teamvoided.dusks_and_dungeons.data.gen.structure

import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.structure.templatesystem.*
import org.teamvoided.dusks_and_dungeons.data.structure.DnDStructureProcessorLists
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks


@Suppress("MagicNumber")
object StructureProcessorCreator {
    // StructureProcessorLists
    val flowerpotRule = RuleStructureProcessor(
        ProcessorRule(
            RandomBlockMatchTest(Blocks.FLOWER_POT, 0.3f),
            AlwaysTrueTest.INSTANCE,
            Blocks.POTTED_BLUE_ORCHID.defaultBlockState()
        ),
        ProcessorRule(
            RandomBlockMatchTest(Blocks.FLOWER_POT, 0.1f),
            AlwaysTrueTest.INSTANCE,
            Blocks.POTTED_RED_MUSHROOM.defaultBlockState()
        ),
        ProcessorRule(
            RandomBlockMatchTest(Blocks.FLOWER_POT, 0.2f),
            AlwaysTrueTest.INSTANCE,
            Blocks.POTTED_BROWN_MUSHROOM.defaultBlockState()
        ),
        ProcessorRule(
            RandomBlockMatchTest(Blocks.FLOWER_POT, 0.1f),
            AlwaysTrueTest.INSTANCE,
            Blocks.POTTED_CORNFLOWER.defaultBlockState()
        ),
        ProcessorRule(
            RandomBlockMatchTest(Blocks.FLOWER_POT, 0.1f),
            AlwaysTrueTest.INSTANCE,
            DnDBlocks.POTTED_CASCADE_SAPLING.defaultBlockState()
        ),
        ProcessorRule(
            RandomBlockMatchTest(Blocks.FLOWER_POT, 0.1f),
            AlwaysTrueTest.INSTANCE,
            Blocks.POTTED_DARK_OAK_SAPLING.defaultBlockState()
        ),
        ProcessorRule(
            RandomBlockMatchTest(Blocks.FLOWER_POT, 0.2f),
            AlwaysTrueTest.INSTANCE,
            Blocks.POTTED_OAK_SAPLING.defaultBlockState()
        )
    )

    fun bootstrap(c: BootstrapContext<StructureProcessorList>) {
        val blockTags = c.lookup(Registries.BLOCK)
        autumnRuinsProcessorLists(c)
    }

    fun autumnRuinsProcessorLists(c: BootstrapContext<StructureProcessorList>) {
        c.register(
            DnDStructureProcessorLists.AUTUMN_RUINS_DEFAULT,
            BlockRotProcessor(0.95f),
            RuleStructureProcessor(
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.VINE, 0.5f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.AIR.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.DIRT_PATH, 0.2f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.GRASS_BLOCK.defaultBlockState()
                )
            ),
            flowerpotRule,
            ProtectedBlockProcessor(BlockTags.FEATURES_CANNOT_REPLACE)
        )
    }

    private fun BootstrapContext<StructureProcessorList>.register(
        key: ResourceKey<StructureProcessorList>, vararg procList: StructureProcessor,
    ) = this.register(key, StructureProcessorList(procList.toList()))

    private fun RuleStructureProcessor(vararg procRules: ProcessorRule): RuleProcessor =
        RuleProcessor(procRules.toList())

}
