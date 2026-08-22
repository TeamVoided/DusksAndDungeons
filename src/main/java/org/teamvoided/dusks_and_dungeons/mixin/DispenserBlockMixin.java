package org.teamvoided.dusks_and_dungeons.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import static org.teamvoided.dusks_and_dungeons.item.DnDDispenserBehaviour.getCustomDispenseMethod;

@Mixin(DispenserBlock.class)
public class DispenserBlockMixin {

    @Shadow
    @Final
    private static DefaultDispenseItemBehavior DEFAULT_BEHAVIOR;

    @ModifyReturnValue(method = "getDispenseMethod", at = @At("RETURN"))
    DispenseItemBehavior crateCustomDispenseMethod(DispenseItemBehavior original, Level level, ItemStack itemStack) {
        if (original == DEFAULT_BEHAVIOR) {
            var dispenseMethod = getCustomDispenseMethod(level, itemStack);
            if (dispenseMethod != null) {
                return dispenseMethod;
            }
        }
        return original;
    }

}
