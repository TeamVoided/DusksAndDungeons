package org.teamvoided.dusk_autumn.entity

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.component.DataComponentTypes
import net.minecraft.entity.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.vehicle.AbstractMinecartEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtList
import net.minecraft.particle.BlockStateParticleEffect
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.tag.DamageTypeTags
import net.minecraft.registry.tag.ItemTags
import net.minecraft.registry.tag.TagKey
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.Arm
import net.minecraft.util.Hand
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.EulerAngle
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import net.minecraft.world.event.GameEvent
import net.minecraft.world.explosion.Explosion
import org.teamvoided.dusk_autumn.data.tags.DnDItemTags
import org.teamvoided.dusk_autumn.init.DnDEntities
import org.teamvoided.dusk_autumn.init.DnDItems
import java.util.function.Predicate


class ScarecrowEntity(entityType: EntityType<out ScarecrowEntity>, world: World) : LivingEntity(entityType, world) {
    private val heldItems: DefaultedList<ItemStack> = DefaultedList.ofSize(2, ItemStack.EMPTY)
    private val armorItems: DefaultedList<ItemStack> = DefaultedList.ofSize(4, ItemStack.EMPTY)
    private val decorationItems: DefaultedList<ItemStack> = DefaultedList.ofSize(4, ItemStack.EMPTY)
    var lastHitTime: Long = 0
    private var postRotation: EulerAngle
    private var headRotation: EulerAngle
    private var bodyRotation: EulerAngle
    private var leftArmRotation: EulerAngle
    private var rightArmRotation: EulerAngle
    private var leftLegRotation: EulerAngle
    private var rightLegRotation: EulerAngle

