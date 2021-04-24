package xyz.epsilonsmp.EpsilonCarpet.mixins;

import xyz.epsilonsmp.EpsilonCarpet.EpsilonCarpetSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {

    @Inject(method = "neighborUpdate", at = @At("HEAD"))
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify, CallbackInfo ci) {
        if (state.isOf(Blocks.EMERALD_ORE) && EpsilonCarpetSettings.oreUpdateSuppressor)
            throw new StackOverflowError("Epsilon-Carpet oreUpdateSuppressor");
    }

}
