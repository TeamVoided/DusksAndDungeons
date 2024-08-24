//import net.minecraft.block.Block
//import net.minecraft.block.BlockState
//import net.minecraft.block.FenceGateBlock
//import net.minecraft.registry.tag.BlockTags
//import net.minecraft.util.math.Direction
//
//fun canConnect(state: BlockState, neighborIsFullSquare: Boolean, dir: Direction?): Boolean {
//    val block = state.block
//    val bl: Boolean = canConnectToFence(state)
//    val bl2 = block is FenceGateBlock && FenceGateBlock.canWallConnect(state, dir)
//    return !Block.cannotConnect(state) && neighborIsFullSquare || bl || bl2
//}
//
//private fun canConnectToFence(state: BlockState): Boolean {
//    return state.isIn(BlockTags.FENCES) && state.isIn(BlockTags.WOODEN_FENCES) == this.getDefaultState()
//        .isIn(BlockTags.WOODEN_FENCES)
//}