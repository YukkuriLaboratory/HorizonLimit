package net.yukulab.horizonlimit.config;

import java.util.HashMap;
import java.util.UUID;

public class ServerConfig {
    public int timeLimit = 10;
    public HashMap<String, HashMap<UUID, UserHeight>> limit = new HashMap<>();
}
