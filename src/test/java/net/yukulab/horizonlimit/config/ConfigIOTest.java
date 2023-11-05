package net.yukulab.horizonlimit.config;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfigIOTest {
    @Test
    public void testConfig()  {
        var baseDir = new File(".");
        var config = ConfigIO.readConfig(baseDir, ServerConfig.class).orElseGet(() -> {
            var _config = ServerConfig.asDefault();
            ConfigIO.writeConfig(baseDir, _config);
            return _config;
        });
        var dummyHeights = new HashMap<UUID, UserHeight>();
        var dummyHeight = new UserHeight(true, 2);
        var dummyId = UUID.randomUUID();
        dummyHeights.put(dummyId, dummyHeight);
        config.limit().put("overworld", dummyHeights);
        ConfigIO.writeConfig(baseDir, config);

        config = ConfigIO.readConfig(baseDir, ServerConfig.class).orElseGet(() -> {
            var _config = ServerConfig.asDefault();
            ConfigIO.writeConfig(baseDir, _config);
            return _config;
        });

        assertEquals(dummyHeight,config.limit().get("overworld").get(dummyId));

        assert ConfigIO.getConfigFile(baseDir, ServerConfig.class).delete();
    }
}
