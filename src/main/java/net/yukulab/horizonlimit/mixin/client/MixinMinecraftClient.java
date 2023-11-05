package net.yukulab.horizonlimit.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.yukulab.horizonlimit.config.ClientConfig;
import net.yukulab.horizonlimit.config.ConfigIO;
import net.yukulab.horizonlimit.extension.ClientConfigHolder;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MinecraftClient.class)
@Environment(EnvType.CLIENT)
public class MixinMinecraftClient implements ClientConfigHolder {
    private ClientConfig clientConfig = ConfigIO.readConfig(ClientConfig.class).orElseGet(() -> {
        var config = ClientConfig.asDefault();
        ConfigIO.writeConfig(config);
        return config;
    });

    @Override
    public ClientConfig horizonlimit$getClientConfig() {
        return clientConfig;
    }

    @Override
    public void horizonlimit$setClientConfig(@NotNull ClientConfig config) {
        clientConfig = config;
        ConfigIO.writeConfig(config);
    }
}
