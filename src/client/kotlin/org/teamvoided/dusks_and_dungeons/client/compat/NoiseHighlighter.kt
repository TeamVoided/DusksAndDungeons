package org.teamvoided.dusks_and_dungeons.client.compat

import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedColor
import net.minecraft.client.Minecraft
import net.minecraft.core.SectionPos
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.Level
import net.minecraft.world.level.levelgen.DensityFunction
import org.teamvoided.creative_works.client.api.gui.DebugUI
import xaero.map.highlight.ChunkHighlighter

object NoiseHighlighter : ChunkHighlighter(false) {

    override fun regionHasHighlights(dimension: ResourceKey<Level>, regionX: Int, regionZ: Int): Boolean {
        return getServerLevel() != null
    }

    override fun getColors(dimension: ResourceKey<Level>, chunkX: Int, chunkZ: Int): IntArray? {
        return resultStore
    }

    val yLevel = DebugUI.addInt("Map Y Level", 63)
    val scaler = DebugUI.addSlider("Noise Texture Scale", 2, 254, 11)

    override fun getChunkHighlitColor(dimension: ResourceKey<Level>, chunkX: Int, chunkZ: Int): IntArray? {
        val level = getServerLevel() ?: return null
        for (x in 0..15) {
            for (z in 0..15) {
                val worldX = SectionPos.sectionToBlockCoord(chunkX, x)
                val worldZ = SectionPos.sectionToBlockCoord(chunkZ, z)
                val compressed = compressNoise(getNoiseValue(level, worldX, worldZ))

                val color = ValidatedColor.ColorHolder(compressed, compressed, 230, compressed, true)
                setResult(x, z, color.argb())
            }
        }
        return resultStore
    }

    // do the nosie here properly
    fun getNoiseValue(level: ServerLevel, worldX: Int, worldZ: Int): Double {
        val randomState = level.chunkSource.randomState()
        val noiseRouter = randomState.router()
        val df = noiseRouter.continents()
//        val df = level.registryAccess()
//            .lookupOrThrow(Registries.DENSITY_FUNCTION)
//            .getOrThrow(NoiseRouterData.CONTINENTS)
//            .value()


        val rawNoise = sampleNoise(worldX, worldZ, df)
        return rawNoise
    }

    private fun compressNoise(rawNoise: Double): Int {
        val noise = (((rawNoise + 1.0) / 2.0) * 255).toInt()
        return (noise / scaler.get()) * scaler.get()
    }

    val cache = mutableMapOf<DensityFunction.SinglePointContext, Double>()

    fun sampleNoise(x: Int, z: Int, densityFunction: DensityFunction): Double {
        val ctx = DensityFunction.SinglePointContext(x, yLevel.get(), z)
        var sample = cache[ctx]
        if (sample == null) {
            sample = densityFunction.compute(ctx)
            cache[ctx] = sample
        }
        return sample
    }

    fun getServerLevel(): ServerLevel? {
        val integratedServer = Minecraft.getInstance().singleplayerServer ?: return null
        return Minecraft.getInstance().level?.dimension()?.let(integratedServer::getLevel)
    }

    override fun calculateRegionHash(dimension: ResourceKey<Level>, regionX: Int, regionZ: Int): Int {
        return regionX.hashCode() * 31 + regionZ.hashCode()
    }

    override fun chunkIsHighlit(dimension: ResourceKey<Level>, chunkX: Int, chunkZ: Int): Boolean {
        return true
    }

    override fun getChunkHighlightSubtleTooltip(dimension: ResourceKey<Level>, chunkX: Int, chunkZ: Int): Component {
        val level = getServerLevel() ?: return Component.literal("Bleh")
        val rawNoise = getNoiseValue(
            level,
            SectionPos.sectionToBlockCoord(chunkX, 8),
            SectionPos.sectionToBlockCoord(chunkZ, 8),
        )
        return Component.literal("Noise: $rawNoise")
    }

    override fun getChunkHighlightBluntTooltip(dimension: ResourceKey<Level>, chunkX: Int, chunkZ: Int): Component {
        return Component.empty()
    }

    override fun addMinimapBlockHighlightTooltips(
        list: MutableList<Component>, dimension: ResourceKey<Level>, blockX: Int, blockZ: Int, width: Int,
    ) {
        list.add(Component.literal("test"))
    }

}