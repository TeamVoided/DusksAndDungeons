package org.teamvoided.dusk_autumn.world.gen.trunk

import com.google.common.collect.Lists
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.TestableWorld
import net.minecraft.world.gen.feature.TreeFeature
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.foliage.FoliagePlacer
import net.minecraft.world.gen.trunk.TrunkPlacer
import net.minecraft.world.gen.trunk.TrunkPlacerType
import org.teamvoided.dusk_autumn.init.DuskWorldgen
import java.util.function.BiConsumer

class ThreeWideTrunkPlacer(i: Int, j: Int, k: Int) : TrunkPlacer(i, j, k) {
    override fun getType(): TrunkPlacerType<*> {
        return DuskWorldgen.THREE_WIDE_TRUNK_PLACER
    }

    override fun generate(
        world: TestableWorld,
        replacer: BiConsumer<BlockPos, BlockState>,
        random: RandomGenerator,
        height: Int,
        startPos: BlockPos,
        config: TreeFeatureConfig
    ): List<FoliagePlacer.TreeNode> {
        val list: MutableList<FoliagePlacer.TreeNode> = Lists.newArrayList()
        val blockPos = startPos.down()
        setToDirt(world, replacer, random, blockPos.north().west(), config)
        setToDirt(world, replacer, random, blockPos.north(), config)
        setToDirt(world, replacer, random, blockPos.north().east(), config)
        setToDirt(world, replacer, random, blockPos.west(), config)
        setToDirt(world, replacer, random, blockPos, config)
        setToDirt(world, replacer, random, blockPos.east(), config)
        setToDirt(world, replacer, random, blockPos.south().west(), config)
        setToDirt(world, replacer, random, blockPos.south(), config)
        setToDirt(world, replacer, random, blockPos.south().east(), config)
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
                this.placeTrunkBlock(world, replacer, random, blockPos2.north().west(), config)
                this.placeTrunkBlock(world, replacer, random, blockPos2.north(), config)
                this.placeTrunkBlock(world, replacer, random, blockPos2.north().east(), config)
                this.placeTrunkBlock(world, replacer, random, blockPos2.west(), config)
                this.placeTrunkBlock(world, replacer, random, blockPos2, config)
                this.placeTrunkBlock(world, replacer, random, blockPos2.east(), config)
                this.placeTrunkBlock(world, replacer, random, blockPos2.south().west(), config)
                this.placeTrunkBlock(world, replacer, random, blockPos2.south(), config)
                this.placeTrunkBlock(world, replacer, random, blockPos2.south().east(), config)
            }
            ++g
        }

//        list.add(FoliagePlacer.TreeNode(BlockPos(posX, posYAlt, posZ), 0, true))

        g = -2
        while (g <= 3) {
            r = -2
            while (r <= 3) {
//                does not place on corner, then not interior, then chance to place
                if (!((g < -1 ||g > 2) && (r < -1 ||r > 2)) && (g < 0 || g > 1 || r < 0 || r > 1) && random.nextInt(6) <= 0) {
                    val randMax = random.nextInt(3) + 3
                    val randOffset = random.nextInt(3)

                    for (t in 0 until randMax) {
                        this.placeTrunkBlock(world, replacer, random, BlockPos(posX + g, posYAlt - t + randOffset, posZ + r), config)
                        this.placeTrunkBlock(world, replacer, random, BlockPos(posX + g - 1, posYAlt - t + randOffset, posZ + r), config)
                        this.placeTrunkBlock(world, replacer, random, BlockPos(posX + g, posYAlt - t + randOffset, posZ + r - 1), config)
                        this.placeTrunkBlock(world, replacer, random, BlockPos(posX + g - 1, posYAlt - t + randOffset, posZ + r - 1), config)

//              Debug
//                        this.placeTrunkBlock(world, replacer, random, BlockPos(posX + q, posYAlt - t + randOffset + 20, posZ + r), config)
                    }

//                    list.add(FoliagePlacer.TreeNode(BlockPos(posX + g, posYAlt + randOffset, posZ + r), 0, false))
                }
                ++r
            }
            ++g
        }

        return list
    }

    companion object {
        val CODEC: Codec<ThreeWideTrunkPlacer> =
            RecordCodecBuilder.create { fillTrunkPlacerFields(it).apply(it, ::ThreeWideTrunkPlacer) }
    }
//        suprise tool i want to use later
//        val direction = Direction.Type.HORIZONTAL.random(random)
}