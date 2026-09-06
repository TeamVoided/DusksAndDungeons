package org.teamvoided.dusks_and_dungeons.datagen.data

import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.advancements.AdvancementRewards
import net.minecraft.advancements.AdvancementType
import net.minecraft.advancements.critereon.*
import net.minecraft.core.HolderLookup
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.biome.Biome
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.mc
import org.teamvoided.dusks_and_dungeons.data.DnDAdvancements
import org.teamvoided.dusks_and_dungeons.data.DnDAdvancements.description
import org.teamvoided.dusks_and_dungeons.data.DnDAdvancements.title
import org.teamvoided.dusks_and_dungeons.data.registry.DnDWolfVariants
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDBiomes
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.voidlib.devin.FabricOutput
import org.teamvoided.voidlib.devin.FutureProvider
import java.util.function.Consumer

class AdvancementsProvider(o: FabricOutput, p: FutureProvider) : FabricAdvancementProvider(o, p) {

    val adventuringTime = vanillaAdv("adventure/adventuring_time")
    val adventure = vanillaAdv("adventure/root")
    val theWholePack = vanillaAdv("husbandry/whole_pack")

    val autumnBiomes = listOf( //move this to a list file and use for the IS_AUTUMN tag?
        DnDBiomes.AUTUMN_WOODS,
        DnDBiomes.AUTUMN_PASTURES,
        DnDBiomes.AUTUMN_CASCADES,
        DnDBiomes.GOLDEN_WOODS,
        DnDBiomes.GOLDEN_PASTURES,
    )

    override fun generateAdvancement(provider: HolderLookup.Provider, gen: Consumer<AdvancementHolder>) {
        Advancement.Builder.advancement()
            .addBiomes(provider, autumnBiomes)
            .display(
                DnDBlocks.CASCADE_SAPLING,
                title(DnDAdvancements.FALL), description(DnDAdvancements.FALL),
                null, AdvancementType.GOAL, true, true, false
            )
            .rewards(expReward(50))
            .parent(adventuringTime)
            .save(gen, DnDAdvancements.FALL)

        Advancement.Builder.advancement()
            .addCriterion(
                DnDWolfVariants.AUTUMN.location().toString(),
                TameAnimalTrigger.TriggerInstance.tamedAnimal(
                    EntityPredicate.Builder.entity().subPredicate(
                        EntitySubPredicates.wolfVariant(
                            HolderSet.direct(
                                provider.lookupOrThrow(Registries.WOLF_VARIANT).getOrThrow(DnDWolfVariants.AUTUMN)
                            )
                        )
                    )
                )
            )
            .display(
                DnDBlocks.CASCADE_LOG,
                title(DnDAdvancements.WOOF), description(DnDAdvancements.WOOF),
                null, AdvancementType.GOAL, true, true, false
            )
            .rewards(expReward(5))
            .parent(theWholePack)
            .save(gen, DnDAdvancements.WOOF)

        Advancement.Builder.advancement()
            .addCollectItems(
                DnDBlocks.BIG_CANDLES + DnDBlocks.BIG_SOUL_CANDLES + listOf(
                    DnDBlocks.BIG_CHAIN,
                    DnDBlocks.BIG_LANTERN,
                    DnDBlocks.BIG_SOUL_LANTERN,
                    DnDBlocks.BIG_SCAFFOLDING
                )
            )
            .display(
                DnDBlocks.BIG_CANDLES.uncolored,
                title(DnDAdvancements.BIG_BLOCKS), description(DnDAdvancements.BIG_BLOCKS),
                null, AdvancementType.CHALLENGE, true, true, false
            )
            .rewards(expReward(3))
            .parent(adventure)
            .save(gen, DnDAdvancements.BIG_BLOCKS)
    }

    // Once there are more than 5 functions here. Move them to a helper file to keep this file clean.
    fun expReward(amount: Int): AdvancementRewards.Builder = AdvancementRewards.Builder.experience(amount)

    @Suppress("TYPE_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun vanillaAdv(id: String): AdvancementHolder = AdvancementHolder(mc(id), null)

    fun Advancement.Builder.save(gen: Consumer<AdvancementHolder>, key: ResourceKey<Advancement>) {
        this.save(gen, key.location().toString())
    }

    fun Advancement.Builder.addBiomes(
        provider: HolderLookup.Provider, list: List<ResourceKey<Biome>>,
    ): Advancement.Builder {
        val lookup = provider.lookupOrThrow(Registries.BIOME)
        for (resourceKey in list) {
            addCriterion(
                resourceKey.location().toString(),
                PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(lookup.getOrThrow(resourceKey)))
            )
        }
        return this
    }

    fun Advancement.Builder.addCollectItems(items: List<ItemLike>): Advancement.Builder {
        for (holder in items.map { it.asItem().builtInRegistryHolder() }) {
            addCriterion(
                "has_" + holder.key().location().toString().replace(":", "_"),
                InventoryChangeTrigger.TriggerInstance.hasItems(holder.value())
            )
        }
        return this
    }

}