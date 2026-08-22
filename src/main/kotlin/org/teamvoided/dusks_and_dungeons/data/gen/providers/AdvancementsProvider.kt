package org.teamvoided.dusks_and_dungeons.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.advancements.AdvancementRewards
import net.minecraft.advancements.AdvancementType
import net.minecraft.advancements.critereon.TameAnimalTrigger
import net.minecraft.data.advancements.packs.VanillaAdventureAdvancements
import net.minecraft.advancements.critereon.EntityPredicate
import net.minecraft.advancements.critereon.EntitySubPredicates
import net.minecraft.core.HolderLookup
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.mc
import org.teamvoided.dusks_and_dungeons.data.variants.DnDWolfVariants
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.worldgen.DnDBiomes
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class AdvancementsProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricAdvancementProvider(o, r) {
    private val adventuringTime = AdvancementHolder(mc("adventure/adventuring_time"), null)
    private val adventure = AdvancementHolder(mc("adventure/root"), null)
    private val autumnBiomes = listOf(
        DnDBiomes.AUTUMN_WOODS,
        DnDBiomes.AUTUMN_PASTURES,
        DnDBiomes.AUTUMN_CASCADES,
        DnDBiomes.GOLDEN_WOODS,
        DnDBiomes.GOLDEN_PASTURES,
    )
    private val theWholePack = AdvancementHolder(mc("husbandry/whole_pack"), null)
    override fun generateAdvancement(provider: HolderLookup.Provider, c: Consumer<AdvancementHolder>?) {

//        val bigItems = arrayOf<ItemPredicate.Builder>(
//            ItemPredicate.Builder.create().items(DnDBlocks.BIG_CHAIN),
//            ItemPredicate.Builder.create().items(DnDBlocks.BIG_LANTERN),
//            ItemPredicate.Builder.create().items(DnDBlocks.BIG_SOUL_LANTERN),
//        )
//        DnDBlockLists.bigCandles.forEach {
//            bigItems + (ItemPredicate.Builder.create().items(it.first))
//        }
//        DnDBlockLists.bigSoulCandles.forEach {
//            bigItems + (ItemPredicate.Builder.create().items(it.first))
//        }
//
//        Advancement.Builder.create().parent(adventure).display(
//            DnDBlocks.BIG_CANDLE,
//            Text.of("NOW$ YOURE CH4NCE TO B3 A [[BIG]]!!"),
//            Text.of("Obtain all of the Big items"),
//            null,
//            AdvancementType.CHALLENGE,
//            true,
//            true,
//            true
//        ).putCriteria(
//            "get_big", InventoryChangedCriterionTrigger.Conditions.create(
//                arrayOf<ItemPredicate.Builder>(
//                    ItemPredicate.Builder.create().items(DnDBlocks.BIG_CHAIN.asItem()),
//                    ItemPredicate.Builder.create().items(DnDBlocks.BIG_LANTERN.asItem()),
//                    ItemPredicate.Builder.create().items(DnDBlocks.BIG_SOUL_LANTERN.asItem()),
//                )
//            )
//        ).build(c, "story/mine_stone")


        VanillaAdventureAdvancements.addBiomes(
            Advancement.Builder.advancement(),
            provider,
            autumnBiomes
        ).display(
            DnDBlocks.CASCADE_SAPLING,
            Component.nullToEmpty("Fall!"),
            Component.nullToEmpty("Visit the golden and autumn biomes!"),
            null,
            AdvancementType.GOAL,
            true,
            true,
            false
        ).rewards(AdvancementRewards.Builder.experience(50)).parent(adventuringTime)
            .save(c, id("adventure/fall").toString())

        Advancement.Builder.advancement()
            .addCriterion(
                DnDWolfVariants.AUTUMN.toString(), TameAnimalTrigger.TriggerInstance.tamedAnimal(
                    EntityPredicate.Builder.entity().subPredicate(
                        EntitySubPredicates.wolfVariant(
                            HolderSet.direct(
                                provider.lookupOrThrow(Registries.WOLF_VARIANT)
                                    .getOrThrow(DnDWolfVariants.AUTUMN)
                            )
                        )
                    )
                )
            ).display(
                DnDBlocks.CASCADE_LOG,
                Component.nullToEmpty("Woof"),
                Component.nullToEmpty("Find and tame the Autumn Wolf"),
                null,
                AdvancementType.GOAL,
                true,
                true,
                false
            ).rewards(AdvancementRewards.Builder.experience(5)).parent(theWholePack)
            .save(c, id("husbandry/woof").toString())
    }

}
