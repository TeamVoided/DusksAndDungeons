package org.teamvoided.dusks_and_dungeons.init.worldgen

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType.ContextlessType
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.register
import org.teamvoided.dusks_and_dungeons.world.gen.structures.mine.MineCorridor
import org.teamvoided.dusks_and_dungeons.world.gen.structures.mine.MineCrossing
import org.teamvoided.dusks_and_dungeons.world.gen.structures.mine.MineRoom
import org.teamvoided.dusks_and_dungeons.world.gen.structures.mine.MineStairs

object DnDStructurePiceTypes {

    val MINE_CORRIDOR = register(::MineCorridor, "mine_corridor")
    val MINE_CROSSING = register(::MineCrossing, "mine_crossing")
    val MINE_ROOM = register(::MineRoom, "mine_room")
    val MINE_STAIRS = register(::MineStairs, "mine_stairs")

    fun init() = Unit

    fun register(codec: ContextlessType, name: String): StructurePieceType {
        return BuiltInRegistries.STRUCTURE_PIECE.register(id(name), codec)
    }

}