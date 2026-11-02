package org.teamvoided.dusks_and_dungeons.client.entity

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.minecraft.client.model.geom.ModelLayerLocation
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.client.entity.scarecrow.model.ScarecrowArmorEntityModel
import org.teamvoided.dusks_and_dungeons.client.entity.scarecrow.model.ScarecrowEntityModel
import org.teamvoided.dusks_and_dungeons.client.entity.scarecrow.model.ScarecrowWoodModel

object DnDEntityModelLayers {

    val SCARECROW = registerMain("scarecrow")
    val SCARECROW_INNER_ARMOR = registerInnerArmor("scarecrow")
    val SCARECROW_OUTER_ARMOR = registerOuterArmor("scarecrow")
    val SCARECROW_WOOD = register("scarecrow", "wood")

    fun init() {
        register(SCARECROW, ScarecrowEntityModel::createBodyLayer)
        register(SCARECROW_WOOD, ScarecrowWoodModel::createBodyLayer)
        register(SCARECROW_INNER_ARMOR, ScarecrowArmorEntityModel::createScarecrowInnerArmor)
        register(SCARECROW_OUTER_ARMOR, ScarecrowArmorEntityModel::createScarecrowOuterArmor)
    }

    fun register(layer: ModelLayerLocation, provider: EntityModelLayerRegistry.TexturedModelDataProvider) {
        EntityModelLayerRegistry.registerModelLayer(layer, provider)
    }

    fun registerMain(id: String): ModelLayerLocation = register(id, "main")
    fun registerInnerArmor(id: String): ModelLayerLocation = register(id, "inner_armor")
    fun registerOuterArmor(id: String): ModelLayerLocation = register(id, "outer_armor")

    fun register(id: String, layer: String): ModelLayerLocation {
        return ModelLayerLocation(id(id), layer)
    }

}