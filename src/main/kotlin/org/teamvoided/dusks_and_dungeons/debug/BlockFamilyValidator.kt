package org.teamvoided.dusks_and_dungeons.debug

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.BlockFamilies
import net.minecraft.data.BlockFamily
import net.minecraft.data.BlockFamily.Variant
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.HangingSignItem
import net.minecraft.world.level.block.*
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.log
import org.teamvoided.dusks_and_dungeons.block.CarpetPlateBlock
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.util.isDev

@Suppress("SimplifyBooleanWithConstants", "KotlinConstantConditions")
object BlockFamilyValidator {

    val ignoreList = listOf<Block>(
        Blocks.IRON_DOOR,
        Blocks.IRON_TRAPDOOR,
        Blocks.PETRIFIED_OAK_SLAB,

        DnDBlocks.BLUE_DOOR,
    )

    // stop the game yelling about vanilla copper doors and trapdoors
    val copperPredicate = { block: Block ->
        var id = block.getId()
        id.namespace == ResourceLocation.DEFAULT_NAMESPACE && id.path.contains("copper_")
    }

    val tempIgnoreList = listOf<Block>()

    var preventGameBoot = false && isDev()

    fun init() {
        if (tempIgnoreList.isNotEmpty()) {
            log.info("(BFV) There are (${tempIgnoreList.size}) entries in the temporary ignore list")
        }


        val allFamilies = BlockFamilies.getAllFamilies().toList().toMutableSet()
        val knowFamilies = mutableListOf<BlockFamily>()
        val missingFamilies = mutableListOf<Block>()

        blocks@ for (block in BuiltInRegistries.BLOCK.filterNot(::filterBlocks)) {
            val (variant, altVariant) = getVariant(block) ?: continue

            for (family in knowFamilies) {
                if (family.get(variant) == block) {
                    continue@blocks
                }
                if (altVariant != null && family.get(altVariant) == block) {
                    continue@blocks
                }
            }

            for (family in allFamilies.toList()) {
                if (family.get(variant) == block) {
                    knowFamilies.add(family)
                    allFamilies.remove(family)
                    continue@blocks
                }
                if (altVariant != null && family.get(altVariant) == block) {
                    knowFamilies.add(family)
                    allFamilies.remove(family)
                    continue@blocks
                }
            }
            missingFamilies.add(block)
        }
        println(knowFamilies.map { it.baseBlock.getId() })
        if (missingFamilies.isNotEmpty()) {
            log.info("Missing Families for Blocks: ")
            for (block in missingFamilies) {
                log.info(" - ${block.getId()}")
            }
            if (preventGameBoot) throw IllegalStateException("Missing Families!")
        }
    }

    @Suppress("DEPRECATION")
    fun Block.getId(): ResourceLocation = builtInRegistryHolder().unwrapKey().get().location()

    fun filterBlocks(block: Block): Boolean {
        return ignoreList.contains(block) || copperPredicate(block) || tempIgnoreList.contains(block)
    }

    private fun getVariant(block: Block): Pair<Variant, Variant?>? = when (block) {
        is ButtonBlock -> Variant.BUTTON to null
        is DoorBlock -> Variant.DOOR to null
        is FenceBlock -> Variant.FENCE to Variant.CUSTOM_FENCE
        is FenceGateBlock -> Variant.FENCE_GATE to Variant.CUSTOM_FENCE_GATE
        is SignBlock -> if (block.asItem() !is HangingSignItem) Variant.SIGN to Variant.WALL_SIGN else null
        is SlabBlock -> Variant.SLAB to null
        is StairBlock -> Variant.STAIRS to null
        is PressurePlateBlock -> if (block !is CarpetPlateBlock) Variant.PRESSURE_PLATE to null else null
        is TrapDoorBlock -> Variant.TRAPDOOR to null
        is WallBlock -> Variant.WALL to null
        else -> null
    }

}