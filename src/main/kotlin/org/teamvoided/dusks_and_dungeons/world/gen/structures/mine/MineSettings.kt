package org.teamvoided.dusks_and_dungeons.world.gen.structures.mine

import com.mojang.serialization.Codec
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import java.util.function.IntFunction

enum class MineSettings(val id: String, wood: Block, planks: Block, fence: Block, chain: Block) : StringRepresentable {
    VERDANT("verdant", DnDBlocks.VERDANT_LOG, DnDBlocks.VERDANT_PLANKS, DnDBlocks.VERDANT_FENCE, Blocks.CHAIN),
    UNUSED("unused", Blocks.OAK_LOG, Blocks.BEDROCK, Blocks.OAK_FENCE, Blocks.CHAIN);

    val woodState: BlockState = wood.defaultBlockState()
    val planksState: BlockState = planks.defaultBlockState()
    val fenceState: BlockState = fence.defaultBlockState()
    val chainState: BlockState = chain.defaultBlockState()

    override fun getSerializedName(): String = id

    fun isStructure(state: BlockState): Boolean {
        return state.`is`(woodState.block)
                || state.`is`(planksState.block)
                || state.`is`(fenceState.block)
                || state.`is`(chainState.block)
    }

    companion object {

        @JvmField
        val CODEC: Codec<MineSettings> = StringRepresentable.fromEnum { entries.toTypedArray() }
        private val BY_ID: IntFunction<MineSettings> =
            ByIdMap.continuous(MineSettings::ordinal, entries.toTypedArray(), ByIdMap.OutOfBoundsStrategy.ZERO)

        fun byId(i: Int): MineSettings = BY_ID.apply(i)

    }
}