package org.teamvoided.dusks_and_dungeons.world.gen.treedcorator

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.Util
import net.minecraft.util.ExtraCodecs
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType
import org.teamvoided.dusks_and_dungeons.init.DnDWorldgen

class AttachedToTrunkTreeDecorator(
    val probability: Float,
    val xzExclusionRadius: Int,
    val yExclusionRadius: Int,
    val blockProvider: BlockStateProvider,
    val requiredEmptyBlocks: Int,
    val directions: List<Direction>
) :
    TreeDecorator() {
    override fun place(placer: Context) {
        val set: MutableSet<BlockPos> = HashSet()
        val randomGenerator = placer.random()
        val var4: Iterator<*> = Util.shuffledCopy(placer.logs(), randomGenerator).iterator()

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
                        direction = Util.getRandom(this.directions, randomGenerator)
                        blockPos2 = blockPos.relative(direction)
                    } while (set.contains(blockPos2))
                } while (!(randomGenerator.nextFloat() < this.probability))
            } while (!this.hasRequiredEmptyBlocks(placer, blockPos, direction))

            val blockPos3 = blockPos2.offset(-this.xzExclusionRadius, -this.yExclusionRadius, -this.xzExclusionRadius)
            val blockPos4 = blockPos2.offset(this.xzExclusionRadius, this.yExclusionRadius, this.xzExclusionRadius)
            val var10: Iterator<*> = BlockPos.betweenClosed(blockPos3, blockPos4).iterator()

            while (var10.hasNext()) {
                val blockPos5 = var10.next() as BlockPos
                set.add(blockPos5.immutable())
            }

            placer.setBlock(blockPos2, blockProvider.getState(randomGenerator, blockPos2))
        }
    }

    private fun hasRequiredEmptyBlocks(placer: Context, pos: BlockPos, direction: Direction): Boolean {
        for (i in 1..this.requiredEmptyBlocks) {
            val blockPos = pos.relative(direction, i)
            if (!placer.isAir(blockPos)) {
                return false
            }
        }

        return true
    }

    override fun type(): TreeDecoratorType<*> {
        return DnDWorldgen.ATTACHED_TO_TRUNK
    }

    companion object {
        val CODEC: MapCodec<AttachedToTrunkTreeDecorator> = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                Codec.floatRange(0.0f, 1.0f).fieldOf("probability").forGetter { it.probability },
                Codec.intRange(0, 16).fieldOf("xz_exclusion_radius").forGetter { it.xzExclusionRadius },
                Codec.intRange(0, 16).fieldOf("y_exclusion_radius").forGetter { it.yExclusionRadius },
                BlockStateProvider.CODEC.fieldOf("block_provider").forGetter { it.blockProvider },
                Codec.intRange(1, 16).fieldOf("required_empty_blocks").forGetter { it.requiredEmptyBlocks },
                ExtraCodecs.nonEmptyList(Direction.CODEC.listOf()).fieldOf("directions").forGetter { it.directions },
            ).apply(instance, ::AttachedToTrunkTreeDecorator)
        }
    }
}
