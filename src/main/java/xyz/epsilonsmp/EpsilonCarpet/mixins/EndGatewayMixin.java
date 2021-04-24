package xyz.epsilonsmp.EpsilonCarpet.mixins;

import xyz.epsilonsmp.EpsilonCarpet.EpsilonCarpetSettings;
import net.minecraft.block.entity.EndGatewayBlockEntity;
import net.minecraft.block.entity.EndPortalBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndGatewayBlockEntity.class)
public abstract class EndGatewayMixin extends EndPortalBlockEntity {
    @Inject(method="needsCooldownBeforeTeleporting", at=@At("RETURN"), cancellable = true)
    private void suppressCooldown(CallbackInfoReturnable<Boolean> cir) {
        if (!EpsilonCarpetSettings.endGatewayCooldown) {
            cir.setReturnValue(false);// Returns target method early
        }
    }
}