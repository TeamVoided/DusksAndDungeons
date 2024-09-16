package org.teamvoided.dusk_autumn

import net.fabricmc.loader.api.FabricLoader
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory
import org.teamvoided.dusk_autumn.event.AddWanderingTrades
import org.teamvoided.dusk_autumn.init.*
import org.teamvoided.dusk_autumn.init.worldgen.DnDBiomeModifications
import org.teamvoided.dusk_autumn.modules.BarteringInjection
import org.teamvoided.dusk_autumn.modules.SnifferInjection

@Suppress("unused")
object DusksAndDungeons {
    const val MODID = "dusk_autumn"

    @JvmField
    val log = LoggerFactory.getLogger(DusksAndDungeons::class.java)

    fun init() {
        log.info("Its DnD time!")
        DnDItems.init()
        DnDBlocks.init()
        DnDBlockEntities.init()
        DnDEffects.init()
        DnDEntities.init()
        DnDWorldgen.init()
        DnDBiomeModifications.init()
        DnDParticles.init()
        DnDSoundEvents.init()
        DnDItemGroups.init()
        SnifferInjection.init()
        BarteringInjection.init()
        AddWanderingTrades.init()
       /* CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
            val test = literal("end").executes {
                0
            }.build()
            dispatcher.root.addChild(test)
        }*/

//        if (isModLoaded("dramaticdoors") && isDev()) DramaticDoorsCompat.initCompat()
    }

    fun id(path: String) = Identifier.of(MODID, path)
    fun mc(path: String) = Identifier.ofDefault(path)
    fun id(modId: String, path: String) = Identifier.of(modId, path)

    fun isDev() = FabricLoader.getInstance().isDevelopmentEnvironment
    fun isModLoaded(modId: String) = FabricLoader.getInstance().isModLoaded(modId)
}
