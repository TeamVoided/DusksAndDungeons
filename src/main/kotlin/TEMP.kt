//package net.minecraft.item
//
//import com.google.common.collect.ImmutableMultimap
//import com.google.common.collect.Multimap
//import net.minecraft.block.dispenser.DispenserBehavior
//import net.minecraft.block.dispenser.DispenserBlock
//import net.minecraft.block.dispenser.ItemDispenserBehavior
//import net.minecraft.entity.EquipmentSlot
//import net.minecraft.entity.LivingEntity
//import net.minecraft.entity.attribute.EntityAttribute
//import net.minecraft.entity.attribute.EntityAttributeModifier
//import net.minecraft.entity.attribute.EntityAttributes
//import net.minecraft.entity.mob.MobEntity
//import net.minecraft.entity.player.PlayerEntity
//import net.minecraft.predicate.entity.EntityPredicates
//import net.minecraft.sound.SoundEvent
//import net.minecraft.util.Hand
//import net.minecraft.util.TypedActionResult
//import net.minecraft.util.Util
//import net.minecraft.util.math.BlockPointer
//import net.minecraft.util.math.Box
//import net.minecraft.util.math.Direction
//import net.minecraft.world.World
//import java.util.*
//import java.util.function.Consumer
//
//open class ArmorItem(val material: ArmorMaterial, val armorSlot: ArmorSlot, settings: Settings) : Item(
//    settings.maxDamageIfAbsent(
//        material.getDurability(armorSlot)
//    )
//),
//    Equippable {
//    val protection: Int = material.getProtection(armorSlot)
//    val toughness: Float = material.toughness
//    protected val knockbackResistance: Float = material.knockbackResistance
//    private val attributeModifiers: Multimap<EntityAttribute, EntityAttributeModifier>
//
//    init {
//        DispenserBlock.registerBehavior(this, DISPENSER_BEHAVIOR)
//        val builder = ImmutableMultimap.builder<EntityAttribute, EntityAttributeModifier>()
//        val uUID = SLOT_UUID_MODIFIERS[armorSlot]
//        builder.put(
//            EntityAttributes.GENERIC_ARMOR, EntityAttributeModifier(
//                uUID, "Armor modifier",
//                protection.toDouble(), EntityAttributeModifier.Operation.ADDITION
//            )
//        )
//        builder.put(
//            EntityAttributes.GENERIC_ARMOR_TOUGHNESS, EntityAttributeModifier(
//                uUID, "Armor toughness",
//                toughness.toDouble(), EntityAttributeModifier.Operation.ADDITION
//            )
//        )
//        if (material === ArmorMaterials.NETHERITE) {
//            builder.put(
//                EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, EntityAttributeModifier(
//                    uUID, "Armor knockback resistance",
//                    knockbackResistance.toDouble(), EntityAttributeModifier.Operation.ADDITION
//                )
//            )
//        }
//
//        this.attributeModifiers = builder.build()
//    }
//
//    override fun getEnchantability(): Int {
//        return material.enchantability
//    }
//
//    override fun canRepair(stack: ItemStack, ingredient: ItemStack): Boolean {
//        return material.repairIngredient.test(ingredient) || super.canRepair(stack, ingredient)
//    }
//
//    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
//        return this.use(this, world, user, hand)
//    }
//
//    override fun getAttributeModifiers(slot: EquipmentSlot): Multimap<EntityAttribute, EntityAttributeModifier> {
//        return if (slot == armorSlot.equipmentSlot) this.attributeModifiers else super.getAttributeModifiers(slot)
//    }
//
//    override fun getPreferredSlot(): EquipmentSlot {
//        return armorSlot.equipmentSlot
//    }
//
//    override fun getEquipSound(): SoundEvent {
//        return material.equipSound
//    }
//
//    enum class ArmorSlot(val equipmentSlot: EquipmentSlot, val translationKey: String) {
//        HELMET(EquipmentSlot.HEAD, "helmet"),
//        CHESTPLATE(EquipmentSlot.CHEST, "chestplate"),
//        LEGGINGS(EquipmentSlot.LEGS, "leggings"),
//        BOOTS(EquipmentSlot.FEET, "boots")
//    }
//
//    companion object {
//        private val SLOT_UUID_MODIFIERS: EnumMap<ArmorSlot, UUID> = Util.make(EnumMap<Any?, Any?>(
//            ArmorSlot::class.java
//        ), Consumer { map: EnumMap<*, *> ->
//            map[ArmorSlot.BOOTS] = UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B")
//            map[ArmorSlot.LEGGINGS] = UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D")
//            map[ArmorSlot.CHESTPLATE] = UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E")
//            map[ArmorSlot.HELMET] = UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")
//        }) as EnumMap<*, *>
//        val DISPENSER_BEHAVIOR: DispenserBehavior = object : ItemDispenserBehavior() {
//            override fun dispenseSilently(pointer: BlockPointer, stack: ItemStack): ItemStack {
//                return if (dispenseArmor(pointer, stack)) stack else super.dispenseSilently(pointer, stack)
//            }
//        }
//
//        fun dispenseArmor(pointer: BlockPointer, armor: ItemStack): Boolean {
//            val blockPos = pointer.pos.offset(pointer.blockState.get(DispenserBlock.FACING) as Direction)
//            val list = pointer.world.getEntitiesByClass(
//                LivingEntity::class.java,
//                Box(blockPos),
//                EntityPredicates.EXCEPT_SPECTATOR.and(EntityPredicates.Equippable(armor))
//            )
//            if (list.isEmpty()) {
//                return false
//            } else {
//                val livingEntity = list[0] as LivingEntity
//                val equipmentSlot = MobEntity.getPreferredEquipmentSlot(armor)
//                val itemStack = armor.split(1)
//                livingEntity.equipStack(equipmentSlot, itemStack)
//                if (livingEntity is MobEntity) {
//                    livingEntity.setEquipmentDropChance(equipmentSlot, 2.0f)
//                    livingEntity.setPersistent()
//                }
//
//                return true
//            }
//        }
//    }
//}