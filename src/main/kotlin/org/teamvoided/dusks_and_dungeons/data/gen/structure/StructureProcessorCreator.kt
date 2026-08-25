package org.teamvoided.dusks_and_dungeons.data.gen.structure

import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.structure.templatesystem.*
import org.teamvoided.dusks_and_dungeons.data.structure.DnDStructureProcessorLists
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks


@Suppress("MagicNumber")
object StructureProcessorCreator {
    // StructureProcessorLists
    val flowerpotRule = ruleStructureProcessor(
        chanceReplaceRule(Blocks.FLOWER_POT, 0.3f, Blocks.POTTED_BLUE_ORCHID),
        chanceReplaceRule(Blocks.FLOWER_POT, 0.1f, Blocks.POTTED_RED_MUSHROOM),
        chanceReplaceRule(Blocks.FLOWER_POT, 0.2f, Blocks.POTTED_BROWN_MUSHROOM),
        chanceReplaceRule(Blocks.FLOWER_POT, 0.1f, Blocks.POTTED_CORNFLOWER),
        chanceReplaceRule(Blocks.FLOWER_POT, 0.1f, DnDBlocks.POTTED_CASCADE_SAPLING),
        chanceReplaceRule(Blocks.FLOWER_POT, 0.1f, Blocks.POTTED_DARK_OAK_SAPLING),
        chanceReplaceRule(Blocks.FLOWER_POT, 0.2f, Blocks.POTTED_OAK_SAPLING)
    )

    fun bootstrap(c: BootstrapContext<StructureProcessorList>) {
        val blockTags = c.lookup(Registries.BLOCK)
        autumnRuinsProcessorLists(c)
    }

    fun autumnRuinsProcessorLists(c: BootstrapContext<StructureProcessorList>) {
        c.register(
            DnDStructureProcessorLists.AUTUMN_RUINS_DEFAULT,
            BlockRotProcessor(0.95f),
            ruleStructureProcessor(
                chanceReplaceRule(Blocks.VINE, 0.5f, Blocks.AIR),
                chanceReplaceRule(Blocks.DIRT_PATH, 0.2f, Blocks.GRASS_BLOCK)
            ),
            flowerpotRule,
            ProtectedBlockProcessor(BlockTags.FEATURES_CANNOT_REPLACE)
        )
    }

    fun chanceReplaceRule(input: Block, replaceChance: Float, output: Block): ProcessorRule =
        chanceReplaceRule(input, replaceChance, output.defaultBlockState())

    fun chanceReplaceRule(input: Block, replaceChance: Float, output: BlockState): ProcessorRule =
        ProcessorRule(RandomBlockMatchTest(input, replaceChance), AlwaysTrueTest.INSTANCE, output)


    private fun BootstrapContext<StructureProcessorList>.register(
        key: ResourceKey<StructureProcessorList>, vararg procList: StructureProcessor,
    ) = this.register(key, StructureProcessorList(procList.toList()))

    private fun ruleStructureProcessor(vararg procRules: ProcessorRule): RuleProcessor =
        RuleProcessor(procRules.toList())

}
