package org.teamvoided.dusks_and_dungeons.config

import me.fzzyhmstrs.fzzy_config.annotations.Action
import me.fzzyhmstrs.fzzy_config.annotations.RequiresAction
import me.fzzyhmstrs.fzzy_config.config.Config
import me.fzzyhmstrs.fzzy_config.validation.minecraft.ValidatedIdentifier
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.storage.loot.BuiltInLootTables
import org.teamvoided.dusks_and_dungeons.DnDConst.MODID
import org.teamvoided.dusks_and_dungeons.DnDConst.TRUE
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

class DnDConfig : Config(id(MODID)) {

    // TODO(1.0) make it be not validated ResourceKey & add some modded / datapack support
    @RequiresAction(Action.RELOAD_DATA)
    var lootTablesThatHaveTintedGlassBottles =
        ValidatedIdentifier.ofDynamicKey(id("empty"), Registries.LOOT_TABLE, "tinted_loot_tables", TRUE)
            .toList(
                BuiltInLootTables.ANCIENT_CITY.location()
            )

}