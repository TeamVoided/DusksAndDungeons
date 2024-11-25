//package org.teamvoided.dusk_autumn.entity.scarecrow.render
//
//import net.minecraft.client.render.VertexConsumerProvider
//import net.minecraft.client.render.entity.feature.FeatureRenderer
//import net.minecraft.client.render.entity.feature.FeatureRendererContext
//import net.minecraft.client.util.math.MatrixStack
//import net.minecraft.entity.EquipmentSlot
//import net.minecraft.registry.Registries
//import net.minecraft.util.Identifier
//import org.teamvoided.dusk_autumn.DusksAndDungeons.id
//import org.teamvoided.dusk_autumn.entity.ScarecrowEntity
//import org.teamvoided.dusk_autumn.entity.scarecrow.model.ScarecrowEntityModel
//
//class ScarecrowBodyFeatureRenderer(
//    context: FeatureRendererContext<ScarecrowEntity, ScarecrowEntityModel>,
//    private val model:ScarecrowEntityModel
//) : FeatureRenderer<ScarecrowEntity, ScarecrowEntityModel>(context) {
//
//    override fun render(
//        matrices: MatrixStack,
//        vertexConsumers: VertexConsumerProvider,
//        i: Int,
//        scarecrowEntity: ScarecrowEntity,
//        f: Float,
//        g: Float,
//        h: Float,
//        j: Float,
//        k: Float,
//        l: Float
//    ) {
//        val bodyBlock = scarecrowEntity.getEquippedStack(EquipmentSlot.CHEST)
//        if (!cloakBlock.isEmpty) {
//            render(
//                this.contextModel,
//                this.model,
//                textureId(Registries.ITEM.getId(cloakBlock.item).path),
//                matrices,
//                vertexConsumers,
//                i,
//                scarecrowEntity,
//                f,
//                g,
//                j,
//                k,
//                l,
//                h,
//                -1
//            )
//        }
//    }
//
//    companion object {
//        private fun textureId(string: String): Identifier =
//            id("textures/entity/scarecrow/body/$string.png")
//    }
//}