package net.yukulab.horizonlimit.mixin;

import net.minecraft.server.MinecraftServer;
import net.yukulab.horizonlimit.config.ConfigIO;
import net.yukulab.horizonlimit.config.ServerConfig;
import net.yukulab.horizonlimit.extension.ServerConfigHolder;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer implements ServerConfigHolder {
    @Unique
    private ServerConfig horizonlimit$serverConfig = ConfigIO.readConfig(ServerConfig.class)
            .orElseGet(() -> {
                var config = ServerConfig.asDefault();
                ConfigIO.writeConfig(config);
                return config;
            });

    @Override
    public ServerConfig horizonlimit$getServerConfig() {
        return horizonlimit$serverConfig;
    }

    @Override
    public void horizonlimit$setServerConfig(@NotNull ServerConfig config) {
        horizonlimit$serverConfig = config;
        ConfigIO.writeConfig(config);
    }
}
