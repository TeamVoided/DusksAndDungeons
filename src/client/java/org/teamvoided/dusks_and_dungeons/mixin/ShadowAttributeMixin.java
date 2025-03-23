package org.teamvoided.dusks_and_dungeons.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

// TODO move to voidlib
@Mixin(EntityRenderDispatcher.class)
public class ShadowAttributeMixin {

    @ModifyExpressionValue(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/EntityRenderer;method_55831(Lnet/minecraft/entity/Entity;)F"))
    float shadowAttribute(float original, Entity entity) {
        var shadowSize = original;
        if (entity instanceof LivingEntity living) {
            for (ItemStack it : living.getAllArmorItems()) {
//                if (it.isOf(DnDItems.VILE_WITCH_HAT)) shadowSize *= 2;
            }
        }
        return shadowSize;
    }
}