    constructor(world: World, x: Double, y: Double, z: Double) : this(DnDEntities.SCARECROW, world) {
        this.setPosition(x, y, z)
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

    override fun calculateDimensions() {
        val posX = this.x
        val posY = this.y
        val posZ = this.z
        super.calculateDimensions()
        this.setPosition(posX, posY, posZ)
    }

    private fun canClip(): Boolean {
        return !this.hasNoGravity()
    }

    override fun canAiMove(): Boolean {
        return super.canAiMove() && this.canClip()
    }

    override fun initDataTracker(builder: DataTracker.Builder) {
        super.initDataTracker(builder)
        builder.add(TRACKER_IS_SMALL, false)
        builder.add(TRACKER_HAS_LEGS, false)
        builder.add(TRACKER_POST_ROTATION, DEFAULT_POST_ROTATION)
        builder.add(TRACKER_HEAD_ROTATION, DEFAULT_HEAD_ROTATION)
        builder.add(TRACKER_BODY_ROTATION, DEFAULT_BODY_ROTATION)
        builder.add(TRACKER_LEFT_ARM_ROTATION, DEFAULT_LEFT_ARM_ROTATION)
        builder.add(TRACKER_RIGHT_ARM_ROTATION, DEFAULT_RIGHT_ARM_ROTATION)
        builder.add(TRACKER_LEFT_LEG_ROTATION, DEFAULT_LEFT_LEG_ROTATION)
        builder.add(TRACKER_RIGHT_LEG_ROTATION, DEFAULT_RIGHT_LEG_ROTATION)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        val armorItemList = NbtList()
        val armorItems: Iterator<*> = armorItems.iterator()
        while (armorItems.hasNext()) {
            val armorItemStack = armorItems.next() as ItemStack
            armorItemList.add(armorItemStack.getEncoded(this.registryManager))
        }
        val heldItemList = NbtList()
        val heldItems: Iterator<*> = heldItems.iterator()
        while (heldItems.hasNext()) {
            val heldItemStack = heldItems.next() as ItemStack
            heldItemList.add(heldItemStack.getEncoded(this.registryManager))
        }
        val decorationItemList = NbtList()
        val decorationItems: Iterator<*> = decorationItems.iterator()
        while (decorationItems.hasNext()) {
            val decorationItemStack = decorationItems.next() as ItemStack
            heldItemList.add(decorationItemStack.getEncoded(this.registryManager))
        }


        nbt.put("ArmorItems", armorItemList)
        nbt.put("HandItems", heldItemList)
        nbt.put("DecorationItems", decorationItemList)
        nbt.putBoolean("Invisible", this.isInvisible)
        nbt.putBoolean("Small", this.isSmall)
        nbt.putBoolean("Legs", this.hasLegs)

        nbt.put("Pose", this.poseToNbt())
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        var nbtList: NbtList
        var iterator: Int
        var nbtCompound: NbtCompound?
        if (nbt.contains("ArmorItems", 9)) {
            nbtList = nbt.getList("ArmorItems", 10)

            iterator = 0
            while (iterator < armorItems.size) {
                nbtCompound = nbtList.getCompound(iterator)
                armorItems[iterator] = ItemStack.fromNbt(this.registryManager, nbtCompound)
                ++iterator
            }
        }

        if (nbt.contains("HandItems", 9)) {
            nbtList = nbt.getList("HandItems", 10)

            iterator = 0
            while (iterator < heldItems.size) {
                nbtCompound = nbtList.getCompound(iterator)
                heldItems[iterator] = ItemStack.fromNbt(this.registryManager, nbtCompound)
                ++iterator
            }
        }

        if (nbt.contains("DecorationItems", 9)) {
            nbtList = nbt.getList("DecorationItems", 10)

            iterator = 0
            while (iterator < decorationItems.size) {
                nbtCompound = nbtList.getCompound(iterator)
                decorationItems[iterator] = ItemStack.fromNbt(this.registryManager, nbtCompound)
                ++iterator
            }
        }

        this.isInvisible = nbt.getBoolean("Invisible")
        this.isSmall = nbt.getBoolean("Small")
        this.hasLegs = nbt.getBoolean("Legs")
        this.noClip = !this.canClip()
        val nbtCompound2 = nbt.getCompound("Pose")
        this.readPoseNbt(nbtCompound2)
    }

    private fun readPoseNbt(nbt: NbtCompound) {
        val postPose = nbt.getList("Post", 5)
        this.setPostRotation(if (postPose.isEmpty()) DEFAULT_POST_ROTATION else EulerAngle(postPose))
        val headPose = nbt.getList("Head", 5)
        this.setHeadRotation(if (headPose.isEmpty()) DEFAULT_HEAD_ROTATION else EulerAngle(headPose))
        val bodyPose = nbt.getList("Body", 5)
        this.setBodyRotation(if (bodyPose.isEmpty()) DEFAULT_BODY_ROTATION else EulerAngle(bodyPose))
        val leftArmPose = nbt.getList("LeftArm", 5)
        this.setLeftArmRotation(if (leftArmPose.isEmpty()) DEFAULT_LEFT_ARM_ROTATION else EulerAngle(leftArmPose))
        val rightArmPose = nbt.getList("RightArm", 5)
        this.setRightArmRotation(if (rightArmPose.isEmpty()) DEFAULT_RIGHT_ARM_ROTATION else EulerAngle(rightArmPose))
        val leftLegPose = nbt.getList("LeftLeg", 5)
        this.setLeftLegRotation(if (leftLegPose.isEmpty()) DEFAULT_LEFT_LEG_ROTATION else EulerAngle(leftLegPose))
        val rightLegPose = nbt.getList("RightLeg", 5)
        this.setRightLegRotation(if (rightLegPose.isEmpty()) DEFAULT_RIGHT_LEG_ROTATION else EulerAngle(rightLegPose))
    }

    private fun poseToNbt(): NbtCompound {
        val nbtCompound = NbtCompound()
        if (DEFAULT_POST_ROTATION != postRotation) {
            nbtCompound.put("Post", postRotation.toNbt())
        }
        if (DEFAULT_HEAD_ROTATION != headRotation) {
            nbtCompound.put("Head", headRotation.toNbt())
        }
        if (DEFAULT_BODY_ROTATION != bodyRotation) {
            nbtCompound.put("Body", bodyRotation.toNbt())
        }
        if (DEFAULT_LEFT_ARM_ROTATION != leftArmRotation) {
            nbtCompound.put("LeftArm", leftArmRotation.toNbt())
        }
        if (DEFAULT_RIGHT_ARM_ROTATION != rightArmRotation) {
            nbtCompound.put("RightArm", rightArmRotation.toNbt())
        }
        if (DEFAULT_LEFT_LEG_ROTATION != leftLegRotation) {
            nbtCompound.put("LeftLeg", leftLegRotation.toNbt())
        }
        if (DEFAULT_RIGHT_LEG_ROTATION != rightLegRotation) {
            nbtCompound.put("RightLeg", rightLegRotation.toNbt())
        }
        return nbtCompound
    }

    override fun isPushable(): Boolean {
        return false
    }

    override fun pushAway(entity: Entity) {
    }

    override fun tickCramming() {
        val otherEntity = world.getOtherEntities(this, this.bounds, RIDEABLE_MINECART_PREDICATE)
        val otherEntities: Iterator<*> = otherEntity.iterator()

        while (otherEntities.hasNext()) {
            val entity = otherEntities.next() as Entity
            if (this.squaredDistanceTo(entity) <= 0.2) {
                entity.pushAwayFrom(this)
            }
        }
    }

    override fun getArmorItems(): Iterable<ItemStack> {
        return this.armorItems
    }

    override fun getHandItems(): Iterable<ItemStack> {
        return this.heldItems
    }

    fun getDecorationItems(): Iterable<ItemStack> {
        return this.decorationItems
    }

    override fun canUseSlot(slot: EquipmentSlot): Boolean {
        return slot != EquipmentSlot.BODY
    }

    override fun interactAt(player: PlayerEntity, hitPos: Vec3d, hand: Hand): ActionResult {
        super.interactAt(player, hitPos, hand)
        val playerHandStack = player.getStackInHand(hand)
        if (player.isSpectator) {
            return ActionResult.SUCCESS
        } else if (player.world.isClient) {
            return ActionResult.CONSUME
        } else {
            if (!playerHandStack.isEmpty) {
                //the below is done for ordering
                val perferEquipmentSlot = this.getPreferredEquipmentSlot(playerHandStack)
                if (perferEquipmentSlot.type == EquipmentSlot.Type.HUMANOID_ARMOR &&
                    equip(player, perferEquipmentSlot, playerHandStack)
                ) {
                    return ActionResult.SUCCESS
                } else if (
                    equip(player, EquipmentSlot.MAINHAND, playerHandStack) ||
                    equip(player, EquipmentSlot.OFFHAND, playerHandStack)
                ) {
                    return ActionResult.SUCCESS
                } else if (
                    equipDecor(player, 0, playerHandStack, DnDItemTags.SCARECROW_WOOD_ITEMS) ||
                    equipDecor(player, 1, playerHandStack, DnDItemTags.SCARECROW_BALE_ITEMS) ||
                    equipDecor(player, 2, playerHandStack, DnDItemTags.SCARECROW_HEAD_ITEMS) ||
                    equipDecor(player, 3, playerHandStack, DnDItemTags.SCARECROW_CLOTHES_ITEMS)
                ) {
                    return ActionResult.SUCCESS
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
                    return ActionResult.SUCCESS
                }
            }
        }
        return super.interactAt(player, hitPos, hand)
    }

    fun equip(player: PlayerEntity, slot: EquipmentSlot, playerHandStack: ItemStack): Boolean {
        if (getEquippedStack(slot).isEmpty && !isSlotDisabled(slot)) {
            this.equipStack(slot, playerHandStack.copyWithCount(1))
            playerHandStack.consume(1, player)
            this.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1f, 0f)
            return true
        }
        return false
    }

