package org.teamvoided.dusk_autumn

import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory
import org.teamvoided.dusk_autumn.init.*

@Suppress("unused")
object DuskAutumns {
    const val MODID = "dusk_autumn"

    @JvmField
    val LOGGER = LoggerFactory.getLogger(DuskAutumns::class.java)

    fun commonInit() {
        LOGGER.info("Hello from Common")
        DuskBlocks.init()
        DuskItems.init()
        DuskWorldgen.init()
        DuskParticles.init()
        DuskItemGroups.init()
    }

    fun clientInit() {
        LOGGER.info("Hello from Client")
        DuskBlocks.initClient()
        DuskItems.initClient()
        DuskParticles.initClient()
    }

    fun id(path: String) = Identifier(MODID, path)
}
