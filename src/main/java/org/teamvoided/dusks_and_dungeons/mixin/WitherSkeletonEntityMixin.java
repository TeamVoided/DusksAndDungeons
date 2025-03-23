package org.teamvoided.dusks_and_dungeons.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teamvoided.dusks_and_dungeons.init.DnDItems;

@Mixin(WitherSkeletonEntity.class)
public abstract class WitherSkeletonEntityMixin extends AbstractSkeletonEntity {

    protected WitherSkeletonEntityMixin(EntityType<? extends AbstractSkeletonEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyReturnValue(method = "createArrowProjectile", at = @At("RETURN"))
    private PersistentProjectileEntity applyWitherToArrow(PersistentProjectileEntity original) {
        if (original instanceof ArrowEntity arrowEntity) {
            arrowEntity.setFireTicks(0);
            arrowEntity.addEffect(new StatusEffectInstance(StatusEffects.WITHER, 160));
        }
        return original;
    }

    @Inject(method = "initEquipment", at = @At("TAIL"))
    protected void initEquipment(RandomGenerator random, LocalDifficulty difficulty, CallbackInfo ci) {
        equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.AIR));

        Item item;
        if (random.nextFloat() > 0.7f) {
            if (random.nextInt(3) < 2) item = DnDItems.BLACKSTONE_AXE;
            else item = Items.BOW;
        } else item = DnDItems.BLACKSTONE_SWORD;
        var hand = (random.nextFloat() < 0.1f) ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;

        equipStack(hand, new ItemStack(item));
    }
}