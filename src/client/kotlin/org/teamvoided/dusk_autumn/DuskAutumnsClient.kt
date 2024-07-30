package org.teamvoided.dusk_autumn

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.fabric.api.client.particle.v1.ParticleRenderEvents
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.client.color.world.BiomeColors
import net.minecraft.client.color.world.FoliageColors
import net.minecraft.client.color.world.GrassColors
import net.minecraft.client.particle.FlameParticle
import net.minecraft.client.render.RenderLayer
import net.minecraft.component.type.DyedColorComponent
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockRenderView
import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.init.DuskItems
import org.teamvoided.dusk_autumn.init.DuskParticles.CASCADE_LEAF_PARTICLE
import org.teamvoided.dusk_autumn.init.DuskParticles.SMALL_SOUL_FLAME_PARTICLE
import org.teamvoided.dusk_autumn.particle.FallingLeafParticle.Companion.FallingLeafFactory

@Suppress("unused")
object DuskAutumnsClient {

    //    val key = KeyBindingHelper.registerKeyBinding(KeyBind("debug", InputUtil.KEY_R_CODE, "debug"))
    const val CASCADE_LEAF_COLOR = 14701655

    var cooldown = 0
    private val coloredBlocks = listOf(
        DuskBlocks.OVERGROWN_COBBLESTONE,
        DuskBlocks.OVERGROWN_COBBLESTONE_STAIRS,
        DuskBlocks.OVERGROWN_COBBLESTONE_SLAB,
        DuskBlocks.OVERGROWN_COBBLESTONE_WALL,
        DuskBlocks.OVERGROWN_STONE_BRICKS,
        DuskBlocks.OVERGROWN_STONE_BRICK_STAIRS,
        DuskBlocks.OVERGROWN_STONE_BRICK_SLAB,
        DuskBlocks.OVERGROWN_STONE_BRICK_WALL,
        DuskBlocks.ROCKY_GRASS,
        DuskBlocks.SLATED_GRASS,
        DuskBlocks.BLACKSTONE_GRASS
    )

    fun init() {
        initBlocks()
        initItems()
        ParticleFactoryRegistry.getInstance().register(CASCADE_LEAF_PARTICLE, ::FallingLeafFactory)
        ParticleFactoryRegistry.getInstance().register(SMALL_SOUL_FLAME_PARTICLE, FlameParticle::SmallFactory)

//        ClientTickEvents.END_CLIENT_TICK.register {
//            if (key.isPressed && cooldown < 1) {
//                it.networkHandler?.sendCommand("place feature dusk_autumn:cascade_tree")
//                cooldown = 35
//            }
//
//            if (cooldown > 0) cooldown--
//        }

        ParticleRenderEvents.ALLOW_BLOCK_DUST_TINT.register { blockState, _, _ ->
            blockState.block !in coloredBlocks
        }
    }


    private fun initBlocks() {
        ColorProviderRegistry.BLOCK.register(
            { _, world, pos, _ ->
                foliageColorOrDefault(world, pos)
            },
            DuskBlocks.OAK_LEAF_PILE,
            DuskBlocks.JUNGLE_LEAF_PILE,
            DuskBlocks.ACACIA_LEAF_PILE,
            DuskBlocks.DARK_OAK_LEAF_PILE,
            DuskBlocks.MANGROVE_LEAF_PILE
        )
        ColorProviderRegistry.BLOCK.register(
            { _, world, pos, tintIndex ->
                if (tintIndex != 0) {
                    grassColorOrDefault(world, pos)
                } else -1
            },
            DuskBlocks.BLUE_PETALS
        )
        ColorProviderRegistry.BLOCK.register(
            { _, world, pos, _ -> grassColorOrDefault(world, pos) },
            *coloredBlocks.toTypedArray()
        )
        ColorProviderRegistry.BLOCK.register(
            { _, _, _, _ -> FoliageColors.getSpruceColor() }, DuskBlocks.SPRUCE_LEAF_PILE
        )
        ColorProviderRegistry.BLOCK.register(
            { _, _, _, _ -> FoliageColors.getBirchColor() }, DuskBlocks.BIRCH_LEAF_PILE
        )
        ColorProviderRegistry.BLOCK.register(
            { _, _, _, _ -> CASCADE_LEAF_COLOR }, DuskBlocks.CASCADE_LEAVES, DuskBlocks.CASCADE_LEAF_PILE
        )

//    val GOLDEN_BIRCH_COLOR = 16761873
//    val GOLDEN_BIRCH_COLOR = 16760872

        DuskBlocks.CUTOUT_BLOCKS.forEach { BlockRenderLayerMap.INSTANCE.putBlock(it, RenderLayer.getCutout()) }
    }

    private fun initItems() {
        ColorProviderRegistry.ITEM.register(
            { _, _ -> GrassColors.getDefault() },
            *coloredBlocks.map { it.asItem() }.toTypedArray()
        )
        ColorProviderRegistry.ITEM.register(
            { _, _ -> FoliageColors.getDefaultColor() },
            DuskBlocks.OAK_LEAF_PILE.asItem(),
            DuskBlocks.JUNGLE_LEAF_PILE.asItem(),
            DuskBlocks.ACACIA_LEAF_PILE.asItem(),
            DuskBlocks.DARK_OAK_LEAF_PILE.asItem(),
            DuskBlocks.MANGROVE_LEAF_PILE.asItem()
        )
        ColorProviderRegistry.ITEM.register(
            { _, _ -> FoliageColors.getSpruceColor() },
            DuskBlocks.SPRUCE_LEAF_PILE.asItem()
        )
        ColorProviderRegistry.ITEM.register(
            { _, _ -> FoliageColors.getBirchColor() },
            DuskBlocks.BIRCH_LEAF_PILE.asItem()
        )
        ColorProviderRegistry.ITEM.register(
            { _, _ -> CASCADE_LEAF_COLOR }, DuskBlocks.CASCADE_LEAVES.asItem(), DuskBlocks.CASCADE_LEAF_PILE.asItem()
        )
        ColorProviderRegistry.ITEM.register(
            { stack, _ -> DyedColorComponent.getColorOrDefault(stack, 0xffffff) }, DuskItems.FARMERS_HAT
        )
    }

    fun grassColorOrDefault(world: BlockRenderView?, pos: BlockPos?): Int {
        return if (world != null && pos != null) BiomeColors.getGrassColor(world, pos)
        else GrassColors.getDefault()
    }

    fun foliageColorOrDefault(world: BlockRenderView?, pos: BlockPos?): Int {
        return if (world != null && pos != null) BiomeColors.getFoliageColor(world, pos)
        else FoliageColors.getColor(0.8, 0.4)
    }
}
