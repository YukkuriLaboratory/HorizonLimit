package net.yukulab.horizonlimit.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import net.yukulab.horizonlimit.damage.HLDamageTypes;
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
        var worldName = world.getDimension().effects().toString();
        var users = config.limit().get(worldName);
        if (users == null) return;
        var limit = users.get(player.getUuid());
        if (limit == null) return;
        var limitTick = config.timeLimit();
        var isSkySide = limit.isSkySide();
        var limitHeight = limit.limit();
        if ((isSkySide && y < limitHeight) || (!isSkySide && y > limitHeight)) {
            if (horizonlimit$currentOverTimeTick == -1) {
                horizonlimit$currentOverTimeTick = limitTick;
            }
            if (horizonlimit$currentOverTimeTick > 0) {
                horizonlimit$currentOverTimeTick--;
            } else {
                var source = HLDamageTypes.of(player.getWorld(), HLDamageTypes.HORIZONTAL_LIMIT);
                player.damage(source, player.getHealth() + 1);
            }
        } else {
            horizonlimit$currentOverTimeTick = -1;
        }
        UpdateCountdownS2CPacket.send(player, horizonlimit$currentOverTimeTick);
    }
}
