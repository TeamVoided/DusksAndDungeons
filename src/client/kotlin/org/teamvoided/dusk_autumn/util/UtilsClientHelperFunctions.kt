package org.teamvoided.dusk_autumn.util

import net.minecraft.client.MinecraftClient
import net.minecraft.text.Text
import org.teamvoided.dusk_autumn.DusksAndDungeons.isDev

fun sendMessageIngame(message: String) {
    if (isDev())
        MinecraftClient.getInstance().player?.sendMessage(Text.literal(message), true)
    else
        println("this message: $message; has been brought to you by:")
}