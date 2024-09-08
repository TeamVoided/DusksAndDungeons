package org.teamvoided.dusk_autumn.util

import net.fabricmc.fabric.api.registry.FlattenableBlockRegistry
import net.fabricmc.fabric.api.registry.TillableBlockRegistry
import net.minecraft.block.*
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.item.HoeItem
import net.minecraft.item.ItemConvertible
import net.minecraft.particle.DefaultParticleType
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.sound.SoundEvents
import org.teamvoided.dusk_autumn.block.*
import org.teamvoided.dusk_autumn.block.big.BigCandleBlock
import org.teamvoided.dusk_autumn.block.big.BigCandleCakeBlock
import org.teamvoided.dusk_autumn.block.big.BigSoulCandleBlock
import org.teamvoided.dusk_autumn.block.big.BigSoulCandleCakeBlock
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.blocks.DnDBigBlocks
import org.teamvoided.voidmill.sign.VoidCeilingHangingSignBlock
import org.teamvoided.voidmill.sign.VoidSignBlock
import org.teamvoided.voidmill.sign.VoidWallHangingSignBlock
import org.teamvoided.voidmill.sign.VoidWallSignBlock
import java.util.function.ToIntFunction

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
fun light(lightLevel: Int): ToIntFunction<BlockState> {
    return ToIntFunction { lightLevel }
}

fun Block.cutout(): Block {
    DnDBlocks.CUTOUT_BLOCKS.add(this)
    return this
}

fun Block.translucent(): Block {
    DnDBlocks.TRANSLUCENT_BLOCKS.add(this)
    return this
}

fun Block.grass(): Block {
    DnDBlocks.GRASS_TINT_BLOCKS.add(this)
    return this
}

fun Block.foliage(): Block {
    DnDBlocks.FOLIAGE_TINT_BLOCKS.add(this)
    return this
}

fun Block.flammablePlanks(): Block {
    DnDBlocks.FLAMMABLE_PLANKS.add(this)
    return this
}

fun Block.flammableLogs(): Block {
    DnDBlocks.FLAMMABLE_LOGS.add(this)
    return this
}

fun Block.flammableLeaves(): Block {
    DnDBlocks.FLAMMABLE_LEAVES.add(this)
    return this
}

fun Block.sword(): Block {
    DnDBlocks.SWORDABLE.add(this)
    return this
}

fun Block.pickaxe(): Block {
    DnDBlocks.PICKAXABLE.add(this)
    return this
}

fun Block.axe(): Block {
    DnDBlocks.AXABLE.add(this)
    return this
}

fun Block.shovel(): Block {
    DnDBlocks.SHOVELABLE.add(this)
    return this
}

fun Block.hoe(): Block {
    DnDBlocks.HOEABLE.add(this)
    return this
}

fun stairsOf(block: Block): Block = StairsBlock(block.defaultState, AbstractBlock.Settings.copy(block))

fun slabOf(block: Block): Block = SlabBlock(AbstractBlock.Settings.copy(block))

fun wallOf(block: Block): Block = WallBlock(AbstractBlock.Settings.copy(block).solid())

fun fenceOf(block: Block): Block = FenceBlock(AbstractBlock.Settings.copy(block).solid())

fun fenceGateOf(woodType: WoodType, block: Block): Block =
    FenceGateBlock(woodType, AbstractBlock.Settings.copy(block).solid())

fun doorOf(blockSetType: BlockSetType, block: Block): Block =
    DoorBlock(blockSetType, AbstractBlock.Settings.copy(block).strength(3.0f).nonOpaque())

fun trapdoorOf(blockSetType: BlockSetType, block: Block): Block =
    TrapdoorBlock(blockSetType, AbstractBlock.Settings.copy(block).allowsSpawning(Blocks::nonSpawnable))

fun pressurePlateOf(blockSetType: BlockSetType, block: Block): Block =
    PressurePlateBlock(
        blockSetType,
        AbstractBlock.Settings.copy(block).noCollision().strength(0.5f).solid().pistonBehavior(PistonBehavior.DESTROY)
    )

fun signOf(woodType: WoodType, block: Block): Block =
    VoidSignBlock(woodType, AbstractBlock.Settings.copy(block).solid().noCollision().strength(1.0f))

fun wallSignOf(woodType: WoodType, block: Block, sign: Block): Block =
    VoidWallSignBlock(
        woodType, AbstractBlock.Settings.copy(block).solid().noCollision().strength(1.0f)
            .dropsLike(sign)
    )

fun hangingSignOf(woodType: WoodType, block: Block): Block =
    VoidCeilingHangingSignBlock(woodType, AbstractBlock.Settings.copy(block).solid().noCollision().strength(1.0f))

fun wallHangingSignOf(woodType: WoodType, block: Block, hangingSign: Block): Block =
    VoidWallHangingSignBlock(
        woodType, AbstractBlock.Settings.copy(block).solid().noCollision().strength(1.0f)
            .dropsLike(hangingSign)
    )

fun candleSettings(): AbstractBlock.Settings = AbstractBlock.Settings.create().nonOpaque().strength(0.1f)
    .luminance(CandleBlock.STATE_TO_LUMINANCE).pistonBehavior(PistonBehavior.DESTROY)

fun bigCandleOf(color: MapColor): Block = BigCandleBlock(candleSettings().mapColor(color).sounds(bigCandleSound))

fun soulCandleOf(color: MapColor): Block =
    SoulCandleBlock(candleSettings().mapColor(color).sounds(BlockSoundGroup.CANDLE))

fun bigSoulCandleOf(color: MapColor): Block =
    BigSoulCandleBlock(candleSettings().mapColor(color).sounds(bigCandleSound))

fun bigCandleCakeOf(block: Block): Block =
    BigCandleCakeBlock(block, AbstractBlock.Settings.copy(DnDBigBlocks.BIG_CANDLE_CAKE))

fun bigCandleCakeOf(block: Block, candleCake: Block): Block =
    BigCandleCakeBlock(block, AbstractBlock.Settings.copy(candleCake))

fun soulCandleCakeOf(block: Block): Block = soulCandleCakeOf(block, DnDBigBlocks.SOUL_CANDLE_CAKE)

fun soulCandleCakeOf(block: Block, candleCake: Block): Block =
    SoulCandleCakeBlock(block, AbstractBlock.Settings.copy(candleCake))

fun bigSoulCandleCakeOf(block: Block): Block = bigSoulCandleCakeOf(block, DnDBigBlocks.BIG_SOUL_CANDLE_CAKE)

fun bigSoulCandleCakeOf(block: Block, candleCake: Block): Block =
    BigSoulCandleCakeBlock(block, AbstractBlock.Settings.copy(candleCake))

fun hollowLog(log: Block): Block = HollowLogWithCuttingBlock(AbstractBlock.Settings.copy(log))
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

fun fallingLeafPile(particle: DefaultParticleType, mapColor: MapColor): Block {
    return fallingLeafPile(particle, mapColor, BlockSoundGroup.GRASS)
}

fun fallingLeafPile(particle: DefaultParticleType, mapColor: MapColor, soundGroup: BlockSoundGroup): Block {
    return FallingLeafPileBlock(
        particle,
        AbstractBlock.Settings.create()
            .mapColor(mapColor).sounds(soundGroup).strength(0.2F).nonOpaque().suffocates(Blocks::nonSolid)
            .blockVision(Blocks::nonSolid).solidBlock(Blocks::nonSolid).lavaIgnitable().noCollision().nonSolid()
            .pistonBehavior(PistonBehavior.DESTROY)
    )
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