    fun unequip(player: PlayerEntity, equipmentSlot: EquipmentSlot): Boolean {
        val equippedStack = getEquippedStack(equipmentSlot)
        if (!equippedStack.isEmpty) {
            this.equipStack(equipmentSlot, ItemStack.EMPTY)
            player.giveItemStack(equippedStack)
            this.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1f, 1f)
            return true
        }
        return false
    }

    fun equipDecor(player: PlayerEntity, slot: Int, playerHandStack: ItemStack, tag: TagKey<Item>): Boolean {
        val equippedStack = decorationItems[slot]
        if (equippedStack.isEmpty && playerHandStack.isIn(tag)) {
            decorationItems.set(slot, playerHandStack.copyWithCount(1))
            playerHandStack.consume(1, player)
            this.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1f, 0f)
            return true
        }
        return false
    }

    fun unequipDecor(player: PlayerEntity, equipmentSlot: Int): Boolean {
        val equippedStack = decorationItems[equipmentSlot]
        if (!equippedStack.isEmpty) {
            decorationItems.set(equipmentSlot, ItemStack.EMPTY)
            player.giveItemStack(equippedStack)
            this.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1f, 1f)
            return true
        }
        return false
    }

    fun isSlotDisabled(slot: EquipmentSlot): Boolean {
        return (!this.hasLegs && (slot == EquipmentSlot.FEET || slot == EquipmentSlot.LEGS))
    }

    override fun equipStack(slot: EquipmentSlot, stack: ItemStack) {
        this.processEquippedStack(stack)
        when (slot.type) {
            EquipmentSlot.Type.HAND -> this.onEquipItem(
                slot,
                heldItems.set(slot.entitySlotId, stack), stack
            )

            EquipmentSlot.Type.HUMANOID_ARMOR -> this.onEquipItem(
                slot,
                armorItems.set(slot.entitySlotId, stack), stack
            )

            else -> {}
        }
    }

    override fun canEquip(stack: ItemStack): Boolean {
        val equipmentSlot = this.getPreferredEquipmentSlot(stack)
        return getEquippedStack(equipmentSlot).isEmpty
    }

    override fun getEquippedStack(slot: EquipmentSlot): ItemStack {
        return when (slot.type) {
            EquipmentSlot.Type.HAND -> heldItems[slot.entitySlotId]
            EquipmentSlot.Type.HUMANOID_ARMOR -> armorItems[slot.entitySlotId]
            else -> ItemStack.EMPTY
        }
    }

    override fun damage(source: DamageSource, amount: Float): Boolean {
        if (this.isRemoved) {
            return false
        } else {
            val serverWorld = this.world
            if (serverWorld is ServerWorld) {
                if (source.isTypeIn(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
                    this.kill()
                    return false
                } else if (!this.isInvulnerableTo(source)) {
                    if (source.isTypeIn(DamageTypeTags.IS_EXPLOSION)) {
                        this.onBreak(serverWorld, source)
                        this.kill()
                        return false
                    } else if (source.isTypeIn(DamageTypeTags.IGNITES_ARMOR_STANDS)) {
                        if (this.isOnFire) {
                            this.updateHealth(serverWorld, source, 0.15f)
                        } else {
                            this.method_5639(5f)
                        }

                        return false
                    } else if (source.isTypeIn(DamageTypeTags.BURNS_ARMOR_STANDS) && this.health > 0.5f) {
                        this.updateHealth(serverWorld, source, 4.0f)
                        return false
                    } else {
                        val canBreak = source.isTypeIn(DamageTypeTags.CAN_BREAK_ARMOR_STAND)
                        val willKill = source.isTypeIn(DamageTypeTags.ALWAYS_KILLS_ARMOR_STANDS)
                        if (!canBreak && !willKill) {
                            return false
                        } else {
                            val attacker = source.attacker
                            if (attacker is PlayerEntity) {
                                if (!attacker.abilities.allowModifyWorld) {
                                    return false
                                }
                            }

                            if (source.isSourceCreativePlayer) {
                                this.playBreakSound()
                                this.spawnBreakParticles()
                                this.kill()
                                return true
                            } else {
                                val time = serverWorld.time
                                if (time - this.lastHitTime > 5L && !willKill) {
                                    serverWorld.sendEntityStatus(this, 32.toByte())
                                    this.emitGameEvent(GameEvent.ENTITY_DAMAGE, source.attacker)
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

    override fun shouldRender(distance: Double): Boolean {
        var d = this.bounds.averageSideLength * 4.0
        if (java.lang.Double.isNaN(d) || d == 0.0) {
            d = 4.0
        }

        d *= 64.0
        return distance < d * d
    }

    private fun spawnBreakParticles() {
        if (world is ServerWorld) {
            (world as ServerWorld).spawnParticles(
                BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.OAK_PLANKS.defaultState),
                this.x,
                this.getBodyY(0.6666666666666666),
                this.z, 10,
                (this.width / 4.0f).toDouble(),
                (this.height / 4.0f).toDouble(),
                (this.width / 4.0f).toDouble(), 0.05
            )
        }
    }

    private fun updateHealth(world: ServerWorld, damageSource: DamageSource, amount: Float) {
        var f = this.health
        f -= amount
        if (f <= 0.5f) {
            this.onBreak(world, damageSource)
            this.kill()
        } else {
            this.health = f
            this.emitGameEvent(GameEvent.ENTITY_DAMAGE, damageSource.attacker)
        }
    }

    private fun breakAndDropItem(world: ServerWorld, damageSource: DamageSource) {
        val itemStack = ItemStack(DnDItems.SCARECROW_ITEM)
        itemStack.set(DataComponentTypes.CUSTOM_NAME, this.customName)
        Block.dropStack(this.world, this.blockPos, itemStack)
        this.onBreak(world, damageSource)
    }

    private fun onBreak(world: ServerWorld, damageSource: DamageSource) {
        this.playBreakSound()
        this.drop(world, damageSource)
        var itemStack: ItemStack
        var iterator = 0
        while (iterator < heldItems.size) {
            itemStack = heldItems[iterator]
            if (!itemStack.isEmpty) {
                Block.dropStack(this.world, blockPos.up(), itemStack)
                heldItems[iterator] = ItemStack.EMPTY
            }
            ++iterator
        }

        iterator = 0
        while (iterator < armorItems.size) {
            itemStack = armorItems[iterator]
            if (!itemStack.isEmpty) {
                Block.dropStack(this.world, blockPos.up(), itemStack)
                armorItems[iterator] = ItemStack.EMPTY
            }
            ++iterator
        }

        iterator = 0
        while (iterator < decorationItems.size) {
            itemStack = decorationItems[iterator]
            if (!itemStack.isEmpty) {
                Block.dropStack(this.world, blockPos.up(), itemStack)
                decorationItems[iterator] = ItemStack.EMPTY
            }
            ++iterator
        }
    }

    private fun playBreakSound() {
        world.playSound(
            null as PlayerEntity?,
            this.x,
            this.y,
            this.z, SoundEvents.ENTITY_ARMOR_STAND_BREAK,
            this.soundCategory, 1.0f, 1.0f
        )
    }

    override fun turnHead(bodyRotation: Float, headRotation: Float): Float {
        this.prevBodyYaw = this.prevYaw
        this.bodyYaw = this.yaw
        return 0f
    }

    override fun travel(movementInput: Vec3d) {
        if (this.canClip()) {
            super.travel(movementInput)
        }
    }

    override fun setBodyYaw(bodyYaw: Float) {
        this.prevYaw = bodyYaw
        this.prevBodyYaw = this.prevYaw
        this.headYaw = bodyYaw
        this.prevHeadYaw = this.headYaw
    }

    override fun setHeadYaw(headYaw: Float) {
        this.prevYaw = headYaw
        this.prevBodyYaw = this.prevYaw
        this.headYaw = headYaw
        this.prevHeadYaw = this.headYaw
    }

    override fun tick() {
        super.tick()
        val headAngle = dataTracker.get(TRACKER_HEAD_ROTATION) as EulerAngle
        if (headRotation != headAngle) {
            this.setHeadRotation(headAngle)
        }

        val bodyAngle = dataTracker.get(TRACKER_BODY_ROTATION) as EulerAngle
        if (bodyRotation != bodyAngle) {
            this.setBodyRotation(bodyAngle)
        }

        val leftArmAngle = dataTracker.get(TRACKER_LEFT_ARM_ROTATION) as EulerAngle
        if (leftArmRotation != leftArmAngle) {
            this.setLeftArmRotation(leftArmAngle)
        }

        val rightArmAngle = dataTracker.get(TRACKER_RIGHT_ARM_ROTATION) as EulerAngle
        if (rightArmRotation != rightArmAngle) {
            this.setRightArmRotation(rightArmAngle)
        }

        val leftLegAngle = dataTracker.get(TRACKER_LEFT_LEG_ROTATION) as EulerAngle
        if (leftLegRotation != leftLegAngle) {
            this.setLeftLegRotation(leftLegAngle)
        }

        val rightLegAngle = dataTracker.get(TRACKER_RIGHT_LEG_ROTATION) as EulerAngle
        if (rightLegRotation != rightLegAngle) {
            this.setRightLegRotation(rightLegAngle)
        }
    }

    override fun isBaby(): Boolean {
        return this.isSmall
    }

    override fun kill() {
        this.remove(RemovalReason.KILLED)
        this.emitGameEvent(GameEvent.ENTITY_DIE)
    }

    override fun isImmuneToExplosion(explosion: Explosion): Boolean {
        return this.isInvisible
    }

    var isSmall: Boolean
        get() = dataTracker[TRACKER_IS_SMALL]
        set(isBaby) {
            dataTracker[TRACKER_IS_SMALL] = isBaby
        }

    var hasLegs: Boolean
        get() = dataTracker[TRACKER_HAS_LEGS]
        set(hasLegs) {
            dataTracker[TRACKER_HAS_LEGS] = hasLegs
        }

    fun setPostRotation(angle: EulerAngle) {
        this.postRotation = angle
        dataTracker.set(TRACKER_POST_ROTATION, angle)
    }

    fun setHeadRotation(angle: EulerAngle) {
        this.headRotation = angle
        dataTracker.set(TRACKER_HEAD_ROTATION, angle)
    }

    fun setBodyRotation(angle: EulerAngle) {
        this.bodyRotation = angle
        dataTracker.set(TRACKER_BODY_ROTATION, angle)
    }

    fun setLeftArmRotation(angle: EulerAngle) {
        this.leftArmRotation = angle
        dataTracker.set(TRACKER_LEFT_ARM_ROTATION, angle)
    }

    fun setRightArmRotation(angle: EulerAngle) {
        this.rightArmRotation = angle
        dataTracker.set(TRACKER_RIGHT_ARM_ROTATION, angle)
    }

    fun setLeftLegRotation(angle: EulerAngle) {
        this.leftLegRotation = angle
        dataTracker.set(TRACKER_LEFT_LEG_ROTATION, angle)
    }

    fun setRightLegRotation(angle: EulerAngle) {
        this.rightLegRotation = angle
        dataTracker.set(TRACKER_RIGHT_LEG_ROTATION, angle)
    }

    fun getPostRotation(): EulerAngle {
        return this.postRotation
    }

    fun getHeadRotation(): EulerAngle {
        return this.headRotation
    }

    fun getBodyRotation(): EulerAngle {
        return this.bodyRotation
    }

    fun getLeftArmRotation(): EulerAngle {
        return this.leftArmRotation
    }

    fun getRightArmRotation(): EulerAngle {
        return this.rightArmRotation
    }

    fun getLeftLegRotation(): EulerAngle {
        return this.leftLegRotation
    }

    fun getRightLegRotation(): EulerAngle {
        return this.rightLegRotation
    }

    override fun handleAttack(attacker: Entity): Boolean {
        return attacker is PlayerEntity && !world.canPlayerModifyAt(attacker, this.blockPos)
    }

    override fun getMainArm(): Arm {
        return Arm.RIGHT
    }

    override fun getFallSounds(): FallSounds {
        return FallSounds(SoundEvents.ENTITY_ARMOR_STAND_FALL, SoundEvents.ENTITY_ARMOR_STAND_FALL)
    }

    override fun getHurtSound(source: DamageSource): SoundEvent = SoundEvents.ENTITY_ARMOR_STAND_HIT

    override fun getDeathSound(): SoundEvent = SoundEvents.ENTITY_ARMOR_STAND_BREAK

    fun getWeirdSound(): SoundEvent = SoundEvents.ENTITY_GHAST_SCREAM

    override fun onStruckByLightning(world: ServerWorld, lightning: LightningEntity) {
        world.playSound(
            this.x,
            this.y,
            this.z,
            getWeirdSound(),
            this.soundCategory,
            0.3f,
            1.0f,
            false
        )
    }

    override fun isAffectedBySplashPotions(): Boolean = false

    override fun isMobOrPlayer(): Boolean = false

    public override fun getDefaultDimensions(pose: EntityPose): EntityDimensions {
        return if (this.isBaby) SMALL_DIMENSIONS else type.dimensions
    }

    override fun getPickBlockStack(): ItemStack {
        return ItemStack(DnDItems.SCARECROW_ITEM)
    }

    companion object {
        const val WOBBLE_DURATION: Float = 5f
        private val DEFAULT_HEAD_ROTATION = EulerAngle(0f, 0f, 0f)
        private val DEFAULT_POST_ROTATION = EulerAngle(0f, 0f, 0f)
        private val DEFAULT_BODY_ROTATION = EulerAngle(0f, 0f, 0f)
        private val DEFAULT_LEFT_ARM_ROTATION = EulerAngle(0f, 0f, 0f)
        private val DEFAULT_RIGHT_ARM_ROTATION = EulerAngle(0f, 0f, 0f)
        private val DEFAULT_LEFT_LEG_ROTATION = EulerAngle(0f, 0f, 0f)
        private val DEFAULT_RIGHT_LEG_ROTATION = EulerAngle(0f, 0f, 0f)
        private val SMALL_DIMENSIONS: EntityDimensions =
            DnDEntities.SCARECROW.dimensions.scaled(0.5f).withEyeHeight(0.9875f)
        val TRACKER_IS_SMALL: TrackedData<Boolean> = DataTracker.registerData(
            ScarecrowEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN
        )
        val TRACKER_HAS_LEGS: TrackedData<Boolean> = DataTracker.registerData(
            ScarecrowEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN
        )
        val TRACKER_POST_ROTATION: TrackedData<EulerAngle> = DataTracker.registerData(
            ScarecrowEntity::class.java, TrackedDataHandlerRegistry.ROTATION
        )
        val TRACKER_HEAD_ROTATION: TrackedData<EulerAngle> = DataTracker.registerData(
            ScarecrowEntity::class.java, TrackedDataHandlerRegistry.ROTATION
        )
        val TRACKER_BODY_ROTATION: TrackedData<EulerAngle> = DataTracker.registerData(
            ScarecrowEntity::class.java, TrackedDataHandlerRegistry.ROTATION
        )
        val TRACKER_LEFT_ARM_ROTATION: TrackedData<EulerAngle> = DataTracker.registerData(
            ScarecrowEntity::class.java, TrackedDataHandlerRegistry.ROTATION
        )
        val TRACKER_RIGHT_ARM_ROTATION: TrackedData<EulerAngle> = DataTracker.registerData(
            ScarecrowEntity::class.java, TrackedDataHandlerRegistry.ROTATION
        )
        val TRACKER_LEFT_LEG_ROTATION: TrackedData<EulerAngle> = DataTracker.registerData(
            ScarecrowEntity::class.java, TrackedDataHandlerRegistry.ROTATION
        )
        val TRACKER_RIGHT_LEG_ROTATION: TrackedData<EulerAngle> = DataTracker.registerData(
            ScarecrowEntity::class.java, TrackedDataHandlerRegistry.ROTATION
        )
        private val RIDEABLE_MINECART_PREDICATE =
            Predicate { entity: Entity? -> entity is AbstractMinecartEntity && entity.minecartType == AbstractMinecartEntity.Type.RIDEABLE }

        fun createAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes().add(EntityAttributes.GENERIC_STEP_HEIGHT, 0.0)
        }
    }
}
