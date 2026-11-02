package org.teamvoided.dusks_and_dungeons.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.renderer.entity.FoxRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Fox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static org.teamvoided.dusks_and_dungeons.client.util.DnDSprites.SILVER_TEXTURE;
import static org.teamvoided.dusks_and_dungeons.client.util.DnDSprites.SLEEPING_SILVER_TEXTURE;

@Mixin(FoxRenderer.class)
public class FoxRendererMixin {

    @ModifyReturnValue(method = "getTextureLocation(Lnet/minecraft/world/entity/animal/Fox;)Lnet/minecraft/resources/ResourceLocation;", at = @At("RETURN"))
    public ResourceLocation addDuskFox(ResourceLocation original, Fox foxEntity) {
        if (foxEntity.getVariant().getId() == 2) {
            return foxEntity.isSleeping() ? SLEEPING_SILVER_TEXTURE : SILVER_TEXTURE;
        }
        return original;
    }

}