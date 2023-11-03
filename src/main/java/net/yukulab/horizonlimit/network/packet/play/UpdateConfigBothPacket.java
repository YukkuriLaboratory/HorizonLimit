package net.yukulab.horizonlimit.network.packet.play;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.yukulab.horizonlimit.HorizonLimit;
import net.yukulab.horizonlimit.network.Networking;

public class UpdateConfigBothPacket {
    public static void send(ServerPlayerEntity player, Object config) {
        ServerPlayNetworking.send(player, Networking.UPDATE_CONFIG, convert(config));
    }

    public static void onReceive(
            MinecraftServer server,
            ServerPlayerEntity player,
            ServerPlayNetworkHandler handler,
            PacketByteBuf buf,
            PacketSender responseSender) {
        var config = readConfig(buf);
        // TODO: Save config
        HorizonLimit.LOGGER.debug("Received config from " + player.getName() + ": " + config);
    }

    @Environment(EnvType.CLIENT)
    public static void send(Object config) {
        ClientPlayNetworking.send(Networking.UPDATE_CONFIG, convert(config));
    }

    @Environment(EnvType.CLIENT)
    public static void onReceive(
            MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        var config = readConfig(buf);
        HorizonLimit.LOGGER.debug("Received config from server: " + config);
    }

    private static PacketByteBuf convert(Object config) {
        var buf = PacketByteBufs.create();
        // TODO write config
        return buf;
    }

    private static Object readConfig(PacketByteBuf buf) {
        // TODO read config
        return null;
    }
}
