package org.teamvoided.dusks_and_dungeons.world.gen.structures

import com.mojang.datafixers.util.Either
import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.BlockPos
import net.minecraft.util.ByIdMap
import net.minecraft.util.Mth
import net.minecraft.util.StringRepresentable
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.structure.Structure
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.worldgen.DnDStructureTypes
import java.util.*
import java.util.function.IntFunction

class DnDMineshaftStructure(settings: StructureSettings, val type: Type) : Structure(settings) {

    public override fun findGenerationPoint(generationContext: GenerationContext): Optional<GenerationStub> {
        generationContext.random().nextDouble()
        val chunkPos = generationContext.chunkPos()
        val blockPos = BlockPos(chunkPos.middleBlockX, 50, chunkPos.minBlockZ)
        val structurePiecesBuilder = StructurePiecesBuilder()
        val i = this.generatePiecesAndAdjust(structurePiecesBuilder, generationContext)
        return Optional.of(GenerationStub(blockPos.offset(0, i, 0), Either.right(structurePiecesBuilder)))
    }

    private fun generatePiecesAndAdjust(
        structurePiecesBuilder: StructurePiecesBuilder,
        generationContext: GenerationContext,
    ): Int {
        val chunkPos = generationContext.chunkPos()
        val worldgenRandom = generationContext.random()
        val chunkGenerator = generationContext.chunkGenerator()
        val mineShaftRoom =
            DnDMineshaftPieces.MineShaftRoom(0, worldgenRandom, chunkPos.getBlockX(2), chunkPos.getBlockZ(2), type)
        structurePiecesBuilder.addPiece(mineShaftRoom)
        mineShaftRoom.addChildren(mineShaftRoom, structurePiecesBuilder, worldgenRandom)
        val i = chunkGenerator.seaLevel
        if (this.type == Type.UNUSED) {
            val blockPos = structurePiecesBuilder.boundingBox.center
            val j = chunkGenerator.getBaseHeight(
                blockPos.x,
                blockPos.z,
                Heightmap.Types.WORLD_SURFACE_WG,
                generationContext.heightAccessor(),
                generationContext.randomState()
            )
            val k = if (j <= i) i else Mth.randomBetweenInclusive(worldgenRandom, i, j)
            val l = k - blockPos.y
            structurePiecesBuilder.offsetPiecesVertically(l)
            return l
        } else {
            return structurePiecesBuilder.moveBelowSeaLevel(i, chunkGenerator.minY, worldgenRandom, 10)
        }
    }

    override fun type() = DnDStructureTypes.MINESHAFT

    enum class Type(val id: String, block: Block, block2: Block, block3: Block) : StringRepresentable {
        VERDANT("verdant", DnDBlocks.VERDANT_LOG, DnDBlocks.VERDANT_PLANKS, DnDBlocks.VERDANT_FENCE),
        UNUSED("unused", Blocks.OAK_LOG, Blocks.BIRCH_PLANKS, Blocks.OAK_FENCE);

        val woodState: BlockState = block.defaultBlockState()
        val planksState: BlockState = block2.defaultBlockState()
        val fenceState: BlockState = block3.defaultBlockState()

        override fun getSerializedName(): String = id

        companion object {

            val CODEC: Codec<Type> = StringRepresentable.fromEnum { entries.toTypedArray() }
            private val BY_ID: IntFunction<Type> =
                ByIdMap.continuous(Type::ordinal, entries.toTypedArray(), ByIdMap.OutOfBoundsStrategy.ZERO)

            fun byId(i: Int): Type = BY_ID.apply(i)

        }
    }

    companion object {

        val CODEC: MapCodec<DnDMineshaftStructure> = RecordCodecBuilder.mapCodec { inst ->
            inst
                .group(
                    settingsCodec(inst),
                    Type.CODEC.fieldOf("mineshaft_type").forGetter { it.type }
                )
                .apply(inst, ::DnDMineshaftStructure)
        }

    }
}