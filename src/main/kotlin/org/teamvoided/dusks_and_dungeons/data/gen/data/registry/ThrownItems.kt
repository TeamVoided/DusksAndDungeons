package org.teamvoided.dusks_and_dungeons.data.gen.data.registry

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.TagKey
import net.minecraft.world.damagesource.DamageType
import net.minecraft.world.damagesource.DamageTypes
import net.minecraft.world.item.EitherHolder
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import org.teamvoided.dusks_and_dungeons.data.registry.DnDThrownItemDefinitions
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.item.ThrownItemDefinition
import java.util.*

object ThrownItems {

    typealias ThrownID = ThrownItemDefinition

    fun init(c: BootstrapContext<ThrownID>) = c.boostrap()

    fun BootstrapContext<ThrownID>.boostrap() {
        register(
            DnDThrownItemDefinitions.BRICKS,
            ConventionalItemTags.BRICKS,
            damage = 2,
            DamageTypes.THROWN,
            cooldown = 10,
            DnDBlockTags.THROWN_BRICK_BREAK
        )
    }


    fun BootstrapContext<ThrownID>.register(
        key: ResourceKey<ThrownID>,
        items: TagKey<Item>,
        damage: Int,
        damageType: ResourceKey<DamageType>,
        cooldown: Int,
        blockBreakTag: TagKey<Block>,
    ) {
        register(
            key, ThrownID(
                items,
                damage,
                EitherHolder(Optional.empty(), damageType),
                cooldown,
                blockBreakTag
            )
        )
    }

}