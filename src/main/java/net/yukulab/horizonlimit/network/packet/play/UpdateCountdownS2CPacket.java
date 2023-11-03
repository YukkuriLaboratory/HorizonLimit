package net.yukulab.horizonlimit.network.packet.play;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.yukulab.horizonlimit.network.Networking;

public class UpdateCountdownS2CPacket {
    public static void send(ServerPlayerEntity player, int countdown) {
        var buf = PacketByteBufs.create();
        buf.writeInt(countdown);
        ServerPlayNetworking.send(player, Networking.UPDATE_COUNTDOWN, buf);
    }

    @Environment(EnvType.CLIENT)
    public static void onReceive(
            MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        var currentCountdown = buf.readInt();
        // TODO update point
    }
}
