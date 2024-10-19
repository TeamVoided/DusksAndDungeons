package org.teamvoided.dusk_autumn

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.loot.LootTable
import net.minecraft.loot.LootTables
import net.minecraft.nbt.NbtOps
import net.minecraft.registry.RegistryKeys
import net.minecraft.server.command.CommandManager.literal
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory
import org.teamvoided.dusk_autumn.block.DnDFamilies
import org.teamvoided.dusk_autumn.event.AddWanderingTrades
import org.teamvoided.dusk_autumn.init.*
import org.teamvoided.dusk_autumn.init.DnDBlocks.EVIL_BLOCKS
import org.teamvoided.dusk_autumn.init.DnDItems.EVIL_ITEMS
import org.teamvoided.dusk_autumn.init.worldgen.DnDBiomeModifications
import org.teamvoided.dusk_autumn.modules.BarteringInjection
import org.teamvoided.dusk_autumn.modules.SnifferInjection
import org.teamvoided.dusk_autumn.util.mergeSecrets

@Suppress("unused")
object DusksAndDungeons {
    const val MODID = "dusk_autumn"

    @JvmField
    val log = LoggerFactory.getLogger(DusksAndDungeons::class.java)

    fun init() {
        log.info("Its DnD time!")
        DnDItems.init()
        DnDBlocks.init()
        EVIL_ITEMS.addAll(EVIL_BLOCKS.map { it.asItem() })
        DnDFamilies.init()

        DnDBlockEntities.init()
        DnDEffects.init()
        DnDEntities.init()
        DnDWorldgen.init()
        DnDBiomeModifications.init()
        DnDParticles.init()
        DnDSoundEvents.init()
        DnDTabs.init()
        SnifferInjection.init()
        BarteringInjection.init()
        AddWanderingTrades.init()
        if (isDev()) CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
            val test = literal("end").executes { scc ->
                val src = scc.source
//                val z = src.world.getLootTable(LootTables.PIGLIN_BARTERING_GAMEPLAY)
                val lookup = src.world.registryManager
                val tableLookup = lookup.getLookupOrThrow(RegistryKeys.LOOT_TABLE)
                val table = tableLookup.getHolderOrThrow(LootTables.PIGLIN_BARTERING_GAMEPLAY)

                val registryOps = lookup.createSerializationContext(NbtOps.INSTANCE)
                LootTable.field_50021.encodeStart(registryOps, table.value())
                    .ifError(::println)
                    .ifSuccess(::println)

                0
            }.build()
            dispatcher.root.addChild(test)
        }

        mergeSecrets()
//        if (isModLoaded("dramaticdoors") && isDev()) DramaticDoorsCompat.initCompat()
    }

    fun id(path: String) = Identifier.of(MODID, path)
    fun mc(path: String) = Identifier.ofDefault(path)
    fun id(modId: String, path: String) = Identifier.of(modId, path)

    fun isDev() = FabricLoader.getInstance().isDevelopmentEnvironment
    fun isModLoaded(modId: String) = FabricLoader.getInstance().isModLoaded(modId)
}
