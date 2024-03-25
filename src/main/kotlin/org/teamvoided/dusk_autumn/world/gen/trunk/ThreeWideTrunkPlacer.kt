//package org.teamvoided.dusk_autumn.world.gen.trunk
//
//import com.google.common.collect.Lists
//import com.mojang.serialization.Codec
//import net.minecraft.block.BlockState
//import net.minecraft.util.math.BlockPos
//import net.minecraft.util.math.Direction
//import net.minecraft.util.random.RandomGenerator
//import net.minecraft.world.TestableWorld
//import net.minecraft.world.gen.feature.TreeFeature
//import net.minecraft.world.gen.feature.TreeFeatureConfig
//import net.minecraft.world.gen.foliage.FoliagePlacer
//import net.minecraft.world.gen.trunk.TrunkPlacer
//import net.minecraft.world.gen.trunk.TrunkPlacerType
//import org.teamvoided.dusk_autumn.init.DuskWorldgen
//import java.util.function.BiConsumer
//
//class ThreeWideTrunkPlacer(i: Int, j: Int, k: Int) : TrunkPlacer(i, j, k) {
//    override fun getType(): TrunkPlacerType<*> {
//        return DuskWorldgen.THREE_WIDE_TRUNK_PLACER
//    }
//
//    override fun generate(
//        world: TestableWorld,
//        replacer: BiConsumer<BlockPos, BlockState>,
//        random: RandomGenerator,
//        height: Int,
//        startPos: BlockPos,
//        config: TreeFeatureConfig
//    ): List<FoliagePlacer.TreeNode> {
//        val list: MutableList<FoliagePlacer.TreeNode> = Lists.newArrayList()
//        val blockPos = startPos.down()
//        setToDirt(world, replacer, random, blockPos, config)
//        setToDirt(world, replacer, random, blockPos.east(), config)
//        setToDirt(world, replacer, random, blockPos.south(), config)
//        setToDirt(world, replacer, random, blockPos.south().east(), config)
//        val direction = Direction.Type.HORIZONTAL.random(random)
//        val i = height - random.nextInt(4)
//        var j = 2 - random.nextInt(3)
//        val posX = startPos.x
//        val posY = startPos.y
//        val posZ = startPos.z
//        var n = posX
//        var o = posZ
//        val p = posY + height - 1
//        var r: Int
//        var q = 0
//        while (q < height) {
//            if (q >= i && j > 0) {
//                n += direction.offsetX
//                o += direction.offsetZ
//                --j
//            }
//
//            r = posY + q
//            val blockPos2 = BlockPos(n, r, o)
//            if (TreeFeature.isAirOrLeaves(world, blockPos2)) {
//                this.placeTrunkBlock(world, replacer, random, blockPos2, config)
//                this.placeTrunkBlock(world, replacer, random, blockPos2.east(), config)
//                this.placeTrunkBlock(world, replacer, random, blockPos2.south(), config)
//                this.placeTrunkBlock(world, replacer, random, blockPos2.east().south(), config)
//            }
//            ++q
//        }
//
//        list.add(FoliagePlacer.TreeNode(BlockPos(n, p, o), 0, true))
//
//        q = -1
//        while (q <= 2) {
//            r = -1
//            while (r <= 2) {
//                if ((q < 0 || q > 1 || r < 0 || r > 1) && random.nextInt(3) <= 0) {
//                    val s = random.nextInt(3) + 2
//
//                    for (t in 0 until s) {
//                        this.placeTrunkBlock(world, replacer, random, BlockPos(posX + q, p - t - 1, posZ + r), config)
//                    }
//
//                    list.add(FoliagePlacer.TreeNode(BlockPos(n + q, p, o + r), 0, false))
//                }
//                ++r
//            }
//            ++q
//        }
//
//        return list
//    }
//
//    companion object {
////        val CODEC: Codec<ThreeWideTrunkPlacer> =
////            RecordCodecBuilder.create(Function<RecordCodecBuilder.Instance<ThreeWideTrunkPlacer?>, App<RecordCodecBuilder.Mu<ThreeWideTrunkPlacer>, ThreeWideTrunkPlacer>> { instance: RecordCodecBuilder.Instance<ThreeWideTrunkPlacer?>? ->
////                fillTrunkPlacerFields(instance).apply<Any>(instance, Function3<*, *, *, *> { i: *, j: *, k: * ->
////                    ThreeWideTrunkPlacer(
////                        i, j, k
////                    )
////                } as Function3<*, *, *, *>?)
////            })
//    }
//}