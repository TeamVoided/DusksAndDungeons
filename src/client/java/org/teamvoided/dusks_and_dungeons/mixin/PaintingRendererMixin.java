package org.teamvoided.dusks_and_dungeons.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.entity.PaintingRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.entity.decoration.Painting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PaintingRenderer.class)
public abstract class PaintingRendererMixin {

    @Shadow
    protected abstract void vertex(PoseStack.Pose pose, VertexConsumer vertexConsumer, float f, float g, float h, float i, float j, int k, int l, int m, int n);

    @Inject(
            method = "renderPainting",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/PaintingRenderer;vertex(Lcom/mojang/blaze3d/vertex/PoseStack$Pose;Lcom/mojang/blaze3d/vertex/VertexConsumer;FFFFFIIII)V",
                    ordinal = 8
            )
    )
    void customSideRenderer(PoseStack poseStack, VertexConsumer buffer, Painting painting, int width, int height, TextureAtlasSprite paintingSprite, TextureAtlasSprite backSprite, CallbackInfo ci,
                            @Local PoseStack.Pose pose,
                            @Local(ordinal = 2) int segmentX, @Local(ordinal = 3) int segmentY,
                            @Local(ordinal = 7) int lightCoords,
                            @Local(ordinal = 0) double deltaU, @Local(ordinal = 1) double deltaV,
                            @Local(ordinal = 15) float x0, @Local(ordinal = 16) float x1, @Local(ordinal = 17) float y0, @Local(ordinal = 18) float y1
    ) {
        enders_paintings$renderSides(buffer, width, height, paintingSprite, pose, segmentX, segmentY, lightCoords, deltaU, deltaV, x0, x1, y0, y1);
    }

    @Unique
    void enders_paintings$renderSides(VertexConsumer buffer, int width, int height, TextureAtlasSprite paintingSprite, PoseStack.Pose pose,
                                      int segmentX, int segmentY, int lightCoords, double deltaU, double deltaV, float x0, float x1, float y0, float y1) {
        double newDeltaU;
        double newDeltaV;

        float frontU0;
        float frontU1;
        float frontV0;
        float frontV1;

        if (segmentY == height - 1) {
            newDeltaU = deltaU;
            newDeltaV = deltaV / 16;

            frontU0 = paintingSprite.getU((float) (newDeltaU * (width - segmentX)));
            frontU1 = paintingSprite.getU((float) (newDeltaU * (width - (segmentX + 1))));
            frontV0 = paintingSprite.getV((float) (newDeltaV * (height - segmentY)));
            frontV1 = paintingSprite.getV((float) (newDeltaV * (height - (segmentY + 1))));

            vertex(pose, buffer, x0, y0, frontU1, frontV0, -0.03125F, 0, 1, 0, lightCoords);
            vertex(pose, buffer, x1, y0, frontU0, frontV0, -0.03125F, 0, 1, 0, lightCoords);
            vertex(pose, buffer, x1, y0, frontU0, frontV1, 0.03125F, 0, 1, 0, lightCoords);
            vertex(pose, buffer, x0, y0, frontU1, frontV1, 0.03125F, 0, 1, 0, lightCoords);
        }

        if (segmentY == 0) {
            newDeltaU = deltaU;

            frontU0 = paintingSprite.getU((float) (newDeltaU * (width - segmentX)));
            frontU1 = paintingSprite.getU((float) (newDeltaU * (width - (segmentX + 1))));
            frontV0 = paintingSprite.getV((float) (deltaV * (height - segmentY)));
            frontV1 = paintingSprite.getV((float) (deltaV * (height - (segmentY + 1f / 16f))));

            vertex(pose, buffer, x0, y1, frontU1, frontV0, 0.03125F, 0, -1, 0, lightCoords);
            vertex(pose, buffer, x1, y1, frontU0, frontV0, 0.03125F, 0, -1, 0, lightCoords);
            vertex(pose, buffer, x1, y1, frontU0, frontV1, -0.03125F, 0, -1, 0, lightCoords);
            vertex(pose, buffer, x0, y1, frontU1, frontV1, -0.03125F, 0, -1, 0, lightCoords);
        }


        if (segmentX == width - 1) {
            newDeltaU = deltaU / 16;
            newDeltaV = deltaV;

            frontU0 = paintingSprite.getU((float) (newDeltaU * (width - segmentX)));
            frontU1 = paintingSprite.getU((float) (newDeltaU * (width - (segmentX + 1))));
            frontV0 = paintingSprite.getV((float) (newDeltaV * (height - segmentY)));
            frontV1 = paintingSprite.getV((float) (newDeltaV * (height - (segmentY + 1))));

            vertex(pose, buffer, x0, y0, frontU1, frontV1, 0.03125F, -1, 0, 0, lightCoords);
            vertex(pose, buffer, x0, y1, frontU1, frontV0, 0.03125F, -1, 0, 0, lightCoords);
            vertex(pose, buffer, x0, y1, frontU0, frontV0, -0.03125F, -1, 0, 0, lightCoords);
            vertex(pose, buffer, x0, y0, frontU0, frontV1, -0.03125F, -1, 0, 0, lightCoords);
        }

        if (segmentX == 0) {
            frontU0 = paintingSprite.getU((float) (deltaU * (width - segmentX)));
            frontU1 = paintingSprite.getU((float) (deltaU * (width - (segmentX + 1f / 16f))));
            frontV0 = paintingSprite.getV((float) (deltaV * (height - segmentY)));
            frontV1 = paintingSprite.getV((float) (deltaV * (height - (segmentY + 1))));

            vertex(pose, buffer, x1, y0, frontU1, frontV1, -0.03125F, 1, 0, 0, lightCoords);
            vertex(pose, buffer, x1, y1, frontU1, frontV0, -0.03125F, 1, 0, 0, lightCoords);
            vertex(pose, buffer, x1, y1, frontU0, frontV0, 0.03125F, 1, 0, 0, lightCoords);
            vertex(pose, buffer, x1, y0, frontU0, frontV1, 0.03125F, 1, 0, 0, lightCoords);
        }
    }


    @WrapWithCondition(
            method = "renderPainting",
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/PaintingRenderer;vertex(Lcom/mojang/blaze3d/vertex/PoseStack$Pose;Lcom/mojang/blaze3d/vertex/VertexConsumer;FFFFFIIII)V", ordinal = 8)
            ),
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/PaintingRenderer;vertex(Lcom/mojang/blaze3d/vertex/PoseStack$Pose;Lcom/mojang/blaze3d/vertex/VertexConsumer;FFFFFIIII)V")
    )
    boolean modifiedVertex(PaintingRenderer instance, PoseStack.Pose pose, VertexConsumer vertexConsumer, float f, float g, float h, float i, float j, int k, int l, int m, int n) {
        return false;
    }
}
