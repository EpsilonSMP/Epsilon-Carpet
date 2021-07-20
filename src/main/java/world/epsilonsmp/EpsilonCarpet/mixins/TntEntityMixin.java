package world.epsilonsmp.EpsilonCarpet.mixins;

import carpet.fakes.TntEntityInterface;
import world.epsilonsmp.EpsilonCarpet.EpsilonCarpetSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TntEntity.class)
public abstract class TntEntityMixin extends Entity implements TntEntityInterface {

    @Shadow public abstract int getFuse();

    private int mergedTNT = 1;

    public TntEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = {"tick"}, at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        if (EpsilonCarpetSettings.ftlTNT) {
            for (Entity entity : this.world.getOtherEntities(this, this.getBoundingBox())) {
                if (entity instanceof TntEntity)
                    if (entity.getVelocity().equals(this.getVelocity()) && entity.getPos().equals(this.getPos()) && ((TntEntity) entity).getFuse() == getFuse()) {
                        mergedTNT += ((TntEntityInterface) entity).getMergedTNT();
                        entity.remove(RemovalReason.DISCARDED);
                    }
            }
        }
    }

    @Override
    public int getMergedTNT() {
        return this.mergedTNT;
    }
}
