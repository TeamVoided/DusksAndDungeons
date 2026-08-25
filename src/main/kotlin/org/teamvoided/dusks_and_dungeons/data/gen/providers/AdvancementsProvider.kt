package org.teamvoided.dusks_and_dungeons.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.advancements.AdvancementRewards
import net.minecraft.advancements.AdvancementType
import net.minecraft.advancements.critereon.EntityPredicate
import net.minecraft.advancements.critereon.EntitySubPredicates
import net.minecraft.advancements.critereon.TameAnimalTrigger
import net.minecraft.core.HolderLookup
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.advancements.packs.VanillaAdventureAdvancements
import net.minecraft.resources.ResourceKey
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.mc
import org.teamvoided.dusks_and_dungeons.data.registry.DnDAdvancements
import org.teamvoided.dusks_and_dungeons.data.registry.DnDAdvancements.description
import org.teamvoided.dusks_and_dungeons.data.registry.DnDAdvancements.title
import org.teamvoided.dusks_and_dungeons.data.variants.DnDWolfVariants
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.worldgen.DnDBiomes
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class AdvancementsProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricAdvancementProvider(o, r) {

    val adventuringTime = vanillaAdv("adventure/adventuring_time")
//    val adventure = vanillaAdv("adventure/root")
    val theWholePack = vanillaAdv("husbandry/whole_pack")

    val autumnBiomes = listOf( //move this to a list file and use for the IS_AUTUMN tag?
        DnDBiomes.AUTUMN_WOODS,
        DnDBiomes.AUTUMN_PASTURES,
        DnDBiomes.AUTUMN_CASCADES,
        DnDBiomes.GOLDEN_WOODS,
        DnDBiomes.GOLDEN_PASTURES,
    )

    override fun generateAdvancement(provider: HolderLookup.Provider, gen: Consumer<AdvancementHolder>) {

        /*  val bigItems = arrayOf<ItemPredicate.Builder>(
              ItemPredicate.Builder.create().items(DnDBlocks.BIG_CHAIN),
              ItemPredicate.Builder.create().items(DnDBlocks.BIG_LANTERN),
              ItemPredicate.Builder.create().items(DnDBlocks.BIG_SOUL_LANTERN),
          )
          DnDBlockLists.bigCandles.forEach {
              bigItems + (ItemPredicate.Builder.create().items(it.first))
          }
          DnDBlockLists.bigSoulCandles.forEach {
              bigItems + (ItemPredicate.Builder.create().items(it.first))
          }

          Advancement.Builder.create().parent(adventure).display(
              DnDBlocks.BIG_CANDLE,
              Text.of("NOW$ YOURE CH4NCE TO B3 A [[BIG]]!!"),
              Text.of("Obtain all of the Big items"),
              null,
              AdvancementType.CHALLENGE,
              true,
              true,
              true
          ).putCriteria(
              "get_big", InventoryChangedCriterionTrigger.Conditions.create(
                  arrayOf<ItemPredicate.Builder>(
                      ItemPredicate.Builder.create().items(DnDBlocks.BIG_CHAIN.asItem()),
                      ItemPredicate.Builder.create().items(DnDBlocks.BIG_LANTERN.asItem()),
                      ItemPredicate.Builder.create().items(DnDBlocks.BIG_SOUL_LANTERN.asItem()),
                  )
              )
          ).build(c, "story/mine_stone")*/

        VanillaAdventureAdvancements
            .addBiomes(Advancement.Builder.advancement(), provider, autumnBiomes)
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
    }

    fun expReward(amount: Int): AdvancementRewards.Builder = AdvancementRewards.Builder.experience(amount)

    @Suppress("TYPE_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun vanillaAdv(id: String): AdvancementHolder = AdvancementHolder(mc(id), null)

    fun Advancement.Builder.save(gen: Consumer<AdvancementHolder>, key: ResourceKey<Advancement>) {
        this.save(gen, key.location().toString())
    }

}