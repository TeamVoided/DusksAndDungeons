package org.teamvoided.dusks_and_dungeons.mixin.signs;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusks_and_dungeons.block.DnDWoodTypes;

import static org.teamvoided.dusks_and_dungeons.util.ClientUtilsKt.renderTintedSign;

@Mixin(SignRenderer.class)
public abstract class SignRendererMixin {

    @Shadow
    public abstract float getSignModelRenderScale();

    @Shadow
    abstract Material getSignMaterial(WoodType woodType);

    @WrapOperation(
            method = "renderSignWithText",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/blockentity/SignRenderer;renderSign(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IILnet/minecraft/world/level/block/state/properties/WoodType;Lnet/minecraft/client/model/Model;)V"
            )
    )
    public void renderVerdantSign(SignRenderer instance, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j, WoodType woodType, Model model, Operation<Void> original, SignBlockEntity sign) {
        if (woodType == DnDWoodTypes.VERDANT_WOOD_TYPE) {
            renderTintedSign(sign, poseStack, multiBufferSource, i, j, model, getSignModelRenderScale(), getSignMaterial(woodType));
        } else {
            original.call(instance, poseStack, multiBufferSource, i, j, woodType, model);
        }
    }

}