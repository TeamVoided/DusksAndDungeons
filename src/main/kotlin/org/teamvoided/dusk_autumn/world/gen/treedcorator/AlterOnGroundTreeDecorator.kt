package org.teamvoided.dusk_autumn.world.gen.treedcorator

import com.google.common.collect.Lists
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.Block
import net.minecraft.registry.HolderSet
import net.minecraft.registry.RegistryCodecs
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.math.BlockPos
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import net.minecraft.world.gen.treedecorator.TreeDecorator
import net.minecraft.world.gen.treedecorator.TreeDecoratorType
import org.teamvoided.dusk_autumn.init.DuskWorldgen
import kotlin.math.abs


class AlterOnGroundTreeDecorator(
    private val provider: BlockStateProvider, private val radius: Int, private val radiusPercentChance: Int, private val foliagePercentChance: Int, val canBePlacedOn: HolderSet<Block>
) : TreeDecorator() {
    override fun getType(): TreeDecoratorType<*> {
        return DuskWorldgen.ALTER_ON_GROUND
    }

    override fun generate(generator: Placer) {
        val list = Lists.newArrayList<BlockPos>()
        val list2 = generator.rootPositions
        val list3 = generator.logPositions
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
                        val rand = generator.random.nextInt(100)
                        if (radiusPercentChance >= rand) {
                            setArea(generator, blockPos.add(x, 0, z))
                        }
                    }
                }
            }
        }
    }

    private fun setArea(placer: Placer, pos: BlockPos) {
        for (i in -2..2) {
            for (j in -2..2) {
                if (abs(i) != 2 || abs(j) != 2) {
                    val rand = placer.random.nextInt(100)
                    if (foliagePercentChance >= rand) {
                        this.setBlock(placer, pos.add(i, 0, j))
                    }
                }
            }
        }
    }

    private fun setBlock(placer: Placer, pos: BlockPos) {
        for (i in 2 downTo -3) {
            val blockPos = pos.up(i)
            if (placer.world.testBlockState(blockPos) { it.isIn(canBePlacedOn) } && placer.isAir(blockPos.up())){
                placer.replace(blockPos.up(), provider.getBlockState(placer.random, pos))
                break
            }

            if (!placer.isAir(blockPos) && i < 0) {
                break
            }
        }
    }


    companion object {

        val CODEC: Codec<AlterOnGroundTreeDecorator> = RecordCodecBuilder.create {
            it.group(
                BlockStateProvider.TYPE_CODEC.fieldOf("block_provider").forGetter { decorator -> decorator.provider },
                Codec.intRange(0, 15).fieldOf("radius").forGetter { decorator -> decorator.radius },
                Codec.intRange(1, 100).fieldOf("radius_percent_chance").forGetter { decorator -> decorator.radiusPercentChance },
                Codec.intRange(1, 100).fieldOf("foliage_percent_chance").forGetter { decorator -> decorator.foliagePercentChance },
                RegistryCodecs.homogeneousList(RegistryKeys.BLOCK).fieldOf("can_be_placed_on").forGetter { placement -> placement.canBePlacedOn },
            ).apply(it, ::AlterOnGroundTreeDecorator)
        }
    }

}