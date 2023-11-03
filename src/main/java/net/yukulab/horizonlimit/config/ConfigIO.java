package net.yukulab.horizonlimit.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import net.fabricmc.loader.api.FabricLoader;
import net.yukulab.horizonlimit.HorizonLimit;
import org.jetbrains.annotations.VisibleForTesting;

public class ConfigIO {
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static void writeConfig(ServerConfig config) {
        writeConfig(FabricLoader.getInstance().getConfigDir().toFile(), config);
    }

    public static void writeConfig(File baseDir, ServerConfig config) {
        String json = "";
        try {
            json = mapper.writeValueAsString(config);
        } catch (JsonProcessingException e) {
            HorizonLimit.LOGGER.error("Error while in processing json", e);
        }
        if (!json.isEmpty()) {
            try (var writer = new FileWriter(getConfigFile(baseDir), false)) {
                writer.write(json);
                HorizonLimit.LOGGER.info("Config was wrote.");
            } catch (IOException e) {
                HorizonLimit.LOGGER.error("Failed to write config", e);
            }
        }
    }

    public static ServerConfig readConfig() {
        return readConfig(FabricLoader.getInstance().getConfigDir().toFile());
    }

    public static ServerConfig readConfig(File baseDir) {
        ServerConfig config = ServerConfig.asDefault();
        File configFile = getConfigFile(baseDir);
        if (!configFile.exists()) writeConfig(baseDir, config);
        try {
            config = mapper.readValue(configFile, ServerConfig.class);
            HorizonLimit.LOGGER.info("Config was read.");
        } catch (StreamReadException e) {
            HorizonLimit.LOGGER.error("Error in reading stream", e);
        } catch (DatabindException e) {
            HorizonLimit.LOGGER.error("Failed to databind", e);
        } catch (IOException e) {
            HorizonLimit.LOGGER.error("IOException was occurred", e);
        }
        return config;
    }

    @VisibleForTesting
    public static File getConfigFile(File baseDir) {
        return new File(baseDir, HorizonLimit.MOD_ID + ".json");
    }
}
