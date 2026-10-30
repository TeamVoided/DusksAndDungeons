package org.teamvoided.dusks_and_dungeons.config

import me.fzzyhmstrs.fzzy_config.annotations.Action
import me.fzzyhmstrs.fzzy_config.annotations.RequiresAction
import me.fzzyhmstrs.fzzy_config.config.Config
import me.fzzyhmstrs.fzzy_config.config.ConfigSection
import me.fzzyhmstrs.fzzy_config.validation.minecraft.ValidatedIdentifier
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedDouble
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedNumber
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedNumber.Companion.withIncrement
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
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

    @RequiresAction(Action.RELOAD_DATA)
    var densityConfig = DensityConfig()

    class DensityConfig : ConfigSection() {
        var cascade = dfValidator()
        var sypia = dfValidator()
        var overgrownGrotto = dfValidator()

        operator fun get(id: ResourceLocation): Double? {
            return when (id) {
                CASCADE -> cascade.get()
                SYPIA -> sypia.get()
                OVERGROWN_GROTTO -> overgrownGrotto.get()
                else -> null
            }
        }

    }


    companion object {

        fun dfValidator(): ValidatedDouble {
            return ValidatedDouble(1.0, 256.0, 0.0, ValidatedNumber.WidgetType.TEXTBOX_WITH_BUTTONS).withIncrement(0.1)
        }

        val CASCADE = id("cascade")
        val SYPIA = id("sypia")
        val OVERGROWN_GROTTO = id("overgrown_grotto")

    }
}