package org.teamvoided.dusk_autumn.entity.scarecrow.render

import com.google.common.collect.Maps
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.TexturedRenderLayers
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.feature.FeatureRenderer
import net.minecraft.client.render.entity.feature.FeatureRendererContext
import net.minecraft.client.render.model.BakedModelManager
import net.minecraft.client.texture.SpriteAtlasTexture
import net.minecraft.client.util.ColorUtil.Argb32
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.DyedColorComponent
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.trim.ArmorTrimPattern
import net.minecraft.item.trim.ArmorTrimPermutation
import net.minecraft.registry.Holder
import net.minecraft.registry.tag.ItemTags
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.entity.ScarecrowEntity
import org.teamvoided.dusk_autumn.entity.scarecrow.model.ScarecrowArmorEntityModel
import org.teamvoided.dusk_autumn.entity.scarecrow.model.ScarecrowEntityModel

@Environment(EnvType.CLIENT)
class ScarecrowArmorFeatureRenderer(
    context: FeatureRendererContext<ScarecrowEntity, ScarecrowEntityModel>,
    private val leggingsModel: ScarecrowArmorEntityModel,
    private val bodyModel: ScarecrowArmorEntityModel,
    modelManager: BakedModelManager
) : FeatureRenderer<ScarecrowEntity, ScarecrowEntityModel>(context) {
    private val armorAtlas: SpriteAtlasTexture = modelManager.getAtlas(TexturedRenderLayers.ARMOR_TRIMS_ATLAS_TEXTURE)

    override fun render(
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        livingEntity: ScarecrowEntity,
        f: Float,
        g: Float,
        h: Float,
        j: Float,
        k: Float,
        l: Float
    ) {
        this.renderArmor(
            matrices,
            vertexConsumers,
            livingEntity,
            EquipmentSlot.HEAD,
            light,
            this.getArmor(EquipmentSlot.HEAD)
        )
        this.renderArmor(
            matrices,
            vertexConsumers,
            livingEntity,
            EquipmentSlot.CHEST,
            light,
            this.getArmor(EquipmentSlot.CHEST)
        )
        this.renderArmor(
            matrices,
            vertexConsumers,
            livingEntity,
            EquipmentSlot.LEGS,
            light,
            this.getArmor(EquipmentSlot.LEGS)
        )
        this.renderArmor(
            matrices,
            vertexConsumers,
            livingEntity,
            EquipmentSlot.FEET,
            light,
            this.getArmor(EquipmentSlot.FEET)
        )
    }

    private fun renderArmor(
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        entity: ScarecrowEntity,
        armorSlot: EquipmentSlot,
        light: Int,
        model: ScarecrowArmorEntityModel
    ) {
        val itemStack = entity.getEquippedStack(armorSlot)
        val item = itemStack.item
        if (item is ArmorItem) {
            if (item.preferredSlot == armorSlot) {
                this.contextModel.setAttributes(model)
                this.setVisible(model, armorSlot)
                val useSecondLayer = this.usesSecondLayer(armorSlot)
                val armorMaterial = item.material.value() as ArmorMaterial
                val dyeTint = if (itemStack.isIn(ItemTags.DYEABLE)) Argb32.toOpaque(
                    DyedColorComponent.getColorOrDefault(
                        itemStack,
                        -6265536
                    )
                ) else -1
                val armorLayers: Iterator<*> = armorMaterial.layers().iterator()

                while (armorLayers.hasNext()) {
                    val layer = armorLayers.next() as ArmorMaterial.Layer
                    val tint = if (layer.isDyeable) dyeTint else -1
                    this.renderArmorParts(matrices, vertexConsumers, light, model, tint, layer.texture(useSecondLayer))
                }

                val armorTrimPermutation = itemStack.get(DataComponentTypes.TRIM)
                if (armorTrimPermutation != null) {
                    this.renderArmor(
                        item.material,
                        matrices,
                        vertexConsumers,
                        light,
                        armorTrimPermutation,
                        model,
                        useSecondLayer
                    )
                }

                if (itemStack.hasGlint()) {
                    this.renderArmorGlint(matrices, vertexConsumers, light, model)
                }
            }
        }
    }

    protected fun setVisible(scarecrowArmor: ScarecrowArmorEntityModel, slot: EquipmentSlot?) {
        scarecrowArmor.setArmorVisible(false)
        when (slot) {
            EquipmentSlot.HEAD -> {
                scarecrowArmor.headNotFun.visible = true
                scarecrowArmor.hat.visible = true
            }

            EquipmentSlot.CHEST -> {
                scarecrowArmor.body.visible = true
                scarecrowArmor.rightArm.visible = true
                scarecrowArmor.leftArm.visible = true
            }

            EquipmentSlot.LEGS -> {
                scarecrowArmor.body.visible = true
                scarecrowArmor.rightLeg.visible = true
                scarecrowArmor.leftLeg.visible = true
            }

            EquipmentSlot.FEET -> {
                scarecrowArmor.rightLeg.visible = true
                scarecrowArmor.leftLeg.visible = true
            }

            else -> println("hiya-------------------------------------------------------------------------------------------------")
        }
    }

    private fun renderArmorParts(
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        model: ScarecrowArmorEntityModel,
        i: Int,
        texture: Identifier
    ) {
        val vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(texture))
        model.method_2828(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, i)
    }

    private fun renderArmor(
        holder: Holder<ArmorMaterial>,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        permutation: ArmorTrimPermutation,
        model: ScarecrowArmorEntityModel,
        hasGlint: Boolean
    ) {
        val sprite = armorAtlas.getSprite(
            if (hasGlint) permutation.getLeggingsTexture(holder) else permutation.getBodyTexture(holder)
        )
        val vertexConsumer = sprite.getTextureSpecificVertexConsumer(
            vertexConsumers.getBuffer(
                TexturedRenderLayers.getArmorTrim(
                    (permutation.pattern.value() as ArmorTrimPattern).decal()
                )
            )
        )
        model.method_60879(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV)
    }

    private fun renderArmorGlint(
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        model: ScarecrowArmorEntityModel
    ) {
        model.method_60879(
            matrices,
            vertexConsumers.getBuffer(RenderLayer.getArmorEntityGlint()),
            light,
            OverlayTexture.DEFAULT_UV
        )
    }

    private fun getArmor(slot: EquipmentSlot): ScarecrowArmorEntityModel {
        return if (this.usesSecondLayer(slot)) this.leggingsModel else this.bodyModel
    }

    private fun usesSecondLayer(slot: EquipmentSlot): Boolean {
        return slot == EquipmentSlot.LEGS
    }

    companion object {
        private val ARMOR_TEXTURE_CACHE: Map<String, Identifier> = Maps.newHashMap()
    }
}