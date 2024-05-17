package net.minecraft.entity.passive.org.teamvoided.dusk_autumn.datagen.tags
//
//import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
//import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
//import net.minecraft.block.Blocks
//import net.minecraft.registry.HolderLookup
//import net.minecraft.registry.tag.BiomeTags
//import net.minecraft.registry.tag.BlockTags
//import net.minecraft.registry.tag.EntityTypeTags
//import org.teamvoided.dusk_autumn.data.DuskBlockTags
//import org.teamvoided.dusk_autumn.init.DuskBlocks
//import java.util.concurrent.CompletableFuture
//
//class EntityTypeTagsProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
//    FabricTagProvider<EntityTypeTags>(output, registriesFuture) {
//    override fun configure(arg: HolderLookup.Provider) {
//        getOrCreateTagBuilder(EntityTypeTags.AQUATIC)
//        getOrCreateTagBuilder(EntityTypeTags.ARTHROPOD)
//    }
//}