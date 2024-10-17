package org.teamvoided.dusk_autumn.mixin;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.teamvoided.dusk_autumn.init.DnDItems;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/EntityRenderer;method_55831(Lnet/minecraft/entity/Entity;)F"))
    float dusks_and_dungeons$bigger_shadow(EntityRenderer instance, Entity strider) {
        var shadowSize = ((EntityRendererAccessor) instance).dusks_and_dungeons$getShadowRadius(strider);
        if (strider instanceof LivingEntity entity) {
            for (ItemStack it : entity.getAllArmorItems()) {
                if (it.isOf(DnDItems.VILE_WITCH_HAT)) {
                    shadowSize *= 2;
                    break;
                }
            }
        }
        return shadowSize;
    }
}
