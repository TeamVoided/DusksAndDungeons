//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.minecraft.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Type;
import net.minecraft.util.random.RandomGenerator;

public class BeehiveTreeDecorator extends TreeDecorator {
    public static final Codec<BeehiveTreeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(BeehiveTreeDecorator::new, (decorator) -> {
        return decorator.probability;
    }).codec();
    private static final Direction WORLDGEN_FACING;
    private static final Direction[] SPAWN_DIRECTIONS;
    private final float probability;

    public BeehiveTreeDecorator(float probability) {
        this.probability = probability;
    }

    protected TreeDecoratorType<?> getType() {
        return TreeDecoratorType.BEEHIVE;
    }

    public void generate(TreeDecorator.Placer placer) {
        RandomGenerator randomGenerator = placer.getRandom();
        if (!(randomGenerator.nextFloat() >= this.probability)) {
            List<BlockPos> list = placer.getLeafPositions();
            List<BlockPos> list2 = placer.getLogPositions();
            int i = !list.isEmpty() ? Math.max(((BlockPos)list.get(0)).getY() - 1, ((BlockPos)list2.get(0)).getY() + 1) : Math.min(((BlockPos)list2.get(0)).getY() + 1 + randomGenerator.nextInt(3), ((BlockPos)list2.get(list2.size() - 1)).getY());
            List<BlockPos> list3 = (List)list2.stream().filter((pos) -> {
                return pos.getY() == i;
            }).flatMap((pos) -> {
                Stream var10000 = Stream.of(SPAWN_DIRECTIONS);
                Objects.requireNonNull(pos);
                return var10000.map(pos::offset);
            }).collect(Collectors.toList());
            if (!list3.isEmpty()) {
                Collections.shuffle(list3);
                Optional<BlockPos> optional = list3.stream().filter((pos) -> {
                    return placer.isAir(pos) && placer.isAir(pos.offset(WORLDGEN_FACING));
                }).findFirst();
                if (!optional.isEmpty()) {
                    placer.replace((BlockPos)optional.get(), (BlockState)Blocks.BEE_NEST.getDefaultState().with(BeehiveBlock.FACING, WORLDGEN_FACING));
                    placer.getWorld().getBlockEntity((BlockPos)optional.get(), BlockEntityType.BEEHIVE).ifPresent((blockEntity) -> {
                        int i = 2 + randomGenerator.nextInt(2);

                        for(int j = 0; j < i; ++j) {
                            NbtCompound nbtCompound = new NbtCompound();
                            nbtCompound.putString("id", Registries.ENTITY_TYPE.getId(EntityType.BEE).toString());
                            blockEntity.addBee(nbtCompound, randomGenerator.nextInt(599), false);
                        }

                    });
                }
            }
        }
    }

    static {
        WORLDGEN_FACING = Direction.SOUTH;
        SPAWN_DIRECTIONS = (Direction[])Type.HORIZONTAL.stream().filter((direction) -> {
            return direction != WORLDGEN_FACING.getOpposite();
        }).toArray((i) -> {
            return new Direction[i];
        });
    }
}
