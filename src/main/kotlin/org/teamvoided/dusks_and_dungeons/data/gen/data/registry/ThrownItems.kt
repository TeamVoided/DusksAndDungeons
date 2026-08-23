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
import org.teamvoided.dusks_and_dungeons.data.registry.DnDDamageTypes
import org.teamvoided.dusks_and_dungeons.data.registry.DnDThrownItemDefinitions
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags
import org.teamvoided.dusks_and_dungeons.item.throwable.ThrownItemDefinition
import java.util.*

object ThrownItems {

    fun init(c: BootstrapContext<ThrownItemDefinition>) = c.boostrap()

    fun BootstrapContext<ThrownItemDefinition>.boostrap() {
        register(
            DnDThrownItemDefinitions.EMPTY,
            DnDItemTags.EMPTY, 0f, DamageTypes.GENERIC, 0f, 0f, 0, DnDBlockTags.EMPTY
        )

        register(
            DnDThrownItemDefinitions.BRICKS,
            ConventionalItemTags.BRICKS,
            damage = 2f,
            DnDDamageTypes.THROWN_BRICK,
            power = 1.5f,
            uncertainty = 1f,
            cooldown = 10,
            DnDBlockTags.THROWN_BRICK_BREAK
        )
    }


    fun BootstrapContext<ThrownItemDefinition>.register(
        key: ResourceKey<ThrownItemDefinition>,
        items: TagKey<Item>,
        damage: Float, damageType: ResourceKey<DamageType>,
        power: Float, uncertainty: Float, cooldown: Int,
        blockBreakTag: TagKey<Block>,
    ) {
        register(
            key, ThrownItemDefinition(
                items,
                damage,
                EitherHolder(Optional.empty(), damageType),
                power,
                uncertainty,
                cooldown,
                blockBreakTag
            )
        )
    }

}