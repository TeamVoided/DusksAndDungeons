/*
 * TODO(cleanup)
 */

package org.teamvoided.voidlib.impl

import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.ResourceManagerReloadListener
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.voidlib.api.InvalidateResourcesCallback

// TODO(lib) move to voidlib
object EventImpl {

    fun init() {
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(ResourceInvalidator)
    }

    object ResourceInvalidator : ResourceManagerReloadListener, IdentifiableResourceReloadListener {

        override fun getFabricId() = id("resource_invalidator")

        override fun onResourceManagerReload(resourceManager: ResourceManager) {
            InvalidateResourcesCallback.EVENT.invoker().invalidate()
        }

    }

}