package org.teamvoided.dusks_and_dungeons.recipe

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.item.crafting.SingleItemRecipe
import net.minecraft.world.item.crafting.SingleRecipeInput
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import org.teamvoided.dusks_and_dungeons.init.DnDRecipes

class UseOnBlockRecipe(
    group: String,
    input: Ingredient,
    val inputState: BlockState,
    val resultState: BlockState,
) : SingleItemRecipe(
    DnDRecipes.USE_ON_BLOCK, DnDRecipes.USE_ON_BLOCK_SERIALIZER, group, input, resultState.asItemOrEmpty()
) {

    override fun matches(input: SingleRecipeInput, level: Level): Boolean = ingredient.test(input.item())

    fun getIngredient(): Ingredient = ingredient

    override fun getToastSymbol(): ItemStack = ItemStack(inputState.block)

    class Serializer : RecipeSerializer<UseOnBlockRecipe> {

        override fun codec(): MapCodec<UseOnBlockRecipe> = CODEC

        override fun streamCodec(): StreamCodec<RegistryFriendlyByteBuf, UseOnBlockRecipe> = STREAM_CODEC

        companion object {

            val CODEC: MapCodec<UseOnBlockRecipe> = RecordCodecBuilder.mapCodec { inst ->
                inst
                    .group(
                        Codec.STRING.optionalFieldOf("group", "").forGetter(UseOnBlockRecipe::getGroup),
                        Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(UseOnBlockRecipe::getIngredient),
                        BlockState.CODEC.fieldOf("input_state").forGetter(UseOnBlockRecipe::inputState),
                        BlockState.CODEC.fieldOf("result_state").forGetter(UseOnBlockRecipe::resultState)
                    )
                    .apply(inst, ::UseOnBlockRecipe)
            }

            val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, UseOnBlockRecipe> = StreamCodec.composite(
                ByteBufCodecs.STRING_UTF8, UseOnBlockRecipe::getGroup,
                Ingredient.CONTENTS_STREAM_CODEC, UseOnBlockRecipe::getIngredient,
                ByteBufCodecs.fromCodec(BlockState.CODEC), UseOnBlockRecipe::inputState,
                ByteBufCodecs.fromCodec(BlockState.CODEC), UseOnBlockRecipe::resultState,
                ::UseOnBlockRecipe
            )

        }
    }

    companion object {

        fun BlockState.asItemOrEmpty(): ItemStack {
            val stack = block.asItem().defaultInstance
            return if (stack != null && !stack.`is`(Items.AIR)) stack else ItemStack.EMPTY
        }

    }
}
