package org.teamvoided.dusk_autumn.util

import net.fabricmc.fabric.api.registry.FlattenableBlockRegistry
import net.fabricmc.fabric.api.registry.TillableBlockRegistry
import net.minecraft.block.*
import net.minecraft.block.enums.NoteBlockInstrument
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.item.HoeItem
import net.minecraft.item.ItemConvertible
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.sound.SoundEvents
import org.teamvoided.dusk_autumn.block.*
import org.teamvoided.dusk_autumn.block.big.BigCandleBlock
import org.teamvoided.dusk_autumn.block.big.BigCandleCakeBlock
import org.teamvoided.dusk_autumn.block.big.BigSoulCandleBlock
import org.teamvoided.dusk_autumn.block.big.BigSoulCandleCakeBlock
import org.teamvoided.dusk_autumn.init.DnDBlocks

val bonewoodSound = BlockSoundGroup(
    1.0F,
    0.8F,
    SoundEvents.BLOCK_BONE_BLOCK_BREAK,
    SoundEvents.BLOCK_BONE_BLOCK_STEP,
    SoundEvents.BLOCK_BONE_BLOCK_PLACE,
    SoundEvents.BLOCK_BONE_BLOCK_HIT,
    SoundEvents.BLOCK_BONE_BLOCK_FALL
)
val witheringBonewoodSound = BlockSoundGroup(
    1.0F,
    0.0F,
    SoundEvents.BLOCK_BONE_BLOCK_BREAK,
    SoundEvents.BLOCK_BONE_BLOCK_STEP,
    SoundEvents.BLOCK_BONE_BLOCK_PLACE,
    SoundEvents.BLOCK_BONE_BLOCK_HIT,
    SoundEvents.BLOCK_BONE_BLOCK_FALL
)
val bigChainSound = BlockSoundGroup(
    1.0F,
    0.8F,
    SoundEvents.BLOCK_CHAIN_BREAK,
    SoundEvents.BLOCK_CHAIN_STEP,
    SoundEvents.BLOCK_CHAIN_PLACE,
    SoundEvents.BLOCK_CHAIN_HIT,
    SoundEvents.BLOCK_CHAIN_FALL
)
val bigLanternSound = BlockSoundGroup(
    1.0F,
    0.8F,
    SoundEvents.BLOCK_LANTERN_BREAK,
    SoundEvents.BLOCK_LANTERN_STEP,
    SoundEvents.BLOCK_LANTERN_PLACE,
    SoundEvents.BLOCK_LANTERN_HIT,
    SoundEvents.BLOCK_LANTERN_FALL
)
val bigCandleSound = BlockSoundGroup(
    1.0F,
    0.8F,
    SoundEvents.BLOCK_CANDLE_BREAK,
    SoundEvents.BLOCK_CANDLE_STEP,
    SoundEvents.BLOCK_CANDLE_PLACE,
    SoundEvents.BLOCK_CANDLE_HIT,
    SoundEvents.BLOCK_CANDLE_FALL
)
val rootBlockSound = BlockSoundGroup(
    1f,
    0.8f,
    SoundEvents.BLOCK_HANGING_ROOTS_BREAK,
    SoundEvents.BLOCK_HANGING_ROOTS_STEP,
    SoundEvents.BLOCK_HANGING_ROOTS_PLACE,
    SoundEvents.BLOCK_HANGING_ROOTS_HIT,
    SoundEvents.BLOCK_HANGING_ROOTS_FALL
)

fun stairsOf(block: Block): Block = StairsBlock(block.defaultState, AbstractBlock.Settings.copy(block))

fun slabOf(block: Block): Block = SlabBlock(AbstractBlock.Settings.copy(block))

fun wallOf(block: Block): Block = WallBlock(AbstractBlock.Settings.copy(block).solid())

fun fenceOf(block: Block): Block = FenceBlock(AbstractBlock.Settings.copy(block).solid())

fun fenceGateOf(woodType: WoodType, block: Block): Block =
    FenceGateBlock(woodType, AbstractBlock.Settings.copy(block).solid())

