/*
 * TODO(cleanup)
 */

package org.teamvoided.dusks_and_dungeons.recipe

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.HolderLookup
import net.minecraft.core.NonNullList
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.*
import net.minecraft.world.level.Level
import org.teamvoided.dusks_and_dungeons.init.DnDRecipes
import java.util.*


// TODO(1.0) clean up and optimize
class MixedShapedRecipe(
    group: String,
    category: CraftingBookCategory,
    val mixedPattern: MixedRecipePattern,
    val mixedResult: ItemStack,
    showNotification: Boolean = true,
) : ShapedRecipe(
    group, category,
    ShapedRecipePattern(1, 1, NonNullList.createWithCapacity(1), Optional.empty()),
    mixedResult, showNotification
) {

    override fun getSerializer() = DnDRecipes.MIXED_CRAFTING_SHAPED_SERIALIZER

    override fun getResultItem(lookup: HolderLookup.Provider): ItemStack = mixedResult

    override fun getIngredients(): NonNullList<Ingredient> = mixedPattern.ingredients

    override fun canCraftInDimensions(width: Int, height: Int): Boolean {
        return width >= getWidth() && height >= getHeight()
    }

    override fun matches(input: CraftingInput, world: Level): Boolean {
        return mixedPattern.matches(input)
    }

    override fun assemble(input: CraftingInput, provider: HolderLookup.Provider): ItemStack {
        return getResultItem(provider).copy()
    }

    override fun getWidth(): Int = mixedPattern.width

    override fun getHeight(): Int = mixedPattern.height

    override fun isIncomplete(): Boolean {
        return ingredients.isEmpty() || ingredients.stream().filter { !it.isEmpty }.anyMatch { it.getItems().size == 0 }
    }

    class Serializer : RecipeSerializer<MixedShapedRecipe> {

        override fun codec(): MapCodec<MixedShapedRecipe> = CODEC

        override fun streamCodec(): StreamCodec<RegistryFriendlyByteBuf, MixedShapedRecipe> = PACKET_CODEC

        companion object {

            val CODEC: MapCodec<MixedShapedRecipe> = RecordCodecBuilder.mapCodec { inst ->
                inst
                    .group(
                        Codec.STRING.optionalFieldOf("group", "").forGetter { it.group },
                        CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC)
                            .forGetter { it.category() },
                        MixedRecipePattern.CODEC.forGetter { it.mixedPattern },
                        ItemStack.STRICT_CODEC.fieldOf("result").forGetter { it.mixedResult },
                        Codec.BOOL.optionalFieldOf("show_notification", true).forGetter { it.showNotification() }
                    )
                    .apply(inst, ::MixedShapedRecipe)
            }
            val PACKET_CODEC: StreamCodec<RegistryFriendlyByteBuf, MixedShapedRecipe> = StreamCodec.of(::write, ::read)

            private fun read(buf: RegistryFriendlyByteBuf): MixedShapedRecipe {
                val group = buf.readUtf()
                val category = buf.readEnum(CraftingBookCategory::class.java)
                val pattern = MixedRecipePattern.PACKET_CODEC.decode(buf)
                val result = ItemStack.STREAM_CODEC.decode(buf)
                val showNotification = buf.readBoolean()
                return MixedShapedRecipe(group, category, pattern, result, showNotification)
            }

            private fun write(buf: RegistryFriendlyByteBuf, recipe: MixedShapedRecipe) {
                buf.writeUtf(recipe.group)
                buf.writeEnum(recipe.category())
                MixedRecipePattern.PACKET_CODEC.encode(buf, recipe.mixedPattern)
                ItemStack.STREAM_CODEC.encode(buf, recipe.mixedResult)
                buf.writeBoolean(recipe.showNotification())
            }

        }
    }

}