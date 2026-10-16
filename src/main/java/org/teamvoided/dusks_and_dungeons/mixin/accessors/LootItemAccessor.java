package org.teamvoided.dusks_and_dungeons.mixin.accessors;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LootItem.class)
public interface LootItemAccessor {

    @Accessor("item")
    Holder<Item> dnd_getItem();

    @Mutable
    @Accessor("item")
    void dnd_setItem(Holder<Item> item);

}
