package org.teamvoided.dusk_autumn.entity

import net.minecraft.block.BlockState
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.projectile.thrown.ThrownItemEntity
import net.minecraft.item.Item
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.math.EulerAngle
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import org.joml.Math.lerp
import org.teamvoided.dusk_autumn.init.DnDEntities
import org.teamvoided.dusk_autumn.init.DnDItems

class DiceEntity : ThrownItemEntity {

    constructor(entityType: EntityType<out DiceEntity>, world: World) : super(entityType, world)

    constructor(world: World, owner: LivingEntity, color: Int) : super(DnDEntities.DIE, owner, world) {
        this.color = color
    }

    constructor(world: World, x: Double, y: Double, z: Double, color: Int) : super(DnDEntities.DIE, x, y, z, world) {
        this.color = color
    }

    var stop: Boolean
        get() = dataTracker[TRACKER_STOP]
        set(stop) {
            dataTracker[TRACKER_STOP] = stop
        }
    var sideUp: Int
        get() = dataTracker[TRACKER_SIDE_UP]
        set(sideUp) {
            dataTracker[TRACKER_SIDE_UP] = sideUp
        }

    var rotationVec: EulerAngle
        get() = dataTracker[TRACKER_ROTATION]
        set(rotationVec) {
            dataTracker[TRACKER_ROTATION] = rotationVec
        }

    var timeSinceLastFall: Int
        get() = dataTracker[TRACKER_TIME_SINCE_LAST_FALL]
        set(timeSinceLastFall) {
            dataTracker[TRACKER_TIME_SINCE_LAST_FALL] = timeSinceLastFall
        }

    var color: Int
        get() = dataTracker[TRACKER_COLOR]
        set(color) {
            dataTracker[TRACKER_COLOR] = color
        }

    init {
        stop = false
        sideUp = 1
        rotationVec = EulerAngle(random.nextFloat(), random.nextFloat(), random.nextFloat())
        timeSinceLastFall = 0
    }

    override fun initDataTracker(builder: DataTracker.Builder) {
        println("SUPER")
        super.initDataTracker(builder)
        println("TRACKER_STOP")
        builder.add(TRACKER_STOP, false)
        println("TRACKER_SIDE_UP")
        builder.add(TRACKER_SIDE_UP, 1)
        println("TRACKER_ROTATION")
        builder.add(TRACKER_ROTATION, DEFAULT_ROTATION)
        println("TRACKER_TIME_SINCE_LAST_FALL")
        builder.add(TRACKER_TIME_SINCE_LAST_FALL, 0)
        println("TRACKER_COLOR")
        builder.add(TRACKER_COLOR, 0xFFFFFF)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)

