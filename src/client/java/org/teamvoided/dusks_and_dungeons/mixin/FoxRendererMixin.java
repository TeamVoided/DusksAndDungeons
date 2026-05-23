package org.teamvoided.dusks_and_dungeons.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.renderer.entity.FoxRenderer;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons;

@Mixin(FoxRenderer.class)
public class FoxRendererMixin {
    @Unique
    private static final ResourceLocation SILVER_TEXTURE = DusksAndDungeons.INSTANCE.id("textures/entity/fox/silver_fox.png");
    @Unique
    private static final ResourceLocation SLEEPING_SILVER_TEXTURE = DusksAndDungeons.INSTANCE.id("textures/entity/fox/silver_fox_sleep.png");

    @ModifyReturnValue(method = "getTextureLocation(Lnet/minecraft/world/entity/animal/Fox;)Lnet/minecraft/resources/ResourceLocation;", at = @At("RETURN"))
    public ResourceLocation addDuskFox(ResourceLocation original, Fox foxEntity) {
        if (foxEntity.getVariant().getId() == 2) {
            return foxEntity.isSleeping() ? SLEEPING_SILVER_TEXTURE : SILVER_TEXTURE;
        }
        return original;
    }
}