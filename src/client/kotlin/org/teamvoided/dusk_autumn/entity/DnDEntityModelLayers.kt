package org.teamvoided.dusk_autumn.entity

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.minecraft.client.render.entity.model.EntityModelLayer
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.entity.chill_charge.render.ChillChargeEntityModel

object DnDEntityModelLayers {
    val CHILL_CHARGE: EntityModelLayer = registerMain("chill_charge")

    fun init() {
        EntityModelLayerRegistry.registerModelLayer(CHILL_CHARGE, ChillChargeEntityModel::texturedModelData)
    }

    private fun registerMain(id: String): EntityModelLayer {
        return register(id, "main")
    }

    private fun register(id: String, layer: String): EntityModelLayer {
        val entityModelLayer = create(id, layer)
        return entityModelLayer
    }

    private fun create(id: String, layer: String): EntityModelLayer {
        return EntityModelLayer(id(id), layer)
    }
}