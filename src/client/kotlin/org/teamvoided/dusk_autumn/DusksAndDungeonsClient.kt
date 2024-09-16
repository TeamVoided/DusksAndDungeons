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
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory
import net.minecraft.component.type.DyedColorComponent
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockRenderView
import org.teamvoided.dusk_autumn.DusksAndDungeons.MODID
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import org.teamvoided.dusk_autumn.entity.DnDEntityModelLayers
import org.teamvoided.dusk_autumn.entity.bird.BirdEntityRenderer
import org.teamvoided.dusk_autumn.entity.block.CelestalBellBlockEntityRenderer
import org.teamvoided.dusk_autumn.entity.chill_charge.ChillChargeEntityRenderer
import org.teamvoided.dusk_autumn.init.*
import org.teamvoided.dusk_autumn.init.blocks.DnDWoodBlocks
import org.teamvoided.dusk_autumn.particle.ColorableOminousParticle
import org.teamvoided.dusk_autumn.particle.FallingLeafParticle.Companion.FallingLeafFactory
import org.teamvoided.dusk_autumn.particle.SnowflakeParticle
import org.teamvoided.dusk_autumn.particle.SpiderlilyPetalParticle
import org.teamvoided.dusk_autumn.particle.SpiralParticle
import org.teamvoided.dusk_autumn.util.DnDBlockLists

@Suppress("unused")
object DusksAndDungeonsClient {

    //    val key = KeyBindingHelper.registerKeyBinding(KeyBind("debug", InputUtil.KEY_R_CODE, "debug"))
//    const val CASCADE_LEAF_COLOR = 14701655
//    const val GOLDEN_BIRCH_COLOR = 16761873
//    const val GOLDEN_BIRCH_COLOR = 16760872

//    var cooldown = 0

    fun init() {
        DnDEntityModelLayers.init()
        initBlocks()
        initItems()
        ParticleFactoryRegistry.getInstance().register(DnDParticles.CASCADE_LEAF_PARTICLE, ::FallingLeafFactory)
        ParticleFactoryRegistry.getInstance().register(
            DnDParticles.SMALL_SOUL_FLAME_PARTICLE, FlameParticle::SmallFactory
        )
        ParticleFactoryRegistry.getInstance().register(
            DnDParticles.COLORABLE_OMINOUS_PARTICLE, ColorableOminousParticle::Factory
        )
        ParticleFactoryRegistry.getInstance().register(DnDParticles.SPIDERLILY, SpiderlilyPetalParticle::Factory)
        ParticleFactoryRegistry.getInstance().register(DnDParticles.SNOWFLAKE, SnowflakeParticle::Factory)

        ParticleFactoryRegistry.getInstance().register(DnDParticles.SPIRAL, SpiralParticle::Factory)

        EntityRendererRegistry.register(DnDEntities.CHILL_CHARGE, ::ChillChargeEntityRenderer)
        EntityRendererRegistry.register(DnDEntities.BIRD_TEST, ::BirdEntityRenderer)

//        ClientTickEvents.END_CLIENT_TICK.register {
//            if (key.isPressed && cooldown < 1) {
//                it.networkHandler?.sendCommand("place feature dusk_autumn:cascade_tree")
//                cooldown = 35
//            }
//
//            if (cooldown > 0) cooldown--
//        }

        ParticleRenderEvents.ALLOW_BLOCK_DUST_TINT.register { blockState, _, _ ->
            blockState.block !in DnDBlocks.GRASS_TINT_BLOCKS
        }

        BlockEntityRendererFactories.register(DnDBlockEntities.CELESTAL_BELL) { context: BlockEntityRendererFactory.Context ->
            CelestalBellBlockEntityRenderer(
                context, context.renderManager, DnDBlocks.CELESTAL_BELL
            )
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
            DnDWoodBlocks.OAK_LEAF_PILE,
            DnDWoodBlocks.JUNGLE_LEAF_PILE,
            DnDWoodBlocks.ACACIA_LEAF_PILE,
            DnDWoodBlocks.DARK_OAK_LEAF_PILE,
            DnDWoodBlocks.MANGROVE_LEAF_PILE
        )
        ColorProviderRegistry.BLOCK.register(
            { _, world, pos, tintIndex ->
                if (tintIndex != 0) {
                    grassColorOrDefault(world, pos)
                } else -1
            },
            *DnDBlockLists.flowerbedBlocks.toTypedArray()
        )
        ColorProviderRegistry.BLOCK.register(
            { _, world, pos, _ -> grassColorOrDefault(world, pos) },
            *DnDBlocks.GRASS_TINT_BLOCKS.toTypedArray()
        )
        ColorProviderRegistry.BLOCK.register(
            { _, _, _, _ -> FoliageColors.getSpruceColor() }, DnDWoodBlocks.SPRUCE_LEAF_PILE
        )
        ColorProviderRegistry.BLOCK.register(
            { _, _, _, _ -> FoliageColors.getBirchColor() }, DnDWoodBlocks.BIRCH_LEAF_PILE
        )

        DnDBlocks.CUTOUT_BLOCKS.forEach { BlockRenderLayerMap.INSTANCE.putBlock(it, RenderLayer.getCutout()) }
        DnDBlocks.TRANSLUCENT_BLOCKS.forEach { BlockRenderLayerMap.INSTANCE.putBlock(it, RenderLayer.getTranslucent()) }
    }

    private fun initItems() {
        ColorProviderRegistry.ITEM.register(
            { _, _ -> GrassColors.getDefault() },
            *DnDBlocks.GRASS_TINT_BLOCKS.map { it.asItem() }.toTypedArray()
        )
        ColorProviderRegistry.ITEM.register(
            { _, _ -> FoliageColors.getDefaultColor() },
            DnDWoodBlocks.OAK_LEAF_PILE.asItem(),
            DnDWoodBlocks.JUNGLE_LEAF_PILE.asItem(),
            DnDWoodBlocks.ACACIA_LEAF_PILE.asItem(),
            DnDWoodBlocks.DARK_OAK_LEAF_PILE.asItem()
        )
        ColorProviderRegistry.ITEM.register(
            { _, _ -> FoliageColors.getSpruceColor() },
            DnDWoodBlocks.SPRUCE_LEAF_PILE.asItem()
        )
        ColorProviderRegistry.ITEM.register(
            { _, _ -> FoliageColors.getBirchColor() },
            DnDWoodBlocks.BIRCH_LEAF_PILE.asItem()
        )
        ColorProviderRegistry.ITEM.register(
            { _, _ -> FoliageColors.getMangroveColor() },
            DnDWoodBlocks.MANGROVE_LEAF_PILE.asItem()
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
