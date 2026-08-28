package org.teamvoided.dusks_and_dungeons.world.gen.structures

import com.mojang.datafixers.util.Either
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.BlockPos
import net.minecraft.util.Mth
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.structure.Structure
import net.minecraft.world.level.levelgen.structure.StructureType
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder
import org.teamvoided.dusks_and_dungeons.init.worldgen.DnDStructureTypes
import org.teamvoided.dusks_and_dungeons.world.gen.structures.mine.MinePieces
import org.teamvoided.dusks_and_dungeons.world.gen.structures.mine.MineRoom
import org.teamvoided.dusks_and_dungeons.world.gen.structures.mine.MineSettings
import java.util.*

class MineStructure(settings: StructureSettings, val type: MineSettings) : Structure(settings) {

    public override fun findGenerationPoint(ctx: GenerationContext): Optional<GenerationStub> {
        ctx.random().nextDouble()
        val chunkPos = ctx.chunkPos()
        val startPos = BlockPos(chunkPos.middleBlockX, MinePieces.MAGIC_START_Y, chunkPos.minBlockZ)
        val mineshaftPiecesBuilder = StructurePiecesBuilder()
        val yOffset = generatePiecesAndAdjust(mineshaftPiecesBuilder, ctx)
        return Optional.of(GenerationStub(startPos.offset(0, yOffset, 0), Either.right(mineshaftPiecesBuilder)))
    }

    private fun generatePiecesAndAdjust(builder: StructurePiecesBuilder, ctx: GenerationContext): Int {
        val chunkPos = ctx.chunkPos()
        val random = ctx.random()
        val chunkGenerator = ctx.chunkGenerator()
        val mineRoom = MineRoom(0, random, chunkPos.getBlockX(2), chunkPos.getBlockZ(2), type)
        builder.addPiece(mineRoom)
        mineRoom.addChildren(mineRoom, builder, random)
        val seaLevel = chunkGenerator.seaLevel
        if (type == MineSettings.UNUSED) {
            val center = builder.boundingBox.center
            val surfaceHeight = chunkGenerator.getBaseHeight(
                center.x,
                center.z,
                Heightmap.Types.WORLD_SURFACE_WG,
                ctx.heightAccessor(),
                ctx.randomState()
            )
            val targetYForCenter =
                if (surfaceHeight <= seaLevel) seaLevel else Mth.randomBetweenInclusive(random, seaLevel, surfaceHeight)
            val dy = targetYForCenter - center.y
            builder.offsetPiecesVertically(dy)
            return dy
        }

        return builder.moveBelowSeaLevel(seaLevel, chunkGenerator.minY, random, 10)
    }

    override fun type(): StructureType<*> = DnDStructureTypes.MINE

    companion object {

        val CODEC: MapCodec<MineStructure> = RecordCodecBuilder.mapCodec { inst ->
            inst
                .group(
                    settingsCodec(inst),
                    MineSettings.CODEC.fieldOf("mine_type").forGetter { it.type }
                )
                .apply(inst, ::MineStructure)
        }

    }
}
