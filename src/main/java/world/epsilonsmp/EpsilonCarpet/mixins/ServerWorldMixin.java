package world.epsilonsmp.EpsilonCarpet.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.server.world.ServerChunkManager;
import org.spongepowered.asm.mixin.injection.Redirect;
import world.epsilonsmp.EpsilonCarpet.EpsilonCarpetSettings;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.MutableWorldProperties;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin extends World {

    protected ServerWorldMixin(MutableWorldProperties properties, RegistryKey<World> registryRef, DimensionType dimensionType, Supplier<Profiler> profiler, boolean isClient, boolean debugWorld, long seed) {
        super(properties, registryRef, dimensionType, profiler, isClient, debugWorld, seed);
    }

    @Inject(method="createEndSpawnPlatform", at = @At("HEAD"), cancellable = true)
    private static void cancelEndPlatformCreate(ServerWorld world, CallbackInfo ci) {
        if (EpsilonCarpetSettings.noObsidianPlatform)
            ci.cancel();
    }

    //forceLoadEnderPearls
    @Redirect(
            method = "checkEntityChunkPos",
            at = @At(
                    value = "INVOKE",
                    target = "net/minecraft/entity/Entity.teleportRequested()Z"
            )
    )
    private boolean conditionAdder(Entity entity) {
        return entity.teleportRequested() || (EpsilonCarpetSettings.forceLoadEnderPearls && (entity instanceof EnderPearlEntity));
    }

    @Redirect(
            method = "tickEntity",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/world/ServerChunkManager;shouldTickEntity(Lnet/minecraft/entity/Entity;)Z"
            )
    )
    public boolean onShouldTickEntity(ServerChunkManager serverChunkManager, Entity entity) {
        return (EpsilonCarpetSettings.forceLoadEnderPearls && (entity instanceof EnderPearlEntity)) ||
                serverChunkManager.shouldTickEntity(entity);
    }
}
