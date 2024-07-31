package org.teamvoided.dusk_autumn.data.gen.structure

import net.minecraft.block.Blocks
import net.minecraft.registry.BootstrapContext
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BlockTags
import net.minecraft.structure.processor.*
import net.minecraft.structure.rule.*
import org.teamvoided.dusk_autumn.data.structure.DnDStructureProcessorLists
import org.teamvoided.dusk_autumn.init.DnDBlocks


@Suppress("MagicNumber")
object StructureProcessorCreator {
    // StructureProcessorLists
    val flowerpotRule = RuleStructureProcessor(
        StructureProcessorRule(
            RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.3f),
            AlwaysTrueRuleTest.INSTANCE,
            Blocks.POTTED_BLUE_ORCHID.defaultState
        ),
        StructureProcessorRule(
            RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.1f),
            AlwaysTrueRuleTest.INSTANCE,
            Blocks.POTTED_RED_MUSHROOM.defaultState
        ),
        StructureProcessorRule(
            RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.2f),
            AlwaysTrueRuleTest.INSTANCE,
            Blocks.POTTED_BROWN_MUSHROOM.defaultState
        ),
        StructureProcessorRule(
            RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.1f),
            AlwaysTrueRuleTest.INSTANCE,
            Blocks.POTTED_CORNFLOWER.defaultState
        ),
        StructureProcessorRule(
            RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.1f),
            AlwaysTrueRuleTest.INSTANCE,
            DnDBlocks.POTTED_CASCADE_SAPLING.defaultState
        ),
        StructureProcessorRule(
            RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.1f),
            AlwaysTrueRuleTest.INSTANCE,
            Blocks.POTTED_DARK_OAK_SAPLING.defaultState
        ),
        StructureProcessorRule(
            RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.2f),
            AlwaysTrueRuleTest.INSTANCE,
            Blocks.POTTED_OAK_SAPLING.defaultState
        )
    )

    fun bootstrap(c: BootstrapContext<StructureProcessorList>) {
        val blockTags = c.getRegistryLookup(RegistryKeys.BLOCK)
        autumnRuinsProcessorLists(c)
    }

    fun autumnRuinsProcessorLists(c: BootstrapContext<StructureProcessorList>) {
        c.register(
            DnDStructureProcessorLists.AUTUMN_RUINS_DEFAULT,
            BlockRotStructureProcessor(0.95f),
            RuleStructureProcessor(
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.VINE, 0.5f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.AIR.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.DIRT_PATH, 0.2f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.GRASS_BLOCK.defaultState
                )
            ),
            flowerpotRule,
            ProtectedBlocksStructureProcessor(BlockTags.FEATURES_CANNOT_REPLACE)
        )
    }

    private fun BootstrapContext<StructureProcessorList>.register(
        key: RegistryKey<StructureProcessorList>, vararg procList: StructureProcessor
    ) = this.register(key, StructureProcessorList(procList.toList()))

    private fun RuleStructureProcessor(vararg procRules: StructureProcessorRule): RuleStructureProcessor =
        RuleStructureProcessor(procRules.toList())

}
