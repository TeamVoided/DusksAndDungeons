package org.teamvoided.dusk_autumn

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.fabric.api.client.particle.v1.ParticleRenderEvents
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.ResourcePackActivationType
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.client.color.world.BiomeColors
import net.minecraft.client.color.world.FoliageColors
import net.minecraft.client.color.world.GrassColors
import net.minecraft.client.particle.FlameParticle
import net.minecraft.client.render.RenderLayer
import net.minecraft.component.type.DyedColorComponent
import org.teamvoided.dusk_autumn.util.DnDBlockLists.coloredBlocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockRenderView
import org.teamvoided.dusk_autumn.DuskAutumns.MODID
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.entity.DnDEntityModelLayers
import org.teamvoided.dusk_autumn.entity.chill_charge.ChillChargeEntityRenderer
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.DnDEntities
import org.teamvoided.dusk_autumn.init.DnDItems
import org.teamvoided.dusk_autumn.init.DnDParticles.CASCADE_LEAF_PARTICLE
import org.teamvoided.dusk_autumn.init.DnDParticles.SMALL_SOUL_FLAME_PARTICLE
import org.teamvoided.dusk_autumn.init.DnDParticles.SNOWFLAKE
import org.teamvoided.dusk_autumn.particle.FallingLeafParticle.Companion.FallingLeafFactory
import org.teamvoided.dusk_autumn.particle.SnowflakeParticle

@Suppress("unused")
object DuskAutumnsClient {

    //    val key = KeyBindingHelper.registerKeyBinding(KeyBind("debug", InputUtil.KEY_R_CODE, "debug"))
    const val CASCADE_LEAF_COLOR = 14701655

//    var cooldown = 0

    fun init() {
        DnDEntityModelLayers.init()
        initBlocks()
        initItems()
        ParticleFactoryRegistry.getInstance().register(CASCADE_LEAF_PARTICLE, ::FallingLeafFactory)
        ParticleFactoryRegistry.getInstance().register(SMALL_SOUL_FLAME_PARTICLE, FlameParticle::SmallFactory)
        ParticleFactoryRegistry.getInstance().register(SNOWFLAKE, SnowflakeParticle::Factory)


        EntityRendererRegistry.register(DnDEntities.CHILL_CHARGE, ::ChillChargeEntityRenderer)

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

        FabricLoader.getInstance().getModContainer(MODID).ifPresent {
            ResourceManagerHelper.registerBuiltinResourcePack(id("fancy_names"), it, ResourcePackActivationType.NORMAL)
        }
    }


    private fun initBlocks() {

        ColorProviderRegistry.BLOCK.register(
            { _, world, pos, _ ->
                foliageColorOrDefault(world, pos)
            },
            DnDBlocks.OAK_LEAF_PILE,
            DnDBlocks.JUNGLE_LEAF_PILE,
            DnDBlocks.ACACIA_LEAF_PILE,
            DnDBlocks.DARK_OAK_LEAF_PILE,
            DnDBlocks.MANGROVE_LEAF_PILE
        )
        ColorProviderRegistry.BLOCK.register(
            { _, world, pos, tintIndex ->
                if (tintIndex != 0) {
                    grassColorOrDefault(world, pos)
                } else -1
            },
            DnDBlocks.BLUE_PETALS
        )
        ColorProviderRegistry.BLOCK.register(
            { _, world, pos, _ -> grassColorOrDefault(world, pos) },
            *coloredBlocks.toTypedArray()
        )
        ColorProviderRegistry.BLOCK.register(
            { _, _, _, _ -> FoliageColors.getSpruceColor() }, DnDBlocks.SPRUCE_LEAF_PILE
        )
        ColorProviderRegistry.BLOCK.register(
            { _, _, _, _ -> FoliageColors.getBirchColor() }, DnDBlocks.BIRCH_LEAF_PILE
        )
        ColorProviderRegistry.BLOCK.register(
            { _, _, _, _ -> CASCADE_LEAF_COLOR }, DnDBlocks.CASCADE_LEAVES, DnDBlocks.CASCADE_LEAF_PILE
        )

//    val GOLDEN_BIRCH_COLOR = 16761873
//    val GOLDEN_BIRCH_COLOR = 16760872

        DnDBlocks.CUTOUT_BLOCKS.forEach { BlockRenderLayerMap.INSTANCE.putBlock(it, RenderLayer.getCutout()) }
        DnDBlocks.TRANSLUCENT_BLOCKS.forEach { BlockRenderLayerMap.INSTANCE.putBlock(it, RenderLayer.getTranslucent()) }
    }

    private fun initItems() {
        ColorProviderRegistry.ITEM.register(
            { _, _ -> GrassColors.getDefault() },
            *coloredBlocks.map { it.asItem() }.toTypedArray()
        )
        ColorProviderRegistry.ITEM.register(
            { _, _ -> FoliageColors.getDefaultColor() },
            DnDBlocks.OAK_LEAF_PILE.asItem(),
            DnDBlocks.JUNGLE_LEAF_PILE.asItem(),
            DnDBlocks.ACACIA_LEAF_PILE.asItem(),
            DnDBlocks.DARK_OAK_LEAF_PILE.asItem()
        )
        ColorProviderRegistry.ITEM.register(
            { _, _ -> FoliageColors.getSpruceColor() },
            DnDBlocks.SPRUCE_LEAF_PILE.asItem()
        )
        ColorProviderRegistry.ITEM.register(
            { _, _ -> FoliageColors.getBirchColor() },
            DnDBlocks.BIRCH_LEAF_PILE.asItem()
        )
        ColorProviderRegistry.ITEM.register(
            { _, _ -> FoliageColors.getMangroveColor() },
            DnDBlocks.MANGROVE_LEAF_PILE.asItem()
        )
        ColorProviderRegistry.ITEM.register(
            { _, _ -> CASCADE_LEAF_COLOR }, DnDBlocks.CASCADE_LEAVES.asItem(), DnDBlocks.CASCADE_LEAF_PILE.asItem()
        )
        ColorProviderRegistry.ITEM.register(
            { stack, _ -> DyedColorComponent.getColorOrDefault(stack, 0xffffff) }, DnDItems.FARMERS_HAT
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
