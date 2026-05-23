package org.teamvoided.dusks_and_dungeons.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

// TODO move to voidlib
@Mixin(EntityRenderDispatcher.class)
public class ShadowAttributeMixin {

    @ModifyExpressionValue(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/EntityRenderer;getShadowRadius(Lnet/minecraft/world/entity/Entity;)F"))
    float shadowAttribute(float original, Entity entity) {
        var shadowSize = original;
        if (entity instanceof LivingEntity living) {
            for (ItemStack it : living.getArmorAndBodyArmorSlots()) {
//                if (it.isOf(DnDItems.VILE_WITCH_HAT)) shadowSize *= 2;
            }
        }
        return shadowSize;
    }
}
