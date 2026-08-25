package org.teamvoided.dusks_and_dungeons.data.gen.packs

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.DetectedVersion
import net.minecraft.data.PackOutput
import net.minecraft.data.metadata.PackMetadataGenerator
import net.minecraft.network.chat.Component
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.metadata.pack.PackMetadataSection
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.data.gen.DnDData.createBuiltInPack
import java.util.*

object FancyNamesPack {

    fun create(gen: FabricDataGenerator) {
        gen.createBuiltInPack(id("fancy_names")) {
            addProvider(::FancyNameLangProvider)
            addProvider(::FancyNameMcLangProvider)
            addProvider { o -> createResource(o, Component.literal("Better Nether Brick Names")) }
        }
    }

    fun createResource(o: PackOutput, description: Component): PackMetadataGenerator {
        return PackMetadataGenerator(o).add(
            PackMetadataSection.TYPE,
            PackMetadataSection(description, getPackVersion(PackType.CLIENT_RESOURCES), Optional.empty())
        )
    }

    fun getPackVersion(type: PackType): Int = DetectedVersion.BUILT_IN.getPackVersion(type)

}