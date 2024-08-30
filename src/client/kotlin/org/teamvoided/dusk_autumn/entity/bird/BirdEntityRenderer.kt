//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package org.teamvoided.dusk_autumn.entity.bird

import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.client.render.entity.model.EntityModelLayers
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper
import org.teamvoided.dusk_autumn.entity.BirdEntity
import org.teamvoided.dusk_autumn.entity.DnDEntityModelLayers
import org.teamvoided.dusk_autumn.entity.bird.render.BirdEntityModel

class BirdEntityRenderer(context: EntityRendererFactory.Context) :
    MobEntityRenderer<BirdEntity, BirdEntityModel>(
        context,
        BirdEntityModel(context.getPart(DnDEntityModelLayers.BIRD)),
        0.3f
    ) {
    override fun getTexture(parrotEntity: BirdEntity): Identifier {
        return TEXTURE
    }

    override fun getAnimationProgress(birdEntity: BirdEntity, f: Float): Float {
        val g = MathHelper.lerp(f, birdEntity.prevFlapProgress, birdEntity.flapProgress)
        val h = MathHelper.lerp(f, birdEntity.prevMaxWingDeviation, birdEntity.maxWingDeviation)
        return (MathHelper.sin(g) + 1.0f) * h
    }

    companion object {
        private val TEXTURE: Identifier = Identifier.ofDefault("textures/block/white_concrete.png")
    }
}