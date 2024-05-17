//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package net.minecraft.entity.passive

import com.google.common.collect.ImmutableList
import com.mojang.datafixers.util.Pair
import com.mojang.logging.LogUtils
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.brain.*
import net.minecraft.entity.ai.brain.sensor.Sensor
import net.minecraft.entity.ai.brain.sensor.SensorType
import net.minecraft.entity.ai.brain.task.*
import net.minecraft.entity.mob.PathAwareEntity
import net.minecraft.item.ItemStack
import net.minecraft.registry.tag.ItemTags
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Unit
import net.minecraft.util.math.BlockPos
import org.slf4j.Logger
import java.util.Map
import java.util.Set
import java.util.function.Function
import java.util.function.Predicate

object SnifferBrain {
    private val LOGGER: Logger = LogUtils.getLogger()
    private const val MAX_LOOK_DISTANCE = 6
    val SENSORS: List<SensorType<out Sensor<in SnifferEntity>?>> =
        ImmutableList.of(
            SensorType.NEAREST_LIVING_ENTITIES,
            SensorType.HURT_BY,
            SensorType.NEAREST_PLAYERS,
            SensorType.SNIFFER_TEMPTATIONS
        )
    val MEMORY_MODULES: List<MemoryModuleType<*>> = ImmutableList.of(
        MemoryModuleType.LOOK_TARGET,
        MemoryModuleType.WALK_TARGET,
        MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
        MemoryModuleType.PATH,
        MemoryModuleType.IS_PANICKING,
        MemoryModuleType.SNIFFER_SNIFFING_TARGET,
        MemoryModuleType.SNIFFER_DIGGING,
        MemoryModuleType.SNIFFER_HAPPY,
        MemoryModuleType.SNIFF_COOLDOWN,
        MemoryModuleType.SNIFFER_EXPLORED_POSITIONS,
        MemoryModuleType.VISIBLE_MOBS,
        MemoryModuleType.BREED_TARGET,
        *arrayOf(
            MemoryModuleType.TEMPTING_PLAYER,
            MemoryModuleType.TEMPTATION_COOLDOWN_TICKS,
            MemoryModuleType.IS_TEMPTED
        )
    )
    private const val SNIFFING_COOLDOWN_TICKS = 9600
    private const val IDLING_SPEED_MULTIPLIER = 1.0f
    private const val PANICKING_SPEED_MULTIPLIER = 2.0f
    private const val SNIFFING_SPEED_MULTIPLIER = 1.25f
    private const val TEMPTED_SPEED_MULTIPLIER = 1.25f

    val breedingIngredient: Predicate<ItemStack>
        get() = Predicate { stack: ItemStack -> stack.isIn(ItemTags.SNIFFER_FOOD) }

    internal fun addActivities(brain: Brain<SnifferEntity>): Brain<*> {
        addCoreActivities(brain)
        addIdleActivities(brain)
        addSniffActivities(brain)
        addDigActivities(brain)
        brain.setCoreActivities(Set.of(Activity.CORE))
        brain.setDefaultActivity(Activity.IDLE)
        brain.resetPossibleActivities()
        return brain
    }

    fun reset(sniffer: SnifferEntity): SnifferEntity {
        sniffer.brain.forget(MemoryModuleType.SNIFFER_DIGGING)
        sniffer.brain.forget(MemoryModuleType.SNIFFER_SNIFFING_TARGET)
        return sniffer.updateState(SnifferEntity.State.IDLING)
    }

    private fun addCoreActivities(brain: Brain<SnifferEntity>) {
        brain.setTaskList(
            Activity.CORE,
            0,
            ImmutableList.of(StayAboveWaterTask(0.8f), object : WalkTask<SnifferEntity>(2.0f) {
                override fun run(world: ServerWorld, snifferEntity: SnifferEntity, l: Long) {
                    reset(snifferEntity)
                    super.run(world, snifferEntity, l)
                }
            }, WanderAroundTask(500, 700), ReduceCooldownTask(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS))
        )
    }

