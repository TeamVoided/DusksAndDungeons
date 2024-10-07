////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by FernFlower decompiler)
////
//package net.minecraft.entity.projectile
//
//import net.minecraft.enchantment.EnchantmentHelper
//import net.minecraft.entity.EntityType
//import net.minecraft.entity.EquipmentSlot
//import net.minecraft.entity.LivingEntity
//import net.minecraft.entity.data.DataTracker
//import net.minecraft.entity.data.TrackedData
//import net.minecraft.entity.data.TrackedDataHandlerRegistry
//import net.minecraft.entity.player.PlayerEntity
//import net.minecraft.item.Item
//import net.minecraft.item.ItemStack
//import net.minecraft.item.Items
//import net.minecraft.nbt.NbtCompound
//import net.minecraft.server.network.ServerPlayerEntity
//import net.minecraft.server.world.ServerWorld
//import net.minecraft.sound.SoundEvent
//import net.minecraft.sound.SoundEvents
//import net.minecraft.util.hit.BlockHitResult
//import net.minecraft.util.hit.EntityHitResult
//import net.minecraft.util.math.MathHelper
//import net.minecraft.util.math.Vec3d
//import net.minecraft.world.World
//
//class TridentEntity : PersistentProjectileEntity {
//    private var dealtDamage = false
//    var returnTimer: Int = 0
//
//    constructor(entityType: EntityType<out TridentEntity?>?, world: World?) : super(entityType, world)
//
//    constructor(world: World?, owner: LivingEntity?, stack: ItemStack) : super(
//        EntityType.TRIDENT,
//        owner,
//        world,
//        stack,
//        null as ItemStack?
//    ) {
//        dataTracker.set(LOYALTY, this.method_59960(stack))
//        dataTracker.set(ENCHANTED, stack.hasGlint())
//    }
//
//    constructor(world: World?, d: Double, e: Double, f: Double, stack: ItemStack) : super(
//        EntityType.TRIDENT,
//        d,
//        e,
//        f,
//        world,
//        stack,
//        stack
//    ) {
//        dataTracker.set(LOYALTY, this.method_59960(stack))
//        dataTracker.set(ENCHANTED, stack.hasGlint())
//    }
//
//    override fun initDataTracker(builder: DataTracker.Builder) {
//        super.initDataTracker(builder)
//        builder.add(LOYALTY, 0.toByte())
//        builder.add(ENCHANTED, false)
//    }
//
//    override fun tick() {
//        if (this.inGroundTime > 4) {
//            this.dealtDamage = true
//        }
//
//        val entity = this.owner
//        val i = (dataTracker.get(LOYALTY) as Byte).toInt()
//        if (i > 0 && (this.dealtDamage || this.isNoClip) && entity != null) {
//            if (!this.isOwnerAlive) {
//                if (!world.isClient && this.pickupType == PickupPermission.ALLOWED) {
//                    this.dropStack(this.asItemStack(), 0.1f)
//                }
//
//                this.discard()
//            } else {
//                this.isNoClip = true
//                val vec3d = entity.eyePos.subtract(this.pos)
//                this.setPos(this.x, this.y + vec3d.y * 0.015 * (i.toDouble()), this.z)
//                if (world.isClient) {
//                    this.lastRenderY = this.y
//                }
//
//                val d = 0.05 * i.toDouble()
//                this.velocity = velocity.multiply(0.95).add(vec3d.normalize().multiply(d))
//                if (this.returnTimer == 0) {
//                    this.playSound(SoundEvents.ITEM_TRIDENT_RETURN, 10.0f, 1.0f)
//                }
//
//                ++this.returnTimer
//            }
//        }
//
//        super.tick()
//    }
//
//    private val isOwnerAlive: Boolean
//        get() {
//            val entity = this.owner
//            return if (entity != null && entity.isAlive) {
//                entity !is ServerPlayerEntity || !entity.isSpectator()
//            } else {
//                false
//            }
//        }
//
//    val isEnchanted: Boolean
//        get() = dataTracker.get(ENCHANTED) as Boolean
//
//    override fun getEntityCollision(currentPosition: Vec3d, nextPosition: Vec3d): EntityHitResult? {
//        return if (this.dealtDamage) null else super.getEntityCollision(currentPosition, nextPosition)
//    }
//
//    override fun onEntityHit(entityHitResult: EntityHitResult) {
//        val entity = entityHitResult.entity
//        var f = 8.0f
//        val entity2 = this.owner
//        val damageSource = this.damageSources.trident(
//            this, (entity2
//                ?: this)
//        )
//        var var7 = this.world
//        if (var7 is ServerWorld) {
//            f = EnchantmentHelper.getDamage(var7, this.method_59958(), entity, damageSource, f)
//        }
//
//        this.dealtDamage = true
//        if (entity.damage(damageSource, f)) {
//            if (entity.type === EntityType.ENDERMAN) {
//                return
//            }
//
//            var7 = this.world
//            if (var7 is ServerWorld) {
//                serverWorld = var7
//                EnchantmentHelper.onEntityHitWithItem(serverWorld, entity, damageSource, this.method_59958())
//            }
//
//            if (entity is LivingEntity) {
//                val livingEntity = entity
//                this.method_59957(livingEntity, damageSource)
//                this.onHit(livingEntity)
//            }
//        }
//
//        this.velocity = velocity.multiply(-0.01, -0.1, -0.01)
//        this.playSound(SoundEvents.ITEM_TRIDENT_HIT, 1.0f, 1.0f)
//    }
//
//    override fun method_59956(world: ServerWorld, result: BlockHitResult, stack: ItemStack) {
//        val vec3d = result.blockPos.method_60913(result.pos)
//        val var6 = this.owner
//        val var10002 = if (var6 is LivingEntity) {
//            var6
//        } else {
//            null
//        }
//
//        EnchantmentHelper.onHitBlock(
//            world, stack, var10002, this, null as EquipmentSlot?, vec3d, world.getBlockState(result.blockPos)
//        ) { item: Item? ->
//            this.kill()
//        }
//    }
//
//    override fun method_59958(): ItemStack? {
//        return this.stack
//    }
//
//    override fun tryPickup(player: PlayerEntity): Boolean {
//        return super.tryPickup(player) || this.isNoClip && this.isOwner(player) && player.inventory.insertStack(this.asItemStack())
//    }
//
//    override fun getDefaultItemStack(): ItemStack {
//        return ItemStack(Items.TRIDENT)
//    }
//
//    override fun getHitSound(): SoundEvent {
//        return SoundEvents.ITEM_TRIDENT_HIT_GROUND
//    }
//
//    override fun onPlayerCollision(player: PlayerEntity) {
//        if (this.isOwner(player) || this.owner == null) {
//            super.onPlayerCollision(player)
//        }
//    }
//
//    override fun readCustomDataFromNbt(nbt: NbtCompound) {
//        super.readCustomDataFromNbt(nbt)
//        this.dealtDamage = nbt.getBoolean("DealtDamage")
//        dataTracker.set(LOYALTY, this.method_59960(this.stack))
//    }
//
//    override fun writeCustomDataToNbt(nbt: NbtCompound) {
//        super.writeCustomDataToNbt(nbt)
//        nbt.putBoolean("DealtDamage", this.dealtDamage)
//    }
//
//    private fun method_59960(stack: ItemStack): Byte {
//        val var3 = this.world
//        return if (var3 is ServerWorld) {
//            MathHelper.clamp(EnchantmentHelper.getTridentReturnAcceleration(var3, stack, this), 0, 127).toByte()
//        } else {
//            0
//        }
//    }
//
//    public override fun age() {
//        val i = (dataTracker.get(LOYALTY) as Byte).toInt()
//        if (this.pickupType != PickupPermission.ALLOWED || i <= 0) {
//            super.age()
//        }
//    }
//
//    override fun getDragInWater(): Float {
//        return 0.99f
//    }
//
//    override fun shouldRender(cameraX: Double, cameraY: Double, cameraZ: Double): Boolean {
//        return true
//    }
//
//    companion object {
//        private val LOYALTY: TrackedData<Byte> =
//            DataTracker.registerData(TridentEntity::class.java, TrackedDataHandlerRegistry.BYTE)
//        private val ENCHANTED: TrackedData<Boolean> = DataTracker.registerData(
//            TridentEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN
//        )
//    }
//}