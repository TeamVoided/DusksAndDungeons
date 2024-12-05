package org.teamvoided.dusks_and_dungeons.util.block

import net.fabricmc.fabric.api.registry.FlattenableBlockRegistry
import net.fabricmc.fabric.api.registry.TillableBlockRegistry
import net.minecraft.block.*
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Blocks.*
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.item.HoeItem
import net.minecraft.item.ItemConvertible
import net.minecraft.particle.DefaultParticleType
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.state.property.Properties
import net.minecraft.util.Color
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.isDev
import org.teamvoided.dusks_and_dungeons.block.*
import org.teamvoided.dusks_and_dungeons.block.big.*
import org.teamvoided.dusks_and_dungeons.block.meltable.MeltableSlabBlock
import org.teamvoided.dusks_and_dungeons.block.meltable.MeltableStairsBlock
import org.teamvoided.dusks_and_dungeons.block.meltable.MeltableWallBlock
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDBigBlocks
import org.teamvoided.dusks_and_dungeons.init.misc.DnDBlockSettings
import org.teamvoided.voidlib.consortium.block.AbstractBlockSet
import org.teamvoided.voidlib.consortium.block.BlockSetBuilder
import org.teamvoided.voidlib.helpers.addAndReturn
import org.teamvoided.voidmill.sign.VoidCeilingHangingSignBlock
import org.teamvoided.voidmill.sign.VoidSignBlock
import org.teamvoided.voidmill.sign.VoidWallHangingSignBlock
import org.teamvoided.voidmill.sign.VoidWallSignBlock
import java.util.function.ToIntFunction


val FULL_CUBE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)

//private val SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 6.0)
//private val CENTER_SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 5.0, 16.0, 16.0, 11.0)
val gravestoneShape: VoxelShape = VoxelShapes.union(
    Block.createCuboidShape(0.0, 0.0, 0.0, 2.0, 16.0, 6.0), //left
    Block.createCuboidShape(14.0, 0.0, 0.0, 16.0, 16.0, 6.0), //right
    Block.createCuboidShape(0.0, 13.0, 0.0, 16.0, 16.0, 6.0), //top
    Block.createCuboidShape(2.0, 0.0, 1.0, 14.0, 13.0, 5.0) //center
)
val centerGravestoneShape: VoxelShape = VoxelShapes.union(
    Block.createCuboidShape(0.0, 0.0, 5.0, 2.0, 16.0, 11.0), //left
    Block.createCuboidShape(14.0, 0.0, 5.0, 16.0, 16.0, 11.0), //right
    Block.createCuboidShape(0.0, 13.0, 5.0, 16.0, 16.0, 11.0), //top
    Block.createCuboidShape(2.0, 0.0, 6.0, 14.0, 13.0, 10.0) //center
)

val smallGravestoneShape: VoxelShape = Block.createCuboidShape(3.0, 0.0, 0.0, 13.0, 12.0, 2.0)
val centerSmallGravestoneShape: VoxelShape = Block.createCuboidShape(3.0, 0.0, 7.0, 13.0, 12.0, 9.0)

val headstoneShape: VoxelShape = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 2.0)
val centerHeadstoneShape: VoxelShape = Block.createCuboidShape(0.0, 0.0, 7.0, 16.0, 16.0, 9.0)


fun light(lightLevel: Int): ToIntFunction<BlockState> = ToIntFunction { lightLevel }
fun AbstractBlock.Settings.luminance(lightLevel: Int): AbstractBlock.Settings = this.luminance { lightLevel }

val CUTOUT_BLOCKS = mutableSetOf<Block>()
val TRANSLUCENT_BLOCKS = mutableSetOf<Block>()
val GRASS_TINT_BLOCKS = mutableSetOf<Block>()
val FOLIAGE_TINT_BLOCKS = mutableSetOf<Block>()
val FLAMMABLE_PLANKS = mutableSetOf<Block>()
val FLAMMABLE_LOGS = mutableSetOf<Block>()
val FLAMMABLE_LEAVES = mutableSetOf<Block>()
val SWORDABLE = mutableSetOf<Block>()
val PICKAXABLE = mutableSetOf<Block>()
val AXABLE = mutableSetOf<Block>()
val SHOVELABLE = mutableSetOf<Block>()
val HOEABLE = mutableSetOf<Block>()

// Extensions
fun Block.cutout(): Block = CUTOUT_BLOCKS.addAndReturn(this)
fun Block.translucent(): Block = TRANSLUCENT_BLOCKS.addAndReturn(this)
fun Block.grass(): Block = GRASS_TINT_BLOCKS.addAndReturn(this)
fun Block.foliage(): Block = FOLIAGE_TINT_BLOCKS.addAndReturn(this)
fun Block.flammablePlanks(): Block = FLAMMABLE_PLANKS.addDev(this)
fun Block.flammableLogs(): Block = FLAMMABLE_LOGS.addDev(this)
fun Block.flammableLeaves(): Block = FLAMMABLE_LEAVES.addDev(this)
fun Block.sword(): Block = SWORDABLE.addDev(this)
fun Block.pickaxe(): Block = PICKAXABLE.addDev(this)
fun Block.axe(): Block = AXABLE.addDev(this)
fun Block.shovel(): Block = SHOVELABLE.addDev(this)
fun Block.hoe(): Block = HOEABLE.addDev(this)

