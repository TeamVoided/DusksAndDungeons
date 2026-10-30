package org.teamvoided.dusks_and_dungeons.init.worldgen

import com.mojang.serialization.MapCodec
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.world.gen.treedcorator.*

object DnDTreeDecoratorTypes {

    val ALTER_GROUND_RADIUS = register("alter_ground_radius", AlterGroundRadiusTreeDecorator.CODEC)
    val ALTER_ON_GROUND = register("alter_on_ground", AlterOnGroundTreeDecorator.CODEC)
    val ATTACHED_TO_TRUNK = register("attached_to_trunk", AttachedToTrunkTreeDecorator.CODEC)
    val BEEHIVE_BIG_TREE_DECORATOR = register("beehive_big_tree_decorator", BeehiveBigTreeDecorator.CODEC)
    val FEATURE_AT_BASE = register("feature_at_base", FeatureAtBaseTreeDecorator.CODEC)
    val FEATURE_ON_LEAVES = register("feature_on_leaves", FeatureOnLeavesTreeDecorator.CODEC)

    fun init(): Unit = Unit

    fun <P : TreeDecorator> register(id: String, codec: MapCodec<P>): TreeDecoratorType<P> {
        return Registry.register(BuiltInRegistries.TREE_DECORATOR_TYPE, id(id), TreeDecoratorType(codec))
    }

}