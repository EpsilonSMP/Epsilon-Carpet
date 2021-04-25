package world.epsilonsmp.EpsilonCarpet.mixins;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.tag.Tag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import world.epsilonsmp.EpsilonCarpet.EpsilonCarpetSettings;

@Mixin(EndermanEntity.PickUpBlockGoal.class)
public abstract class EndermanAntiGriefExceptMelons {// Screw you melons
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;isIn(Lnet/minecraft/tag/Tag;)Z"))
    public boolean IsHoldableMelon(Block block, Tag<Block> tag) {
        return EpsilonCarpetSettings.antiEnderGriefExceptMelon ?
                block == Blocks.MELON
                : block.isIn(tag);
    }
}
