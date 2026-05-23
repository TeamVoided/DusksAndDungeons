package org.teamvoided.dusks_and_dungeons.entity.scarecrow.render

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Font
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.Sheets
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.layers.RenderLayer
import net.minecraft.client.renderer.entity.RenderLayerParent
import net.minecraft.client.resources.model.ModelManager
import net.minecraft.client.renderer.texture.TextureAtlas
import net.minecraft.util.FastColor.ARGB32
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.core.component.DataComponents
import net.minecraft.world.item.component.DyedItemColor
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.armortrim.ArmorTrim
import net.minecraft.core.Holder
import net.minecraft.tags.ItemTags
import net.minecraft.network.chat.Component
import net.minecraft.ChatFormatting
import net.minecraft.resources.ResourceLocation
import com.mojang.math.Axis
import org.teamvoided.dusks_and_dungeons.entity.ScarecrowEntity
import org.teamvoided.dusks_and_dungeons.entity.scarecrow.model.ScarecrowArmorEntityModel
import org.teamvoided.dusks_and_dungeons.entity.scarecrow.model.ScarecrowEntityModel

@Environment(EnvType.CLIENT)
class ScarecrowArmorFeatureRenderer(
    context: RenderLayerParent<ScarecrowEntity, ScarecrowEntityModel>,
    private val leggingsModel: ScarecrowArmorEntityModel,
    private val bodyModel: ScarecrowArmorEntityModel,
    modelManager: ModelManager,
) : RenderLayer<ScarecrowEntity, ScarecrowEntityModel>(context) {
    private val armorAtlas: TextureAtlas = modelManager.getAtlas(Sheets.ARMOR_TRIMS_SHEET)

    override fun render(
        matrices: PoseStack, vertexConsumers: MultiBufferSource, light: Int, livingEntity: ScarecrowEntity,
        f: Float, g: Float, h: Float, j: Float, k: Float, l: Float,
    ) {
        renderArmor(matrices, vertexConsumers, livingEntity, EquipmentSlot.HEAD, light, bodyModel)
        renderArmor(matrices, vertexConsumers, livingEntity, EquipmentSlot.CHEST, light, bodyModel)
        renderArmor(matrices, vertexConsumers, livingEntity, EquipmentSlot.LEGS, light, leggingsModel)
        renderArmor(matrices, vertexConsumers, livingEntity, EquipmentSlot.FEET, light, bodyModel)
    }

    private fun renderArmor(
        matrices: PoseStack,
        vertexConsumers: MultiBufferSource,
        entity: ScarecrowEntity,
        armorSlot: EquipmentSlot,
        light: Int,
        model: ScarecrowArmorEntityModel,
    ) {
        val stack = entity.getItemBySlot(armorSlot)
        val item = stack.item
        if (item is ArmorItem && item.equipmentSlot == armorSlot) {

            parentModel.setAttributes(model)

            setVisible(model, armorSlot)

            val textList = mapOf(
                "Slot: " to armorSlot.name,
                "Stack: " to stack,
                "Item: " to item,
                "Head_Shown:" to model.head.visible,
                "Hat_Shown:" to model.hat.visible,
            )
            val client = Minecraft.getInstance()
            matrices.pushPose()
            matrices.rotateAround(Axis.ZN.rotationDegrees(180f), 0f, 0f, 0f)
            matrices.translate(0f, 1.3f, 0f)
            matrices.scale(0.025f, -0.025f, 0.025f)
            matrices.rotateAround(Axis.YP.rotationDegrees(180f), 0f, 0f, 0f)


            val color = 0xff_ff_ff_ff.toInt()
            val font = client.font
            for ((idx, rawText) in textList.toList().withIndex()) {
                val text = Component.literal(rawText.first)
                    .append(Component.literal("${rawText.second}").withStyle(ChatFormatting.GREEN))
                font.drawInBatch(
                    text, font.width(text) / -2f, idx * -(1f + font.lineHeight), color,
                    true, matrices.last().pose(), vertexConsumers,
                    Font.DisplayMode.NORMAL, 0, 15728880
                )
            }

            matrices.popPose()

            val useSecondLayer = usesSecondLayer(armorSlot)

            val armorMaterial = item.material.value()
            val dyeTint = if (stack.`is`(ItemTags.DYEABLE)) ARGB32.opaque(
                DyedItemColor.getOrDefault(stack, DyedItemColor.LEATHER_COLOR)
            ) else -1
            armorMaterial.layers().forEach { layer ->
                val tint = if (layer.dyeable()) dyeTint else -1
                renderArmorParts(matrices, vertexConsumers, light, model, tint, layer.texture(useSecondLayer))
            }

            val armorTrimPermutation = stack.get(DataComponents.TRIM)
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

            if (stack.hasFoil())
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
        matrices: PoseStack, vertexConsumers: MultiBufferSource,
        light: Int, model: ScarecrowArmorEntityModel,
        i: Int, texture: ResourceLocation,
    ) {
        val vertexConsumer = vertexConsumers.getBuffer(RenderType.armorCutoutNoCull(texture))
        model.renderToBuffer(matrices, vertexConsumer, light, OverlayTexture.NO_OVERLAY, i)
    }

    private fun renderArmor(
        holder: Holder<ArmorMaterial>,
        matrices: PoseStack,
        vertexConsumers: MultiBufferSource,
        light: Int,
        permutation: ArmorTrim,
        model: ScarecrowArmorEntityModel,
        hasGlint: Boolean,
    ) {
        val sprite = armorAtlas.getSprite(
            if (hasGlint) permutation.innerTexture(holder) else permutation.outerTexture(holder)
        )
        val vertexConsumer = sprite.wrap(
            vertexConsumers.getBuffer(Sheets.armorTrimsSheet(permutation.pattern().value().decal()))
        )
        model.renderToBuffer(matrices, vertexConsumer, light, OverlayTexture.NO_OVERLAY)
    }

    private fun renderArmorGlint(
        matrices: PoseStack, vertexConsumers: MultiBufferSource, light: Int, model: ScarecrowArmorEntityModel,
    ) {
        model.renderToBuffer(
            matrices, vertexConsumers.getBuffer(RenderType.armorEntityGlint()), light, OverlayTexture.NO_OVERLAY
        )
    }

    fun usesSecondLayer(slot: EquipmentSlot): Boolean = slot == EquipmentSlot.LEGS
}