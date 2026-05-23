package org.teamvoided.voidlib.consortium.block.set

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.data.BlockFamilies
import net.minecraft.data.BlockFamily
import net.minecraft.world.item.Item
import net.minecraft.world.level.ItemLike
import org.teamvoided.voidlib.consortium.block.BlockCollection
import org.teamvoided.voidlib.consortium.utils.HasFamily
import org.teamvoided.voidlib.helpers.block.BlockConvertable
import java.util.function.Supplier

abstract class AbstractBlockSet(
    val name: String,
    val parent: Block, val stairs: Block, val slab: Block, val wall: Block,
    val hasStoneCutting: Boolean
) : ItemLike, Supplier<Block>, BlockConvertable, BlockCollection<Block>, HasFamily {
    override val list: List<Block> = listOf(stairs, slab, wall)
    override fun getIdMap(): Map<String, Block> =
        mapOf("${name}_stairs" to stairs, "${name}_slab" to slab, "${name}_wall" to wall)

    override fun asItem(): Item = parent.asItem()
    override fun get() = parent
    override fun asBlock(): Block = parent
    override fun getDefaultState(): BlockState = parent.defaultBlockState()

    open fun headless() = list
    override val family: BlockFamily = BlockFamilies.familyBuilder(parent).stairs(stairs).slab(slab).wall(wall).family
}