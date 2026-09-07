package org.teamvoided.dusks_and_dungeons.block.candelabra

import net.minecraft.core.BlockPos
import net.minecraft.core.particles.DustParticleOptions
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.tags.ItemTags
import net.minecraft.util.RandomSource
import net.minecraft.world.Containers
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block.box
import net.minecraft.world.level.block.CandleBlock
import net.minecraft.world.level.block.RedstoneTorchBlock
import net.minecraft.world.level.block.TorchBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.block.DnDBlockStateProperties
import org.teamvoided.dusks_and_dungeons.block.big.BigCandleBlock
import org.teamvoided.dusks_and_dungeons.block.big.SoulCandleBlock
import org.teamvoided.dusks_and_dungeons.block.candelabra.EmptyCandelabraBlock.Companion.FACING
import org.teamvoided.dusks_and_dungeons.block.entity.CandelabraBlockEntity
import org.teamvoided.dusks_and_dungeons.init.DnDBlockEntities
import org.teamvoided.dusks_and_dungeons.util.getFlameParticle
import org.teamvoided.dusks_and_dungeons.util.rotate
import org.teamvoided.dusks_and_dungeons.util.spawnCandleParticles
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
        DnDBlockStateProperties.CANDLES.possibleValues.associateWith { count ->
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
        return SHAPES[state.getValue(FACING)]?.get(state.getValue(DnDBlockStateProperties.CANDLES)) ?: Shapes.block()
    }

    const val PIXEL_SCALER = 0.0625

    val OFFSETS = listOf(
        listOf(
            Vec3(0.0, 8.0, 0.0)
        ),
        listOf(
            Vec3(4.0, 8.0, 0.0),
            Vec3(-4.0, 8.0, 0.0),
        ),
        listOf(
            Vec3(5.0, 8.0, 0.0),
            Vec3(-5.0, 8.0, 0.0),
            Vec3(0.0, 10.0, 0.0),
        ),
        listOf(
            Vec3(5.0, 8.0, 0.0),
            Vec3(-5.0, 8.0, 0.0),
            Vec3(0.0, 8.0, 5.0),
            Vec3(0.0, 8.0, -5.0),
        ),
        listOf(
            Vec3(5.0, 8.0, 0.0),
            Vec3(-5.0, 8.0, 0.0),
            Vec3(0.0, 8.0, 5.0),
            Vec3(0.0, 8.0, -5.0),
            Vec3(0.0, 10.0, 0.0),
        )
    ).map { list -> list.map { it.scale(PIXEL_SCALER) } }

    fun canAddToCandelabra(stack: ItemStack): Boolean = stack.`is`(ItemTags.CANDLES) || stack.`is`(Items.HEAVY_CORE)

    fun tryAddToCandelabra(level: Level, pos: BlockPos, stack: ItemStack, player: Player): Boolean {
        val candelabra = level.getBlockEntity(pos, DnDBlockEntities.CANDELABRA).getOrNull() ?: return false
        var idx = 0
        for (item in candelabra.candles) {
            if (item.isEmpty) {
                break
            }
            idx++
        }
        if (candelabra.tryAddCandle(stack, idx)) {
            stack.consume(1, player)
            return true
        }
        return false
    }

    fun dropContentsOnDestroy(state: BlockState, otherState: BlockState, level: Level, pos: BlockPos) {
        if (state.`is`(otherState.block)) {
            return
        }
        val be = level.getBlockEntity(pos, DnDBlockEntities.CANDELABRA).getOrNull() ?: return
        Containers.dropContents(level, pos, be.candles)
        level.updateNeighbourForOutputSignal(pos, state.block)
    }

    fun spawnCandelabraParticles(
        be: CandelabraBlockEntity, pos: Vec3, level: Level, random: RandomSource, state: BlockState,
    ) {
        for ((idx, offset) in be.particleOffsets.withIndex()) {
            if (offset == null || be.stateCache[idx].isAir) {
                continue
            }
            val inWorldPos = offset.add(pos)
            when (val block = be.stateCache[idx].block) {
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

    fun BlockGetter.updateCandelabra(pos: BlockPos) {
        getCandelabra(pos)?.updateStateCache(getCandelabra(pos)!!.level!!)
    }

    fun BlockGetter.getCandelabra(pos: BlockPos): CandelabraBlockEntity? {
        return getBlockEntity(pos, DnDBlockEntities.CANDELABRA)?.getOrNull()
    }

}