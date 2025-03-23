package org.teamvoided.dusks_and_dungeons.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.render.entity.FoxEntityRenderer;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons;

@Mixin(FoxEntityRenderer.class)
public class FoxEntityRendererMixin {
    @Unique
    private static final Identifier SILVER_TEXTURE = DusksAndDungeons.INSTANCE.id("textures/entity/fox/silver_fox.png");
    @Unique
    private static final Identifier SLEEPING_SILVER_TEXTURE = DusksAndDungeons.INSTANCE.id("textures/entity/fox/silver_fox_sleep.png");

    @ModifyReturnValue(method = "getTexture(Lnet/minecraft/entity/passive/FoxEntity;)Lnet/minecraft/util/Identifier;", at = @At("RETURN"))
    public Identifier addDuskFox(Identifier original, FoxEntity foxEntity) {
        if (foxEntity.getVariant().getId() == 2) {
            return foxEntity.isSleeping() ? SLEEPING_SILVER_TEXTURE : SILVER_TEXTURE;
        }
        return original;
    }
}