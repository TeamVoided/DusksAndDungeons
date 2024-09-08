package org.teamvoided.dusk_autumn.mixin;

import net.minecraft.client.render.entity.FoxEntityRenderer;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.dusk_autumn.DusksAndDungeons;

@Mixin(FoxEntityRenderer.class)
public class FoxEntityRendererMixin {
    @Unique
    private static final Identifier SILVER_TEXTURE = DusksAndDungeons.INSTANCE.id("textures/entity/fox/silver_fox.png");
    @Unique
    private static final Identifier SLEEPING_SILVER_TEXTURE = DusksAndDungeons.INSTANCE.id("textures/entity/fox/silver_fox_sleep.png");

    @Inject(at=@At("HEAD"),method = "getTexture(Lnet/minecraft/entity/passive/FoxEntity;)Lnet/minecraft/util/Identifier;", cancellable = true)
    public void addDuskFox(FoxEntity foxEntity, CallbackInfoReturnable<Identifier> cir){
        if (foxEntity.getVariant().getId() == 2) {
            cir.setReturnValue(foxEntity.isSleeping() ? SLEEPING_SILVER_TEXTURE : SILVER_TEXTURE);
        }
    }
}