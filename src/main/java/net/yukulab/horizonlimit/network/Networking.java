package net.yukulab.horizonlimit.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerLoginConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerLoginNetworking;
import net.minecraft.util.Identifier;
import net.yukulab.horizonlimit.HorizonLimit;
import net.yukulab.horizonlimit.network.packet.HandShakeS2CPacket;

public class Networking {
    public static final Identifier HANDSHAKE = id("handshake");

    public static void registerServerReceivers() {
        ServerLoginConnectionEvents.QUERY_START.register(HandShakeS2CPacket::sendQuery);
        ServerLoginNetworking.registerGlobalReceiver(HANDSHAKE, HandShakeS2CPacket::onHandShakeServer);
    }

    @Environment(EnvType.CLIENT)
    public static void registerClientReceivers() {
        ClientLoginNetworking.registerGlobalReceiver(HANDSHAKE, HandShakeS2CPacket::onHandShakeClient);
    }



    private static Identifier id(String name) {
        return Identifier.of(HorizonLimit.MOD_ID, name);
    }
}
