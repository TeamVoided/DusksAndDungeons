package org.teamvoided.dusks_and_dungeons

import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.MODID
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.isDev
import org.teamvoided.dusks_and_dungeons.entity.DnDEntityModelLayers
import org.teamvoided.dusks_and_dungeons.init.*
import org.teamvoided.dusks_and_dungeons.util.BETTER_BRICK_NAMES
import org.teamvoided.dusks_and_dungeons.util.block.CUTOUT_BLOCKS
import org.teamvoided.dusks_and_dungeons.util.block.TRANSLUCENT_BLOCKS
import org.teamvoided.voidlib.helpers.registerBuiltInPack

@Suppress("unused")
object DusksAndDungeonsClient {

    fun init() {
        DnDEntityModelLayers.init()
        DnDBlocksClient.init()
        DnDItemsClient.init()
        DnDParticlesClient.init()
        DnDEntitiesClient.init()
        DnDBlockEntitiesClient.init()

        registerBuiltInPack(MODID, BETTER_BRICK_NAMES)
        if (!isDev()) {
            CUTOUT_BLOCKS.clear()
            TRANSLUCENT_BLOCKS.clear()
//            GRASS_TINT_BLOCKS.clear()
        }
    }
}
