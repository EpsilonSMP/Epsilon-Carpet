package world.epsilonsmp.EpsilonCarpet.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.world.SpawnHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import world.epsilonsmp.EpsilonCarpet.EpsilonCarpetSettings;
import world.epsilonsmp.EpsilonCarpet.utils.ChunkManagerHelper;

@Mixin(ServerChunkManager.class)
public abstract class ServerChunkManagerMixin
{
    @Redirect(
            method = "tickChunks",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/SpawnHelper;setupSpawn(ILjava/lang/Iterable;Lnet/minecraft/world/SpawnHelper$ChunkSource;)Lnet/minecraft/world/SpawnHelper$Info;"
            )
    )
    public SpawnHelper.Info infoSetter(int spawningChunkCount, Iterable<Entity> entities,
                                       SpawnHelper.ChunkSource chunkSource) {
        SpawnHelper.Info info = SpawnHelper.setupSpawn(spawningChunkCount, entities, chunkSource);
        if (EpsilonCarpetSettings.phantomsCapped) {
            ChunkManagerHelper.putInfo(info);
            ChunkManagerHelper.putSpawningChunkCount(spawningChunkCount);
        }
        return info;
    }
}