fun Block.plant() = this.cutout().sword().hoe()
fun Block.grassLike() = this.cutout().sword().axe()
fun Block.leaves() = this.cutout().hoe().flammableLeaves()
fun Block.wood() = this.axe().flammablePlanks()
fun Block.rocky() = cutout().pickaxe().shovel()

fun AbstractBlockSet.cutout(): AbstractBlockSet = CUTOUT_BLOCKS.addSet(this)
fun AbstractBlockSet.translucent(): AbstractBlockSet = TRANSLUCENT_BLOCKS.addSet(this)
fun AbstractBlockSet.grass(): AbstractBlockSet = GRASS_TINT_BLOCKS.addSet(this)
fun AbstractBlockSet.foliage(): AbstractBlockSet = FOLIAGE_TINT_BLOCKS.addSet(this)
fun AbstractBlockSet.flammablePlanks(): AbstractBlockSet = FLAMMABLE_PLANKS.addDevSet(this)
fun AbstractBlockSet.flammableLogs(): AbstractBlockSet = FLAMMABLE_LOGS.addDevSet(this)
fun AbstractBlockSet.flammableLeaves(): AbstractBlockSet = FLAMMABLE_LEAVES.addDevSet(this)
fun AbstractBlockSet.sword(): AbstractBlockSet = SWORDABLE.addDevSet(this)
fun AbstractBlockSet.pickaxe(): AbstractBlockSet = PICKAXABLE.addDevSet(this)
fun AbstractBlockSet.axe(): AbstractBlockSet = AXABLE.addDevSet(this)
fun AbstractBlockSet.shovel(): AbstractBlockSet = SHOVELABLE.addDevSet(this)
fun AbstractBlockSet.hoe(): AbstractBlockSet = HOEABLE.addDevSet(this)

fun AbstractBlockSet.overgrown(): AbstractBlockSet = this.cutout().grass().pickaxe()

// Block Helpers
fun <T : Any> MutableCollection<T>.addDev(element: T): T {
    if (isDev()) this.add(element)
    return element
}

fun blockOf(block: Block): Block = Block(copy(block))

fun stairsOf(block: Block): Block = StairsBlock(block.defaultState, copy(block))

fun slabOf(block: Block): Block = SlabBlock(copy(block))

fun wallOf(block: Block): Block = WallBlock(copy(block).solid())

fun fenceOf(block: Block): Block = FenceBlock(copy(block).solid())

fun fenceGateOf(woodType: WoodType, block: Block): Block =
    FenceGateBlock(woodType, copy(block).solid())

fun doorOf(blockSetType: BlockSetType, block: Block): Block =
    DoorBlock(blockSetType, copy(block).strength(3.0f).nonOpaque())

fun trapdoorOf(blockSetType: BlockSetType, block: Block): Block =
    TrapdoorBlock(blockSetType, copy(block).allowsSpawning(Blocks::nonSpawnable))

fun pressurePlateOf(blockSetType: BlockSetType, block: Block): Block =
    PressurePlateBlock(
        blockSetType,
        copy(block).noCollision().strength(0.5f).solid().pistonBehavior(PistonBehavior.DESTROY)
    )

fun signOf(woodType: WoodType, block: Block): Block =
    VoidSignBlock(woodType, copy(block).solid().noCollision().strength(1.0f))

fun wallSignOf(woodType: WoodType, block: Block, sign: Block): Block =
    VoidWallSignBlock(
        woodType, copy(block).solid().noCollision().strength(1.0f)
            .dropsLike(sign)
    )

fun hangingSignOf(woodType: WoodType, block: Block): Block =
    VoidCeilingHangingSignBlock(woodType, copy(block).solid().noCollision().strength(1.0f))

fun wallHangingSignOf(woodType: WoodType, block: Block, hangingSign: Block): Block =
    VoidWallHangingSignBlock(
        woodType, copy(block).solid().noCollision().strength(1.0f)
            .dropsLike(hangingSign)
    )

fun candleSettings(): AbstractBlock.Settings = AbstractBlock.Settings.create().nonOpaque().strength(0.1f)
    .luminance(CandleBlock.STATE_TO_LUMINANCE).pistonBehavior(PistonBehavior.DESTROY)

fun bigCandleOf(color: MapColor): Block =
    BigCandleBlock(ParticleTypes.FLAME, candleSettings().mapColor(color).sounds(bigCandleSound))

fun soulCandleOf(color: MapColor): Block =
    SoulCandleBlock(candleSettings().mapColor(color).sounds(BlockSoundGroup.CANDLE))

fun bigSoulCandleOf(color: MapColor): Block =
    BigCandleBlock(ParticleTypes.SOUL_FIRE_FLAME, candleSettings().mapColor(color).sounds(bigCandleSound))

