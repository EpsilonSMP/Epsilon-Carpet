package world.epsilonsmp.EpsilonCarpet.mixins;

import world.epsilonsmp.EpsilonCarpet.EpsilonCarpetSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(FishEntity.class)
public class FishEntityMixin {

    @Inject(method = "canSpawn", at = @At("HEAD"), cancellable = true)
    private static void canSpawn(EntityType<? extends FishEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random, CallbackInfoReturnable<Boolean> cir) {
        if (EpsilonCarpetSettings.seaLevelFishes && pos.getY() > 45 && pos.getY() < world.getSeaLevel())
            cir.setReturnValue(false);
    }
}
