package org.teamvoided.dusks_and_dungeons.mixin.signs;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.gui.screens.inventory.SignEditScreen;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusks_and_dungeons.util.SignColorProvider;

@Mixin(SignEditScreen.class)
public abstract class SignEditScreenMixin implements SignColorProvider {

    @WrapOperation(
            method = "renderSignBackground",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/geom/ModelPart;render(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;II)V")
    )
    void renderTinted(ModelPart instance, PoseStack poseStack, VertexConsumer vertexConsumer, int light, int overlay, Operation<Void> original) {
        var color = dnd_getSignColor();
        if (color != null) {
            instance.render(poseStack, vertexConsumer, light, overlay, color);
        } else {
            original.call(instance, poseStack, vertexConsumer, light, overlay);
        }
    }

}
