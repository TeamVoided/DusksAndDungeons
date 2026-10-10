/*
 * TODO(cleanup)
 */

package org.teamvoided.dusks_and_dungeons.client.particle

import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.Camera
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.*
import net.minecraft.core.Direction
import net.minecraft.util.Mth
import org.joml.Quaternionf
import org.joml.Vector3f
import org.teamvoided.dusks_and_dungeons.particle.ShriekDirectionalParticleOption
import org.teamvoided.dusks_and_dungeons.util.Utils

class ShriekDirectionalParticle(
    level: ClientLevel, x: Double, y: Double, z: Double,
    val direction: Direction,
    var delay: Int,
) : TextureSheetParticle(level, x, y, z, 0.0, 0.0, 0.0) {

    val rotVec = direction.rotationFormDirection()

    init {
        quadSize = 0.85f
        lifetime = 30
        gravity = 0f
        xd = direction.normal.x * 0.1
        yd = direction.normal.y * 0.1
        zd = direction.normal.z * 0.1
    }

    override fun render(vertexConsumer: VertexConsumer, camera: Camera, tickDelta: Float) {
        if (delay <= 0) {
            alpha = 1.0f - Mth.clamp((age + tickDelta) / lifetime, 0.0f, 1.0f)
            val rot = Quaternionf()
            rot.rotationYXZ(rotVec.y, rotVec.x, rotVec.z)
            renderRotatedQuad(vertexConsumer, camera, rot, tickDelta)
            rot.rotationYXZ(rotVec.y - Utils.rotate180, -rotVec.x, rotVec.z)
            renderRotatedQuad(vertexConsumer, camera, rot, tickDelta)
        }
    }

    override fun getLightColor(tickDelta: Float): Int = 240

    override fun getRenderType(): ParticleRenderType = ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT

    override fun getQuadSize(tickDelta: Float) = quadSize * Mth.clamp((age + tickDelta) / lifetime * 0.75f, 0f, 1f)

    override fun tick() {
        if (delay > 0) {
            delay--
        } else {
            super.tick()
        }
    }

    class Provider(private val sprite: SpriteSet) : ParticleProvider<ShriekDirectionalParticleOption> {
        override fun createParticle(
            options: ShriekDirectionalParticleOption,
            clientLevel: ClientLevel,
            x: Double, y: Double, z: Double,
            velX: Double, velY: Double, velZ: Double,
        ): Particle {
            val particle = ShriekDirectionalParticle(clientLevel, x, y, z, options.direction, options.delay)
            particle.pickSprite(sprite)
            particle.setAlpha(1.0f)
            return particle
        }
    }

    companion object {

        fun Direction.rotationFormDirection(): Vector3f {
            return when (this) {
                Direction.UP, Direction.DOWN -> Vector3f(-Utils.rotate60, 0f, 0f)
                Direction.NORTH -> Vector3f(-Utils.rotate30, 0f, 0f)
                Direction.SOUTH -> Vector3f(Utils.rotate30, 0f, 0f)
                Direction.EAST -> Vector3f(Utils.rotate30, Utils.rotate90, 0f)
                Direction.WEST -> Vector3f(-Utils.rotate30, Utils.rotate90, 0f)
            }
        }

    }
}