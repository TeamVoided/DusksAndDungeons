package org.teamvoided.dusks_and_dungeons.entity

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry.registerModelLayer
import net.minecraft.client.model.geom.builders.CubeDeformation
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.ModelLayerLocation
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.entity.raccoon.RaccoonEntityModel
import org.teamvoided.dusks_and_dungeons.entity.scarecrow.model.ScarecrowArmorEntityModel
import org.teamvoided.dusks_and_dungeons.entity.scarecrow.model.ScarecrowEntityModel
import org.teamvoided.dusks_and_dungeons.entity.scarecrow.model.ScarecrowWoodModel

@Suppress("SameParameterValue")
object DnDEntityModelLayers {
    val SCARECROW: ModelLayerLocation = registerMain("scarecrow")
    val SCARECROW_INNER_ARMOR: ModelLayerLocation = createInnerArmor("scarecrow")
    val SCARECROW_OUTER_ARMOR: ModelLayerLocation = createOuterArmor("scarecrow")
    val SCARECROW_WOOD: ModelLayerLocation = register("scarecrow", "wood")
    val RACCOON: ModelLayerLocation = registerMain("raccoon")

    // experimental

    fun init() {
        registerModelLayer(SCARECROW, ScarecrowEntityModel::texturedModelData)
        registerModelLayer(SCARECROW_WOOD, ScarecrowWoodModel::texturedModelData)
        registerModelLayer(SCARECROW_INNER_ARMOR, ::createScarecrowInnerArmor)
        registerModelLayer(SCARECROW_OUTER_ARMOR, ::createScarecrowOuterArmor)
        registerModelLayer(RACCOON, RaccoonEntityModel::texturedModelData)
    }

    private fun createScarecrowInnerArmor(): LayerDefinition =
        LayerDefinition.create(ScarecrowArmorEntityModel.getModelData(CubeDeformation(0.5F)), 64, 32)

    private fun createScarecrowOuterArmor(): LayerDefinition =
        LayerDefinition.create(ScarecrowArmorEntityModel.getModelData(CubeDeformation(1.0F)), 64, 32)

    private fun registerMain(id: String): ModelLayerLocation {
        return register(id, "main")
    }

    private fun createInnerArmor(id: String): ModelLayerLocation {
        return register(id, "inner_armor")
    }

    private fun createOuterArmor(id: String): ModelLayerLocation {
        return register(id, "outer_armor")
    }

    private fun register(id: String, layer: String): ModelLayerLocation {
        val entityModelLayer = create(id, layer)
        return entityModelLayer
    }

    private fun create(id: String, layer: String): ModelLayerLocation {
        return ModelLayerLocation(id(id), layer)
    }
}
