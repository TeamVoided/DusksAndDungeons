package org.teamvoided.dusk_autumn

import com.mojang.blaze3d.platform.InputUtil
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.client.color.world.BiomeColors
import net.minecraft.client.color.world.FoliageColors
import net.minecraft.client.color.world.GrassColors
import net.minecraft.client.option.KeyBind
import net.minecraft.client.render.RenderLayer
import net.minecraft.component.type.DyedColorComponent
import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.init.DuskItems
import org.teamvoided.dusk_autumn.init.DuskParticles.CASCADE_LEAF_PARTICLE
import org.teamvoided.dusk_autumn.particle.FallingLeafParticle.Companion.FallingLeafFactory

@Suppress("unused")
class DuskAutumnsClient {

    val key = KeyBindingHelper.registerKeyBinding(KeyBind("debug", InputUtil.KEY_R_CODE, "debug"))
    val CASCADE_LEAF_COLOR = 14701655

    var cooldown = 0
    fun init() {
        initBlocks()
        initItems()
        ParticleFactoryRegistry.getInstance().register(CASCADE_LEAF_PARTICLE, ::FallingLeafFactory)

        ClientTickEvents.END_CLIENT_TICK.register {
            if (key.isPressed && cooldown < 1) {
                it.networkHandler?.sendCommand("place feature dusk_autumn:cascade_tree")
                cooldown = 35
            }

            if (cooldown > 0) cooldown--
        }
    }


    private fun initBlocks() {
        listOf(
            DuskBlocks.CASCADE_SAPLING,
            DuskBlocks.POTTED_CASCADE_SAPLING,
            DuskBlocks.CASCADE_LEAVES,
            DuskBlocks.BLUE_PETALS,
            DuskBlocks.VIOLET_DAISY,
            DuskBlocks.POTTED_VIOLET_DAISY,
            DuskBlocks.GOLDEN_BIRCH_SAPLING,
            DuskBlocks.POTTED_GOLDEN_BIRCH_SAPLING,
            DuskBlocks.GOLDEN_BIRCH_LEAVES,
            DuskBlocks.CASCADE_LEAF_PILE,
            DuskBlocks.OAK_LEAF_PILE,
            DuskBlocks.SPRUCE_LEAF_PILE,
            DuskBlocks.BIRCH_LEAF_PILE,
            DuskBlocks.JUNGLE_LEAF_PILE,
            DuskBlocks.ACACIA_LEAF_PILE,
            DuskBlocks.DARK_OAK_LEAF_PILE,
            DuskBlocks.MANGROVE_LEAF_PILE,
            DuskBlocks.CHERRY_LEAF_PILE,
            DuskBlocks.AZALEA_LEAF_PILE,
            DuskBlocks.FLOWERING_AZALEA_LEAF_PILE,
            DuskBlocks.GOLDEN_BIRCH_LEAF_PILE,
            DuskBlocks.WILD_WHEAT,
            DuskBlocks.GOLDEN_BEETROOTS,
            DuskBlocks.MOONBERRY_VINE,
            DuskBlocks.MOONBERRY_VINELET
        ).forEach { BlockRenderLayerMap.INSTANCE.putBlock(it, RenderLayer.getCutout()) }

        ColorProviderRegistry.BLOCK.register(
            { _, world, pos, _ ->
                if (world != null && pos != null) BiomeColors.getFoliageColor(world, pos)
                else FoliageColors.getColor(0.8, 0.4)
            },
            DuskBlocks.OAK_LEAF_PILE, DuskBlocks.JUNGLE_LEAF_PILE,
            DuskBlocks.ACACIA_LEAF_PILE, DuskBlocks.DARK_OAK_LEAF_PILE, DuskBlocks.MANGROVE_LEAF_PILE
        )
        ColorProviderRegistry.BLOCK.register(
            { _, world, pos, _ ->
                if (world != null && pos != null) BiomeColors.getGrassColor(world, pos)
                else GrassColors.getColor(0.8, 0.4)
            }, DuskBlocks.BLUE_PETALS
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
    }

    private fun initItems() {
        ColorProviderRegistry.ITEM.register(
            { _, _ -> FoliageColors.getDefaultColor() },
            DuskBlocks.OAK_LEAF_PILE.asItem(),
            DuskBlocks.JUNGLE_LEAF_PILE.asItem(),
            DuskBlocks.ACACIA_LEAF_PILE.asItem(),
            DuskBlocks.DARK_OAK_LEAF_PILE.asItem(),
            DuskBlocks.MANGROVE_LEAF_PILE.asItem()
        )

        ColorProviderRegistry.ITEM.register({ _, _ -> FoliageColors.getSpruceColor() }, DuskBlocks.SPRUCE_LEAF_PILE.asItem())
        ColorProviderRegistry.ITEM.register({ _, _ -> FoliageColors.getBirchColor() }, DuskBlocks.BIRCH_LEAF_PILE.asItem())
        ColorProviderRegistry.ITEM.register(
            { _, _ -> CASCADE_LEAF_COLOR }, DuskBlocks.CASCADE_LEAVES.asItem(), DuskBlocks.CASCADE_LEAF_PILE.asItem()
        )
        ColorProviderRegistry.ITEM.register(
            { stack, _ -> DyedColorComponent.getColorOrDefault(stack, 0xffffff) }, DuskItems.FARMERS_HAT
        )
    }
}