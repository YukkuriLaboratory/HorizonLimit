package net.yukulab.horizonlimit.config;

import java.util.HashMap;
import java.util.UUID;

public record ServerConfig(int timeLimit, HashMap<String, HashMap<UUID, UserHeight>> limit) {
    public static ServerConfig asDefault() {
        return new ServerConfig(70, new HashMap<String, HashMap<UUID, UserHeight>>());
    }
}
