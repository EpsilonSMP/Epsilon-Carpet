package world.epsilonsmp.EpsilonCarpet.mixins;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import world.epsilonsmp.EpsilonCarpet.EpsilonCarpetSettings;
import net.minecraft.block.entity.EndGatewayBlockEntity;
import net.minecraft.block.entity.EndPortalBlockEntity;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EndGatewayBlockEntity.class)
public abstract class EndGatewayBlockEntityMixin extends EndPortalBlockEntity {
    protected EndGatewayBlockEntityMixin(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Redirect(method="canTeleport", at=@At(value="INVOKE", target="Lnet/minecraft/entity/Entity;hasNetherPortalCooldown()Z"))
    private static boolean returnCannotTeleportIfNoCooldown(Entity entity) {
        return EpsilonCarpetSettings.endGatewayCooldown && entity.hasNetherPortalCooldown();
    }
}