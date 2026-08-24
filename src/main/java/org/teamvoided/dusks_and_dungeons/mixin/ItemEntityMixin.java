package org.teamvoided.dusks_and_dungeons.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static org.teamvoided.dusks_and_dungeons.DusksAndDungeons.log;
import static org.teamvoided.dusks_and_dungeons.util.MixinHelperKt.processRecipe;


@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {

    public ItemEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow
    public abstract ItemStack getItem();

    @Shadow
    public abstract void setItem(ItemStack itemStack);

    @Shadow
    private int pickupDelay;

    @Inject(method = "tick", at = @At("HEAD"))
    void tickIFrames(CallbackInfo ci) {
        if (invulnerableTime > 0) {
            invulnerableTime--;
        }
    }

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    void doHurtCrafting(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (invulnerableTime > 0) {
            cir.setReturnValue(false);
        }

        ItemStack stack = getItem();
        var input = new SingleRecipeInput(stack);
        Level level = level();
        processRecipe(source, level, input, (outputStack, iFrames) -> {
            setItem(outputStack.copyWithCount(stack.getCount()));
            invulnerableTime += iFrames;
            pickupDelay /= 2;
            cir.setReturnValue(false);
        });
    }

}