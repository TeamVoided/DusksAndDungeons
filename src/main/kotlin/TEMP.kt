//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package net.minecraft.block

import com.mojang.serialization.MapCodec
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.state.StateManager
import net.minecraft.state.property.IntProperty
import net.minecraft.state.property.Properties
import net.minecraft.state.property.Property
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.random.RandomGenerator
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldView
import net.minecraft.world.event.GameEvent
import kotlin.math.abs
import kotlin.math.min

class SweetBerryBushBlock(settings: Settings?) : PlantBlock(settings), Fertilizable {
    public override fun getCodec(): MapCodec<SweetBerryBushBlock> {
        return field_46468
    }

    override fun getPickStack(world: WorldView, pos: BlockPos, state: BlockState): ItemStack {
        return ItemStack(Items.SWEET_BERRIES)
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return if (state.get(AGE) as Int == 0) {
            SMALL_SHAPE
        } else {
            if (state.get(AGE) < 3) LARGE_SHAPE else super.getOutlineShape(state, world, pos, context)
        }
    }

    override fun hasRandomTicks(state: BlockState): Boolean {
        return state.get(AGE) < 3
    }

    override fun randomTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: RandomGenerator) {
        val i = state.get(AGE) as Int
        if (i < 3 && random.nextInt(5) == 0 && world.getBaseLightLevel(pos.up(), 0) >= 9) {
            val blockState = state.with(AGE, i + 1) as BlockState
            world.setBlockState(pos, blockState, 2)
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.create(blockState))
        }
    }

    override fun onEntityCollision(state: BlockState, world: World, pos: BlockPos, entity: Entity) {
        if (entity is LivingEntity && entity.getType() !== EntityType.FOX && entity.getType() !== EntityType.BEE) {
            entity.setMovementMultiplier(state, Vec3d(0.800000011920929, 0.75, 0.800000011920929))
            if (!world.isClient && state.get(AGE) as Int > 0 && (entity.lastRenderX != entity.getX() || entity.lastRenderZ != entity.getZ())) {
                val d = abs(entity.getX() - entity.lastRenderX)
                val e = abs(entity.getZ() - entity.lastRenderZ)
                if (d >= 0.003000000026077032 || e >= 0.003000000026077032) {
                    entity.damage(world.damageSources.sweetBerryBush(), 1.0f)
                }
            }
        }
    }

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        val i = state.get(AGE) as Int
        val bl = i == 3
        if (!bl && player.getStackInHand(hand).isOf(Items.BONE_MEAL)) {
            return ActionResult.PASS
        } else if (i > 1) {
            val j = 1 + world.random.nextInt(2)
            dropStack(world, pos, ItemStack(Items.SWEET_BERRIES, j + (if (bl) 1 else 0)))
            world.playSound(
                null as PlayerEntity?,
                pos,
                SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES,
                SoundCategory.BLOCKS,
                1.0f,
                0.8f + world.random.nextFloat() * 0.4f
            )
            val blockState = state.with(AGE, 1) as BlockState
            world.setBlockState(pos, blockState, 2)
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.create(player, blockState))
            return ActionResult.success(world.isClient)
        } else {
            return super.onUse(state, world, pos, player, hand, hit)
        }
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(*arrayOf<Property<*>>(AGE))
    }

    override fun isFertilizable(world: WorldView, pos: BlockPos, state: BlockState): Boolean {
        return state.get(AGE) as Int < 3
    }

    override fun canFertilize(world: World, random: RandomGenerator, pos: BlockPos, state: BlockState): Boolean {
        return true
    }

    override fun fertilize(world: ServerWorld, random: RandomGenerator, pos: BlockPos, state: BlockState) {
        val i = min(3.0, (state.get(AGE) as Int + 1).toDouble())
            .toInt()
        world.setBlockState(pos, state.with(AGE, i) as BlockState, 2)
    }

    init {
        this.defaultState = (stateManager.defaultState as BlockState).with(AGE, 0) as BlockState
    }

    companion object {
        val field_46468: MapCodec<SweetBerryBushBlock> = method_54094 { settings: Settings? ->
            SweetBerryBushBlock(
                settings
            )
        }
        private const val HURT_SPEED_THRESHOLD = 0.003f
        const val MAX_AGE: Int = 3
        val AGE: IntProperty = Properties.AGE_3
        private val SMALL_SHAPE: VoxelShape = createCuboidShape(3.0, 0.0, 3.0, 13.0, 8.0, 13.0)
        private val LARGE_SHAPE: VoxelShape =
            createCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0)
    }
}