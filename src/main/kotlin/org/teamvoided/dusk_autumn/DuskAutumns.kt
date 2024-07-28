package org.teamvoided.dusk_autumn

import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory
import org.teamvoided.dusk_autumn.event.TradingEventHandler
import org.teamvoided.dusk_autumn.init.*
import org.teamvoided.dusk_autumn.init.worldgen.DuskBiomeModifications
import org.teamvoided.dusk_autumn.modules.SnifferInjection

@Suppress("unused")
object DuskAutumns {
    const val MODID = "dusk_autumn"

    @JvmField
    val LOGGER = LoggerFactory.getLogger(DuskAutumns::class.java)

    fun init() {
        LOGGER.info("Hello from Common")
        DuskItems.init()
        DuskBlocks.init()
        DuskWorldgen.init()
        DuskBiomeModifications.init()
        DuskParticles.init()
        DuskItemGroups.init()
        SnifferInjection.init()
        TradingEventHandler.init()
       /* CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
            val test = literal("end").executes {
                0
            }.build()
            dispatcher.root.addChild(test)
        }*/
    }

    fun id(path: String) = Identifier.of(MODID, path)
    fun mc(path: String) = Identifier.ofDefault(path)
}