    private fun addSniffActivities(brain: Brain<SnifferEntity>) {
        brain.setTaskList(
            Activity.SNIFF,
            ImmutableList.of(Pair.of(0, SearchTask())),
            Set.of(
                Pair.of(MemoryModuleType.IS_PANICKING, MemoryModuleState.VALUE_ABSENT),
                Pair.of(MemoryModuleType.SNIFFER_SNIFFING_TARGET, MemoryModuleState.VALUE_PRESENT),
                Pair.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.VALUE_PRESENT)
            )
        )
    }

    private fun addDigActivities(brain: Brain<SnifferEntity>) {
        brain.setTaskList(
            Activity.DIG, ImmutableList.of(Pair.of(0, DigTask(160, 180)), Pair.of(0, FinishedDigTask(40))), Set.of(
                Pair.of(MemoryModuleType.IS_PANICKING, MemoryModuleState.VALUE_ABSENT),
                Pair.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.VALUE_ABSENT),
                Pair.of(MemoryModuleType.SNIFFER_DIGGING, MemoryModuleState.VALUE_PRESENT)
            )
        )
    }

    private fun addIdleActivities(brain: Brain<SnifferEntity>) {
        brain.setTaskList(Activity.IDLE, ImmutableList.of(Pair.of(0, object : BreedTask(EntityType.SNIFFER) {
            override fun run(world: ServerWorld, animalEntity: AnimalEntity, l: Long) {
                reset(animalEntity as SnifferEntity)
                super.run(world, animalEntity, l)
            }
        }), Pair.of(1, object : TemptTask(
            Function { livingEntity: LivingEntity? -> 1.25f },
            Function { livingEntity: LivingEntity -> if (livingEntity.isBaby) 2.5 else 3.5 }) {
            override fun run(world: ServerWorld, pathAwareEntity: PathAwareEntity, l: Long) {
                reset(pathAwareEntity as SnifferEntity)
                super.run(world, pathAwareEntity, l)
            }
        }), Pair.of(2, LookAroundTask(45, 90)), Pair.of(3, HappyTask(40, 100)), Pair.of<Int, RandomTask<*>>(
            4, RandomTask(
                ImmutableList.of(
                    Pair.of(GoTowardsLookTarget.create(1.0f, 3), 2),
                    Pair.of(
                        ScentTask(40, 80), 1
                    ),
                    Pair.of(SniffTask(40, 80), 1),
                    Pair.of(FollowMobTask.createMatchingType(EntityType.PLAYER, 6.0f), 1),
                    Pair.of(MeanderTask.create(1.0f), 1),
                    Pair.of(WaitTask(5, 20), 2)
                )
            )
        )
        ), Set.of(
            Pair.of<MemoryModuleType<*>, MemoryModuleState>(
                MemoryModuleType.SNIFFER_DIGGING,
                MemoryModuleState.VALUE_ABSENT
            )
        )
        )
    }

    fun resetPossibleActivities(entity: SnifferEntity) {
        entity.brain.resetPossibleActivities(ImmutableList.of(Activity.DIG, Activity.SNIFF, Activity.IDLE))
    }

    private class SearchTask :
        Task<SnifferEntity>(
            Map.of(
                MemoryModuleType.WALK_TARGET,
                MemoryModuleState.VALUE_PRESENT,
                MemoryModuleType.IS_PANICKING,
                MemoryModuleState.VALUE_ABSENT,
                MemoryModuleType.SNIFFER_SNIFFING_TARGET,
                MemoryModuleState.VALUE_PRESENT
            ), 600
        ) {
        override fun shouldRun(world: ServerWorld, snifferEntity: SnifferEntity): Boolean {
            return snifferEntity.canSniff()
        }

        override fun shouldKeepRunning(world: ServerWorld, snifferEntity: SnifferEntity, l: Long): Boolean {
            if (!snifferEntity.canSniff()) {
                snifferEntity.updateState(SnifferEntity.State.IDLING)
                return false
            } else {
                val optional = snifferEntity.brain.getOptionalMemory(MemoryModuleType.WALK_TARGET)
                    .map { obj: WalkTarget -> obj.lookTarget }
                    .map { obj: LookTarget -> obj.blockPos }
                val optional2 = snifferEntity.brain.getOptionalMemory(MemoryModuleType.SNIFFER_SNIFFING_TARGET)
                return if (!optional.isEmpty && !optional2.isEmpty) optional2.get() == optional.get() else false
            }
        }

        override fun run(world: ServerWorld, snifferEntity: SnifferEntity, l: Long) {
            snifferEntity.updateState(SnifferEntity.State.SEARCHING)
        }

        override fun finishRunning(world: ServerWorld, snifferEntity: SnifferEntity, l: Long) {
            if (snifferEntity.canDig() && snifferEntity.canSniff()) {
                snifferEntity.brain.remember(MemoryModuleType.SNIFFER_DIGGING, true)
            }

            snifferEntity.brain.forget(MemoryModuleType.WALK_TARGET)
            snifferEntity.brain.forget(MemoryModuleType.SNIFFER_SNIFFING_TARGET)
        }
    }

    private class DigTask(minRunTime: Int, maxRunTime: Int) :
        Task<SnifferEntity>(
            Map.of(
                MemoryModuleType.IS_PANICKING,
                MemoryModuleState.VALUE_ABSENT,
                MemoryModuleType.WALK_TARGET,
                MemoryModuleState.VALUE_ABSENT,
                MemoryModuleType.SNIFFER_DIGGING,
                MemoryModuleState.VALUE_PRESENT,
                MemoryModuleType.SNIFF_COOLDOWN,
                MemoryModuleState.VALUE_ABSENT
            ), minRunTime, maxRunTime
        ) {
        override fun shouldRun(world: ServerWorld, snifferEntity: SnifferEntity): Boolean {
            return snifferEntity.canSniff()
        }

        override fun shouldKeepRunning(world: ServerWorld, snifferEntity: SnifferEntity, l: Long): Boolean {
            return snifferEntity.brain.getOptionalMemory(MemoryModuleType.SNIFFER_DIGGING).isPresent && snifferEntity.canDig() && !snifferEntity.isInLove
        }

        override fun run(world: ServerWorld, snifferEntity: SnifferEntity, l: Long) {
            snifferEntity.updateState(SnifferEntity.State.DIGGING)
        }

        override fun finishRunning(world: ServerWorld, snifferEntity: SnifferEntity, l: Long) {
            val bl = this.isTimeLimitExceeded(l)
            if (bl) {
                snifferEntity.brain.remember(MemoryModuleType.SNIFF_COOLDOWN, Unit.INSTANCE, 9600L)
            } else {
                reset(snifferEntity)
            }
        }
    }

    private class FinishedDigTask(runTime: Int) :
        Task<SnifferEntity>(
            Map.of(
                MemoryModuleType.IS_PANICKING,
                MemoryModuleState.VALUE_ABSENT,
                MemoryModuleType.WALK_TARGET,
                MemoryModuleState.VALUE_ABSENT,
                MemoryModuleType.SNIFFER_DIGGING,
                MemoryModuleState.VALUE_PRESENT,
                MemoryModuleType.SNIFF_COOLDOWN,
                MemoryModuleState.VALUE_PRESENT
            ), runTime, runTime
        ) {
        override fun shouldRun(world: ServerWorld, snifferEntity: SnifferEntity): Boolean {
            return true
        }

        override fun shouldKeepRunning(world: ServerWorld, snifferEntity: SnifferEntity, l: Long): Boolean {
            return snifferEntity.brain.getOptionalMemory(MemoryModuleType.SNIFFER_DIGGING).isPresent
        }

        override fun run(world: ServerWorld, snifferEntity: SnifferEntity, l: Long) {
            snifferEntity.updateState(SnifferEntity.State.RISING)
        }

        override fun finishRunning(world: ServerWorld, snifferEntity: SnifferEntity, l: Long) {
            val bl = this.isTimeLimitExceeded(l)
            snifferEntity.updateState(SnifferEntity.State.IDLING).onDiggingComplete(bl)
            snifferEntity.brain.forget(MemoryModuleType.SNIFFER_DIGGING)
            snifferEntity.brain.remember(MemoryModuleType.SNIFFER_HAPPY, true)
        }
    }

    private class HappyTask(minRunTime: Int, maxRunTime: Int) :
        Task<SnifferEntity>(
            Map.of(MemoryModuleType.SNIFFER_HAPPY, MemoryModuleState.VALUE_PRESENT),
            minRunTime,
            maxRunTime
        ) {
        override fun shouldKeepRunning(world: ServerWorld, snifferEntity: SnifferEntity, l: Long): Boolean {
            return true
        }

        override fun run(world: ServerWorld, snifferEntity: SnifferEntity, l: Long) {
            snifferEntity.updateState(SnifferEntity.State.HAPPY)
        }

        override fun finishRunning(world: ServerWorld, snifferEntity: SnifferEntity, l: Long) {
            snifferEntity.updateState(SnifferEntity.State.IDLING)
            snifferEntity.brain.forget(MemoryModuleType.SNIFFER_HAPPY)
        }
    }

    internal class ScentTask(minRunTime: Int, maxRunTime: Int) :
        Task<SnifferEntity>(
            Map.of(
                MemoryModuleType.IS_PANICKING,
                MemoryModuleState.VALUE_ABSENT,
                MemoryModuleType.SNIFFER_DIGGING,
                MemoryModuleState.VALUE_ABSENT,
                MemoryModuleType.SNIFFER_SNIFFING_TARGET,
                MemoryModuleState.VALUE_ABSENT,
                MemoryModuleType.SNIFFER_HAPPY,
                MemoryModuleState.VALUE_ABSENT,
                MemoryModuleType.BREED_TARGET,
                MemoryModuleState.VALUE_ABSENT
            ), minRunTime, maxRunTime
        ) {
        override fun shouldRun(world: ServerWorld, snifferEntity: SnifferEntity): Boolean {
            return !snifferEntity.isTempted
        }

        override fun shouldKeepRunning(world: ServerWorld, snifferEntity: SnifferEntity, l: Long): Boolean {
            return true
        }

        override fun run(world: ServerWorld, snifferEntity: SnifferEntity, l: Long) {
            snifferEntity.updateState(SnifferEntity.State.SCENTING)
        }

        override fun finishRunning(world: ServerWorld, snifferEntity: SnifferEntity, l: Long) {
            snifferEntity.updateState(SnifferEntity.State.IDLING)
        }
    }

    private class SniffTask(minRunTime: Int, maxRunTime: Int) :
        Task<SnifferEntity>(
            Map.of(
                MemoryModuleType.WALK_TARGET,
                MemoryModuleState.VALUE_ABSENT,
                MemoryModuleType.SNIFFER_SNIFFING_TARGET,
                MemoryModuleState.VALUE_ABSENT,
                MemoryModuleType.SNIFF_COOLDOWN,
                MemoryModuleState.VALUE_ABSENT
            ), minRunTime, maxRunTime
        ) {
        override fun shouldRun(world: ServerWorld, snifferEntity: SnifferEntity): Boolean {
            return !snifferEntity.isBaby && snifferEntity.canSniff()
        }

        override fun shouldKeepRunning(world: ServerWorld, snifferEntity: SnifferEntity, l: Long): Boolean {
            return snifferEntity.canSniff()
        }

        override fun run(world: ServerWorld, snifferEntity: SnifferEntity, l: Long) {
            snifferEntity.updateState(SnifferEntity.State.SNIFFING)
        }

        override fun finishRunning(world: ServerWorld, snifferEntity: SnifferEntity, l: Long) {
            val bl = this.isTimeLimitExceeded(l)
            snifferEntity.updateState(SnifferEntity.State.IDLING)
            if (bl) {
                snifferEntity.calculateDigPosition().ifPresent { pos: BlockPos? ->
                    snifferEntity.brain.remember(MemoryModuleType.SNIFFER_SNIFFING_TARGET, pos)
                    snifferEntity.brain
                        .remember(MemoryModuleType.WALK_TARGET, WalkTarget(pos, 1.25f, 0))
                }
            }
        }
    }
}