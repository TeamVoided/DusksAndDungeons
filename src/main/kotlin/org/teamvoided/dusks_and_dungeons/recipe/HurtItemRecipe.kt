package org.teamvoided.dusks_and_dungeons.recipe

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.tags.TagKey
import net.minecraft.util.ExtraCodecs
import net.minecraft.world.damagesource.DamageType
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.item.crafting.SingleItemRecipe
import net.minecraft.world.item.crafting.SingleRecipeInput
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.init.DnDRecipes
import org.teamvoided.dusks_and_dungeons.util.DnDCodecs

class HurtItemRecipe(
    group: String,
    input: Ingredient,
    val damageTypeTag: TagKey<DamageType>,
    val invulnerableTime: Int,
    output: ItemStack,
) : SingleItemRecipe(DnDRecipes.HURT_ITEM, DnDRecipes.HURT_ITEM_SERIALIZER, group, input, output) {

    override fun matches(singleRecipeInput: SingleRecipeInput, level: Level): Boolean {
        return this.ingredient.test(singleRecipeInput.item())
    }

    fun getIngredient(): Ingredient = ingredient

    fun getResult(): ItemStack = result

    override fun getToastSymbol(): ItemStack = ItemStack(Blocks.MAGMA_BLOCK)

    class Serializer : RecipeSerializer<HurtItemRecipe> {

        override fun codec(): MapCodec<HurtItemRecipe> = CODEC

        override fun streamCodec(): StreamCodec<RegistryFriendlyByteBuf, HurtItemRecipe> = STREAM_CODEC

        companion object {

            val CODEC: MapCodec<HurtItemRecipe> = RecordCodecBuilder.mapCodec { inst ->
                inst
                    .group(
                        Codec.STRING.optionalFieldOf("group", "").forGetter(HurtItemRecipe::getGroup),
                        Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(HurtItemRecipe::getIngredient),
                        DnDCodecs.DAMAGE_TYPE_TAG_CODEC.fieldOf("damage_type_tag")
                            .forGetter(HurtItemRecipe::damageTypeTag),
                        ExtraCodecs.NON_NEGATIVE_INT.optionalFieldOf("invulnerable_time", 20)
                            .forGetter(HurtItemRecipe::invulnerableTime),
                        ItemStack.STRICT_CODEC.fieldOf("result").forGetter(HurtItemRecipe::getResult),
                    )
                    .apply(inst, ::HurtItemRecipe)
            }

            val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, HurtItemRecipe> = StreamCodec.composite(
                ByteBufCodecs.STRING_UTF8, HurtItemRecipe::getGroup,
                Ingredient.CONTENTS_STREAM_CODEC, HurtItemRecipe::getIngredient,
                DnDCodecs.DAMAGE_TYPE_TAG_STREAM_CODEC, HurtItemRecipe::damageTypeTag,
                ByteBufCodecs.INT, HurtItemRecipe::invulnerableTime,
                ItemStack.STREAM_CODEC, HurtItemRecipe::getResult,
                ::HurtItemRecipe
            )

        }
    }

}