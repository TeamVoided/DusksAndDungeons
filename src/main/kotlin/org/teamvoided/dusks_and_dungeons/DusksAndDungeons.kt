package org.teamvoided.dusks_and_dungeons

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.loot.LootTable
import net.minecraft.loot.LootTables
import net.minecraft.nbt.NbtOps
import net.minecraft.registry.RegistryKeys
import net.minecraft.server.command.CommandManager.literal
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory
import org.teamvoided.dusks_and_dungeons.block.DnDFamilies
import org.teamvoided.dusks_and_dungeons.init.*
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.EVIL_BLOCKS
import org.teamvoided.dusks_and_dungeons.init.DnDItems.EVIL_ITEMS
import org.teamvoided.dusks_and_dungeons.init.worldgen.DnDBiomeModifications

@Suppress("unused")
object DusksAndDungeons {
    const val MODID = "dusks_and_dungeons"

    @JvmField
    val log = LoggerFactory.getLogger(DusksAndDungeons::class.java)

    private var initialised = false

    fun init() {
        if (initialised) return
        initialised = true
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
        InitializeFabricEvents()

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
    }

    fun id(path: String) = Identifier.of(MODID, path)
    fun mc(path: String) = Identifier.ofDefault(path)
    fun id(modId: String, path: String) = Identifier.of(modId, path)

    @JvmStatic
    fun isDev() = FabricLoader.getInstance().isDevelopmentEnvironment
    fun isModLoaded(modId: String) = FabricLoader.getInstance().isModLoaded(modId)
}
