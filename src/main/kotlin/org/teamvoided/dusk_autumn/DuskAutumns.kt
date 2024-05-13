package org.teamvoided.dusk_autumn

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory
import org.teamvoided.dusk_autumn.client.entity.crab.CrabRenderer
import org.teamvoided.dusk_autumn.init.*
import org.teamvoided.dusk_autumn.modules.SnifferInjection

@Suppress("unused")
object DuskAutumns {
    const val MODID = "dusk_autumn"

    @JvmField
    val LOGGER = LoggerFactory.getLogger(DuskAutumns::class.java)

    fun commonInit() {
        LOGGER.info("Hello from Common")
        DuskBlocks.init()
        DuskItems.init()
        DuskEntities.init()
        DuskWorldgen.init()
        DuskParticles.init()
        DuskItemGroups.init()
        SnifferInjection.init()
    }

    fun clientInit() {
        LOGGER.info("Hello from Client")
        DuskBlocks.initClient()
        DuskItems.initClient()
        EntityRendererRegistry.register(DuskEntities.CRAB,::CrabRenderer)
        DuskParticles.initClient()
    }

    fun id(path: String) = Identifier(MODID, path)
}
