package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.phys.BlockHitResult
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.isDev
import org.teamvoided.dusks_and_dungeons.block.meltable.Meltable

object DnDDebug {

    fun init() {
        if (!isDev()) return

        CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
            /*   val test = literal("end").executes { scc ->
                   val src = scc.source
   //                val z = src.world.getLootTable(LootTables.PIGLIN_BARTERING_GAMEPLAY)
                   val lookup = src.level.registryAccess()
                   val tableLookup = lookup.lookupOrThrow(Registries.LOOT_TABLE)
                   val table = tableLookup.getOrThrow(BuiltInLootTables.PIGLIN_BARTERING_GAMEPLAY)

                   val registryOps = lookup.createSerializationContext(NbtOps.INSTANCE)
                   LootTable.field_50021.encodeStart(registryOps, table.value())
                       .ifError(::println)
                       .ifSuccess(::println)

                   0
               }.build()
               dispatcher.root.addChild(test)*/
        }

        UseBlockCallback.EVENT.register { player, level, hand, result ->
            useEvent(player, level, hand, result)
            InteractionResult.PASS
        }
    }

    fun useEvent(player: Player, level: Level, hand: InteractionHand, result: BlockHitResult) {
        val stack = player.getItemInHand(hand)
        val offhandStack = player.offhandItem
        if (stack.isEmpty && offhandStack.`is`(Items.PRISMARINE_CRYSTALS) && level.isClientSide) {
            val dir = result.direction
            val pos = result.blockPos
            val state = level.getBlockState(pos)

            val shape = Meltable.getShape(state, dir)
            player.msg(buildString {
                append("Shape: ")
                append(shape)
                append(", Dir: ")
                append(dir)
            })

        }
    }

    fun Player.msg(message: String) = displayClientMessage(Component.literal(message), true)

}