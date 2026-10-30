/*
 * TODO(cleanup)
 */

package org.teamvoided.dusks_and_dungeons

import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.resources.ResourceLocation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.dusks_and_dungeons.config.DnDConfig
import org.teamvoided.dusks_and_dungeons.init.*
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.EVIL_BLOCKS
import org.teamvoided.dusks_and_dungeons.init.DnDItems.EVIL_ITEMS
import org.teamvoided.dusks_and_dungeons.init.events.DnDEvents
import org.teamvoided.dusks_and_dungeons.init.misc.DnDFamilies
import org.teamvoided.voidlib.init.VEventInit

@Suppress("unused")
object DusksAndDungeons {

    val MODID get() = DnDConst.MODID

    val config = ConfigApi.registerAndLoadConfig(::DnDConfig)

    @JvmField
    val log: Logger = LoggerFactory.getLogger(MODID)

    fun init() {
        log.info("Its DnD time!")
        DnDItems.init()
        DnDBlocks.init()
        EVIL_ITEMS.addAll(EVIL_BLOCKS.map { it.asItem() })
        DnDFamilies.init()

        DnDLootContext.init()
        DnDRecipes.init()

        DnDDataComponents.init()
        DnDBlockEntities.init()
        DnDEffects.init()
        DnDEntityTypes.init()
        DnDWorldgen.init()
        DnDParticles.init()
        DnDSoundEvents.init()
        DnDTabs.init()
        DnDRegistries.init()
        DnDAttachmentTypes.init()
        DnDNetworking.init()
        DnDEvents.init()

        // TODO(lib) move to voidlib
        VEventInit.init()

        // Should always be initialized Last
        DnDDebug.init()
    }

    fun id(modId: String, path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(modId, path)
    fun mc(path: String): ResourceLocation = ResourceLocation.withDefaultNamespace(path)
    fun id(path: String) = id(MODID, path)

    @JvmStatic
    fun isDev() = FabricLoader.getInstance().isDevelopmentEnvironment
    fun isModLoaded(modId: String) = FabricLoader.getInstance().isModLoaded(modId)

}
