package org.teamvoided.dusks_and_dungeons

import net.fabricmc.loader.api.FabricLoader
import net.minecraft.resources.ResourceLocation
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
//        if (!FabricLoader.getInstance().isModLoaded("transition")) error("$MODID requires Transition to be loaded!")

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
        DnDRegistryKeys.init()
        DnDAttachmentTypes.init()
        DnDNetworking.init()
        InitializeFabricEvents()

        if (isDev()) {
            DnDDebug.init()
        }
    }

    fun id(modId: String, path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(modId, path)
    fun mc(path: String): ResourceLocation = ResourceLocation.withDefaultNamespace(path)
    fun id(path: String) = id(MODID, path)

    @JvmStatic
    fun isDev() = FabricLoader.getInstance().isDevelopmentEnvironment
    fun isModLoaded(modId: String) = FabricLoader.getInstance().isModLoaded(modId)
}
