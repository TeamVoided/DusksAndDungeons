/*
 * TODO(cleanup)
 */

package org.teamvoided.dusks_and_dungeons.recipe

import com.google.common.annotations.VisibleForTesting
import com.mojang.datafixers.util.Pair
import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import com.mojang.serialization.DynamicOps
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import it.unimi.dsi.fastutil.chars.CharArraySet
import it.unimi.dsi.fastutil.chars.CharSet
import net.minecraft.Util
import net.minecraft.core.NonNullList
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.util.ExtraCodecs
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.CraftingInput
import net.minecraft.world.item.crafting.Ingredient
import java.util.*
import java.util.function.Function
import kotlin.jvm.optionals.getOrNull
import kotlin.math.max
import kotlin.math.min

class MixedRecipePattern(
    val width: Int, val height: Int,
    val ingredients: NonNullList<Ingredient>,
    val groups: NonNullList<Char>,
    private val data: Optional<ShapeData>,
) {
    private val symmetric = Util.isSymmetrical(width, height, ingredients)
    private val ingredientCount: Int

    init {
        var count = 0
        for (ingredient in ingredients) {
            if (!ingredient.isEmpty) {
                count++
            }
        }
        ingredientCount = count
    }

    fun matches(input: CraftingInput): Boolean {
        if (input.ingredientCount() != ingredientCount) {
            return false
        }

        if (input.width() == width && input.height() == height) {
            if (!symmetric && matches(input, true)) {
                return true
            }

            if (matches(input, false)) {
                return true
            }
        }

        return false
    }

    private fun matches(input: CraftingInput, mirror: Boolean): Boolean {
        val localGroups = mutableMapOf<Char, Pair<ItemStack, Boolean>>()
        for (y in 0..<height) {
            for (x in 0..<width) {
                val arrayIdx = if (mirror) width - x - 1 + y * width else x + y * width
                val ingredient = ingredients[arrayIdx]

                val stack = input.getItem(x, y)
                if (!ingredient.test(stack)) {
                    return false
                }

                val group = groups[arrayIdx]
                if (group != EMPTY_CHAR) {
                    val tallyStack = localGroups[group]
                    if (tallyStack == null) {
                        localGroups[group] = Pair.of(stack, true)
                    } else if (tallyStack.second) {
                        localGroups[group] = Pair.of(stack, ItemStack.isSameItem(tallyStack.first, stack))
                    }
                }
            }
        }
        for ((_, pair) in localGroups) {
            if (pair.second) {
                return false
            }
        }

        return true
    }

    private fun toBuf(buf: RegistryFriendlyByteBuf) {
        buf.writeVarInt(width)
        buf.writeVarInt(height)
        for (group in groups) {
            buf.writeChar(group?.code ?: EMPTY_CHAR.code)
        }
        for (ingredient in ingredients) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, ingredient)
        }
    }

    @JvmRecord
    data class ShapeData(val key: Map<Char, Pair<Boolean, Ingredient>>, val pattern: List<String>) {

        companion object {

            const val MIXED_KEY = "enforce_mixed"

            private val PATTERN_CODEC: Codec<List<String>> = Codec.STRING.listOf().comapFlatMap(
                { pattern ->
                    if (pattern.size > MAX_WIDTH_AND_HEIGHT) {
                        return@comapFlatMap DataResult.error { "Invalid pattern: too many rows, 3 is maximum" }
                    } else if (pattern.isEmpty()) {
                        return@comapFlatMap DataResult.error { "Invalid pattern: empty pattern not allowed" }
                    } else {
                        val firstLen: Int = pattern.first().length
                        for (string in pattern) {
                            if (string.length > MAX_WIDTH_AND_HEIGHT) {
                                return@comapFlatMap DataResult.error { "Invalid pattern: too many columns, 3 is maximum" }
                            }

                            if (firstLen != string.length) {
                                return@comapFlatMap DataResult.error { "Invalid pattern: each row must be the same width" }
                            }
                        }
                        return@comapFlatMap DataResult.success(pattern)
                    }
                }, Function.identity()
            )

            private val KEY_SYMBOL_CODEC: Codec<Char> = Codec.STRING.comapFlatMap({ symbol ->
                if (symbol.length != 1) {
                    return@comapFlatMap DataResult.error { "Invalid key entry: '$symbol' is an invalid symbol (must be 1 character only)." }
                } else {
                    return@comapFlatMap if (" " == symbol) DataResult.error { "Invalid key entry: ' ' is a reserved symbol." }
                    else DataResult.success(symbol[0])
                }
            }, { it.toString() })

            val SHAPE_KEY_CODEC: Codec<Pair<Boolean, Ingredient>> = object : Codec<Pair<Boolean, Ingredient>> {

                override fun <T : Any> encode(
                    input: Pair<Boolean, Ingredient>, ops: DynamicOps<T>, prefix: T,
                ): DataResult<T> {
                    return Ingredient.CODEC_NONEMPTY.encode<T>(input.second, ops, prefix).map {
                        if (input.first)
                            ops.set(it, MIXED_KEY, ops.createBoolean(true))
                        else
                            it
                    }
                }

                override fun <T : Any> decode(
                    ops: DynamicOps<T>, input: T,
                ): DataResult<Pair<Pair<Boolean, Ingredient>, T>> {
                    val isMixed = ops.get(input, MIXED_KEY)
                        .map { ops.getBooleanValue(it).result().getOrNull() ?: false }
                        .result().getOrNull() ?: false
                    if (isMixed) {
                        ops.remove(input, MIXED_KEY)
                    }
                    val result = Ingredient.CODEC_NONEMPTY.decode(ops, input)
                    return if (result.isSuccess)
                        DataResult.success(Pair.of(Pair.of(isMixed, result.result().get().first), input))
                    else
                        DataResult.error { result.error().get().message() }
                }

            }

            val CODEC: MapCodec<ShapeData> = RecordCodecBuilder.mapCodec { instance ->
                instance.group(
                    ExtraCodecs.strictUnboundedMap(KEY_SYMBOL_CODEC, SHAPE_KEY_CODEC)
                        .fieldOf("key").forGetter(ShapeData::key),
                    PATTERN_CODEC.fieldOf("pattern").forGetter(ShapeData::pattern)
                ).apply(instance, ::ShapeData)
            }

        }
    }

    companion object {

        private const val MAX_WIDTH_AND_HEIGHT = 3
        const val EMPTY_CHAR = ' '

        val CODEC: MapCodec<MixedRecipePattern> = ShapeData.CODEC.flatXmap(::fromData) { pattern ->
            pattern.data
                .map { DataResult.success(it) }
                .orElseGet { DataResult.error { "Cannot encode unpacked recipe" } }
        }

        val PACKET_CODEC: StreamCodec<RegistryFriendlyByteBuf, MixedRecipePattern> =
            StreamCodec.ofMember({ obj, buf -> obj.toBuf(buf) }, ::fromBuf)

        fun of(key: MutableMap<Char, Pair<Boolean, Ingredient>>, pattern: List<String>): MixedRecipePattern {
            return fromData(ShapeData(key.toMap(), pattern.toList())).getOrThrow()
        }

        private fun fromData(data: ShapeData): DataResult<MixedRecipePattern> {
            val strings = trim(data.pattern)
            val i = strings.getOrNull(0)?.length ?: 0
            val j = strings.size
            val defaultedList = NonNullList.withSize(i * j, Ingredient.EMPTY)
            val groupList: NonNullList<Char> = NonNullList.withSize(i * j, EMPTY_CHAR)
            val charSet: CharSet = CharArraySet(data.key.keys)

            for (k in strings.indices) {
                val string = strings[k]

                for ((l, char) in string.withIndex()) {
                    val ingredient = if (char == EMPTY_CHAR) Ingredient.EMPTY else data.key[char]?.second
                    if (ingredient == null) {
                        return DataResult.error { "Pattern references symbol '$char' but it's not defined in the key" }
                    }

                    charSet.remove(char)
                    defaultedList[l + i * k] = ingredient
                    if (data.key[char]?.first == true) {
                        groupList[l + i * k] = char
                    }
                }
            }

            return if (!charSet.isEmpty())
                DataResult.error { "Key defines symbols that aren't used in pattern: $charSet" }
            else
                DataResult.success(MixedRecipePattern(i, j, defaultedList, groupList, Optional.of(data)))
        }

        @VisibleForTesting
        fun trim(pattern: List<String>): Array<String> {
            var i = Int.MAX_VALUE
            var j = 0
            var k = 0
            var l = 0

            for ((m, string) in pattern.withIndex()) {
                i = min(i, findFirstSymbol(string))
                val n = findLastSymbol(string)
                j = max(j, n)
                if (n < 0) {
                    if (k == m) k++
                    l++
                } else l = 0
            }
            if (pattern.size == l) {
                return arrayOf()
            }
            val strings = mutableListOf<String>()

            for (o in 0 until pattern.size - l - k) {
                strings.add(pattern[o + k].substring(i, j + 1))
            }
            return strings.toTypedArray()
        }

        fun findFirstSymbol(row: String): Int {
            var i = 0
            while (i < row.length && row[i] == EMPTY_CHAR) {
                i++
            }
            return i
        }

        fun findLastSymbol(row: String): Int {
            var i = row.length - 1
            while (i >= 0 && row[i] == EMPTY_CHAR) {
                i--
            }
            return i
        }

        private fun fromBuf(buf: RegistryFriendlyByteBuf): MixedRecipePattern {
            val i = buf.readVarInt()
            val j = buf.readVarInt()

            val groupList: NonNullList<Char> = NonNullList.withSize(i * j, EMPTY_CHAR)
            groupList.replaceAll { buf.readChar() }

            val defaultedList = NonNullList.withSize(i * j, Ingredient.EMPTY)
            defaultedList.replaceAll { Ingredient.CONTENTS_STREAM_CODEC.decode(buf) }
            return MixedRecipePattern(i, j, defaultedList, groupList, Optional.empty())
        }

    }
}
