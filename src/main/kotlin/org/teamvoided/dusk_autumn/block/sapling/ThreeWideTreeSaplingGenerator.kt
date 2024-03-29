package net.minecraft.world.gen.foliage.org.teamvoided.dusk_autumn.block.sapling



//abstract class ThreeWideTreeSaplingGenerator : SaplingGenerator() {
//
//    abstract fun getThreeWideTreeFeature(
//        random: RandomGenerator?,
//        bees: Boolean
//    ): RegistryKey<ConfiguredFeature<*, *>?>?
//
//    override fun generate(
//        world: ServerWorld,
//        chunkGenerator: ChunkGenerator,
//        pos: BlockPos,
//        state: BlockState,
//        random: RandomGenerator
//    ): Boolean {
//        for (i in 1 downTo -1) {
//            for (j in 1 downTo -1) {
//                if (canGenerateLargeTree(state, world, pos, i, j)) {
//                    return this.generateThreeWideTree(world, chunkGenerator, pos, state, random, i, j)
//                }
//            }
//        }
//
//        return super.generate(world, chunkGenerator, pos, state, random)
//    }
//
//    fun generateThreeWideTree(
//        world: ServerWorld,
//        chunkGenerator: ChunkGenerator?,
//        pos: BlockPos,
//        state: BlockState?,
//        random: RandomGenerator?,
//        x: Int,
//        z: Int
//    ): Boolean {
//        val registryKey = this.getThreeWideTreeFeature(random, false)
//        if (registryKey == null) {
//            return false
//        } else {
//            val holder: Holder<ConfiguredFeature<*, *>> = world
//                .registryManager
//                .get(RegistryKeys.CONFIGURED_FEATURE)
//                .getHolder(registryKey)
//                .orElse(null)
//            if (holder == null) {
//                return false
//            } else {
//                val configuredFeature = holder.value()
//                val blockState = Blocks.AIR.defaultState
//                world.setBlockState(pos.add(x - 1, 0, z - 1), blockState, Block.NO_REDRAW)
//                world.setBlockState(pos.add(x, 0, z - 1), blockState, Block.NO_REDRAW)
//                world.setBlockState(pos.add(x + 1, 0, z - 1), blockState, Block.NO_REDRAW)
//                world.setBlockState(pos.add(x - 1, 0, z), blockState, Block.NO_REDRAW)
//                world.setBlockState(pos.add(x, 0, z), blockState, Block.NO_REDRAW)
//                world.setBlockState(pos.add(x + 1, 0, z), blockState, Block.NO_REDRAW)
//                world.setBlockState(pos.add(x - 1, 0, z + 1), blockState, Block.NO_REDRAW)
//                world.setBlockState(pos.add(x, 0, z + 1), blockState, Block.NO_REDRAW)
//                world.setBlockState(pos.add(x + 1, 0, z + 1), blockState, Block.NO_REDRAW)
//                if (configuredFeature.generate(world, chunkGenerator, random, pos.add(x, 0, z))) {
//                    return true
//                } else {
//                    world.setBlockState(pos.add(x - 1, 0, z - 1), state, Block.NO_REDRAW)
//                    world.setBlockState(pos.add(x, 0, z - 1), state, Block.NO_REDRAW)
//                    world.setBlockState(pos.add(x + 1, 0, z - 1), state, Block.NO_REDRAW)
//                    world.setBlockState(pos.add(x - 1, 0, z), state, Block.NO_REDRAW)
//                    world.setBlockState(pos.add(x, 0, z), state, Block.NO_REDRAW)
//                    world.setBlockState(pos.add(x + 1, 0, z), state, Block.NO_REDRAW)
//                    world.setBlockState(pos.add(x - 1, 0, z + 1), state, Block.NO_REDRAW)
//                    world.setBlockState(pos.add(x, 0, z + 1), state, Block.NO_REDRAW)
//                    world.setBlockState(pos.add(x + 1, 0, z + 1), state, Block.NO_REDRAW)
//                    return false
//                }
//            }
//        }
//    }
//
//    companion object {
//        fun canGenerateLargeTree(state: BlockState, world: BlockView, pos: BlockPos, x: Int, z: Int): Boolean {
//            val block = state.block
//            return world.getBlockState(pos.add(x - 1, 0, z - 1)).isOf(block) &&
//                    world.getBlockState(pos.add(x, 0, z - 1)).isOf(block) &&
//                    world.getBlockState(pos.add(x + 1, 0, z - 1)).isOf(block) &&
//                    world.getBlockState(pos.add(x - 1, 0, z)).isOf(block) &&
//                    world.getBlockState(pos.add(x, 0, z)).isOf(block) &&
//                    world.getBlockState(pos.add(x + 1, 0, z)).isOf(block) &&
//                    world.getBlockState(pos.add(x - 1, 0, z + 1)).isOf(block) &&
//                    world.getBlockState(pos.add(x, 0, z + 1)).isOf(block) &&
//                    world.getBlockState(pos.add(x + 1, 0, z + 1)).isOf(block)
//        }
//    }
//}