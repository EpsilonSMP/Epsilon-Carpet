package world.epsilonsmp.EpsilonCarpet.mixins;

import world.epsilonsmp.EpsilonCarpet.EpsilonCarpetSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.server.world.ServerChunkManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// keepProjectilesTicked code was adapted from https://github.com/whoImT/carpet-addons

@Mixin(ServerChunkManager.class)
public class ServerChunkManagerMixin {
    @Inject(method = "shouldTickEntity", at = @At(value = "HEAD"), cancellable =  true)
    private void shouldTickEntity(Entity entity, CallbackInfoReturnable<Boolean> cir){
        if ((EpsilonCarpetSettings.keepProjectilesTicked.equals("all") && entity instanceof ProjectileEntity) ||
                (EpsilonCarpetSettings.keepProjectilesTicked.equals("player-only") && entity instanceof ProjectileEntity && ((ProjectileEntity) entity).getOwner() instanceof PlayerEntity) ||
                (EpsilonCarpetSettings.keepProjectilesTicked.equals("enderpearls") && entity instanceof EnderPearlEntity)
        )
            cir.setReturnValue(true);
    }
}
