package org.teamvoided.dusks_and_dungeons.init

import com.mojang.serialization.MapCodec
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.particle.ColorableParticleEffect
import org.teamvoided.dusks_and_dungeons.particle.ShriekDirectionalParticleEffect
import org.teamvoided.dusks_and_dungeons.util.register


object DnDParticles {

    val SMALL_SOUL_FLAME_PARTICLE = register("small_soul_flame")
    val AUTUMN_LEAF_PARTICLE = register("autumn_leaf")
    val CASCADE_LEAF_PARTICLE = register("cascade_leaf")
    val COLORABLE_OMINOUS_PARTICLE = register(
        "colorable_ominous_spawning", ColorableParticleEffect.CODEC, ColorableParticleEffect.PACKET_CODEC
    )
    val SNOWFLAKE = register("snowflake")
    val SHRIEK_DIRECTIONAL = register(
        "shriek_directional", ShriekDirectionalParticleEffect.CODEC, ShriekDirectionalParticleEffect.PACKET_CODEC
    )

    fun init() = Unit

    fun register(name: String): SimpleParticleType = register(name, FabricParticleTypes.simple())

    fun <T : ParticleOptions> register(
        name: String, codec: MapCodec<T>, packetCodec: StreamCodec<in RegistryFriendlyByteBuf, T>,
    ): ParticleType<T> {
        return register(name, FabricParticleTypes.complex(codec, packetCodec))
    }

    fun <O : ParticleOptions, T : ParticleType<O>> register(id: String, particleType: T): T {
        return BuiltInRegistries.PARTICLE_TYPE.register(id(id), particleType)
    }

}