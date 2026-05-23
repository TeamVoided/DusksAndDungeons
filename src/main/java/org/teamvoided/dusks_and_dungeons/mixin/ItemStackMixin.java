package org.teamvoided.dusks_and_dungeons.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import com.llamalad7.mixinextras.sugar.ref.LocalDoubleRef;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

import static org.teamvoided.dusks_and_dungeons.init.DnDItems.CUSTOM_STATS;

// TODO move to voidlib
@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(method = "addModifierTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/attributes/AttributeModifier;is(Lnet/minecraft/resources/ResourceLocation;)Z", ordinal = 0))
    void modifyTooltipContent(Consumer<Component> textConsumer, @Nullable Player player, Holder<Attribute> attribute, AttributeModifier modifier, CallbackInfo ci,
                              @Local(ordinal = 0) LocalDoubleRef amount, @Local(ordinal = 0) LocalBooleanRef greenText) {
        if (CUSTOM_STATS.contains(modifier.id())) {
            assert player != null;
            amount.set(amount.get() + player.getAttributeBaseValue(attribute));
            greenText.set(true);
        }
    }
}
