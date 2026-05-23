package org.teamvoided.dusks_and_dungeons.world.gen.trunk

import com.google.common.collect.Lists
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelSimulatedReader
import net.minecraft.world.level.levelgen.feature.TreeFeature
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType
import org.teamvoided.dusks_and_dungeons.init.DnDWorldgen
import java.util.function.BiConsumer
import java.util.function.Function

class ThreeWideTrunkPlacer(i: Int, j: Int, k: Int) : TrunkPlacer(i, j, k) {
    override fun type(): TrunkPlacerType<*> {
        return DnDWorldgen.THREE_WIDE_TRUNK_PLACER
    }

    override fun placeTrunk(
        world: LevelSimulatedReader,
        replacer: BiConsumer<BlockPos, BlockState>,
        random: RandomSource,
        height: Int,
        startPos: BlockPos,
        config: TreeConfiguration
    ): List<FoliagePlacer.FoliageAttachment> {
        val list: MutableList<FoliagePlacer.FoliageAttachment> = Lists.newArrayList()
        val blockPos = startPos.below()
        setDirtAt(world, replacer, random, blockPos.north().west(), config)
        setDirtAt(world, replacer, random, blockPos.north(), config)
        setDirtAt(world, replacer, random, blockPos.north().east(), config)
        setDirtAt(world, replacer, random, blockPos.west(), config)
        setDirtAt(world, replacer, random, blockPos, config)
        setDirtAt(world, replacer, random, blockPos.east(), config)
        setDirtAt(world, replacer, random, blockPos.south().west(), config)
        setDirtAt(world, replacer, random, blockPos.south(), config)
        setDirtAt(world, replacer, random, blockPos.south().east(), config)
        val posX = startPos.x
        val posY = startPos.y
        val posZ = startPos.z
        val posYAlt = posY + height - 1
        var r: Int
        var g = 0
        while (g < height) {
            r = posY + g
            val blockPos2 = BlockPos(posX, r, posZ)
            if (TreeFeature.isAirOrLeaves(world, blockPos2)) {
                val chance = (height * 1.2 - g).toInt()
                placeLog(world, replacer, random, blockPos2.north().west(), config)
                placeLog(world, replacer, random, blockPos2.north(), config)
                placeLog(world, replacer, random, blockPos2.north().east(), config)
                placeLog(world, replacer, random, blockPos2.west(), config)
                placeLog(world, replacer, random, blockPos2, config)
                placeLog(world, replacer, random, blockPos2.east(), config)
                placeLog(world, replacer, random, blockPos2.south().west(), config)
                placeLog(world, replacer, random, blockPos2.south(), config)
                placeLog(world, replacer, random, blockPos2.south().east(), config)
            }
            ++g
        }

        list.add(FoliagePlacer.FoliageAttachment(BlockPos(posX, posYAlt, posZ), 0, true))

        g = -2
        while (g <= 3) {
            r = -2
            while (r <= 3) {
//                does not place on corner, then not interior, then chance to place
                if (!((g < -1 || g > 2) && (r < -1 || r > 2)) && (g < 0 || g > 1 || r < 0 || r > 1) && random.nextInt(9) <= 0) {
                    val randMax = random.nextInt(3) + 3
                    val randOffset = random.nextInt(4) - 1

                    for (t in 0 until randMax) {
                        val y = posYAlt - t + randOffset
                        placeLog(
                            world,
                            replacer,
                            random,
                            BlockPos(posX + g, y, posZ + r),
                            config
                        )
                        placeLog(
                            world,
                            replacer,
                            random,
                            BlockPos(posX + g - 1, y, posZ + r),
                            config
                        )
                        placeLog(
                            world,
                            replacer,
                            random,
                            BlockPos(posX + g, y, posZ + r - 1),
                            config
                        )
                        placeLog(
                            world,
                            replacer,
                            random,
                            BlockPos(posX + g - 1, y, posZ + r - 1),
                            config
                        )
//              Debug
//                        placeTrunkBlock(world, replacer, random, BlockPos(posX + q, posYAlt - t + randOffset + 20, posZ + r), config)
                    }
                    list.add(FoliagePlacer.FoliageAttachment(BlockPos(posX + g, posYAlt + randOffset, posZ + r), 0, false))
                }
                ++r
            }
            ++g
        }

        return list
    }

    fun placeChance(
        chance: Int,
        currentHeight: Int,
        world: LevelSimulatedReader,
        replacer: BiConsumer<BlockPos, BlockState>,
        random: RandomSource,
        pos: BlockPos,
        config: TreeConfiguration
    ): Boolean? {
        return if (random.nextInt(chance) > 0 || currentHeight <= 1) {
            this.placeLog(world, replacer, random, pos, config, Function.identity())
        } else null
    }

    companion object {
        val CODEC: MapCodec<ThreeWideTrunkPlacer> =
            RecordCodecBuilder.mapCodec { trunkPlacerParts(it).apply(it, ::ThreeWideTrunkPlacer) }
    }
//        suprise tool i want to use later
//        val direction = Direction.Type.HORIZONTAL.random(random)
}
