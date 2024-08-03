package org.teamvoided.dusk_autumn.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider
import net.minecraft.advancement.Advancement
import net.minecraft.advancement.AdvancementHolder
import net.minecraft.advancement.AdvancementRewards
import net.minecraft.advancement.AdvancementType
import net.minecraft.advancement.criterion.TameAnimalCriterionTrigger
import net.minecraft.data.server.advancement.AdventureAdvancementTabGenerator
import net.minecraft.predicate.entity.EntityPredicate
import net.minecraft.predicate.entity.EntitySubPredicateTypes
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.HolderSet
import net.minecraft.registry.RegistryKeys
import net.minecraft.text.Text
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.DuskAutumns.mc
import org.teamvoided.dusk_autumn.data.DnDWolfVariants
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.worldgen.DnDBiomes
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
//        DnDBiomes.AUTUMN_WETLANDS,
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


        AdventureAdvancementTabGenerator.appendEnterAllBiomesCriterion(
            Advancement.Builder.create(),
            provider,
            autumnBiomes
        ).display(
            DnDBlocks.CASCADE_SAPLING,
            Text.of("Fall!"),
            Text.of("Visit the autumn biomes!"),
            null,
            AdvancementType.CHALLENGE,
            true,
            true,
            false
        ).rewards(AdvancementRewards.Builder.experience(50)).parent(adventuringTime)
            .build(c, id("adventure/fall").toString())
        Advancement.Builder.create()
            .putCriteria(
                DnDWolfVariants.AUTUMN.toString(), TameAnimalCriterionTrigger.Conditions.create(
                    EntityPredicate.Builder.create().typeSpecific(
                        EntitySubPredicateTypes.method_59667(
                            HolderSet.createDirect(
                                provider.getLookupOrThrow(RegistryKeys.WOLF_VARIANT)
                                    .getHolderOrThrow(DnDWolfVariants.AUTUMN)
                            )
                        )
                    )
                )
            ).display(
                DnDBlocks.CASCADE_LOG,
                Text.of("Woof"),
                Text.of("Find the Autumn Wolf"),
                null,
                AdvancementType.CHALLENGE,
                true,
                true,
                false
            ).rewards(AdvancementRewards.Builder.experience(5)).parent(theWholePack)
            .build(c, id("husbandry/woof").toString())
    }

}
