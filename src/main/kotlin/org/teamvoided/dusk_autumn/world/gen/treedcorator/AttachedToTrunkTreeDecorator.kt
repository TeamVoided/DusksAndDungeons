package org.teamvoided.dusk_autumn.world.gen.treedcorator

import com.mojang.datafixers.kinds.App
import com.mojang.datafixers.util.Function6
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.Util
import net.minecraft.util.dynamic.Codecs
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import net.minecraft.world.gen.treedecorator.TreeDecorator
import net.minecraft.world.gen.treedecorator.TreeDecoratorType
import java.util.function.Function

class AttachedToTrunkTreeDecorator(
    protected val probability: Float,
    protected val xzExclusionRadius: Int,
    protected val yExclusionRadius: Int,
    protected val blockProvider: BlockStateProvider,
    protected val requiredEmptyBlocks: Int,
    protected val directions: List<Direction>
) :
    TreeDecorator() {
    override fun generate(placer: Placer) {
        val set: MutableSet<BlockPos?> = HashSet<BlockPos?>()
        val randomGenerator = placer.random
        val var4: Iterator<*> = Util.copyShuffled(placer.leafPositions, randomGenerator).iterator()

        while (true) {
            var blockPos: BlockPos
            var direction: Direction
            var blockPos2: BlockPos
            do {
                do {
                    do {
                        if (!var4.hasNext()) {
                            return
                        }

                        blockPos = var4.next() as BlockPos
                        direction = Util.getRandom(this.directions, randomGenerator) as Direction
                        blockPos2 = blockPos.offset(direction)
                    } while (set.contains(blockPos2))
                } while (!(randomGenerator.nextFloat() < this.probability))
            } while (!this.hasRequiredEmptyBlocks(placer, blockPos, direction))

            val blockPos3 = blockPos2.add(-this.xzExclusionRadius, -this.yExclusionRadius, -this.xzExclusionRadius)
            val blockPos4 = blockPos2.add(this.xzExclusionRadius, this.yExclusionRadius, this.xzExclusionRadius)
            val var10: Iterator<*> = BlockPos.iterate(blockPos3, blockPos4).iterator()

            while (var10.hasNext()) {
                val blockPos5 = var10.next() as BlockPos
                set.add(blockPos5.toImmutable())
            }

            placer.replace(blockPos2, blockProvider.getBlockState(randomGenerator, blockPos2))
        }
    }

    private fun hasRequiredEmptyBlocks(placer: Placer, pos: BlockPos, direction: Direction?): Boolean {
        for (i in 1..this.requiredEmptyBlocks) {
            val blockPos = pos.offset(direction, i)
            if (!placer.isAir(blockPos)) {
                return false
            }
        }

        return true
    }

    override fun getType(): TreeDecoratorType<*> {
        return TreeDecoratorType.ATTACHED_TO_LEAVES
    }

//    companion object {
//        val CODEC: Codec<AttachedToTrunkTreeDecorator?>? = RecordCodecBuilder.create(
//            Function<RecordCodecBuilder.Instance<AttachedToTrunkTreeDecorator?>, App<RecordCodecBuilder.Mu<AttachedToTrunkTreeDecorator>, AttachedToTrunkTreeDecorator>> { instance: RecordCodecBuilder.Instance<AttachedToTrunkTreeDecorator?> ->
//                instance.group(
//                    Codec.floatRange(0.0f, 1.0f).fieldOf("probability")
//                        .forGetter { attachedToTrunkTreeDecorator: AttachedToTrunkTreeDecorator -> attachedToTrunkTreeDecorator.probability },
//                    Codec.intRange(0, 16).fieldOf("exclusion_radius_xz")
//                        .forGetter { attachedToTrunkTreeDecorator: AttachedToTrunkTreeDecorator -> attachedToTrunkTreeDecorator.xzExclusionRadius },
//                    Codec.intRange(0, 16).fieldOf("exclusion_radius_y")
//                        .forGetter { attachedToTrunkTreeDecorator: AttachedToTrunkTreeDecorator -> attachedToTrunkTreeDecorator.yExclusionRadius },
//                    BlockStateProvider.TYPE_CODEC.fieldOf("block_provider")
//                        .forGetter { attachedToTrunkTreeDecorator: AttachedToTrunkTreeDecorator -> attachedToTrunkTreeDecorator.blockProvider },
//                    Codec.intRange(1, 16).fieldOf("required_empty_blocks")
//                        .forGetter { attachedToTrunkTreeDecorator: AttachedToTrunkTreeDecorator -> attachedToTrunkTreeDecorator.requiredEmptyBlocks },
//                    Codecs.withNonEmptyList(Direction.CODEC.listOf())
//                        .fieldOf("directions")
//                        .forGetter { attachedToTrunkTreeDecorator: AttachedToTrunkTreeDecorator -> attachedToTrunkTreeDecorator.directions }
//                ).apply<Any>(
//                    instance,
//                    Function6<*, *, *, *, *, *, *> { probability: *, xzExclusionRadius: *, yExclusionRadius: *, blockProvider: *, requiredEmptyBlocks: *, directions: * ->
//                        AttachedToTrunkTreeDecorator(
//                            probability,
//                            xzExclusionRadius,
//                            yExclusionRadius,
//                            blockProvider,
//                            requiredEmptyBlocks,
//                            directions
//                        )
//                    } as Function6<*, *, *, *, *, *, *>?
//                )
//            })
//    }
}