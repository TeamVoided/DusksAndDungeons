package org.teamvoided.dusks_and_dungeons.mixin.signs;

import net.minecraft.client.gui.screens.inventory.AbstractSignEditScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teamvoided.dusks_and_dungeons.block.DnDWoodTypes;
import org.teamvoided.dusks_and_dungeons.util.SignColorProvider;

import static org.teamvoided.dusks_and_dungeons.util.ClientUtilsKt.getColor;

@Mixin(AbstractSignEditScreen.class)
public abstract class AbstractSignEditScreenMixin implements SignColorProvider {

    @Shadow
    @Final
    protected WoodType woodType;
    @Unique
    public @Nullable Integer dnd$signColor = null;

    @Inject(method = "<init>(Lnet/minecraft/world/level/block/entity/SignBlockEntity;ZZLnet/minecraft/network/chat/Component;)V", at = @At("RETURN"))
    void getSignColor(SignBlockEntity sign, boolean bl, boolean bl2, Component component, CallbackInfo ci) {
        if (this.woodType == DnDWoodTypes.VERDANT_WOOD_TYPE) {
            dnd$signColor = getColor(sign);
        }
    }

    @Override
    public @Nullable Integer dnd_getSignColor() {
        return dnd$signColor;
    }

}