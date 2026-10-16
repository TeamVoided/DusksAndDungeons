package org.teamvoided.dusks_and_dungeons.mixin.accessors;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.storage.loot.LootPool.Builder;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Builder.class)
public interface LootPoolBuilderAccessor {

    @Accessor("entries")
    ImmutableList.Builder<LootPoolEntryContainer> dnd_entries();

}