package org.teamvoided.voidlib.devin.provider.tag

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.BlockTagProvider
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block
import org.teamvoided.voidlib.devin.FabricOutput
import org.teamvoided.voidlib.devin.FutureProvider

abstract class DBlockTagProvider(o: FabricOutput, p: FutureProvider) : BlockTagProvider(o, p) {

    public override fun getOrCreateTagBuilder(tag: TagKey<Block>): FabricTagProvider<Block>.FabricTagBuilder {
        return super.getOrCreateTagBuilder(tag)
    }

}