package org.teamvoided.dusks_and_dungeons.mixin.apis;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.teamvoided.dusks_and_dungeons.api.EntityCollisionContextExtension;

@Mixin(EntityCollisionContext.class)
public class EntityCollisionContextMixin implements EntityCollisionContextExtension {
    @Shadow
    @Final
    private ItemStack heldItem;

    @Unique
    private boolean dusks_and_dungeons$recursionPrevention = false;

    @Override
    public boolean isHoldingItem(@NotNull TagKey<@NotNull Item> tag) {
        return heldItem.is(tag);
    }

    @Override
    public void setRecursive(boolean state) {
        dusks_and_dungeons$recursionPrevention = state;
    }

    @Override
    public boolean isRecursive() {
        return dusks_and_dungeons$recursionPrevention;
    }

}
