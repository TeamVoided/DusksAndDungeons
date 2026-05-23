package org.teamvoided.dusks_and_dungeons.entity

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.core.component.DataComponents
import net.minecraft.world.entity.EquipmentSlot.Type.HAND
import net.minecraft.world.entity.EquipmentSlot.Type.HUMANOID_ARMOR
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.vehicle.AbstractMinecart
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.ListTag
import net.minecraft.core.particles.BlockParticleOption
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.tags.DamageTypeTags
import net.minecraft.tags.TagKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.HumanoidArm
import net.minecraft.world.InteractionHand
import net.minecraft.core.NonNullList
import net.minecraft.core.Rotations
import net.minecraft.world.entity.*
import net.minecraft.world.phys.Vec3
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.level.Explosion
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags
import org.teamvoided.dusks_and_dungeons.init.DnDEntities
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import java.util.function.Predicate

class ScarecrowEntity(entityType: EntityType<out ScarecrowEntity>, world: Level) : LivingEntity(entityType, world) {
    private val heldItems: NonNullList<ItemStack> = NonNullList.withSize(2, ItemStack.EMPTY)
    private val armorItems: NonNullList<ItemStack> = NonNullList.withSize(4, ItemStack.EMPTY)
    private val decorationItems: NonNullList<ItemStack> = NonNullList.withSize(4, ItemStack.EMPTY)
    var lastHitTime: Long = 0
    private var postRotation: Rotations
    private var headRotation: Rotations
    private var bodyRotation: Rotations
    private var leftArmRotation: Rotations
    private var rightArmRotation: Rotations
    private var leftLegRotation: Rotations
    private var rightLegRotation: Rotations

    constructor(world: Level, x: Double, y: Double, z: Double) : this(DnDEntities.SCARECROW, world) {
        this.setPos(x, y, z)
    }

    init {
        this.postRotation = DEFAULT_POST_ROTATION
        this.headRotation = DEFAULT_HEAD_ROTATION
        this.bodyRotation = DEFAULT_BODY_ROTATION
        this.leftArmRotation = DEFAULT_LEFT_ARM_ROTATION
        this.rightArmRotation = DEFAULT_RIGHT_ARM_ROTATION
        this.leftLegRotation = DEFAULT_LEFT_LEG_ROTATION
        this.rightLegRotation = DEFAULT_RIGHT_LEG_ROTATION
    }

    override fun refreshDimensions() {
        val posX = this.x
        val posY = this.y
        val posZ = this.z
        super.refreshDimensions()
        this.setPos(posX, posY, posZ)
    }

