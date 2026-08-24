@file:Suppress("unused")

package org.teamvoided.dusks_and_dungeons.util.block

import net.fabricmc.fabric.api.registry.FlattenableBlockRegistry
import net.fabricmc.fabric.api.registry.TillableBlockRegistry
import net.minecraft.core.Direction
import net.minecraft.core.FrontAndTop
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.core.particles.ParticleTypes.SOUL_FIRE_FLAME
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.ColorRGBA
import net.minecraft.world.item.HoeItem
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.Blocks.*
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockSetType
import net.minecraft.world.level.block.state.properties.WoodType
import net.minecraft.world.level.material.MapColor
import net.minecraft.world.level.material.PushReaction
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.isDev
import org.teamvoided.dusks_and_dungeons.block.*
import org.teamvoided.dusks_and_dungeons.block.big.BigCandleBlock
import org.teamvoided.dusks_and_dungeons.block.big.BigCandleCakeBlock
import org.teamvoided.dusks_and_dungeons.block.big.SoulCandleBlock
import org.teamvoided.dusks_and_dungeons.block.big.SoulCandleCakeBlock
import org.teamvoided.dusks_and_dungeons.block.pumpkin.*
import org.teamvoided.dusks_and_dungeons.init.misc.DnDBlockSettings
import org.teamvoided.voidlib.consortium.block.set.AbstractBlockSet
import org.teamvoided.voidlib.helpers.addAndReturn
import org.teamvoided.voidmill.sign.VoidCeilingHangingSignBlock
import org.teamvoided.voidmill.sign.VoidSignBlock
import org.teamvoided.voidmill.sign.VoidWallHangingSignBlock
import org.teamvoided.voidmill.sign.VoidWallSignBlock
import java.util.function.ToIntFunction

@Suppress("DEPRECATION")
fun getId(block: Block): ResourceLocation = block.builtInRegistryHolder().key().location()

fun symmetricalBox(xzMin: Double, yMin: Double, xzMax: Double, yMax: Double): VoxelShape =
    Block.box(xzMin, yMin, xzMin, xzMax, yMax, xzMax)

fun symmetricalBoxY(xzMin: Double, yMin: Double, yMax: Double): VoxelShape =
    Block.box(xzMin, yMin, xzMin, 16 - xzMin, yMax, 16 - xzMin)

fun symmetricalBoxZ(xyMin: Double, zMin: Double, zMax: Double): VoxelShape =
    Block.box(xyMin, xyMin, zMin, 16 - xyMin, 16 - xyMin, zMax)

fun symmetricalBoxX(yzMin: Double, xMin: Double, xMax: Double): VoxelShape =
    Block.box(xMin, yzMin, yzMin, xMax, 16 - yzMin, 16 - yzMin)

//val SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 6.0)
//val CENTER_SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 5.0, 16.0, 16.0, 11.0)

// TODO(1.0) move these to gravestone block class
val gravestoneShape: VoxelShape = Shapes.or(
    Block.box(0.0, 0.0, 0.0, 2.0, 16.0, 6.0), //left
    Block.box(14.0, 0.0, 0.0, 16.0, 16.0, 6.0), //right
    Block.box(0.0, 13.0, 0.0, 16.0, 16.0, 6.0), //top
    Block.box(2.0, 0.0, 1.0, 14.0, 13.0, 5.0) //center
)
val centerGravestoneShape: VoxelShape = Shapes.or(
    Block.box(0.0, 0.0, 5.0, 2.0, 16.0, 11.0), //left
    Block.box(14.0, 0.0, 5.0, 16.0, 16.0, 11.0), //right
    Block.box(0.0, 13.0, 5.0, 16.0, 16.0, 11.0), //top
    Block.box(2.0, 0.0, 6.0, 14.0, 13.0, 10.0) //center
)

val smallGravestoneShape: VoxelShape = Block.box(3.0, 0.0, 0.0, 13.0, 12.0, 2.0)
val centerSmallGravestoneShape: VoxelShape = Block.box(3.0, 0.0, 7.0, 13.0, 12.0, 9.0)

val headstoneShape: VoxelShape = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 2.0)
val centerHeadstoneShape: VoxelShape = Block.box(0.0, 0.0, 7.0, 16.0, 16.0, 9.0)

fun BlockPlaceContext.isCrouching(): Boolean = this.player?.isCrouching == true

fun BlockPlaceContext.getOrientation(): FrontAndTop {
    val nearestLookingDirection: Direction = nearestLookingDirection.opposite
    val verticalDirection = when (nearestLookingDirection) {
        Direction.DOWN -> horizontalDirection.opposite
        Direction.UP -> horizontalDirection
        Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST -> Direction.UP
    }
    return FrontAndTop.fromFrontAndTop(nearestLookingDirection, verticalDirection)
}

