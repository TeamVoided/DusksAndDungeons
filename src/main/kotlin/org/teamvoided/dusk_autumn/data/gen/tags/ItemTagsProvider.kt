package org.teamvoided.dusk_autumn.data.gen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.item.Items
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.tag.ItemTags
import org.teamvoided.dusk_autumn.data.tags.DuskBlockTags
import org.teamvoided.dusk_autumn.data.tags.DuskItemTags
import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.init.DuskItems
import java.util.concurrent.CompletableFuture

class ItemTagsProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<HolderLookup.Provider>,
    blockTags: BlockTagsProvider
) :
    FabricTagProvider.ItemTagProvider(output, registriesFuture, blockTags) {
    override fun configure(arg: HolderLookup.Provider) {
        duskTags()
        vanillaTags()
        conventionTags()
    }

    fun duskTags() {
        copy(DuskBlockTags.LEAF_PILES, DuskItemTags.LEAF_PILES)
        getOrCreateTagBuilder(DuskItemTags.NETHER_BRICKS)
            .add(Items.NETHER_BRICKS)
            .add(Items.RED_NETHER_BRICKS)
    }

    fun vanillaTags() {
        getOrCreateTagBuilder(ItemTags.DYEABLE)
            .add(DuskItems.FARMERS_HAT)
    }

    fun conventionTags() {}
}