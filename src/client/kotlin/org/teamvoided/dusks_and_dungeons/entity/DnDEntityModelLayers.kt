package org.teamvoided.dusks_and_dungeons.entity

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry.registerModelLayer
import net.minecraft.client.model.Dilation
import net.minecraft.client.model.TexturedModelData
import net.minecraft.client.render.entity.model.BipedArmorEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.entity.bird.render.BirdEntityModel
import org.teamvoided.dusks_and_dungeons.entity.block.CelestalBellBlockEntityRenderer
import org.teamvoided.dusks_and_dungeons.entity.chill_charge.render.ChillChargeEntityModel
import org.teamvoided.dusks_and_dungeons.entity.dice.render.DiceEntityModel
import org.teamvoided.dusks_and_dungeons.entity.dust_bunny.render.DustBunnyEntityModel
import org.teamvoided.dusks_and_dungeons.entity.pumpkin.piffling.model.PifflingPumpkinModel
import org.teamvoided.dusks_and_dungeons.entity.scarecrow.model.ScarecrowArmorEntityModel
import org.teamvoided.dusks_and_dungeons.entity.scarecrow.model.ScarecrowEntityModel
import org.teamvoided.dusks_and_dungeons.entity.scarecrow.model.ScarecrowWoodModel

@Suppress("SameParameterValue")
object DnDEntityModelLayers {
    val SCARECROW: EntityModelLayer = registerMain("scarecrow")
    val SCARECROW_INNER_ARMOR: EntityModelLayer = createInnerArmor("scarecrow")
    val SCARECROW_OUTER_ARMOR: EntityModelLayer = createOuterArmor("scarecrow")
    val SCARECROW_WOOD: EntityModelLayer = register("scarecrow", "wood")

    // experimental
    val CHILL_CHARGE: EntityModelLayer = registerMain("chill_charge")
    val BIRD: EntityModelLayer = registerMain("bird")
    val DICE: EntityModelLayer = registerMain("dice")
    val DUST_BUNNY: EntityModelLayer = registerMain("dust_bunny")
    val PIFFLING_PUMPKIN: EntityModelLayer = registerMain("piffling_pumpkin")

    val CELESTAL_BELL = registerMain("celestal_bell")
    fun init() {
        registerModelLayer(SCARECROW, ScarecrowEntityModel::texturedModelData)
        registerModelLayer(SCARECROW_WOOD, ScarecrowWoodModel::texturedModelData)
        registerModelLayer(SCARECROW_INNER_ARMOR, ::createScarecrowInnerArmor)
        registerModelLayer(SCARECROW_OUTER_ARMOR, ::createScarecrowOuterArmor)

        // Experimental
        registerModelLayer(BIRD, BirdEntityModel::texturedModelData)
        registerModelLayer(DICE, DiceEntityModel::texturedModelData)
        registerModelLayer(DUST_BUNNY, DustBunnyEntityModel::texturedModelData)
        registerModelLayer(PIFFLING_PUMPKIN, PifflingPumpkinModel::texturedModelData)
        registerModelLayer(CELESTAL_BELL, CelestalBellBlockEntityRenderer::getTexturedModelData)
        registerModelLayer(CHILL_CHARGE, ChillChargeEntityModel::texturedModelData)

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
