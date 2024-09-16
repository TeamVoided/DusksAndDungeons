////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by FernFlower decompiler)
////
//package net.minecraft.client.render.entity.feature
//
//import net.fabricmc.api.EnvType
//import net.fabricmc.api.Environment
//import net.minecraft.block.BlockState
//import net.minecraft.entity.passive.MooshroomEntity
//import net.minecraft.util.math.Axis
//
//@Environment(EnvType.CLIENT)
//class MooshroomMushroomFeatureRenderer<T : MooshroomEntity?>(context: FeatureRendererContext<T, CowEntityModel<T>?>?,blockRenderManager: BlockRenderManager) : FeatureRenderer<T, CowEntityModel<T>?>(context) {
//    private val blockRenderManager: BlockRenderManager = blockRenderManager
//
//    override fun render(
//        matrices: MatrixStack,
//        vertexConsumers: VertexConsumerProvider,
//        i: Int,
//        mooshroomEntity: T,
//        f: Float,
//        g: Float,
//        h: Float,
//        j: Float,
//        k: Float,
//        l: Float
//    ) {
//        if (!mooshroomEntity!!.isBaby) {
//            val minecraftClient: net.minecraft.client.MinecraftClient =
//                net.minecraft.client.MinecraftClient.getInstance()
//            val bl = minecraftClient.hasOutline(mooshroomEntity) && mooshroomEntity.isInvisible
//            if (!mooshroomEntity.isInvisible || bl) {
//                val blockState = mooshroomEntity.variant.mushroomState
//                val m: Int = LivingEntityRenderer.getOverlay(mooshroomEntity, 0.0f)
//                val bakedModel: BakedModel = blockRenderManager.getModel(blockState)
//                matrices.push()
//                matrices.translate(0.2f, -0.35f, 0.5f)
//                matrices.rotate(Axis.Y_POSITIVE.rotationDegrees(-48.0f))
//                matrices.scale(-1.0f, -1.0f, 1.0f)
//                matrices.translate(-0.5f, -0.5f, -0.5f)
//                this.renderMushroom(matrices, vertexConsumers, i, bl, blockState, m, bakedModel)
//                matrices.pop()
//                matrices.push()
//                matrices.translate(0.2f, -0.35f, 0.5f)
//                matrices.rotate(Axis.Y_POSITIVE.rotationDegrees(42.0f))
//                matrices.translate(0.1f, 0.0f, -0.6f)
//                matrices.rotate(Axis.Y_POSITIVE.rotationDegrees(-48.0f))
//                matrices.scale(-1.0f, -1.0f, 1.0f)
//                matrices.translate(-0.5f, -0.5f, -0.5f)
//                this.renderMushroom(matrices, vertexConsumers, i, bl, blockState, m, bakedModel)
//                matrices.pop()
//                matrices.push()
//                (this.getContextModel() as CowEntityModel).getHead().rotate(matrices)
//                matrices.translate(0.0f, -0.7f, -0.2f)
//                matrices.rotate(Axis.Y_POSITIVE.rotationDegrees(-78.0f))
//                matrices.scale(-1.0f, -1.0f, 1.0f)
//                matrices.translate(-0.5f, -0.5f, -0.5f)
//                this.renderMushroom(matrices, vertexConsumers, i, bl, blockState, m, bakedModel)
//                matrices.pop()
//            }
//        }
//    }
//
//    private fun renderMushroom(
//        matrices: MatrixStack,
//        vertexConsumers: VertexConsumerProvider,
//        light: Int,
//        renderAsModel: Boolean,
//        mushroomState: BlockState,
//        overlay: Int,
//        mushroomModel: BakedModel
//    ) {
//        if (renderAsModel) {
//            blockRenderManager.getModelRenderer().render(
//                matrices.peek(),
//                vertexConsumers.getBuffer(RenderLayer.getOutline(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE)),
//                mushroomState,
//                mushroomModel,
//                0.0f,
//                0.0f,
//                0.0f,
//                light,
//                overlay
//            )
//        } else {
//            blockRenderManager.renderBlockAsEntity(mushroomState, matrices, vertexConsumers, light, overlay)
//        }
//    }
//}