package org.teamvoided.dusks_and_dungeons.mixin.evil;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.Util;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static org.teamvoided.dusks_and_dungeons.util.SecretHelperKt.dataFixerBlackList;

@Mixin(Util.class)
public class UtilMixin {

    @WrapWithCondition(method = "doFetchChoiceType", at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;)V"))
    private static boolean suppressDataFixer(Logger instance, String name, Object o){
        return dataFixerBlackList.contains(name);
    }

}