        nbt.putBoolean("Stop", this.stop)
        nbt.putInt("SideUp", this.sideUp)
        nbt.put("RotationVector", rotationVec.toNbt())
        nbt.putInt("TimeSinceLastFall", this.timeSinceLastFall)
        nbt.putInt("Color", this.color)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)

        val rotation = nbt.getList("RotationVector", 5)

        this.stop = nbt.getBoolean("Stop")
        this.sideUp = nbt.getInt("SideUp")
        this.rotationVec = (if (rotation.isEmpty()) DEFAULT_ROTATION else EulerAngle(rotation))
        this.timeSinceLastFall = nbt.getInt("TimeSinceLastFall")
        this.color = nbt.getInt("Color")
    }

    override fun getDefaultItem(): Item = DnDItems.DIE_ITEM

    override fun onBlockCollision(state: BlockState) {
        super.onBlockCollision(state)
        if (age > 60 && timeSinceLastFall < 10 && this.velocity.y < 0.1) {
            stop = true
        } else {
            sideUp = random.nextInt(5) + 1
            this.velocity = Vec3d(this.velocity.x * theEvil, this.velocity.y * theEvilY, this.velocity.z * theEvil)
            rotationVec = EulerAngle(random.nextFloat(), random.nextFloat(), random.nextFloat())
        }
        timeSinceLastFall = 0
    }

    override fun tick() {
        super.tick()
        if (stop) {
            val lerp = timeSinceLastFall / 10f
            rotationVec = EulerAngle(
                lerp(lerp, rotationVec.pitch, lerpRotationValues(sideUp).pitch),
                lerp(lerp, rotationVec.yaw, lerpRotationValues(sideUp).yaw),
                lerp(lerp, rotationVec.roll, lerpRotationValues(sideUp).roll)
            )
            this.velocity = Vec3d(
                lerp(lerp, this.velocity.x.toFloat(), 0f).toDouble(),
                lerp(lerp, this.velocity.y.toFloat(), 0f).toDouble(),
                lerp(lerp, this.velocity.z.toFloat(), 0f).toDouble()
            )
        } else {
            rotationVec = EulerAngle(
                rotationVec.pitch * tickRotateMult,
                rotationVec.yaw * tickRotateMult,
                rotationVec.roll * tickRotateMult
            )
        }
        timeSinceLastFall++
    }

    fun lerpRotationValues(side: Int): EulerAngle {
        if (!(side >= 1 && side <= 6)) {
            println("oopsie :) --------------------------------------------------------------")
            println(side)
        }
        return when (side) {
            1 -> EulerAngle(0f, 0f, 0f)
            2 -> EulerAngle(0f, 0f, 90f)
            3 -> EulerAngle(90f, 0f, 0f)
            4 -> EulerAngle(0f, 0f, -90f)
            5 -> EulerAngle(-90f, 0f, 0f)
            6 -> EulerAngle(180f, 0f, 0f)
            else -> EulerAngle(0f, 0f, 0f)
        }
    }
    override fun isPushable(): Boolean = false

//    private val particleParameters: ParticleEffect
//        get() {
//            val itemStack = this.stack
//            return (if (!itemStack.isEmpty && !itemStack.isOf(this.defaultItem)) ItemStackParticleEffect(
//                ParticleTypes.ITEM,
//                itemStack
//            ) else ParticleTypes.ITEM_SNOWBALL) as ParticleEffect
//        }

//    override fun handleStatus(status: Byte) {
//        if (status.toInt() == 3) {
//            val particleEffect = this.particleParameters
//
//            for (i in 0..7) {
//                world.addParticle(particleEffect, this.x, this.y, this.z, 0.0, 0.0, 0.0)
//            }
//        }
//    }

//    override fun onEntityHit(entityHitResult: EntityHitResult) {
//        super.onEntityHit(entityHitResult)
//        val entity = entityHitResult.entity
//        val i = if (entity is BlazeEntity) 3 else 0
//        entity.damage(this.damageSources.thrown(this, this.owner), i.toFloat())
//    }



//    override fun onCollision(hitResult: HitResult) {
//        super.onCollision(hitResult)
//        if (!world.isClient) {
//            world.sendEntityStatus(this, 3.toByte())
//            this.discard()
//        }
//    }

    companion object {
        val theEvil = 0.9
        val theEvilY = -0.5
        val tickRotateMult = 0.99f
        private val DEFAULT_ROTATION = EulerAngle(0f, 0f, 0f)
        val TRACKER_STOP: TrackedData<Boolean> = DataTracker.registerData(
            ScarecrowEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN
        )
        val TRACKER_SIDE_UP: TrackedData<Int> = DataTracker.registerData(
            ScarecrowEntity::class.java, TrackedDataHandlerRegistry.INTEGER
        )
        val TRACKER_ROTATION: TrackedData<EulerAngle> = DataTracker.registerData(
            ScarecrowEntity::class.java, TrackedDataHandlerRegistry.ROTATION
        )
        val TRACKER_TIME_SINCE_LAST_FALL: TrackedData<Int> = DataTracker.registerData(
            ScarecrowEntity::class.java, TrackedDataHandlerRegistry.INTEGER
        )
        val TRACKER_COLOR: TrackedData<Int> = DataTracker.registerData(
            ScarecrowEntity::class.java, TrackedDataHandlerRegistry.INTEGER
        )
    }
}