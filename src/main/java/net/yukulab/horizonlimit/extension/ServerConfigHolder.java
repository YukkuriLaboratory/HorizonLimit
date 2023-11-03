package net.yukulab.horizonlimit.extension;

import net.yukulab.horizonlimit.config.ServerConfig;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("checkstyle:MethodName")
public interface ServerConfigHolder {
    default ServerConfig horizonlimit$getServerConfig() {
        return null;
    }
    default void horizonlimit$getServerConfig(@NotNull ServerConfig config) {
    }
}
