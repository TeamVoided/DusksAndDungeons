package org.teamvoided.dusks_and_dungeons.mixin.accessors;

import net.minecraft.world.level.storage.loot.entries.CompositeEntryBase;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(CompositeEntryBase.class)
public interface CompositeEntryBaseAccessor {

    @Accessor("children")
    List<LootPoolEntryContainer> dnd_children();

}