//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package net.minecraft.client.render.entity

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.model.EntityModelLayers
import net.minecraft.client.render.entity.model.WindChargeEntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.projectile.WindChargeProjectileEntity
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper

@Environment(EnvType.CLIENT)
class WindChargeEntityRenderer(context: EntityRendererFactory.Context) :
    EntityRenderer<WindChargeProjectileEntity>(context) {
    private val model = WindChargeEntityModel(context.getPart(EntityModelLayers.WIND_CHARGE))

    override fun render(
        windChargeProjectileEntity: WindChargeProjectileEntity,
        f: Float,
        g: Float,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        i: Int
    ) {
        if (windChargeProjectileEntity.age >= 2 || !(dispatcher.camera.focusedEntity.squaredDistanceTo(
                windChargeProjectileEntity
            ) < field_52258.toDouble())
        ) {
            val h = windChargeProjectileEntity.age.toFloat() + g
            val vertexConsumer = vertexConsumers.getBuffer(
                RenderLayer.getBreezeWind(
                    TEXTURE,
                    this.method_55268(h) % 1.0f, 0.0f
                )
            )
            model.setAngles(windChargeProjectileEntity, 0.0f, 0.0f, h, 0.0f, 0.0f)
            model.method_60879(matrices, vertexConsumer, i, OverlayTexture.DEFAULT_UV)
            super.render(windChargeProjectileEntity, f, g, matrices, vertexConsumers, i)
        }
    }

    protected fun method_55268(f: Float): Float {
        return f * 0.03f
    }

    override fun getTexture(windChargeProjectileEntity: WindChargeProjectileEntity): Identifier {
        return TEXTURE
    }

    companion object {
        private val field_52258 = MathHelper.square(3.5f)
        private val TEXTURE: Identifier = Identifier.ofDefault("textures/entity/projectiles/wind_charge.png")
    }
}