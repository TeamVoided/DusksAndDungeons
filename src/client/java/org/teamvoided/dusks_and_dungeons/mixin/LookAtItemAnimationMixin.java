package org.teamvoided.dusks_and_dungeons.mixin;

import dev.tr7zw.notenoughanimations.animations.hands.LookAtItemAnimation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.teamvoided.dusks_and_dungeons.block.CandelabraBlock;

import java.util.Set;


@Pseudo
@Mixin(value = LookAtItemAnimation.class)
public class LookAtItemAnimationMixin {

    @Redirect(method = "isValid", at = @At(value = "INVOKE", target = "Ljava/util/Set;contains(Ljava/lang/Object;)Z"), remap = false)
    boolean addDnDItems(Set<Item> instance, Object item) {
        return instance.contains((Item) item) ||
                (item instanceof BlockItem block && block.getBlock() instanceof CandelabraBlock);
    }
}
