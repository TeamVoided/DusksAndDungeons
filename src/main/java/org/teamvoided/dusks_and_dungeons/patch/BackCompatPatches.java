package org.teamvoided.dusks_and_dungeons.patch;

import com.google.common.collect.Maps;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

import static org.teamvoided.dusks_and_dungeons.util.HelpersKt.rotate;
import static org.teamvoided.dusks_and_dungeons.util.HelpersKt.rotateColumn;

public interface BackCompatPatches {

    static VoxelShape boxZ(double sizeXY, double minZ, double maxZ) {
        return boxZ(sizeXY, sizeXY, minZ, maxZ);
    }

    static VoxelShape boxZ(double sizeX, double sizeY, double minZ, double maxZ) {
        double halfY = sizeY / 2.0;
        return boxZ(sizeX, 8.0 - halfY, 8.0 + halfY, minZ, maxZ);
    }

    static VoxelShape boxZ(double sizeX, double minY, double maxY, double minZ, double maxZ) {
        double halfX = sizeX / 2.0;
        return Block.box(8.0 - halfX, minY, minZ, 8.0 + halfX, maxY, maxZ);
    }

    static Map<Direction, VoxelShape> rotateHorizontal(VoxelShape north) {
        return Maps.newEnumMap(
                Map.of(
                        Direction.NORTH,
                        rotate(north, 0),
                        Direction.EAST,
                        rotate(north, 1),
                        Direction.SOUTH,
                        rotate(north, 2),
                        Direction.WEST,
                        rotate(north, 3)
                )
        );
    }

    static Map<Direction, VoxelShape> rotateAll(VoxelShape shape) {
        return Maps.newEnumMap(
                Map.of(
                        Direction.NORTH,
                        rotate(shape, 0),
                        Direction.EAST,
                        rotate(shape, 1),
                        Direction.SOUTH,
                        rotate(shape, 2),
                        Direction.WEST,
                        rotate(shape, 3),
                        Direction.UP,
                        rotateColumn(shape, Direction.Axis.Z),
                        Direction.DOWN,
                        rotateColumn(shape, Direction.Axis.Z)
                )
        );
    }

}