fun light(lightLevel: Int): ToIntFunction<BlockState> = ToIntFunction { lightLevel }
fun BlockBehaviour.Properties.luminance(lightLevel: Int): BlockBehaviour.Properties = this.lightLevel { lightLevel }

val CUTOUT_BLOCKS = mutableSetOf<Block>()
val TRANSLUCENT_BLOCKS = mutableSetOf<Block>()
val GRASS_TINT_BLOCKS = mutableSetOf<Block>()
val TINT_PARTICLES = mutableSetOf<Block>()
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
fun Block.tint(): Block = TINT_PARTICLES.addAndReturn(this)
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
fun Block.rocky() = this.cutout().pickaxe().shovel()

fun AbstractBlockSet.cutout(): AbstractBlockSet = CUTOUT_BLOCKS.addSet(this)
fun AbstractBlockSet.translucent(): AbstractBlockSet = TRANSLUCENT_BLOCKS.addSet(this)
fun AbstractBlockSet.grass(): AbstractBlockSet = GRASS_TINT_BLOCKS.addSet(this)
fun AbstractBlockSet.tint(): AbstractBlockSet = TINT_PARTICLES.addSet(this)
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

// Custom Collections
val WOOD_SETS = mutableSetOf<AbstractBlockSet>()
fun AbstractBlockSet.woodSet(): AbstractBlockSet = WOOD_SETS.addDevSets(this)

// Block Helpers
fun <T : Any> MutableCollection<T>.addDev(element: T): T {
    if (isDev()) this.add(element)
    return element
}

fun blockOf(block: Block): Block = Block(ofFullCopy(block))

fun stairsOf(block: Block): Block = StairBlock(block.defaultBlockState(), ofFullCopy(block))

fun slabOf(block: Block): Block = SlabBlock(ofFullCopy(block))

fun wallOf(block: Block): Block = WallBlock(ofFullCopy(block).forceSolidOn())

fun fenceOf(block: Block): Block = FenceBlock(ofFullCopy(block).forceSolidOn())

fun fenceGateOf(woodType: WoodType, block: Block): Block =
    FenceGateBlock(woodType, ofFullCopy(block).forceSolidOn())

fun doorOf(blockSetType: BlockSetType, block: Block): Block =
    DoorBlock(blockSetType, ofFullCopy(block).strength(3.0f).noOcclusion())

fun trapdoorOf(blockSetType: BlockSetType, block: Block): Block =
    TrapDoorBlock(blockSetType, ofFullCopy(block).isValidSpawn(Blocks::never))

fun pressurePlateOf(blockSetType: BlockSetType, block: Block): Block =
    PressurePlateBlock(
        blockSetType,
        ofFullCopy(block).noCollission().strength(0.5f).forceSolidOn().pushReaction(PushReaction.DESTROY)
    )

fun signOf(woodType: WoodType, block: Block): Block =
    VoidSignBlock(woodType, ofFullCopy(block).forceSolidOn().noCollission().strength(1.0f))

fun wallSignOf(woodType: WoodType, block: Block, sign: Block): Block =
    VoidWallSignBlock(
        woodType, ofFullCopy(block).forceSolidOn().noCollission().strength(1.0f)
            .dropsLike(sign)
    )

fun hangingSignOf(woodType: WoodType, block: Block): Block =
    VoidCeilingHangingSignBlock(woodType, ofFullCopy(block).forceSolidOn().noCollission().strength(1.0f))

fun wallHangingSignOf(woodType: WoodType, block: Block, hangingSign: Block): Block =
    VoidWallHangingSignBlock(
        woodType, ofFullCopy(block).forceSolidOn().noCollission().strength(1.0f)
            .dropsLike(hangingSign)
    )


// Candles
fun bigCandleOf(candle: Block) = BigCandleBlock(ParticleTypes.FLAME, ofFullCopy(candle).sound(bigCandleSound))
fun bigCandleCakeOf(block: Block) = BigCandleCakeBlock(block, ParticleTypes.FLAME, ofFullCopy(CANDLE_CAKE))
fun candelabraOf(candle: Block) = CandelabraBlock(candle, ofFullCopy(candle).lightLevel(CandelabraBlock.LUMINANCE))

// Soul
fun soulCandleOf(candle: Block) = SoulCandleBlock(ofFullCopy(candle))
fun soulCandleCakeOf(block: Block) = SoulCandleCakeBlock(block, ofFullCopy(CANDLE_CAKE))
fun bigSoulCandleOf(candle: Block) = BigCandleBlock(SOUL_FIRE_FLAME, ofFullCopy(candle).sound(bigCandleSound))
fun bigSoulCandleCakeOf(block: Block) = BigCandleCakeBlock(block, SOUL_FIRE_FLAME, ofFullCopy(CANDLE_CAKE))

