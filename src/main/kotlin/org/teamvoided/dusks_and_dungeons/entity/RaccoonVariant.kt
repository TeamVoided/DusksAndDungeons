package org.teamvoided.dusks_and_dungeons.entity

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.Identifier

data class RaccoonVariant(val texture: Identifier) {

    companion object {
        val CODEC: Codec<RaccoonVariant> = RecordCodecBuilder.create { instance ->
            instance.group(
                Identifier.CODEC.fieldOf("texture").forGetter { it.texture }
            ).apply(instance) { RaccoonVariant(it) }
        }
    }
}
