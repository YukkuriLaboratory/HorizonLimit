package net.yukulab.horizonlimit.config;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class ConfigIOTest {
    @Test
    public void testConfig()  {
        var baseDir = new File(".");
        var config = ConfigIO.readConfig(baseDir);
        var dummyHeights = new HashMap<UUID, UserHeight>();
        var dummyHeight = new UserHeight(true, 2);
        var dummyId = UUID.randomUUID();
        dummyHeights.put(dummyId, dummyHeight);
        config.limit.put("overworld", dummyHeights);
        ConfigIO.writeConfig(baseDir, config);

        config = ConfigIO.readConfig(baseDir);
        assert config.limit.get("overworld").get(dummyId).equals(dummyHeight);

        assert ConfigIO.getConfigFile(baseDir).exists();
    }
}