// Other
fun hollowLog(log: Block): Block = CuttableHollowLogBlock(ofFullCopy(log))
fun hollowBambooBlock(bambooBlock: Block): Block = HollowBambooBlock(ofFullCopy(bambooBlock))
fun logPile(log: Block): Block = LogPileBlock(ofFullCopy(log).noOcclusion())
fun logPile(log: Block, mapColor: MapColor): Block =
    LogPileBlock(ofFullCopy(log).mapColor(mapColor).noOcclusion())

fun leafPile(): Block = leafPile(MapColor.PLANT, SoundType.GRASS)
fun leafPile(soundGroup: SoundType): Block = leafPile(MapColor.PLANT, soundGroup)
fun leafPile(mapColor: MapColor): Block = leafPile(mapColor, SoundType.GRASS)

@Suppress("DEPRECATION")
fun leafPile(mapColor: MapColor, soundGroup: SoundType): Block {
    return LeafPileBlock(
        BlockBehaviour.Properties.of()
            .mapColor(mapColor).sound(soundGroup).strength(0.2F).noOcclusion().isSuffocating(Blocks::never)
            .isViewBlocking(Blocks::never).isRedstoneConductor(Blocks::never).ignitedByLava().noCollission()
            .forceSolidOff()
            .pushReaction(PushReaction.DESTROY)
    )
}

fun fallingLeafPile(particle: SimpleParticleType, mapColor: MapColor): Block =
    fallingLeafPile(particle, mapColor, SoundType.GRASS)

@Suppress("DEPRECATION")
fun fallingLeafPile(particle: SimpleParticleType, mapColor: MapColor, soundGroup: SoundType): Block =
    FallingLeafPileBlock(
        particle, BlockBehaviour.Properties.of()
            .mapColor(mapColor).sound(soundGroup).strength(0.2F).noOcclusion().isSuffocating(Blocks::never)
            .isViewBlocking(Blocks::never).isRedstoneConductor(Blocks::never).ignitedByLava().noCollission()
            .forceSolidOff()
            .pushReaction(PushReaction.DESTROY)
    )

fun pumpkinOf(block: Block) = DnDPumpkinBlock(block, ofFullCopy(block))
fun glowingPumpkinOf(block: Block) = DnDCarvedPumpkinBlock(ofFullCopy(block).luminance(15))
fun carvedPumpkin(color: MapColor) = DnDCarvedPumpkinBlock(ofFullCopy(CARVED_PUMPKIN).mapColor(color))

fun sPumpkinOf(block: Block) = SmallPumpkinBlock(block, ofFullCopy(block))
fun sGlowingPumpkinOf(block: Block) = SmallCarvedPumpkinBlock(ofFullCopy(block).luminance(15))
fun sCarvedPumpkinOf(block: Block) = SmallCarvedPumpkinBlock(DnDBlockSettings.smallPumpkin(block.defaultMapColor()))

fun stemOf(block: Block) = DnDPumpkinStemBlock(block, ofFullCopy(PUMPKIN_STEM))

fun gravel(color: MapColor) = ColoredFallingBlock(ColorRGBA(-8356741), ofFullCopy(GRAVEL).mapColor(color))
fun sand(color: MapColor) = ColoredFallingBlock(ColorRGBA(14406560), ofFullCopy(SAND).mapColor(color))
fun redSand(color: MapColor) = ColoredFallingBlock(ColorRGBA(11098145), ofFullCopy(RED_SAND).mapColor(color))

// Misc Registries
fun dirtPath(input: Block, output: Block) = FlattenableBlockRegistry.register(input, output.defaultBlockState())

fun removeRocks(input: Block, output: Block, craftingIngredient: ItemLike) = TillableBlockRegistry
    .register(input, { true }, HoeItem.changeIntoStateAndDropItem(output.defaultBlockState(), craftingIngredient))


// Set Helpers
fun MutableCollection<Block>.addSet(set: AbstractBlockSet): AbstractBlockSet {
    this.addAll(set.list)
    return set
}

// Use for populating tags when running data-gen in dev mode
fun MutableCollection<Block>.addDevSet(set: AbstractBlockSet): AbstractBlockSet {
    if (isDev()) this.addAll(set.list)
    return set
}

fun MutableCollection<AbstractBlockSet>.addDevSets(set: AbstractBlockSet): AbstractBlockSet {
    if (isDev()) this.add(set)
    return set
}

fun copy(set: AbstractBlockSet): BlockBehaviour.Properties = ofFullCopy(set.parent)
