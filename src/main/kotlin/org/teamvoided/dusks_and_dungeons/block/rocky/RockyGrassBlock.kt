package org.teamvoided.dusks_and_dungeons.block.rocky

import com.mojang.serialization.MapCodec
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.BonemealableBlock
import net.minecraft.world.level.block.BonemealableBlock.Type
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.server.level.ServerLevel
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration
import net.minecraft.data.worldgen.placement.VegetationPlacements

class RockyGrassBlock(dirt: Block, settings: Properties) :
    RockySpreadableBlock(Blocks.GRASS_BLOCK, dirt, settings), BonemealableBlock {
    public override fun codec(): MapCodec<RockyGrassBlock> = CODEC
    override fun isValidBonemealTarget(world: LevelReader, pos: BlockPos, state: BlockState?): Boolean =
        world.getBlockState(pos.above()).isAir

    override fun isBonemealSuccess(world: Level?, random: RandomSource?, pos: BlockPos?, state: BlockState?): Boolean =
        true

    override fun performBonemeal(world: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState?) {
        val blockPos = pos.above()
        val blockState = Blocks.SHORT_GRASS.defaultBlockState()
        val optional =
            world.registryAccess()
                .registryOrThrow(Registries.PLACED_FEATURE).getHolder(VegetationPlacements.GRASS_BONEMEAL)

        label49@ for (i in 0..127) {
            var blockPos2 = blockPos

            for (j in 0 until i / 16) {
                blockPos2 = blockPos2.offset(
                    random.nextInt(3) - 1,
                    (random.nextInt(3) - 1) * random.nextInt(3) / 2,
                    random.nextInt(3) - 1
                )
                if (!world.getBlockState(blockPos2.below()).`is`(this) || world.getBlockState(blockPos2)
                        .isCollisionShapeFullBlock(world, blockPos2)
                ) {
                    continue@label49
                }
            }

            val blockState2 = world.getBlockState(blockPos2)
            if (blockState2.`is`(blockState.block) && random.nextInt(10) == 0) {
                (blockState.block as BonemealableBlock).performBonemeal(world, random, blockPos2, blockState2)
            }

            if (blockState2.isAir) {
                var holder: Holder<*>
                if (random.nextInt(8) == 0) {
                    val list = (world.getBiome(blockPos2).value() as Biome).generationSettings.flowerFeatures
                    if (list.isEmpty()) {
                        continue
                    }

                    holder = ((list[0] as ConfiguredFeature<*, *>).config() as RandomPatchConfiguration).feature()
                } else {
                    if (!optional.isPresent) {
                        continue
                    }

                    holder = optional.get()
                }

                holder.value().place(world, world.chunkSource.generator, random, blockPos2)
            }
        }
    }

    override fun getType(): Type = Type.NEIGHBOR_SPREADER

    companion object {
        val CODEC: MapCodec<RockyGrassBlock> = simpleCodec { RockyGrassBlock(Blocks.DIRT, it) }
    }
}