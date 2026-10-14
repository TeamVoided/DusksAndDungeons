package org.teamvoided.voidlib.devin

import net.minecraft.data.worldgen.BootstrapContext

interface RegistryBootstrapper<T : Any> {

    fun bootstrap(context: BootstrapContext<T>) = context.init()

    fun BootstrapContext<T>.init()

}