fun candleSettings(): AbstractBlock.Settings = AbstractBlock.Settings.create().nonOpaque().strength(0.1f)
    .luminance(CandleBlock.STATE_TO_LUMINANCE).pistonBehavior(PistonBehavior.DESTROY)

fun bigCandleOf(color: MapColor): Block = BigCandleBlock(candleSettings().mapColor(color).sounds(bigCandleSound))

fun soulCandleOf(color: MapColor): Block =
    SoulCandleBlock(candleSettings().mapColor(color).sounds(BlockSoundGroup.CANDLE))

fun bigSoulCandleOf(color: MapColor): Block =
    BigSoulCandleBlock(candleSettings().mapColor(color).sounds(bigCandleSound))

fun bigCandleCakeOf(block: Block): Block =
    BigCandleCakeBlock(block, AbstractBlock.Settings.copy(DnDBlocks.BIG_CANDLE_CAKE))

fun bigCandleCakeOf(block: Block, candleCake: Block): Block =
    BigCandleCakeBlock(block, AbstractBlock.Settings.copy(candleCake))

fun soulCandleCakeOf(block: Block): Block = soulCandleCakeOf(block, DnDBlocks.SOUL_CANDLE_CAKE)

fun soulCandleCakeOf(block: Block, candleCake: Block): Block =
    SoulCandleCakeBlock(block, AbstractBlock.Settings.copy(candleCake))

fun bigSoulCandleCakeOf(block: Block): Block = bigSoulCandleCakeOf(block, DnDBlocks.BIG_SOUL_CANDLE_CAKE)

fun bigSoulCandleCakeOf(block: Block, candleCake: Block): Block =
    BigSoulCandleCakeBlock(block, AbstractBlock.Settings.copy(candleCake))

fun hollowLog(log: Block): Block = HollowLogBlockWithCutting(AbstractBlock.Settings.copy(log))
fun hollowBambooBlock(bambooBlock: Block): Block = HollowBambooBlock(AbstractBlock.Settings.copy(bambooBlock))
fun logPile(log: Block): Block = LogPileBlock(AbstractBlock.Settings.copy(log).nonOpaque())
fun logPile(log: Block, mapColor: MapColor): Block =
    LogPileBlock(AbstractBlock.Settings.copy(log).mapColor(mapColor).nonOpaque())

fun leafPile(): Block = leafPile(MapColor.PLANT, BlockSoundGroup.GRASS)
fun leafPile(soundGroup: BlockSoundGroup): Block = leafPile(MapColor.PLANT, soundGroup)
fun leafPile(mapColor: MapColor): Block = leafPile(mapColor, BlockSoundGroup.GRASS)

fun leafPile(mapColor: MapColor, soundGroup: BlockSoundGroup): Block {
    return LeafPileBlock(
        AbstractBlock.Settings.create()
            .mapColor(mapColor).sounds(soundGroup).strength(0.2F).nonOpaque().suffocates(Blocks::nonSolid)
            .blockVision(Blocks::nonSolid).solidBlock(Blocks::nonSolid).lavaIgnitable().noCollision().nonSolid()
            .pistonBehavior(PistonBehavior.DESTROY)
    )
}

fun fallingLeafPile(mapColor: MapColor, soundGroup: BlockSoundGroup): AbstractBlock.Settings {
    return AbstractBlock.Settings.create()
        .mapColor(mapColor).sounds(soundGroup).strength(0.2F).nonOpaque().suffocates(Blocks::nonSolid)
        .blockVision(Blocks::nonSolid).solidBlock(Blocks::nonSolid).lavaIgnitable().noCollision().nonSolid()
        .pistonBehavior(PistonBehavior.DESTROY)
}

fun dirtPath(input: Block, output: Block) {
    FlattenableBlockRegistry.register(input, output.defaultState)
}

fun removeRocks(input: Block, output: Block, craftingIngredient: ItemConvertible) {
    TillableBlockRegistry.register(
        input, { true },
        HoeItem.createTillAndDropAction(output.defaultState, craftingIngredient)
    )
}