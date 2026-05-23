package org.teamvoided.dusks_and_dungeons.world.gen.treedcorator

import com.google.common.collect.Lists
import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.level.block.Block
import net.minecraft.core.HolderSet
import net.minecraft.core.RegistryCodecs
import net.minecraft.core.registries.Registries
import net.minecraft.core.BlockPos
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType
import org.teamvoided.dusks_and_dungeons.init.DnDWorldgen
import kotlin.math.abs


class AlterGroundRadiusTreeDecorator(
    private val provider: BlockStateProvider,
    private val radius: Int,
    private val percentChance: Int,
    val canReplace: HolderSet<Block>
) : TreeDecorator() {
    override fun type(): TreeDecoratorType<*> {
        return DnDWorldgen.ALTER_GROUND_RADIUS
    }

    override fun place(generator: Context) {
        val list = Lists.newArrayList<BlockPos>()
        val list2 = generator.roots()
        val list3 = generator.logs()
        if (list2.isEmpty) {
            list.addAll(list3)
        } else if (!list3.isEmpty && (list2[0] as BlockPos).y == (list3[0] as BlockPos).y) {
            list.addAll(list3)
            list.addAll(list2)
        } else {
            list.addAll(list2)
        }
        if (list.isEmpty()) {
            return
        }
        val i = (list[0] as BlockPos).y

        list.stream().filter { blockPos: BlockPos -> blockPos.y == i }.forEach { blockPos: BlockPos ->
            this.setArea(generator, blockPos)
            for (x in -radius..radius) {
                for (z in -radius..radius) {
                    if (abs(x) == radius || abs(z) == radius) {
                        val rand = generator.random().nextInt(100)
                        if (percentChance >= rand) {
                            setArea(generator, blockPos.offset(x, 0, z))
                        }
                    }
                }
            }
        }
    }

    private fun setArea(placer: Context, pos: BlockPos) {
        for (i in -2..2) {
            for (j in -2..2) {
                if (abs(i) != 2 || abs(j) != 2) {
                    this.setBlock(placer, pos.offset(i, 0, j))
                }
            }
        }
    }

    private fun setBlock(placer: Context, pos: BlockPos) {
        for (i in 2 downTo -3) {
            val blockPos = pos.above(i)
            if (placer.level().isStateAtPosition(blockPos) { it.`is`(canReplace) }) {
                placer.setBlock(blockPos, provider.getState(placer.random(), pos))
                break
            }

            if (!placer.isAir(blockPos) && i < 0) {
                break
            }
        }
    }

    companion object {
        val CODEC: MapCodec<AlterGroundRadiusTreeDecorator> = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                BlockStateProvider.CODEC.fieldOf("block_provider").forGetter { it.provider },
                Codec.intRange(0, 15).fieldOf("radius").forGetter { it.radius },
                Codec.intRange(1, 100).fieldOf("percent_chance").forGetter { it.percentChance },
                RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("can_replace").forGetter { it.canReplace },
            ).apply(instance, ::AlterGroundRadiusTreeDecorator)
        }
    }
}
