package world.epsilonsmp.EpsilonCarpet.mixins;

import world.epsilonsmp.EpsilonCarpet.EpsilonCarpetSettings;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShulkerBoxBlockEntity.class)
public class ShulkerBoxBlockEntityMixin {

    @Inject(method = "canInsert", at = @At("HEAD"), cancellable = true)
    public void canInsert(int slot, ItemStack stack, Direction dir, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(EpsilonCarpetSettings.shulkerInception || !(Block.getBlockFromItem(stack.getItem()) instanceof ShulkerBoxBlock));
    }

}
