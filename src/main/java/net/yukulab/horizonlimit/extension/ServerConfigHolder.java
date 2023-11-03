package net.yukulab.horizonlimit.extension;

import net.yukulab.horizonlimit.config.ServerConfig;
import org.jetbrains.annotations.NotNull;

public interface ServerConfigHolder {
    default ServerConfig horizonlimit$getServerConfig() {
        return null;
    }

    default void horizonlimit$setServerConfig(@NotNull ServerConfig config) {}
}
