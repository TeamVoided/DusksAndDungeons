package org.teamvoided.dusk_autumn.entity


import net.minecraft.entity.EntityType
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.init.DuskEntities
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.util.GeckoLibUtil


class CrabEntity : AnimalEntity, GeoEntity {
    private val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)

    constructor(entityType: EntityType<out AnimalEntity>, world: World) : super(entityType, world)
    constructor(world: World) : super(DuskEntities.CRAB, world)

    override fun createChild(world: ServerWorld, entity: PassiveEntity): PassiveEntity? = null

    override fun isBreedingItem(stack: ItemStack?): Boolean = false

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(DefaultAnimations.genericIdleController(this))
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache = cache

}
