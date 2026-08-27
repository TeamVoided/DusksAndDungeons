package org.teamvoided.dusks_and_dungeons.init.worldgen

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType.ContextlessType
import net.minecraft.world.level.levelgen.structure.structures.MineshaftPieces
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.register

object DnDStructurePiceTypes {

    val MINESHAFT_CORRIDOR = register(MineshaftPieces::MineShaftCorridor, "mineshaft_corridor")
    val MINESHAFT_CROSSING = register(MineshaftPieces::MineShaftCrossing, "mineshaft_crossing")
    val MINESHAFT_ROOM = register(MineshaftPieces::MineShaftRoom, "mineshaft_room")
    val MINESHAFT_STAIRS = register(MineshaftPieces::MineShaftStairs, "mineshaft_stairs")

    fun init() = Unit

    fun register(codec: ContextlessType, name: String): StructurePieceType {
        return BuiltInRegistries.STRUCTURE_PIECE.register(id(name), codec)
    }

}