fun bigCandleCakeOf(block: Block): Block =
    BigCandleCakeBlock(block, ParticleTypes.FLAME, copy(DnDBigBlocks.BIG_CANDLE_CAKE))

fun bigCandleCakeOf(block: Block, candleCake: Block): Block =
    BigCandleCakeBlock(block, ParticleTypes.FLAME, copy(candleCake))

fun soulCandleCakeOf(block: Block): Block = soulCandleCakeOf(block, DnDBigBlocks.SOUL_CANDLE_CAKE)

fun soulCandleCakeOf(block: Block, candleCake: Block): Block =
    SoulCandleCakeBlock(block, copy(candleCake))

fun bigSoulCandleCakeOf(block: Block): Block = bigSoulCandleCakeOf(block, DnDBigBlocks.BIG_SOUL_CANDLE_CAKE)

fun bigSoulCandleCakeOf(block: Block, candleCake: Block): Block =
    BigCandleCakeBlock(block, ParticleTypes.SOUL_FIRE_FLAME, copy(candleCake))

fun bigTallCandleOf(color: MapColor): Block =
    BigTallCandleBlock(ParticleTypes.FLAME, candleSettings().mapColor(color).sounds(bigCandleSound))

fun bigTallSoulCandleOf(color: MapColor): Block =
    BigTallCandleBlock(ParticleTypes.SOUL_FIRE_FLAME, candleSettings().mapColor(color).sounds(bigCandleSound))

fun candelabraOf(candle: Block): CandelabraBlock = CandelabraBlock(
    candle, copy(candle).luminance { if (it.get(Properties.LIT)) 3 * it.get(CandelabraBlock.CANDLES) else 0 }
)

fun hollowLog(log: Block): Block = HollowLogWithCuttingBlock(copy(log))
fun hollowBambooBlock(bambooBlock: Block): Block = HollowBambooBlock(copy(bambooBlock))
fun logPile(log: Block): Block = LogPileBlock(copy(log).nonOpaque())
fun logPile(log: Block, mapColor: MapColor): Block =
    LogPileBlock(copy(log).mapColor(mapColor).nonOpaque())

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

fun fallingLeafPile(particle: DefaultParticleType, mapColor: MapColor): Block =
    fallingLeafPile(particle, mapColor, BlockSoundGroup.GRASS)

fun fallingLeafPile(particle: DefaultParticleType, mapColor: MapColor, soundGroup: BlockSoundGroup): Block =
    FallingLeafPileBlock(
        particle, AbstractBlock.Settings.create()
            .mapColor(mapColor).sounds(soundGroup).strength(0.2F).nonOpaque().suffocates(Blocks::nonSolid)
            .blockVision(Blocks::nonSolid).solidBlock(Blocks::nonSolid).lavaIgnitable().noCollision().nonSolid()
            .pistonBehavior(PistonBehavior.DESTROY)
    )

fun pumpkinOf(block: Block) = DnDPumpkinBlock(block, copy(block))
fun glowingPumpkinOf(block: Block) = DnDCarvedPumpkinBlock(copy(block).luminance(15))
fun carvedPumpkin(color: MapColor) = DnDCarvedPumpkinBlock(copy(CARVED_PUMPKIN).mapColor(color))

fun sPumpkinOf(block: Block) = SmallPumpkinBlock(block, copy(block))
fun sGlowingPumpkinOf(block: Block) = SmallCarvedPumpkinBlock(copy(block).luminance(15))
fun sCarvedPumpkinOf(block: Block) = SmallCarvedPumpkinBlock(DnDBlockSettings.smallPumpkin(block.defaultMapColor))

fun stemOf(block: Block) = DnDPumpkinStemBlock(block, copy(PUMPKIN_STEM))

fun gravel(color: MapColor) = GravelBlock(Color(-8356741), copy(GRAVEL).mapColor(color))
fun sand(color: MapColor) = GravelBlock(Color(14406560), copy(SAND).mapColor(color))
fun redSand(color: MapColor) = GravelBlock(Color(11098145), copy(RED_SAND).mapColor(color))

// Misc Registries
fun dirtPath(input: Block, output: Block) = FlattenableBlockRegistry.register(input, output.defaultState)

fun removeRocks(input: Block, output: Block, craftingIngredient: ItemConvertible) = TillableBlockRegistry
    .register(input, { true }, HoeItem.createTillAndDropAction(output.defaultState, craftingIngredient))


// Set Helpers
fun MutableCollection<Block>.addSet(set: AbstractBlockSet): AbstractBlockSet {
    this.addAll(set.collect())
    return set
}

fun MutableCollection<Block>.addDevSet(set: AbstractBlockSet): AbstractBlockSet {
    if (isDev()) this.addAll(set.collect())
    return set
}

fun copy(set: AbstractBlockSet): AbstractBlock.Settings = copy(set.parent)

fun BlockSetBuilder.meltable(): BlockSetBuilder =
    this.stairs(::MeltableStairsBlock).slab(::MeltableSlabBlock).wall(::MeltableWallBlock)