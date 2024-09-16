package org.teamvoided.dusk_autumn.entity.block

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.entity.BellBlockEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.block.BlockRenderManager
import net.minecraft.client.render.block.entity.BellBlockEntityRenderer
import net.minecraft.client.render.block.entity.BlockEntityRenderer
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory
import net.minecraft.client.render.model.BakedModel
import net.minecraft.client.resource.Material
import net.minecraft.client.texture.SpriteAtlasTexture
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.math.Direction
import net.minecraft.util.math.MathHelper
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import org.teamvoided.dusk_autumn.block.entity.CelestalBellBlockEntity
import org.teamvoided.dusk_autumn.util.id
import org.teamvoided.dusk_autumn.util.suffix

class CelestalBellBlockEntityRenderer(
    ctx: BlockEntityRendererFactory.Context,
    private val blockRenderManager: BlockRenderManager,
    private val block: Block
//) : BlockEntityRenderer<CelestalBellBlockEntity> {
) : BellBlockEntityRenderer(ctx) {
    override fun render(
        bellBlockEntity: BellBlockEntity,
        f: Float,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        i: Int,
        j: Int
    ) {
        val g = bellBlockEntity.ringTicks.toFloat() + f
        var h = 0.0f
        var k = 0.0f
        if (bellBlockEntity.ringing) {
            val l = MathHelper.sin(g / 3.1415927f) / (4.0f + g / 3.0f)
            when (bellBlockEntity.lastSideHit) {
                Direction.NORTH -> h = -l
                Direction.SOUTH -> h = l
                Direction.EAST -> k = -l
                Direction.WEST -> k = l
                else -> null
            }

            bellBody.pitch = h
            bellBody.roll = k
        }
        val vertexConsumer =
            Material(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, block.id.suffix("_body"))
                .getVertexConsumer(vertexConsumers, RenderLayer::getEntitySolid)
        bellBody.render(matrices, vertexConsumer, i, j)


//            matrices.push()
//            matrices.translate(0.2f, -0.35f, 0.5f)
////            matrices.rotate(Axis.Y_POSITIVE.rotationDegrees(-48.0f))
//            matrices.scale(-1.0f, -1.0f, 1.0f)
//            matrices.translate(-0.5f, -0.5f, -0.5f)
//            this.renderBell(matrices, vertexConsumers, i)
    }

    //    private fun renderBell(
//        matrices: MatrixStack,
//        vertexConsumers: VertexConsumerProvider,
//        light: Int
//    ) {
//        val blockState = Blocks.AMETHYST_BLOCK.defaultState
//        val bakedModel: BakedModel = blockRenderManager.getModel(blockState)
//        if (true) {
//            blockRenderManager.modelRenderer.render(
//                matrices.peek(),
//                vertexConsumers.getBuffer(RenderLayer.getOutline(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE)),
//                blockState,
//                bakedModel,
//                0.0f,
//                0.0f,
//                0.0f,
//                light,
//                0
//            )
//        } else {
//            blockRenderManager.renderBlockAsEntity(blockState, matrices, vertexConsumers, light, 0)
//        }
//    }
}