package world.epsilonsmp.EpsilonCarpet.mixins;

import world.epsilonsmp.EpsilonCarpet.EpsilonCarpetSettings;
import net.minecraft.entity.Entity;
import net.minecraft.server.PlayerManager;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

import java.util.Arrays;
import java.util.stream.Stream;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin{
    @Redirect(method="remove", at = @At(value="INVOKE", target = "Lnet/minecraft/entity/Entity;streamPassengersAndSelf()Ljava/util/stream/Stream;"))
    private Stream<Entity> replaceRemove(Entity entity) {
        if (EpsilonCarpetSettings.llamaDupeExploit) {
            return Arrays.stream(new Entity[]{});
        } else {
            return entity.streamPassengersAndSelf();
        }
    }
}