package org.teamvoided.dusks_and_dungeons.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import com.llamalad7.mixinextras.sugar.ref.LocalDoubleRef;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Holder;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

import static org.teamvoided.dusks_and_dungeons.init.DnDItems.CUSTOM_STATS;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(method = "appendModifierTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/EntityAttributeModifier;matches(Lnet/minecraft/util/Identifier;)Z", ordinal = 0))
    void modifyTooltipContent(Consumer<Text> textConsumer, @Nullable PlayerEntity player, Holder<EntityAttribute> attribute, EntityAttributeModifier modifier, CallbackInfo ci,
                              @Local(ordinal = 0) LocalDoubleRef amount, @Local(ordinal = 0) LocalBooleanRef greenText) {
        if (CUSTOM_STATS.contains(modifier.id())) {
            assert player != null;
            amount.set(amount.get() + player.getAttributeBaseValue(attribute));
            greenText.set(true);
        }
    }
}
