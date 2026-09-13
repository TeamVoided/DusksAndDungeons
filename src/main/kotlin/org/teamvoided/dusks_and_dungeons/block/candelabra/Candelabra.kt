package org.teamvoided.dusks_and_dungeons.block.candelabra

import net.minecraft.core.BlockPos
import net.minecraft.core.component.DataComponents
import net.minecraft.core.particles.DustParticleOptions
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.sounds.SoundSource
import net.minecraft.tags.ItemTags
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.Block.box
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.block.DnDBlockStateProperties.CANDLES
import org.teamvoided.dusks_and_dungeons.block.big.BigCandleBlock
import org.teamvoided.dusks_and_dungeons.block.big.SoulCandleBlock
import org.teamvoided.dusks_and_dungeons.block.candelabra.EmptyCandelabraBlock.Companion.FACING
import org.teamvoided.dusks_and_dungeons.block.entity.CandelabraBlockEntity
import org.teamvoided.dusks_and_dungeons.init.DnDBlockEntities
import org.teamvoided.dusks_and_dungeons.init.DnDDataComponents.CANDELABRA_CONTENTS
import org.teamvoided.dusks_and_dungeons.util.getFlameParticle
import org.teamvoided.dusks_and_dungeons.util.rotate
import org.teamvoided.dusks_and_dungeons.util.spawnCandleParticles
import org.teamvoided.voidlib.helpers.mc.rotateCW
import java.util.function.Predicate
import kotlin.jvm.optionals.getOrNull

object Candelabra {

    val SINGLE_SHAPE: VoxelShape = box(6.0, 0.0, 6.0, 10.0, 8.0, 10.0)
    val DOUBLE_SHAPE: VoxelShape = Shapes.or(
        box(6.0, 0.0, 6.0, 10.0, 4.0, 10.0),
        box(2.0, 4.0, 6.0, 14.0, 8.0, 10.0),
    )
    val TRIPLE_SHAPE: VoxelShape = Shapes.or(
        box(1.0, 4.0, 6.0, 15.0, 8.0, 10.0),
        box(6.0, 0.0, 6.0, 10.0, 10.0, 10.0),
    )
    val QUADRUPLE_SHAPE: VoxelShape = Shapes.or(
        box(6.0, 0.0, 6.0, 10.0, 4.0, 10.0),
        box(1.0, 4.0, 6.0, 15.0, 8.0, 10.0),
        box(6.0, 4.0, 1.0, 10.0, 8.0, 15.0),
    )
    val QUINTUPLE_SHAPE: VoxelShape = Shapes.or(
        box(6.0, 0.0, 6.0, 10.0, 10.0, 10.0),
        box(1.0, 4.0, 6.0, 15.0, 8.0, 10.0),
        box(6.0, 4.0, 1.0, 10.0, 8.0, 15.0),
    )

    val SHAPES = BlockStateProperties.HORIZONTAL_FACING.possibleValues.associateWith { dir ->
        CANDLES.possibleValues.associateWith { count ->
            when (count) {
                1 -> SINGLE_SHAPE
                2 -> DOUBLE_SHAPE
                3 -> TRIPLE_SHAPE
                4 -> QUADRUPLE_SHAPE
                5 -> QUINTUPLE_SHAPE
                else -> Shapes.block()
            }.rotate(dir.get2DDataValue())
        }
    }

    fun getBaseShape(state: BlockState): VoxelShape {
        return SHAPES[state.getValue(FACING)]?.get(state.getValue(CANDLES)) ?: Shapes.block()
    }

    const val PIXEL_SCALER = 0.0625

    /**
     * Offsets are original defied in pixels and then scaled in a map func
     */
    val OFFSETS = listOf(
        arrayOf(
            Vec3(0.0, 8.0, 0.0)
        ),
        arrayOf(
            Vec3(4.0, 8.0, 0.0),
            Vec3(-4.0, 8.0, 0.0),
        ),
        arrayOf(
            Vec3(5.0, 8.0, 0.0),
            Vec3(-5.0, 8.0, 0.0),
            Vec3(0.0, 10.0, 0.0),
        ),
        arrayOf(
            Vec3(5.0, 8.0, 0.0),
            Vec3(-5.0, 8.0, 0.0),
            Vec3(0.0, 8.0, 5.0),
            Vec3(0.0, 8.0, -5.0),
        ),
        arrayOf(
            Vec3(5.0, 8.0, 0.0),
            Vec3(-5.0, 8.0, 0.0),
            Vec3(0.0, 8.0, 5.0),
            Vec3(0.0, 8.0, -5.0),
            Vec3(0.0, 10.0, 0.0),
        )
    ).map { list -> list.map { it.scale(PIXEL_SCALER) }.toTypedArray() }.toTypedArray()

    val MISSING_OFFSET = Vec3(0.0, 1.0, 0.0)

    // TODO make this a tag
    fun canAddToCandelabra(stack: ItemStack): Boolean =
        stack.`is`(ItemTags.CANDLES) || stack.`is`(Items.HEAVY_CORE) || stack.`is`(Items.END_ROD)

    fun tryAddToCandelabra(
        level: Level, pos: BlockPos, state: BlockState, stack: ItemStack, player: Player, hit: BlockHitResult,
    ): Boolean {
        val candelabra = level.getBlockEntity(pos, DnDBlockEntities.CANDELABRA).getOrNull() ?: return false
        // check if candelabra is full
        if (state.getValue(CANDLES) == candelabra.getCandles().sumOf { if (it.isEmpty) 0 else 1 }) {
            return false
        }
        val slot = getSlot(pos, state, { !candelabra.isSlotFull(it) }, hit)
        if (candelabra.tryAddCandle(stack, slot)) {
            stack.consume(1, player)
            level.playSound(
                null,
                pos,
                candelabra.internalBlockStates[slot].soundType.placeSound,
                SoundSource.BLOCKS,
                1f,
                1f
            )
            return true
        }
        return false
    }