    private fun canClip(): Boolean = !this.isNoGravity
    override fun isEffectiveAi(): Boolean = super.isEffectiveAi && this.canClip()
    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        super.defineSynchedData(builder)
        builder.define(TRACKER_IS_SMALL, false)
        builder.define(TRACKER_HAS_LEGS, false)
        builder.define(TRACKER_POST_ROTATION, DEFAULT_POST_ROTATION)
        builder.define(TRACKER_HEAD_ROTATION, DEFAULT_HEAD_ROTATION)
        builder.define(TRACKER_BODY_ROTATION, DEFAULT_BODY_ROTATION)
        builder.define(TRACKER_LEFT_ARM_ROTATION, DEFAULT_LEFT_ARM_ROTATION)
        builder.define(TRACKER_RIGHT_ARM_ROTATION, DEFAULT_RIGHT_ARM_ROTATION)
        builder.define(TRACKER_LEFT_LEG_ROTATION, DEFAULT_LEFT_LEG_ROTATION)
        builder.define(TRACKER_RIGHT_LEG_ROTATION, DEFAULT_RIGHT_LEG_ROTATION)
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)
        val armorItemList = ListTag()
        val armorItems: Iterator<*> = armorItems.iterator()
        while (armorItems.hasNext()) {
            val armorItemStack = armorItems.next() as ItemStack
            armorItemList.add(armorItemStack.saveOptional(this.registryAccess()))
        }
        val heldItemList = ListTag()
        val heldItems: Iterator<*> = heldItems.iterator()
        while (heldItems.hasNext()) {
            val heldItemStack = heldItems.next() as ItemStack
            heldItemList.add(heldItemStack.saveOptional(this.registryAccess()))
        }
        val decorationItemList = ListTag()
        val decorationItems: Iterator<*> = decorationItems.iterator()
        while (decorationItems.hasNext()) {
            val decorationItemStack = decorationItems.next() as ItemStack
            heldItemList.add(decorationItemStack.saveOptional(this.registryAccess()))
        }


        nbt.put("ArmorItems", armorItemList)
        nbt.put("HandItems", heldItemList)
        nbt.put("DecorationItems", decorationItemList)
        nbt.putBoolean("Invisible", this.isInvisible)
        nbt.putBoolean("Small", this.isSmall)
        nbt.putBoolean("Legs", this.hasLegs)

        nbt.put("Pose", this.poseToNbt())
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)
        var nbtList: ListTag
        var iterator: Int
        var nbtCompound: CompoundTag?
        if (nbt.contains("ArmorItems", 9)) {
            nbtList = nbt.getList("ArmorItems", 10)

            iterator = 0
            while (iterator < armorItems.size) {
                nbtCompound = nbtList.getCompound(iterator)
                armorItems[iterator] = ItemStack.parseOptional(this.registryAccess(), nbtCompound)
                ++iterator
            }
        }

        if (nbt.contains("HandItems", 9)) {
            nbtList = nbt.getList("HandItems", 10)

            iterator = 0
            while (iterator < heldItems.size) {
                nbtCompound = nbtList.getCompound(iterator)
                heldItems[iterator] = ItemStack.parseOptional(this.registryAccess(), nbtCompound)
                ++iterator
            }
        }

        if (nbt.contains("DecorationItems", 9)) {
            nbtList = nbt.getList("DecorationItems", 10)

            iterator = 0
            while (iterator < decorationItems.size) {
                nbtCompound = nbtList.getCompound(iterator)
                decorationItems[iterator] = ItemStack.parseOptional(this.registryAccess(), nbtCompound)
                ++iterator
            }
        }

        this.isInvisible = nbt.getBoolean("Invisible")
        this.isSmall = nbt.getBoolean("Small")
        this.hasLegs = nbt.getBoolean("Legs")
        this.noPhysics = !this.canClip()
        val nbtCompound2 = nbt.getCompound("Pose")
        this.readPoseNbt(nbtCompound2)
    }

    private fun readPoseNbt(nbt: CompoundTag) {
        val postPose = nbt.getList("Post", 5)
        this.setPostRotation(if (postPose.isEmpty()) DEFAULT_POST_ROTATION else Rotations(postPose))
        val headPose = nbt.getList("Head", 5)
        this.setHeadRotation(if (headPose.isEmpty()) DEFAULT_HEAD_ROTATION else Rotations(headPose))
        val bodyPose = nbt.getList("Body", 5)
        this.setBodyRotation(if (bodyPose.isEmpty()) DEFAULT_BODY_ROTATION else Rotations(bodyPose))
        val leftArmPose = nbt.getList("LeftArm", 5)
        this.setLeftArmRotation(if (leftArmPose.isEmpty()) DEFAULT_LEFT_ARM_ROTATION else Rotations(leftArmPose))
        val rightArmPose = nbt.getList("RightArm", 5)
        this.setRightArmRotation(if (rightArmPose.isEmpty()) DEFAULT_RIGHT_ARM_ROTATION else Rotations(rightArmPose))
        val leftLegPose = nbt.getList("LeftLeg", 5)
        this.setLeftLegRotation(if (leftLegPose.isEmpty()) DEFAULT_LEFT_LEG_ROTATION else Rotations(leftLegPose))
        val rightLegPose = nbt.getList("RightLeg", 5)
        this.setRightLegRotation(if (rightLegPose.isEmpty()) DEFAULT_RIGHT_LEG_ROTATION else Rotations(rightLegPose))
    }

    private fun poseToNbt(): CompoundTag {
        val nbtCompound = CompoundTag()
        if (DEFAULT_POST_ROTATION != postRotation) {
            nbtCompound.put("Post", postRotation.save())
        }
        if (DEFAULT_HEAD_ROTATION != headRotation) {
            nbtCompound.put("Head", headRotation.save())
        }
        if (DEFAULT_BODY_ROTATION != bodyRotation) {
            nbtCompound.put("Body", bodyRotation.save())
        }
        if (DEFAULT_LEFT_ARM_ROTATION != leftArmRotation) {
            nbtCompound.put("LeftArm", leftArmRotation.save())
        }
        if (DEFAULT_RIGHT_ARM_ROTATION != rightArmRotation) {
            nbtCompound.put("RightArm", rightArmRotation.save())
        }
        if (DEFAULT_LEFT_LEG_ROTATION != leftLegRotation) {
            nbtCompound.put("LeftLeg", leftLegRotation.save())
        }
        if (DEFAULT_RIGHT_LEG_ROTATION != rightLegRotation) {
            nbtCompound.put("RightLeg", rightLegRotation.save())
        }
        return nbtCompound
    }

    override fun isPushable(): Boolean = false
    override fun doPush(entity: Entity) = Unit
    override fun pushEntities() {
        val otherEntity = level().getEntities(this, this.boundingBox, RIDEABLE_MINECART_PREDICATE)
        val otherEntities: Iterator<*> = otherEntity.iterator()

        while (otherEntities.hasNext()) {
            val entity = otherEntities.next() as Entity
            if (this.distanceToSqr(entity) <= 0.2) {
                entity.push(this)
            }
        }
    }

    override fun getArmorSlots(): Iterable<ItemStack> = this.armorItems
    override fun getHandSlots(): Iterable<ItemStack> = this.heldItems
    fun getDecorationItems(): Iterable<ItemStack> = this.decorationItems
    override fun canUseSlot(slot: EquipmentSlot): Boolean = slot != EquipmentSlot.BODY
    override fun interactAt(player: Player, hitPos: Vec3, hand: InteractionHand): InteractionResult {
        super.interactAt(player, hitPos, hand)
        val playerHandStack = player.getItemInHand(hand)
        if (player.isSpectator) {
            return InteractionResult.SUCCESS
        } else if (player.level().isClientSide) {
            return InteractionResult.CONSUME
        } else {
            if (!playerHandStack.isEmpty) {
                //the below is done for ordering
                val perferEquipmentSlot = this.getEquipmentSlotForItem(playerHandStack)
                if (perferEquipmentSlot.type == HUMANOID_ARMOR &&
                    equip(player, perferEquipmentSlot, playerHandStack)
                ) {
                    return InteractionResult.SUCCESS
                } else if (
                    equip(player, EquipmentSlot.MAINHAND, playerHandStack) ||
                    equip(player, EquipmentSlot.OFFHAND, playerHandStack)
                ) {
                    return InteractionResult.SUCCESS
                } else if (
                    equipDecor(player, 0, playerHandStack, DnDItemTags.SCARECROW_WOOD_ITEMS) ||
                    equipDecor(player, 1, playerHandStack, DnDItemTags.SCARECROW_BALE_ITEMS) ||
                    equipDecor(player, 2, playerHandStack, DnDItemTags.SCARECROW_HEAD_ITEMS) ||
                    equipDecor(player, 3, playerHandStack, DnDItemTags.SCARECROW_CLOTHES_ITEMS)
                ) {
                    return InteractionResult.SUCCESS
                }
            } else {
                if (
                    unequip(player, EquipmentSlot.OFFHAND) ||
                    unequip(player, EquipmentSlot.MAINHAND) ||
                    unequip(player, EquipmentSlot.FEET) ||
                    unequip(player, EquipmentSlot.LEGS) ||
                    unequip(player, EquipmentSlot.CHEST) ||
                    unequip(player, EquipmentSlot.HEAD) ||
                    unequipDecor(player, 0) ||
                    unequipDecor(player, 1) ||
                    unequipDecor(player, 2) ||
                    unequipDecor(player, 3)
                ) {
                    return InteractionResult.SUCCESS
                }
            }
        }
        return super.interactAt(player, hitPos, hand)
    }

    fun equip(player: Player, slot: EquipmentSlot, playerHandStack: ItemStack): Boolean {
        if (getItemBySlot(slot).isEmpty && !isSlotDisabled(slot)) {
            this.setItemSlot(slot, playerHandStack.copyWithCount(1))
            playerHandStack.consume(1, player)
            this.playSound(SoundEvents.ITEM_PICKUP, 1f, 0f)
            return true
        }
        return false
    }

    fun unequip(player: Player, equipmentSlot: EquipmentSlot): Boolean {
        val equippedStack = getItemBySlot(equipmentSlot)
        if (!equippedStack.isEmpty) {
            this.setItemSlot(equipmentSlot, ItemStack.EMPTY)
            player.addItem(equippedStack)
            this.playSound(SoundEvents.ITEM_PICKUP, 1f, 1f)
            return true
        }
        return false
    }

    fun equipDecor(player: Player, slot: Int, playerHandStack: ItemStack, tag: TagKey<Item>): Boolean {
        val equippedStack = decorationItems[slot]
        if (equippedStack.isEmpty && playerHandStack.`is`(tag)) {
            decorationItems.set(slot, playerHandStack.copyWithCount(1))
            playerHandStack.consume(1, player)
            this.playSound(SoundEvents.ITEM_PICKUP, 1f, 0f)
            return true
        }
        return false
    }

    fun unequipDecor(player: Player, equipmentSlot: Int): Boolean {
        val equippedStack = decorationItems[equipmentSlot]
        if (!equippedStack.isEmpty) {
            decorationItems.set(equipmentSlot, ItemStack.EMPTY)
            player.addItem(equippedStack)
            this.playSound(SoundEvents.ITEM_PICKUP, 1f, 1f)
            return true
        }
        return false
    }

    fun isSlotDisabled(slot: EquipmentSlot): Boolean =
        (!this.hasLegs && (slot == EquipmentSlot.FEET || slot == EquipmentSlot.LEGS))

    override fun setItemSlot(slot: EquipmentSlot, stack: ItemStack) {
        this.verifyEquippedItem(stack)
        when (slot.type) {
            HAND -> this.onEquipItem(slot, heldItems.set(slot.index, stack), stack)
            HUMANOID_ARMOR -> this.onEquipItem(slot, armorItems.set(slot.index, stack), stack)
            else -> {}
        }
    }

    override fun canTakeItem(stack: ItemStack): Boolean {
        val equipmentSlot = this.getEquipmentSlotForItem(stack)
        return getItemBySlot(equipmentSlot).isEmpty
    }

    override fun getItemBySlot(slot: EquipmentSlot): ItemStack {
        return when (slot.type) {
            HAND -> heldItems[slot.index]
            HUMANOID_ARMOR -> armorItems[slot.index]
            else -> ItemStack.EMPTY
        }
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        if (this.isRemoved) {
            return false
        } else {
            val serverWorld = this.level()
            if (serverWorld is ServerLevel) {
                if (source.`is`(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
                    this.kill()
                    return false
                } else if (!this.isInvulnerableTo(source)) {
                    if (source.`is`(DamageTypeTags.IS_EXPLOSION)) {
                        this.onBreak(serverWorld, source)
                        this.kill()
                        return false
                    } else if (source.`is`(DamageTypeTags.IGNITES_ARMOR_STANDS)) {
                        if (this.isOnFire) {
                            this.updateHealth(serverWorld, source, 0.15f)
                        } else {
                            this.igniteForSeconds(5f)
                        }

                        return false
                    } else if (source.`is`(DamageTypeTags.BURNS_ARMOR_STANDS) && this.health > 0.5f) {
                        this.updateHealth(serverWorld, source, 4.0f)
                        return false
                    } else {
                        val canBreak = source.`is`(DamageTypeTags.CAN_BREAK_ARMOR_STAND)
                        val willKill = source.`is`(DamageTypeTags.ALWAYS_KILLS_ARMOR_STANDS)
                        if (!canBreak && !willKill) {
                            return false
                        } else {
                            val attacker = source.entity
                            if (attacker is Player) {
                                if (!attacker.abilities.mayBuild) {
                                    return false
                                }
                            }

                            if (source.isCreativePlayer) {
                                this.playBreakSound()
                                this.spawnBreakParticles()
                                this.kill()
                                return true
                            } else {
                                val time = serverWorld.gameTime
                                if (time - this.lastHitTime > 5L && !willKill) {
                                    serverWorld.broadcastEntityEvent(this, 32.toByte())
                                    this.gameEvent(GameEvent.ENTITY_DAMAGE, source.entity)
                                    this.lastHitTime = time
                                } else {
                                    this.breakAndDropItem(serverWorld, source)
                                    this.spawnBreakParticles()
                                    this.kill()
                                }
                                return true
                            }
                        }
                    }
                } else {
                    return false
                }
            } else {
                return false
            }
        }
    }

    override fun shouldRenderAtSqrDistance(distance: Double): Boolean {
        var d = this.boundingBox.size * 4.0
        if (java.lang.Double.isNaN(d) || d == 0.0) {
            d = 4.0
        }

        d *= 64.0
        return distance < d * d
    }

    private fun spawnBreakParticles() {
        if (level() is ServerLevel) {
            (level() as ServerLevel).sendParticles(
                BlockParticleOption(ParticleTypes.BLOCK, Blocks.OAK_PLANKS.defaultBlockState()),
                this.x,
                this.getY(0.6666666666666666),
                this.z, 10,
                (this.bbWidth / 4.0f).toDouble(),
                (this.bbHeight / 4.0f).toDouble(),
                (this.bbWidth / 4.0f).toDouble(), 0.05
            )
        }
    }

    private fun updateHealth(world: ServerLevel, damageSource: DamageSource, amount: Float) {
        var f = this.health
        f -= amount
        if (f <= 0.5f) {
            this.onBreak(world, damageSource)
            this.kill()
        } else {
            this.health = f
            this.gameEvent(GameEvent.ENTITY_DAMAGE, damageSource.entity)
        }
    }

    private fun breakAndDropItem(world: ServerLevel, damageSource: DamageSource) {
        val itemStack = ItemStack(DnDItems.SCARECROW_ITEM)
        itemStack.set(DataComponents.CUSTOM_NAME, this.customName)
        Block.popResource(this.level(), this.blockPosition(), itemStack)
        this.onBreak(world, damageSource)
    }

    private fun onBreak(world: ServerLevel, damageSource: DamageSource) {
        this.playBreakSound()
        this.dropAllDeathLoot(world, damageSource)
        var itemStack: ItemStack
        var iterator = 0
        while (iterator < heldItems.size) {
            itemStack = heldItems[iterator]
            if (!itemStack.isEmpty) {
                Block.popResource(this.level(), blockPosition().above(), itemStack)
                heldItems[iterator] = ItemStack.EMPTY
            }
            ++iterator
        }

        iterator = 0
        while (iterator < armorItems.size) {
            itemStack = armorItems[iterator]
            if (!itemStack.isEmpty) {
                Block.popResource(this.level(), blockPosition().above(), itemStack)
                armorItems[iterator] = ItemStack.EMPTY
            }
            ++iterator
        }

        iterator = 0
        while (iterator < decorationItems.size) {
            itemStack = decorationItems[iterator]
            if (!itemStack.isEmpty) {
                Block.popResource(this.level(), blockPosition().above(), itemStack)
                decorationItems[iterator] = ItemStack.EMPTY
            }
            ++iterator
        }
    }

    private fun playBreakSound() = level().playSound(
        null, this.x, this.y, this.z, SoundEvents.ARMOR_STAND_BREAK, this.soundSource, 1.0f, 1.0f
    )


    override fun tickHeadTurn(bodyRotation: Float, headRotation: Float): Float {
        this.yBodyRotO = this.yRotO
        this.yBodyRot = this.yRot
        return 0f
    }

    override fun travel(movementInput: Vec3) = if (this.canClip()) super.travel(movementInput) else Unit
    override fun setYBodyRot(bodyYaw: Float) {
        this.yRotO = bodyYaw
        this.yBodyRotO = this.yRotO
        this.yHeadRot = bodyYaw
        this.yHeadRotO = this.yHeadRot
    }

    override fun setYHeadRot(headYaw: Float) {
        this.yRotO = headYaw
        this.yBodyRotO = this.yRotO
        this.yHeadRot = headYaw
        this.yHeadRotO = this.yHeadRot
    }

    override fun tick() {
        super.tick()
        val headAngle = entityData.get(TRACKER_HEAD_ROTATION) as Rotations
        if (headRotation != headAngle) {
            this.setHeadRotation(headAngle)
        }

        val bodyAngle = entityData.get(TRACKER_BODY_ROTATION) as Rotations
        if (bodyRotation != bodyAngle) {
            this.setBodyRotation(bodyAngle)
        }

        val leftArmAngle = entityData.get(TRACKER_LEFT_ARM_ROTATION) as Rotations
        if (leftArmRotation != leftArmAngle) {
            this.setLeftArmRotation(leftArmAngle)
        }

        val rightArmAngle = entityData.get(TRACKER_RIGHT_ARM_ROTATION) as Rotations
        if (rightArmRotation != rightArmAngle) {
            this.setRightArmRotation(rightArmAngle)
        }

        val leftLegAngle = entityData.get(TRACKER_LEFT_LEG_ROTATION) as Rotations
        if (leftLegRotation != leftLegAngle) {
            this.setLeftLegRotation(leftLegAngle)
        }

        val rightLegAngle = entityData.get(TRACKER_RIGHT_LEG_ROTATION) as Rotations
        if (rightLegRotation != rightLegAngle) {
            this.setRightLegRotation(rightLegAngle)
        }
    }

    override fun isBaby(): Boolean = this.isSmall
    override fun kill() {
        this.remove(RemovalReason.KILLED)
        this.gameEvent(GameEvent.ENTITY_DIE)
    }

    override fun ignoreExplosion(explosion: Explosion): Boolean = this.isInvisible
    var isSmall: Boolean
        get() = entityData[TRACKER_IS_SMALL]
        set(isBaby) {
            entityData[TRACKER_IS_SMALL] = isBaby
        }

    var hasLegs: Boolean
        get() = entityData[TRACKER_HAS_LEGS]
        set(hasLegs) {
            entityData[TRACKER_HAS_LEGS] = hasLegs
        }

    fun setPostRotation(angle: Rotations) {
        this.postRotation = angle
        entityData.set(TRACKER_POST_ROTATION, angle)
    }

    fun setHeadRotation(angle: Rotations) {
        this.headRotation = angle
        entityData.set(TRACKER_HEAD_ROTATION, angle)
    }

    fun setBodyRotation(angle: Rotations) {
        this.bodyRotation = angle
        entityData.set(TRACKER_BODY_ROTATION, angle)
    }

    fun setLeftArmRotation(angle: Rotations) {
        this.leftArmRotation = angle
        entityData.set(TRACKER_LEFT_ARM_ROTATION, angle)
    }

    fun setRightArmRotation(angle: Rotations) {
        this.rightArmRotation = angle
        entityData.set(TRACKER_RIGHT_ARM_ROTATION, angle)
    }

    fun setLeftLegRotation(angle: Rotations) {
        this.leftLegRotation = angle
        entityData.set(TRACKER_LEFT_LEG_ROTATION, angle)
    }

    fun setRightLegRotation(angle: Rotations) {
        this.rightLegRotation = angle
        entityData.set(TRACKER_RIGHT_LEG_ROTATION, angle)
    }

    fun getPostRotation(): Rotations = this.postRotation
    fun getHeadRotation(): Rotations = this.headRotation
    fun getBodyRotation(): Rotations = this.bodyRotation
    fun getLeftArmRotation(): Rotations = this.leftArmRotation
    fun getRightArmRotation(): Rotations = this.rightArmRotation
    fun getLeftLegRotation(): Rotations = this.leftLegRotation
    fun getRightLegRotation(): Rotations = this.rightLegRotation
    override fun skipAttackInteraction(attacker: Entity): Boolean =
        attacker is Player && !level().mayInteract(attacker, this.blockPosition())

    override fun getMainArm(): HumanoidArm = HumanoidArm.RIGHT
    override fun getFallSounds(): Fallsounds =
        Fallsounds(SoundEvents.ARMOR_STAND_FALL, SoundEvents.ARMOR_STAND_FALL)

    override fun getHurtSound(source: DamageSource): SoundEvent = SoundEvents.ARMOR_STAND_HIT
    override fun getDeathSound(): SoundEvent = SoundEvents.ARMOR_STAND_BREAK
    fun getWeirdSound(): SoundEvent = SoundEvents.GHAST_SCREAM
    override fun thunderHit(world: ServerLevel, lightning: LightningBolt) =
        world.playLocalSound(this.x, this.y, this.z, getWeirdSound(), this.soundSource, 0.3f, 1.0f, false)

    override fun isAffectedByPotions(): Boolean = false
    override fun attackable(): Boolean = false
    public override fun getDefaultDimensions(pose: Pose): EntityDimensions =
        if (this.isBaby) SMALL_DIMENSIONS else type.dimensions

    override fun getPickResult(): ItemStack = ItemStack(DnDItems.SCARECROW_ITEM)

    companion object {
        const val WOBBLE_DURATION: Float = 5f
        private val DEFAULT_HEAD_ROTATION = Rotations(0f, 0f, 0f)
        private val DEFAULT_POST_ROTATION = Rotations(0f, 0f, 0f)
        private val DEFAULT_BODY_ROTATION = Rotations(0f, 0f, 0f)
        private val DEFAULT_LEFT_ARM_ROTATION = Rotations(0f, 0f, 0f)
        private val DEFAULT_RIGHT_ARM_ROTATION = Rotations(0f, 0f, 0f)
        private val DEFAULT_LEFT_LEG_ROTATION = Rotations(0f, 0f, 0f)
        private val DEFAULT_RIGHT_LEG_ROTATION = Rotations(0f, 0f, 0f)
        private val SMALL_DIMENSIONS: EntityDimensions =
            DnDEntities.SCARECROW.dimensions.scale(0.5f).withEyeHeight(0.9875f)
        val TRACKER_IS_SMALL: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(ScarecrowEntity::class.java, EntityDataSerializers.BOOLEAN)
        val TRACKER_HAS_LEGS: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(ScarecrowEntity::class.java, EntityDataSerializers.BOOLEAN)
        val TRACKER_POST_ROTATION: EntityDataAccessor<Rotations> =
            SynchedEntityData.defineId(ScarecrowEntity::class.java, EntityDataSerializers.ROTATIONS)
        val TRACKER_HEAD_ROTATION: EntityDataAccessor<Rotations> =
            SynchedEntityData.defineId(ScarecrowEntity::class.java, EntityDataSerializers.ROTATIONS)
        val TRACKER_BODY_ROTATION: EntityDataAccessor<Rotations> =
            SynchedEntityData.defineId(ScarecrowEntity::class.java, EntityDataSerializers.ROTATIONS)
        val TRACKER_LEFT_ARM_ROTATION: EntityDataAccessor<Rotations> =
            SynchedEntityData.defineId(ScarecrowEntity::class.java, EntityDataSerializers.ROTATIONS)
        val TRACKER_RIGHT_ARM_ROTATION: EntityDataAccessor<Rotations> =
            SynchedEntityData.defineId(ScarecrowEntity::class.java, EntityDataSerializers.ROTATIONS)
        val TRACKER_LEFT_LEG_ROTATION: EntityDataAccessor<Rotations> =
            SynchedEntityData.defineId(ScarecrowEntity::class.java, EntityDataSerializers.ROTATIONS)
        val TRACKER_RIGHT_LEG_ROTATION: EntityDataAccessor<Rotations> =
            SynchedEntityData.defineId(ScarecrowEntity::class.java, EntityDataSerializers.ROTATIONS)
        private val RIDEABLE_MINECART_PREDICATE =
            Predicate { entity: Entity? -> entity is AbstractMinecart && entity.minecartType == AbstractMinecart.Type.RIDEABLE }

        fun createAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes().add(Attributes.STEP_HEIGHT, 0.0)
        }
    }
}
