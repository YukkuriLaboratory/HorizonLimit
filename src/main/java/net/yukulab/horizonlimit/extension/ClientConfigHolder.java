package net.yukulab.horizonlimit.extension;

import net.yukulab.horizonlimit.config.ClientConfig;
import org.jetbrains.annotations.NotNull;

public interface ClientConfigHolder {
    default ClientConfig horizonlimit$getClientConfig() {
        return null;
    }

    default void horizonlimit$setClientConfig(@NotNull ClientConfig config) {}
}
