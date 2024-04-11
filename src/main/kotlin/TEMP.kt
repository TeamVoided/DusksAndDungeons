////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by FernFlower decompiler)
////
//package org.teamvoided.dusk_autumn
//
//import com.mojang.serialization.Codec
//import com.mojang.serialization.codecs.RecordCodecBuilder
//import net.minecraft.registry.Holder
//import net.minecraft.sound.BiomeAdditionsSound
//import net.minecraft.sound.BiomeMoodSound
//import net.minecraft.sound.MusicSound
//import net.minecraft.sound.SoundEvent
//import net.minecraft.util.StringIdentifiable
//import net.minecraft.world.biome.Biome
//import net.minecraft.world.biome.BiomeParticleConfig
//import java.util.*
//
//class TEMP internal constructor(
//    val fogColor: Int,
//    val waterColor: Int,
//    val waterFogColor: Int,
//    val skyColor: Int,
//    val foliageColor: Optional<Int>,
//    val grassColor: Optional<Int>,
//    val grassColorModifier: GrassColorModifier,
//    val particleConfig: Optional<BiomeParticleConfig>,
//    val loopSound: Optional<Holder<SoundEvent>>,
//    val moodSound: Optional<BiomeMoodSound>,
//    val additionsSound: Optional<BiomeAdditionsSound>,
//    val music: Optional<MusicSound>
//) {
//    enum class GrassColorModifier(override val name: String) : StringIdentifiable {
//        NONE("none") {
//            override fun getModifiedGrassColor(x: Double, z: Double, color: Int): Int {
//                return color
//            }
//        },
//        DARK_FOREST("dark_forest") {
//            override fun getModifiedGrassColor(x: Double, z: Double, color: Int): Int {
//                return (color and 16711422) + 2634762 shr 1
//            }
//        },
//        SWAMP("swamp") {
//            override fun getModifiedGrassColor(x: Double, z: Double, color: Int): Int {
//                val d = Biome.FOLIAGE_NOISE.sample(x * 0.0225, z * 0.0225, false)
//                return if (d < -0.1) 5011004 else 6975545
//            }
//        };
//
//        abstract fun getModifiedGrassColor(x: Double, z: Double, color: Int): Int
//
//        override fun asString(): String {
//            return this.name
//        }
//
//        companion object {
//            val CODEC: Codec<GrassColorModifier> = StringIdentifiable.createCodec { entries.toTypedArray() }
//        }
//    }
//
//    class Builder {
//        private var fogColor: OptionalInt = OptionalInt.empty()
//        private var waterColor: OptionalInt = OptionalInt.empty()
//        private var waterFogColor: OptionalInt = OptionalInt.empty()
//        private var skyColor: OptionalInt = OptionalInt.empty()
//        private var foliageColor = Optional.empty<Int>()
//        private var grassColor = Optional.empty<Int>()
//        private var grassColorModifier: GrassColorModifier
//        private var particleConfig: Optional<BiomeParticleConfig>
//        private var loopSound: Optional<Holder<SoundEvent>>
//        private var moodSound: Optional<BiomeMoodSound>
//        private var additionsSound: Optional<BiomeAdditionsSound>
//        private var musicSound: Optional<MusicSound>
//
//        init {
//            this.grassColorModifier = GrassColorModifier.NONE
//            this.particleConfig = Optional.empty()
//            this.loopSound = Optional.empty()
//            this.moodSound = Optional.empty()
//            this.additionsSound = Optional.empty()
//            this.musicSound = Optional.empty()
//        }
//
//        fun fogColor(fogColor: Int): Builder {
//            this.fogColor = OptionalInt.of(fogColor)
//            return this
//        }
//
//        fun waterColor(waterColor: Int): Builder {
//            this.waterColor = OptionalInt.of(waterColor)
//            return this
//        }
//
//        fun waterFogColor(waterFogColor: Int): Builder {
//            this.waterFogColor = OptionalInt.of(waterFogColor)
//            return this
//        }
//
//        fun skyColor(skyColor: Int): Builder {
//            this.skyColor = OptionalInt.of(skyColor)
//            return this
//        }
//
//        fun foliageColor(foliageColor: Int): Builder {
//            this.foliageColor = Optional.of(foliageColor)
//            return this
//        }
//
//        fun grassColor(grassColor: Int): Builder {
//            this.grassColor = Optional.of(grassColor)
//            return this
//        }
//
//        fun grassColorModifier(grassColorModifier: GrassColorModifier): Builder {
//            this.grassColorModifier = grassColorModifier
//            return this
//        }
//
//        fun particleConfig(particleConfig: BiomeParticleConfig): Builder {
//            this.particleConfig = Optional.of(particleConfig)
//            return this
//        }
//
//        fun loopSound(sound: Holder<SoundEvent>): Builder {
//            this.loopSound = Optional.of(sound)
//            return this
//        }
//
//        fun moodSound(moodSound: BiomeMoodSound): Builder {
//            this.moodSound = Optional.of(moodSound)
//            return this
//        }
//
//        fun additionsSound(additionsSound: BiomeAdditionsSound): Builder {
//            this.additionsSound = Optional.of(additionsSound)
//            return this
//        }
//
//        fun music(music: MusicSound): Builder {
//            this.musicSound = Optional.ofNullable(music)
//            return this
//        }
//
//        fun build(): TEMP {
//            return TEMP(
//                fogColor.orElseThrow {
//                    IllegalStateException(
//                        "Missing 'fog' color."
//                    )
//                },
//                waterColor.orElseThrow {
//                    IllegalStateException(
//                        "Missing 'water' color."
//                    )
//                },
//                waterFogColor.orElseThrow {
//                    IllegalStateException(
//                        "Missing 'water fog' color."
//                    )
//                },
//                skyColor.orElseThrow {
//                    IllegalStateException(
//                        "Missing 'sky' color."
//                    )
//                },
//                this.foliageColor,
//                this.grassColor,
//                this.grassColorModifier,
//                this.particleConfig,
//                this.loopSound,
//                this.moodSound,
//                this.additionsSound,
//                this.musicSound
//            )
//        }
//    }
//
//    companion object {
//        val CODEC: Codec<TEMP> =
//            RecordCodecBuilder.create { instance: RecordCodecBuilder.Instance<TEMP> ->
//                instance.group(
//                    Codec.INT.fieldOf("fog_color").forGetter { effects: TEMP -> effects.fogColor },
//                    Codec.INT.fieldOf("water_color").forGetter { effects: TEMP -> effects.waterColor },
//                    Codec.INT.fieldOf("water_fog_color").forGetter { effects: TEMP -> effects.waterFogColor },
//                    Codec.INT.fieldOf("sky_color").forGetter { effects: TEMP -> effects.skyColor },
//                    Codec.INT.optionalFieldOf("foliage_color")
//                        .forGetter { effects: TEMP -> effects.foliageColor },
//                    Codec.INT.optionalFieldOf("grass_color").forGetter { effects: TEMP -> effects.grassColor },
//                    GrassColorModifier.CODEC.optionalFieldOf(
//                        "grass_color_modifier",
//                        GrassColorModifier.NONE
//                    ).forGetter { effects: TEMP -> effects.grassColorModifier },
//                    BiomeParticleConfig.CODEC.optionalFieldOf("particle")
//                        .forGetter { effects: TEMP -> effects.particleConfig },
//                    SoundEvent.CODEC.optionalFieldOf("ambient_sound")
//                        .forGetter { effects: TEMP -> effects.loopSound },
//                    BiomeMoodSound.CODEC.optionalFieldOf("mood_sound")
//                        .forGetter { effects: TEMP -> effects.moodSound },
//                    BiomeAdditionsSound.CODEC.optionalFieldOf("additions_sound")
//                        .forGetter { effects: TEMP -> effects.additionsSound },
//                    MusicSound.CODEC.optionalFieldOf("music").forGetter { effects: TEMP -> effects.music }
//                ).apply(
//                    instance
//                ) { fogColor: Int, waterColor: Int, waterFogColor: Int, skyColor: Int, foliageColor: Optional<Int>, grassColor: Optional<Int>, grassColorModifier: GrassColorModifier, particleConfig: Optional<BiomeParticleConfig>, loopSound: Optional<Holder<SoundEvent>>, moodSound: Optional<BiomeMoodSound>, additionsSound: Optional<BiomeAdditionsSound>, music: Optional<MusicSound> ->
//                    TEMP(
//                        fogColor,
//                        waterColor,
//                        waterFogColor,
//                        skyColor,
//                        foliageColor,
//                        grassColor,
//                        grassColorModifier,
//                        particleConfig,
//                        loopSound,
//                        moodSound,
//                        additionsSound,
//                        music
//                    )
//                }
//            }
//    }
//}