package net.yukulab.horizonlimit.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.yukulab.horizonlimit.network.Networking;

@Environment(EnvType.CLIENT)
public class HorizonLimitClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Networking.registerClientReceivers();
    }
}
