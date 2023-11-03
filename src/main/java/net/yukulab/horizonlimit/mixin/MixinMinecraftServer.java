package net.yukulab.horizonlimit.mixin;

import net.minecraft.server.MinecraftServer;
import net.yukulab.horizonlimit.config.ConfigIO;
import net.yukulab.horizonlimit.config.ServerConfig;
import net.yukulab.horizonlimit.extension.ServerConfigHolder;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer implements ServerConfigHolder {
    private ServerConfig serverConfig = ConfigIO.readConfig();

    @Override
    public ServerConfig horizonlimit$getServerConfig() {
        return serverConfig;
    }

    @Override
    public void horizonlimit$setServerConfig(@NotNull ServerConfig config) {
        serverConfig = config;
        ConfigIO.writeConfig(config);
    }
}
