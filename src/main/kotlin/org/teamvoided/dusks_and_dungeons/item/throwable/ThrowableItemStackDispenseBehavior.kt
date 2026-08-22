package org.teamvoided.dusks_and_dungeons.item.throwable

import net.minecraft.core.dispenser.BlockSource
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ProjectileItem.DispenseConfig
import net.minecraft.world.level.block.DispenserBlock
import org.teamvoided.dusks_and_dungeons.entity.ThrownItemStack

object ThrowableItemStackDispenseBehavior : DefaultDispenseItemBehavior() {

    val dispenseCfg: DispenseConfig = DispenseConfig.DEFAULT

    public override fun execute(state: BlockSource, stack: ItemStack): ItemStack {
        val definition = ThrownItemDefinition.getItemDefinition(stack) ?: return stack
        val level = state.level()
        val dir = state.state().getValue(DispenserBlock.FACING)
        val pos = dispenseCfg.positionFunction().getDispensePosition(state, dir)
        val projectile = ThrownItemStack(level, pos.x(), pos.y(), pos.z())
        projectile.item = stack
        projectile.setDefinition(definition)
        val thrown = definition.value()
        projectile.shoot(
            dir.stepX.toDouble(), dir.stepY.toDouble(), dir.stepZ.toDouble(),
            thrown.power, thrown.uncertainty
        )
        level.addFreshEntity(projectile)
        stack.shrink(1)
        return stack
    }

    override fun playSound(blockSource: BlockSource) {
        blockSource.level().levelEvent(dispenseCfg.overrideDispenseEvent().orElse(1002), blockSource.pos(), 0)
    }

}