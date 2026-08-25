package org.teamvoided.dusks_and_dungeons.datagen.packs

import com.google.gson.JsonObject
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.CachedOutput
import net.minecraft.data.DataProvider
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import org.teamvoided.voidlib.devin.FabricOutput
import org.teamvoided.voidlib.devin.FutureProvider
import org.teamvoided.voidlib.devin.provider.DevinLangProvider
import java.nio.file.Path
import java.util.*
import java.util.concurrent.CompletableFuture

class FancyNameMcLangProvider(o: FabricOutput, val provider: FutureProvider) : DevinLangProvider(o, provider) {

    val crimsonBricks = listOf(
        Blocks.RED_NETHER_BRICKS,
        Blocks.RED_NETHER_BRICK_STAIRS,
        Blocks.RED_NETHER_BRICK_SLAB,
        Blocks.RED_NETHER_BRICK_WALL,
    )
    val nether = listOf(
        Blocks.NETHER_WART,
        Blocks.NETHER_WART_BLOCK
    )

    override fun getName(): String = "Vanilla"

    override fun generateTranslations(lookup: HolderLookup.Provider, gen: TranslationBuilder) {
        gen.add(Items.NETHER_WART, "Crimson Wart")
        nether.forEach { gen.add(it.descriptionId, getLang(it.id).replace("Nether", "Crimson")) }
        crimsonBricks.forEach { gen.add(it.descriptionId, getLang(it.id).replace("Red", "Crimson")) }
    }

    val Block.id get() = BuiltInRegistries.BLOCK.getKey(this)

    override fun run(writer: CachedOutput): CompletableFuture<*> {
        val translationEntries = TreeMap<String, String>()
        return provider.thenCompose { lookup ->
            generateTranslations(lookup) { key: String, value: String ->
                if (translationEntries.containsKey(key)) {
                    throw RuntimeException("Existing translation key found - $key - Duplicate will be ignored.")
                }
                translationEntries[key] = value
            }

            val langEntryJson = JsonObject()
            for ((key, value) in translationEntries) {
                langEntryJson.addProperty(key, value)
            }
            DataProvider.saveStable(writer, langEntryJson, getLangFilePath("en_us"))
        }
    }

    fun getLangFilePath(path: String): Path {
        return dataOutput
            .createPathProvider(PackOutput.Target.RESOURCE_PACK, "lang")
            .json(ResourceLocation.withDefaultNamespace(path))
    }

}
