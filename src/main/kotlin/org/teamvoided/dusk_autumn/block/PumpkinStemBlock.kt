package org.teamvoided.dusk_autumn.block

import com.mojang.datafixers.DataFixUtils
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.*
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.MathHelper
import net.minecraft.util.random.RandomGenerator
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.WorldView
import kotlin.math.min

class PumpkinStemBlock(
    private val gourdBlock: RegistryKey<Block>,
    private val smallGourdBlock: RegistryKey<Block>,
    private val seed: RegistryKey<Item>,
    settings: Settings
) : CropBlock(settings) {

    init {
        this.defaultState = (stateManager.defaultState).with(AGE, 0)
    }

    override fun getCodec(): MapCodec<PumpkinStemBlock> {
        return CODEC
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return AGE_TO_SHAPE[(state.get(AGE))]
    }

    override fun canPlantOnTop(floor: BlockState, world: BlockView, pos: BlockPos): Boolean {
        return floor.isOf(Blocks.FARMLAND)
    }

    override fun randomTick(blockState: BlockState, world: ServerWorld, pos: BlockPos, random: RandomGenerator) {
        var state = blockState
        if (world.getBaseLightLevel(pos, 0) >= 9) {
            val f = getAvailableMoisture(this, world, pos)
            if (random.nextInt((25.0f / f).toInt() + 1) == 0) {
                val age = state.get(AGE)
                if (age < maxAge) {
                    state = state.with(AGE, age + 1) as BlockState
                    world.setBlockState(pos, state, 2)
                } else {
                    val direction = Direction.Type.HORIZONTAL.random(random)
                    val blockPos = pos.offset(direction)
                    val groundState = world.getBlockState(blockPos.down())
                    if (world.getBlockState(blockPos).isAir &&
                        (groundState.isSideSolidFullSquare(world, blockPos.down(), Direction.UP))
                    ) {
                        val registry = world.registryManager.get(RegistryKeys.BLOCK)
                        val optional = registry.getOrEmpty(this.gourdBlock)
                        if (optional.isPresent) {
                            world.setBlockState(blockPos, optional.get().defaultState)
                        }
                    }
                }
            }
        }
    }

    override fun getPickStack(world: WorldView, pos: BlockPos, state: BlockState): ItemStack {
        return ItemStack(
            DataFixUtils.orElse(
                world.registryManager.get(RegistryKeys.ITEM).getOrEmpty(this.seed),
                this
            )
        )
    }

    override fun fertilize(world: ServerWorld, random: RandomGenerator, pos: BlockPos, state: BlockState) {
        val i = min(7, (state.get(AGE) + MathHelper.nextInt(world.random, 2, 5)))
        val blockState = state.with(AGE, i)
        world.setBlockState(pos, blockState, 2)
        if (i == maxAge) {
            blockState.randomTick(world, pos, world.random)
        }
    }

    companion object {
        val CODEC: MapCodec<PumpkinStemBlock> =
            RecordCodecBuilder.mapCodec { instance: RecordCodecBuilder.Instance<PumpkinStemBlock> ->
                instance.group(
                    RegistryKey.codec(RegistryKeys.BLOCK).fieldOf("fruit")
                        .forGetter { stemBlock: PumpkinStemBlock -> stemBlock.gourdBlock },
                    RegistryKey.codec(RegistryKeys.BLOCK)
                        .fieldOf("small_fruit").forGetter { stemBlock: PumpkinStemBlock -> stemBlock.smallGourdBlock },
                    RegistryKey.codec(RegistryKeys.ITEM).fieldOf("seed")
                        .forGetter { stemBlock: PumpkinStemBlock -> stemBlock.seed },
                    getSettingsCodec()
                ).apply(instance, ::PumpkinStemBlock)
            }
        protected val AGE_TO_SHAPE: Array<VoxelShape> =
            arrayOf(
                shape(2.0),
                shape(4.0),
                shape(6.0),
                shape(8.0),
                shape(10.0),
                shape(12.0),
                shape(14.0),
                shape(16.0)
            )

        fun shape(height: Double): VoxelShape {
            return createCuboidShape(7.0, 0.0, 7.0, 9.0, height, 9.0)
        }
    }
}