package org.teamvoided.dusk_autumn.world.gen.trunk

import com.google.common.collect.ImmutableList
import com.mojang.datafixers.kinds.App
import com.mojang.datafixers.util.Function3
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.TestableWorld
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.foliage.FoliagePlacer
import net.minecraft.world.gen.trunk.TrunkPlacer
import net.minecraft.world.gen.trunk.TrunkPlacerType
import org.teamvoided.dusk_autumn.init.DuskWorldgen
import java.util.function.BiConsumer
import java.util.function.Function

class ThreeWideTrunkPlacer(i: Int, j: Int, k: Int) : TrunkPlacer(i, j, k) {
//    val CODEC: com.mojang.serialization.Codec<ThreeWideTrunkPlacer> =
//        RecordCodecBuilder.create(Function<RecordCodecBuilder.Instance<ThreeWideTrunkPlacer>, App<RecordCodecBuilder.Mu<ThreeWideTrunkPlacer>, ThreeWideTrunkPlacer>> { instance: RecordCodecBuilder.Instance<ThreeWideTrunkPlacer?>? ->
//            fillTrunkPlacerFields(instance).apply<Any>(instance, Function3<*, *, *, *> { i: *, j: *, k: * ->
//                ThreeWideTrunkPlacer(
//                    i, j, k
//                )
//            } as Function3<*, *, *, *>?)
//        })

//    fun ThreeWideTrunkPlacer(i: Int, j: Int, k: Int) {
//        super(i, j, k)
//    }

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
        val blockPos = startPos.down()
        setToDirt(world, replacer, random, blockPos, config)
        setToDirt(world, replacer, random, blockPos.east(), config)
        setToDirt(world, replacer, random, blockPos.south(), config)
        setToDirt(world, replacer, random, blockPos.south().east(), config)
        val mutable = BlockPos.Mutable()

        for (i in 0 until height) {
            this.setLog(world, replacer, random, mutable, config, startPos, 0, i, 0)
            if (i < height - 1) {
                this.setLog(world, replacer, random, mutable, config, startPos, 1, i, 0)
                this.setLog(world, replacer, random, mutable, config, startPos, 1, i, 1)
                this.setLog(world, replacer, random, mutable, config, startPos, 0, i, 1)
            }
        }

        return ImmutableList.of(FoliagePlacer.TreeNode(startPos.up(height), 0, true))
    }

    private fun setLog(
        world: TestableWorld,
        replacer: BiConsumer<BlockPos, BlockState>,
        random: RandomGenerator,
        pos: BlockPos.Mutable,
        config: TreeFeatureConfig,
        startPos: BlockPos,
        x: Int,
        y: Int,
        z: Int
    ) {
        pos[startPos, x, y] = z
        this.trySetState(world, replacer, random, pos, config)
    }
}