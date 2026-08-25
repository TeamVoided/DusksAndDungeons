package org.teamvoided.dusks_and_dungeons.datagen.data.structure

import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.structure.templatesystem.*
import org.teamvoided.dusks_and_dungeons.datagen.data.RegistryBootstrapper
import org.teamvoided.dusks_and_dungeons.data.structure.DnDProcessorLists
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks


object ModProcessorLists : RegistryBootstrapper<StructureProcessorList> {

    override fun BootstrapContext<StructureProcessorList>.init() {
        autumnRuinsProcessorLists()
    }

    fun BootstrapContext<StructureProcessorList>.autumnRuinsProcessorLists() {
        register(
            DnDProcessorLists.AUTUMN_RUINS_DEFAULT,
            BlockRotProcessor(0.95f),
            ruleProcessor(
                chanceReplaceRule(Blocks.VINE, 0.5f, Blocks.AIR),
                chanceReplaceRule(Blocks.DIRT_PATH, 0.2f, Blocks.GRASS_BLOCK)
            ),
            flowerpotRule,
            ProtectedBlockProcessor(BlockTags.FEATURES_CANNOT_REPLACE)
        )
    }

    // StructureProcessorLists
    val flowerpotRule = ruleProcessor(
        chanceReplaceRule(Blocks.FLOWER_POT, 0.3f, Blocks.POTTED_BLUE_ORCHID),
        chanceReplaceRule(Blocks.FLOWER_POT, 0.1f, Blocks.POTTED_RED_MUSHROOM),
        chanceReplaceRule(Blocks.FLOWER_POT, 0.2f, Blocks.POTTED_BROWN_MUSHROOM),
        chanceReplaceRule(Blocks.FLOWER_POT, 0.1f, Blocks.POTTED_CORNFLOWER),
        chanceReplaceRule(Blocks.FLOWER_POT, 0.1f, DnDBlocks.POTTED_CASCADE_SAPLING),
        chanceReplaceRule(Blocks.FLOWER_POT, 0.1f, Blocks.POTTED_DARK_OAK_SAPLING),
        chanceReplaceRule(Blocks.FLOWER_POT, 0.2f, Blocks.POTTED_OAK_SAPLING)
    )


    fun ruleProcessor(vararg rules: ProcessorRule): RuleProcessor = RuleProcessor(rules.toList())

    fun chanceReplaceRule(input: Block, replaceChance: Float, output: Block): ProcessorRule {
        return chanceReplaceRule(input, replaceChance, output.defaultBlockState())
    }

    fun chanceReplaceRule(input: Block, replaceChance: Float, output: BlockState): ProcessorRule {
        return ProcessorRule(RandomBlockMatchTest(input, replaceChance), AlwaysTrueTest.INSTANCE, output)
    }

    fun BootstrapContext<StructureProcessorList>.register(
        key: ResourceKey<StructureProcessorList>, vararg processors: StructureProcessor,
    ) {
        register(key, StructureProcessorList(processors.toList()))
    }

}