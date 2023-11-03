package net.yukulab.horizonlimit.config;

import java.util.Map;
import java.util.UUID;

public record ServerConfig(int timeLimit, Map<String, Map<UUID, UserHeight>> limit) {
    public static ServerConfig asDefault() {
        return new ServerConfig(70, Map.of());
    }
}
