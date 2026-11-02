/*
 * TODO(cleanup)
 */

package org.teamvoided.dusks_and_dungeons.client

import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import me.fzzyhmstrs.fzzy_config.api.RegisterType
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.MODID
import org.teamvoided.dusks_and_dungeons.client.config.DnDClientConfig
import org.teamvoided.dusks_and_dungeons.client.entity.DnDEntityModelLayers
import org.teamvoided.dusks_and_dungeons.client.init.*
import org.teamvoided.dusks_and_dungeons.client.item.CandelabraItemRenderer
import org.teamvoided.dusks_and_dungeons.client.util.BETTER_BRICK_NAMES
import org.teamvoided.voidlib.api.InvalidateResourcesCallback
import org.teamvoided.voidlib.helpers.registerBuiltInPack
import org.teamvoided.voidlib.impl.EventImpl

@Suppress("unused")
object DusksAndDungeonsClient {

    @JvmField
    val config = ConfigApi.registerAndLoadConfig(::DnDClientConfig, RegisterType.CLIENT)

    fun init() {
        DnDEntityModelLayers.init()
        DnDBlocksClient.init()
        DnDItemsClient.init()
        DnDParticlesClient.init()
        DnDEntitiesClient.init()
        DnDBlockEntitiesClient.init()
        DnDLevelEventHandlers.init()
        DnDClientNetworking.init()

        registerBuiltInPack(MODID, BETTER_BRICK_NAMES)

        EventImpl.init()
        InvalidateResourcesCallback.EVENT.register {
            CandelabraItemRenderer.CANDELABRA_ITEM_CACHE.clear()
        }

        DnDClientDebug.init()
    }

}