package net.yukulab.horizonlimit.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class MixinServerPlayerEntity {
    @Unique
    private int horizonlimit$overTimeTick = -1;

    @Inject(method = "tick", at = @At("RETURN"))
    private void checkYHeight(CallbackInfo ci) {
        var player = (ServerPlayerEntity) (Object) this;
        var y = player.getY();
        // TODO check player over limit

        // TODO send countdown
    }
}
