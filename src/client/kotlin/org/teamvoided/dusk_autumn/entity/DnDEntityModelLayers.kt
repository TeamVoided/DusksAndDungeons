package org.teamvoided.dusk_autumn.entity

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.minecraft.client.model.Dilation
import net.minecraft.client.model.TexturedModelData
import net.minecraft.client.render.entity.model.BipedArmorEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import org.teamvoided.dusk_autumn.entity.chill_charge.render.ChillChargeEntityModel
import org.teamvoided.dusk_autumn.entity.bird.render.BirdEntityModel
import org.teamvoided.dusk_autumn.entity.scarecrow.model.ScarecrowArmorEntityModel
import org.teamvoided.dusk_autumn.entity.scarecrow.model.ScarecrowEntityModel

object DnDEntityModelLayers {
    val CHILL_CHARGE: EntityModelLayer = registerMain("chill_charge")
    val BIRD: EntityModelLayer = registerMain("bird")

    val SCARECROW: EntityModelLayer = registerMain("scarecrow")
    val SCARECROW_INNER_ARMOR: EntityModelLayer = createInnerArmor("scarecrow")
    val SCARECROW_OUTER_ARMOR: EntityModelLayer = createOuterArmor("scarecrow")

    fun init() {
        EntityModelLayerRegistry.registerModelLayer(CHILL_CHARGE, ChillChargeEntityModel::texturedModelData)
        EntityModelLayerRegistry.registerModelLayer(BIRD, BirdEntityModel::texturedModelData)

        EntityModelLayerRegistry.registerModelLayer(SCARECROW, ScarecrowEntityModel::texturedModelData)
        EntityModelLayerRegistry.registerModelLayer(SCARECROW_INNER_ARMOR, ::createScarecrowInnerArmor)
        EntityModelLayerRegistry.registerModelLayer(SCARECROW_OUTER_ARMOR, ::createScarecrowOuterArmor)
    }

    private fun createScarecrowInnerArmor(): TexturedModelData =
        TexturedModelData.of(ScarecrowArmorEntityModel.getModelData(Dilation(0.5F)), 64, 32)

    private fun createScarecrowOuterArmor(): TexturedModelData =
        TexturedModelData.of(ScarecrowArmorEntityModel.getModelData(Dilation(1.0F)), 64, 32)

    private fun createInnerArmor(): TexturedModelData =
        TexturedModelData.of(BipedArmorEntityModel.getModelData(Dilation(0.5F)), 64, 32)

    private fun createOuterArmor(): TexturedModelData =
        TexturedModelData.of(BipedArmorEntityModel.getModelData(Dilation(1.0F)), 64, 32)

    private fun registerMain(id: String): EntityModelLayer {
        return register(id, "main")
    }

    private fun createInnerArmor(id: String): EntityModelLayer {
        return register(id, "inner_armor")
    }

    private fun createOuterArmor(id: String): EntityModelLayer {
        return register(id, "outer_armor")
    }

    private fun register(id: String, layer: String): EntityModelLayer {
        val entityModelLayer = create(id, layer)
        return entityModelLayer
    }

    private fun create(id: String, layer: String): EntityModelLayer {
        return EntityModelLayer(id(id), layer)
    }
}