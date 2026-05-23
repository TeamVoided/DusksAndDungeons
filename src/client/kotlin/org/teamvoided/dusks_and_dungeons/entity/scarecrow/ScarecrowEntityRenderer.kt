package org.teamvoided.dusks_and_dungeons.entity.scarecrow

import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.LivingEntityRenderer
import net.minecraft.client.renderer.entity.layers.ElytraLayer
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.resources.ResourceLocation
import com.mojang.math.Axis
import net.minecraft.util.Mth
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.entity.DnDEntityModelLayers
import org.teamvoided.dusks_and_dungeons.entity.ScarecrowEntity
import org.teamvoided.dusks_and_dungeons.entity.scarecrow.model.ScarecrowArmorEntityModel
import org.teamvoided.dusks_and_dungeons.entity.scarecrow.model.ScarecrowEntityModel
import org.teamvoided.dusks_and_dungeons.entity.scarecrow.render.ScarecrowArmorFeatureRenderer
import org.teamvoided.dusks_and_dungeons.entity.scarecrow.render.ScarecrowWoodFeatureRenderer
import org.teamvoided.dusks_and_dungeons.util.pi

class ScarecrowEntityRenderer(context: EntityRendererProvider.Context) :
    LivingEntityRenderer<ScarecrowEntity, ScarecrowEntityModel>(
        context,
        ScarecrowEntityModel(context.bakeLayer(DnDEntityModelLayers.SCARECROW)),
        0f
    ) {

    init {
        this.addLayer(ItemInHandLayer(this, context.itemInHandRenderer))
        this.addLayer(CustomHeadLayer(this, context.modelSet, context.itemInHandRenderer))
        this.addLayer(
            ScarecrowArmorFeatureRenderer(
                this,
                ScarecrowArmorEntityModel(context.bakeLayer(DnDEntityModelLayers.SCARECROW_INNER_ARMOR)),
                ScarecrowArmorEntityModel(context.bakeLayer(DnDEntityModelLayers.SCARECROW_OUTER_ARMOR)),
                context.modelManager
            )
        )
        this.addLayer(ElytraLayer(this, context.modelSet))
        this.addLayer(ScarecrowWoodFeatureRenderer(this, context.modelSet))
    }

    override fun setupRotations(
        scarecrowEntity: ScarecrowEntity,
        matrices: PoseStack,
        animationProgress: Float,
        bodyYaw: Float,
        tickDelta: Float,
        i: Float
    ) {
        matrices.mulPose(Axis.YP.rotationDegrees(180f - bodyYaw))
        val sinceLastHit = (scarecrowEntity.level().gameTime - scarecrowEntity.lastHitTime).toFloat() + tickDelta
        if (sinceLastHit < ScarecrowEntity.WOBBLE_DURATION) {
            matrices.mulPose(Axis.YP.rotationDegrees(Mth.sin(sinceLastHit / 1.5f * pi) * 3f))
        }
    }

    override fun shouldShowName(scarecrowEntity: ScarecrowEntity): Boolean {
        val distance = entityRenderDispatcher.distanceToSqr(scarecrowEntity)
        val range = if (scarecrowEntity.isCrouching) 32.0f else 64.0f
        return if (distance >= (range * range).toDouble()) false else scarecrowEntity.isCustomNameVisible
    }

    override fun getTextureLocation(scarecrowEntity: ScarecrowEntity): ResourceLocation = TEXTURE

    companion object {
        private val TEXTURE: ResourceLocation = id("minecraft", "textures/block/red_wool.png")
    }
}