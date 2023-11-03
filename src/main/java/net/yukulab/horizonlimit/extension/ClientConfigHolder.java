package net.yukulab.horizonlimit.extension;

import net.yukulab.horizonlimit.config.ServerConfig;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@SuppressWarnings("checkstyle:MethodName")
public interface ClientConfigHolder {
    default Optional<ServerConfig> horizonlimit$getServerConfig() {
        return Optional.empty();
    }
    default void horizonlimit$getServerConfig(@NotNull ServerConfig config) {
    }
}
