package org.teamvoided.dusk_autumn.entity.scarecrow

import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.LivingEntityRenderer
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier
import net.minecraft.util.math.Axis
import net.minecraft.util.math.MathHelper
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import org.teamvoided.dusk_autumn.entity.DnDEntityModelLayers
import org.teamvoided.dusk_autumn.entity.ScarecrowEntity
import org.teamvoided.dusk_autumn.entity.scarecrow.render.ScarecrowArmorFeatureRenderer
import org.teamvoided.dusk_autumn.entity.scarecrow.model.ScarecrowArmorEntityModel
import org.teamvoided.dusk_autumn.entity.scarecrow.model.ScarecrowEntityModel
import org.teamvoided.dusk_autumn.util.pi

class ScarecrowEntityRenderer(context: EntityRendererFactory.Context) :
    LivingEntityRenderer<ScarecrowEntity, ScarecrowEntityModel>(
        context,
        ScarecrowEntityModel(context.getPart(DnDEntityModelLayers.SCARECROW)),
        0f
    ) {

    init {
        this.addFeature(HeldItemFeatureRenderer(this, context.heldItemRenderer))
        this.addFeature(HeadFeatureRenderer(this, context.modelLoader, context.heldItemRenderer))
        this.addFeature(
            ScarecrowArmorFeatureRenderer(
                this,
                ScarecrowArmorEntityModel(context.getPart(DnDEntityModelLayers.SCARECROW_INNER_ARMOR)),
                ScarecrowArmorEntityModel(context.getPart(DnDEntityModelLayers.SCARECROW_OUTER_ARMOR)),
                context.modelManager
            )
        )
        this.addFeature(ElytraFeatureRenderer(this, context.modelLoader))
    }

    override fun setupTransforms(
        scarecrowEntity: ScarecrowEntity,
        matrices: MatrixStack,
        animationProgress: Float,
        bodyYaw: Float,
        tickDelta: Float,
        i: Float
    ) {
        matrices.rotate(Axis.Y_POSITIVE.rotationDegrees(180f - bodyYaw))
        val sinceLastHit = (scarecrowEntity.world.time - scarecrowEntity.lastHitTime).toFloat() + tickDelta
        if (sinceLastHit < ScarecrowEntity.WOBBLE_DURATION) {
            matrices.rotate(Axis.Y_POSITIVE.rotationDegrees(MathHelper.sin(sinceLastHit / 1.5f * pi) * 3f))
        }
    }

    override fun hasLabel(scarecrowEntity: ScarecrowEntity): Boolean {
        val distance = dispatcher.getSquaredDistanceToCamera(scarecrowEntity)
        val range = if (scarecrowEntity.isInSneakingPose) 32.0f else 64.0f
        return if (distance >= (range * range).toDouble()) false else scarecrowEntity.isCustomNameVisible
    }

    override fun getTexture(scarecrowEntity: ScarecrowEntity): Identifier = TEXTURE

    companion object {
        private val TEXTURE: Identifier = id("minecraft", "textures/block/red_wool.png")
    }
}