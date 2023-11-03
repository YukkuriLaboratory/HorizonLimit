package net.yukulab.horizonlimit.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.yukulab.horizonlimit.config.ServerConfig;
import net.yukulab.horizonlimit.extension.ClientConfigHolder;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Optional;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient implements ClientConfigHolder {
    private ServerConfig serverConfig;
    @Override
    public Optional<ServerConfig> horizonlimit$getServerConfig() {
        return Optional.ofNullable(serverConfig);
    }

    @Override
    public void horizonlimit$getServerConfig(@NotNull ServerConfig config) {
        serverConfig = config;
    }
}
