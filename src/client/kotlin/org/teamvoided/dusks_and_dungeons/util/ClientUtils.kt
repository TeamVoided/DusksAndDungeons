package org.teamvoided.dusks_and_dungeons.util

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.Minecraft
import net.minecraft.client.model.Model
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.HangingSignRenderer
import net.minecraft.client.renderer.blockentity.SignRenderer.SignModel
import net.minecraft.client.resources.model.Material
import net.minecraft.world.level.block.entity.SignBlockEntity
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

val BETTER_BRICK_NAMES = id("fancy_names")

fun renderTintedSign(
    sign: SignBlockEntity,
    poseStack: PoseStack, bufferSource: MultiBufferSource, light: Int, overlay: Int,
    model: Model, scale: Float, material: Material,
) {
    poseStack.pushPose()
    poseStack.scale(scale, -scale, -scale)
    val vertexConsumer = material.buffer(bufferSource, model::renderType)

    val color = getColor(sign)
    (model as? SignModel)?.root?.render(poseStack, vertexConsumer, light, overlay, color)
    (model as? HangingSignRenderer.HangingSignModel)?.root?.render(poseStack, vertexConsumer, light, overlay, color)

    poseStack.popPose()
}

fun getColor(sign: SignBlockEntity): Int {
    return Minecraft.getInstance().blockColors.getColor(sign.blockState, sign.level, sign.blockPos, 0)
}
