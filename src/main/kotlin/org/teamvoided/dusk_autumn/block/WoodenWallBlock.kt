package org.teamvoided.dusk_autumn.block

import net.minecraft.block.BlockState
import net.minecraft.block.FenceGateBlock
import net.minecraft.block.PaneBlock
import net.minecraft.block.WallBlock
import net.minecraft.registry.tag.BlockTags
import net.minecraft.util.math.Direction

open class WoodenWallBlock(settings: Settings) : WallBlock(settings) {
//    override fun shouldConnectTo(state: BlockState, faceFullSquare: Boolean, side: Direction): Boolean {
//        val block = state.block
//        val fullFace = (!cannotConnect(state) && faceFullSquare)
//        val fenceGate = block is FenceGateBlock && FenceGateBlock.canWallConnect(state, side)
//        return state.isIn(DnDBlockTags.WOODEN_WALLS) || fullFace || block is PaneBlock || fenceGate
//    }
}