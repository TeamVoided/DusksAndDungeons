package org.teamvoided.dusk_autumn.block.rocky

import com.mojang.serialization.MapCodec
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.Fertilizable
import net.minecraft.block.Fertilizable.FertilizationType
import net.minecraft.registry.Holder
import net.minecraft.registry.RegistryKeys
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.World
import net.minecraft.world.WorldView
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig
import net.minecraft.world.gen.feature.VegetationPlacedFeatures

class RockyGrassBlock(dirt: Block, settings: Settings) :
    RockySpreadableBlock(Blocks.GRASS_BLOCK, dirt, settings), Fertilizable {
    public override fun getCodec(): MapCodec<RockyGrassBlock> {
        return CODEC
    }

    override fun isFertilizable(world: WorldView, pos: BlockPos, state: BlockState?): Boolean {
        return world.getBlockState(pos.up()).isAir
    }

    override fun canFertilize(world: World?, random: RandomGenerator?, pos: BlockPos?, state: BlockState?): Boolean {
        return true
    }

    override fun fertilize(world: ServerWorld, random: RandomGenerator, pos: BlockPos, state: BlockState?) {
        val blockPos = pos.up()
        val blockState = Blocks.SHORT_GRASS.defaultState
        val optional =
            world.registryManager.get(RegistryKeys.PLACED_FEATURE).getHolder(VegetationPlacedFeatures.GRASS_BONE_MEAL)

        label49@ for (i in 0..127) {
            var blockPos2 = blockPos

            for (j in 0 until i / 16) {
                blockPos2 = blockPos2.add(
                    random.nextInt(3) - 1,
                    (random.nextInt(3) - 1) * random.nextInt(3) / 2,
                    random.nextInt(3) - 1
                )
                if (!world.getBlockState(blockPos2.down()).isOf(this) || world.getBlockState(blockPos2)
                        .isFullCube(world, blockPos2)
                ) {
                    continue@label49
                }
            }

            val blockState2 = world.getBlockState(blockPos2)
            if (blockState2.isOf(blockState.block) && random.nextInt(10) == 0) {
                (blockState.block as Fertilizable).fertilize(world, random, blockPos2, blockState2)
            }

            if (blockState2.isAir) {
                var holder: Holder<*>
                if (random.nextInt(8) == 0) {
                    val list = (world.getBiome(blockPos2).value() as Biome).generationSettings.flowerFeatures
                    if (list.isEmpty()) {
                        continue
                    }

                    holder = ((list[0] as ConfiguredFeature<*, *>).config as RandomPatchFeatureConfig).feature()
                } else {
                    if (!optional.isPresent) {
                        continue
                    }

                    holder = optional.get()
                }

                (holder.value() as PlacedFeature).place(world, world.chunkManager.chunkGenerator, random, blockPos2)
            }
        }
    }

    override fun getType(): FertilizationType {
        return FertilizationType.NEIGHBOR_SPREADER
    }

    companion object {
        val CODEC: MapCodec<RockyGrassBlock> = createCodec { settings: Settings ->
            RockyGrassBlock(
                Blocks.DIRT,
                settings
            )
        }
    }
}