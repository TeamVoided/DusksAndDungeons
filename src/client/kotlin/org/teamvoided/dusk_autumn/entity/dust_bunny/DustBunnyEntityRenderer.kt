package org.teamvoided.dusk_autumn.entity.dust_bunny

import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.entity.DustBunnyEntity

class DustBunnyEntityRenderer(ctx: EntityRendererFactory.Context) : EntityRenderer<DustBunnyEntity>(ctx) {

    override fun getTexture(entity: DustBunnyEntity): Identifier? = null
}