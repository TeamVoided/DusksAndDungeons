package org.teamvoided.dusks_and_dungeons.entity.scarecrow.render

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient
import net.minecraft.client.font.TextRenderer
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
import net.minecraft.item.trim.ArmorTrimPermutation
import net.minecraft.registry.Holder
import net.minecraft.registry.tag.ItemTags
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Identifier
import net.minecraft.util.math.Axis
import org.teamvoided.dusks_and_dungeons.entity.ScarecrowEntity
import org.teamvoided.dusks_and_dungeons.entity.scarecrow.model.ScarecrowArmorEntityModel
import org.teamvoided.dusks_and_dungeons.entity.scarecrow.model.ScarecrowEntityModel

@Environment(EnvType.CLIENT)
class ScarecrowArmorFeatureRenderer(
    context: FeatureRendererContext<ScarecrowEntity, ScarecrowEntityModel>,
    private val leggingsModel: ScarecrowArmorEntityModel,
    private val bodyModel: ScarecrowArmorEntityModel,
    modelManager: BakedModelManager,
) : FeatureRenderer<ScarecrowEntity, ScarecrowEntityModel>(context) {
    private val armorAtlas: SpriteAtlasTexture = modelManager.getAtlas(TexturedRenderLayers.ARMOR_TRIMS_ATLAS_TEXTURE)

    override fun render(
        matrices: MatrixStack, vertexConsumers: VertexConsumerProvider, light: Int, livingEntity: ScarecrowEntity,
        f: Float, g: Float, h: Float, j: Float, k: Float, l: Float,
    ) {
        renderArmor(matrices, vertexConsumers, livingEntity, EquipmentSlot.HEAD, light, bodyModel)
        renderArmor(matrices, vertexConsumers, livingEntity, EquipmentSlot.CHEST, light, bodyModel)
        renderArmor(matrices, vertexConsumers, livingEntity, EquipmentSlot.LEGS, light, leggingsModel)
        renderArmor(matrices, vertexConsumers, livingEntity, EquipmentSlot.FEET, light, bodyModel)
    }

    private fun renderArmor(
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        entity: ScarecrowEntity,
        armorSlot: EquipmentSlot,
        light: Int,
        model: ScarecrowArmorEntityModel,
    ) {
        val stack = entity.getEquippedStack(armorSlot)
        val item = stack.item
        if (item is ArmorItem && item.preferredSlot == armorSlot) {

            contextModel.setAttributes(model)

            setVisible(model, armorSlot)

            val textList = mapOf(
                "Slot: " to armorSlot.name,
                "Stack: " to stack,
                "Item: " to item,
                "Head_Shown:" to model.head.visible,
                "Hat_Shown:" to model.hat.visible,
            )
            val client = MinecraftClient.getInstance()
            matrices.push()
            matrices.rotateAround(Axis.Z_NEGATIVE.rotationDegrees(180f), 0f, 0f, 0f)
            matrices.translate(0f, 1.3f, 0f)
            matrices.scale(0.025f, -0.025f, 0.025f)
            matrices.rotateAround(Axis.Y_POSITIVE.rotationDegrees(180f), 0f, 0f, 0f)


            val color = 0xff_ff_ff_ff.toInt()
            val font = client.textRenderer
            for ((idx, rawText) in textList.toList().withIndex()) {
                val text = Text.literal(rawText.first)
                    .append(Text.literal("${rawText.second}").formatted(Formatting.GREEN))
                font.draw(
                    text, font.getWidth(text) / -2f, idx * -(1f + font.fontHeight), color,
                    true, matrices.peek().model, vertexConsumers,
                    TextRenderer.TextLayerType.NORMAL, 0, 15728880
                )
            }

            matrices.pop()

            val useSecondLayer = usesSecondLayer(armorSlot)

            val armorMaterial = item.material.value()
            val dyeTint = if (stack.isIn(ItemTags.DYEABLE)) Argb32.toOpaque(
                DyedColorComponent.getColorOrDefault(stack, DyedColorComponent.DEFAULT_COLOR)
            ) else -1
            armorMaterial.layers().forEach { layer ->
                val tint = if (layer.isDyeable) dyeTint else -1
                renderArmorParts(matrices, vertexConsumers, light, model, tint, layer.texture(useSecondLayer))
            }

            val armorTrimPermutation = stack.get(DataComponentTypes.TRIM)
            if (armorTrimPermutation != null) {
                renderArmor(
                    item.material,
                    matrices,
                    vertexConsumers,
                    light,
                    armorTrimPermutation,
                    model,
                    useSecondLayer
                )
            }

            if (stack.hasGlint())
                renderArmorGlint(matrices, vertexConsumers, light, model)
        }
    }

    fun setVisible(scarecrowArmor: ScarecrowArmorEntityModel, slot: EquipmentSlot) {
        scarecrowArmor.setArmorVisible(false)
        when (slot) {
            EquipmentSlot.HEAD -> {
                scarecrowArmor.head.visible = true
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

            else -> Unit
        }
    }

    private fun renderArmorParts(
        matrices: MatrixStack, vertexConsumers: VertexConsumerProvider,
        light: Int, model: ScarecrowArmorEntityModel,
        i: Int, texture: Identifier,
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
        hasGlint: Boolean,
    ) {
        val sprite = armorAtlas.getSprite(
            if (hasGlint) permutation.getLeggingsTexture(holder) else permutation.getBodyTexture(holder)
        )
        val vertexConsumer = sprite.getTextureSpecificVertexConsumer(
            vertexConsumers.getBuffer(TexturedRenderLayers.getArmorTrim(permutation.pattern.value().decal()))
        )
        model.method_60879(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV)
    }

    private fun renderArmorGlint(
        matrices: MatrixStack, vertexConsumers: VertexConsumerProvider, light: Int, model: ScarecrowArmorEntityModel,
    ) {
        model.method_60879(
            matrices, vertexConsumers.getBuffer(RenderLayer.getArmorEntityGlint()), light, OverlayTexture.DEFAULT_UV
        )
    }

    fun usesSecondLayer(slot: EquipmentSlot): Boolean = slot == EquipmentSlot.LEGS
}