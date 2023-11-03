package net.yukulab.horizonlimit.mixin;

import java.util.HashMap;
import net.minecraft.server.network.ServerPlayerEntity;
import net.yukulab.horizonlimit.HorizonLimitDamageSource;
import net.yukulab.horizonlimit.network.packet.play.UpdateCountdownS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class MixinServerPlayerEntity {
    @Unique
    private int horizonlimit$currentOverTimeTick = -1;

    @Inject(method = "tick", at = @At("RETURN"))
    private void checkYHeight(CallbackInfo ci) {
        var player = (ServerPlayerEntity) (Object) this;
        var world = player.getServerWorld();
        var y = player.getY();
        // これがnullになることはない
        var config = player.getServer().horizonlimit$getServerConfig();
        var worldName = world.getDimensionKey().getRegistry().getNamespace();
        var playerMap = config.limit()
                .computeIfAbsent(worldName, (key) -> new HashMap<>());
        var limit = playerMap.get(player.getUuid());
        if (limit == null) {
            return;
        }
        var limitTick = config.timeLimit();
        var isSkySide = limit.isSkySide();
        var limitHeight = limit.limit();
        if (isSkySide && y > limitHeight || y < limitHeight) {
            if (horizonlimit$currentOverTimeTick == -1) {
                horizonlimit$currentOverTimeTick = limitTick;
            }
            if (horizonlimit$currentOverTimeTick > 0) {
                horizonlimit$currentOverTimeTick--;
            } else {
                var source = new HorizonLimitDamageSource();
                player.damage(source, player.getHealth() + 1);
            }
        } else {
            horizonlimit$currentOverTimeTick = -1;
        }
        UpdateCountdownS2CPacket.send(player, horizonlimit$currentOverTimeTick);
    }
}
