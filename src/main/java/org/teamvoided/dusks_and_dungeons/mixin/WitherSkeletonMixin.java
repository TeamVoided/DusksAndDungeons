package org.teamvoided.dusks_and_dungeons.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teamvoided.dusks_and_dungeons.init.DnDItems;

@Mixin(WitherSkeleton.class)
public abstract class WitherSkeletonMixin extends AbstractSkeleton {

    protected WitherSkeletonMixin(EntityType<? extends AbstractSkeleton> entityType, Level world) {
        super(entityType, world);
    }

    @ModifyReturnValue(method = "getArrow", at = @At("RETURN"))
    private AbstractArrow applyWitherToArrow(AbstractArrow original) {
        if (original instanceof Arrow arrowEntity) {
            arrowEntity.setRemainingFireTicks(0);
            arrowEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 160));
        }
        return original;
    }

    @Inject(method = "populateDefaultEquipmentSlots", at = @At("TAIL"))
    protected void initEquipment(RandomSource random, DifficultyInstance difficulty, CallbackInfo ci) {
        setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.AIR));

        Item item;
        if (random.nextFloat() > 0.7f) {
            if (random.nextInt(3) < 2) item = DnDItems.BLACKSTONE_AXE;
            else item = Items.BOW;
        } else item = DnDItems.BLACKSTONE_SWORD;
        var hand = (random.nextFloat() < 0.1f) ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;

        setItemSlot(hand, new ItemStack(item));
    }
}