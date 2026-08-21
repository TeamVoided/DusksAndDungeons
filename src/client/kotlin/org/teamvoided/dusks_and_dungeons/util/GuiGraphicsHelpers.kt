package org.teamvoided.dusks_and_dungeons.util

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.BufferUploader
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.Tesselator
import com.mojang.blaze3d.vertex.VertexFormat
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.resources.ResourceLocation
import org.joml.Matrix4f


fun GuiGraphics.blit(
    atlasLocation: ResourceLocation,
    x: Int, y: Int, uOffset: Float, vOffset: Float,
    width: Int, height: Int, textureWidth: Int, textureHeight: Int,
    color: Int,
) {
    blit(atlasLocation, x, y, width, height, uOffset, vOffset, width, height, textureWidth, textureHeight, color)
}

fun GuiGraphics.blit(
    atlasLocation: ResourceLocation,
    x: Int, y: Int, width: Int, height: Int,
    uOffset: Float, vOffset: Float, uWidth: Int, vHeight: Int,
    textureWidth: Int, textureHeight: Int,
    color: Int,
) {
    blit(
        atlasLocation, x, x + width, y, y + height, 0, uWidth, vHeight, uOffset, vOffset, textureWidth, textureHeight, color
    )
}



fun GuiGraphics.blit(
    atlasLocation: ResourceLocation,
    x1: Int, x2: Int, y1: Int, y2: Int, blitOffset: Int,
    uWidth: Int, vHeight: Int, uOffset: Float, vOffset: Float,
    textureWidth: Int, textureHeight: Int,
    color: Int,
) {
    innerBlit(
        atlasLocation, x1, x2, y1, y2, blitOffset,
        (uOffset + 0.0f) / textureWidth.toFloat(),
        (uOffset + uWidth.toFloat()) / textureWidth.toFloat(),
        (vOffset + 0.0f) / textureHeight.toFloat(),
        (vOffset + vHeight.toFloat()) / textureHeight.toFloat(),
        color,
    )
}


fun GuiGraphics.innerBlit(
    atlasLocation: ResourceLocation,
    x1: Int, x2: Int, y1: Int, y2: Int,
    blitOffset: Int,
    minU: Float, maxU: Float, minV: Float, maxV: Float,
    color: Int,
) {
    RenderSystem.setShaderTexture(0, atlasLocation)
    RenderSystem.setShader(GameRenderer::getPositionTexColorShader)
    val matrix4f: Matrix4f = pose().last().pose()
    val buffer = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR)
    buffer.addVertex(matrix4f, x1.toFloat(), y1.toFloat(), blitOffset.toFloat()).setUv(minU, minV).setColor(color)
    buffer.addVertex(matrix4f, x1.toFloat(), y2.toFloat(), blitOffset.toFloat()).setUv(minU, maxV).setColor(color)
    buffer.addVertex(matrix4f, x2.toFloat(), y2.toFloat(), blitOffset.toFloat()).setUv(maxU, maxV).setColor(color)
    buffer.addVertex(matrix4f, x2.toFloat(), y1.toFloat(), blitOffset.toFloat()).setUv(maxU, minV).setColor(color)
    BufferUploader.drawWithShader(buffer.buildOrThrow())
}