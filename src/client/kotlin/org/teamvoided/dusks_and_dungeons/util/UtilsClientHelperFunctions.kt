package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.client.Minecraft
import net.minecraft.network.chat.Component
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.MODID
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.isDev

fun sendMessageIngame(message: String) {
    if (isDev()) Minecraft.getInstance().player?.displayClientMessage(Component.literal(message), true)
    else println("this message: $message; has been brought to you by:$MODID")
}