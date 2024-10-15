package org.teamvoided.dusk_autumn.entity.projectile

import net.minecraft.entity.EntityType
import net.minecraft.entity.FlyingItemEntity
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.init.DnDEntities
import org.teamvoided.dusk_autumn.init.blocks.DnDFloraBlocks

class FlyingPumpkinProjectile(entityType: EntityType<out ProjectileEntity>, world: World) :
    ProjectileEntity(entityType, world), FlyingItemEntity {
    constructor(world: World, pos: Vec3d) : this(DnDEntities.FLYING_PUMPKIN, world) {
        this.setPosition(pos)
    }

    override fun initDataTracker(builder: DataTracker.Builder) = Unit
    override fun getStack(): ItemStack = DnDFloraBlocks.SMALL_CARVED_PUMPKIN.asItem().defaultStack
}