    fun getSlot(blockPos: BlockPos, state: BlockState, isSelectable: Predicate<Int>, hit: BlockHitResult): Int {
        val maxCandles = state.getValue(CANDLES)
        if (maxCandles == 1) {
            return 0
        }
        val hitPos = hit.location
        // make position be relative
        var pos = Vec3(hitPos.x - blockPos.x, 0.0, hitPos.z - blockPos.z)
        // rotate position to be default (south)
        val dir = state.getValue(HorizontalDirectionalBlock.FACING)
        pos = pos.rotateCW(dir.get2DDataValue())
        // get possible slots from shape
        val placements = PLACEMENTS.getOrNull(maxCandles - 2) ?: return -1
        placements.sortBy { it.first.distanceToSqr(pos) }
        for ((_, slot) in placements) {
            if (isSelectable.test(slot)) {
                return slot
            }
        }
        return -1
    }

    val PLACEMENTS = arrayOf(
        arrayOf(
            Vec3(4 / 16.0, 0.0, 0.5) to 0,
            Vec3(12 / 16.0, 0.0, 0.5) to 1,
        ),
        arrayOf(
            Vec3(3 / 16.0, 0.0, 0.5) to 0,
            Vec3(13 / 16.0, 0.0, 0.5) to 1,
            Vec3(0.5, 0.0, 0.5) to 2,
        ),
        arrayOf(
            Vec3(3 / 16.0, 0.0, 0.5) to 0,
            Vec3(13 / 16.0, 0.0, 0.5) to 1,
            Vec3(0.5, 0.0, 3 / 16.0) to 2,
            Vec3(0.5, 0.0, 13 / 16.0) to 3,
        ),
        arrayOf(
            Vec3(3 / 16.0, 0.0, 0.5) to 0,
            Vec3(13 / 16.0, 0.0, 0.5) to 1,
            Vec3(0.5, 0.0, 3 / 16.0) to 2,
            Vec3(0.5, 0.0, 13 / 16.0) to 3,
            Vec3(0.5, 0.0, 0.5) to 4,
        ),
    )

    fun spawnCandelabraParticles(
        be: CandelabraBlockEntity, pos: Vec3, level: Level, random: RandomSource, state: BlockState,
    ) {
        for ((idx, offset) in be.particleOffsets.withIndex()) {
            if (offset == null || be.internalBlockStates[idx].isAir) {
                continue
            }
            val inWorldPos = offset.add(pos)
            when (val block = be.internalBlockStates[idx].block) {
                is SoulCandleBlock -> block.spawnCandleParticles(level, inWorldPos, random)
                is BigCandleBlock -> block.spawnCandleParticles(level, inWorldPos, random)
                is CandleBlock -> level.spawnCandleParticles(inWorldPos, random)
                is TorchBlock -> block.spawnTorchParticles(level, inWorldPos)
                is RedstoneTorchBlock -> state.spawnRedstoneTorchParticles(level, inWorldPos, random)
            }
        }
    }

    fun TorchBlock.spawnTorchParticles(level: Level, offset: Vec3) {
        val x = offset.x
        val y = offset.y - (PIXEL_SCALER)
        val z = offset.z
        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0, 0.0, 0.0)
        level.addParticle(getFlameParticle(), x, y, z, 0.0, 0.0, 0.0)
    }

    fun BlockState.spawnRedstoneTorchParticles(level: Level, offset: Vec3, random: RandomSource) {
        if (getValue(RedstoneTorchBlock.LIT)) {
            val x = offset.x + (random.nextDouble() - 0.5) * 0.2
            val y = offset.y - (PIXEL_SCALER) + (random.nextDouble() - 0.5) * 0.2
            val z = offset.z + (random.nextDouble() - 0.5) * 0.2
            level.addParticle(DustParticleOptions.REDSTONE, x, y, z, 0.0, 0.0, 0.0)
        }
    }

    fun BlockGetter.getCandelabra(pos: BlockPos): CandelabraBlockEntity? {
        return getBlockEntity(pos, DnDBlockEntities.CANDELABRA)?.getOrNull()
    }

    fun getPickedBlock(player: Player, state: BlockState, stack: ItemStack): ItemStack {
        if (!player.isShiftKeyDown && state.getValue(CANDLES) > 1) {
            stack.set(CANDELABRA_CONTENTS, CandelabraContents.getStatic(state.getValue(EmptyCandelabraBlock.CANDLES)))
        }
        return stack
    }

    fun canReplace(ctx: BlockPlaceContext, state: BlockState, block: Block): Boolean {
        return (!ctx.isSecondaryUseActive
                && ctx.itemInHand.item === block.asItem()
                && !ctx.itemInHand.has(DataComponents.BLOCK_STATE)
                && (state.getValue(CANDLES) + getSlotCount(ctx.itemInHand)) <= 5)
    }

    fun cycleShapedFromItem(state: BlockState, stack: ItemStack): BlockState? {
        val simple = state.cycle(CANDLES)

        val addedCandles = getSlotCount(stack)
        if (addedCandles > 1) {
            val candles = state.getValue(CANDLES) + addedCandles
            return if (candles <= 5) state.setValue(CANDLES, candles) else null
        }
        return simple
    }

    fun getSlotCount(stack: ItemStack): Int = stack.get(CANDELABRA_CONTENTS)?.slots ?: 1

}