package org.teamvoided.dusks_and_dungeons.mixin.bugfix.furnace;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin  {

    @Shadow
    protected NonNullList<ItemStack> items;

    @Shadow
    public static boolean isFuel(ItemStack itemStack) {
        throw new UnsupportedOperationException("Implemented via mixin");
    }

    @ModifyExpressionValue(method = "canTakeItemThroughFace", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 0))
    boolean extractNonFuel(boolean original, int i, ItemStack itemStack) {
        //noinspection ConstantValue
        return original || !isFuel(itemStack);
    }

    @ModifyExpressionValue(method = "canPlaceItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/AbstractFurnaceBlockEntity;isFuel(Lnet/minecraft/world/item/ItemStack;)Z", ordinal = 0))
    boolean insertMaxStackFuel(boolean original, int i, ItemStack itemStack) {
        ItemStack currentFuel = items.get(1);
        return (currentFuel.isEmpty()) ? original : original && currentFuel.getItem().getRecipeRemainder(currentFuel).isEmpty();
    }

}