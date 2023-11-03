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

public class ConfigIO {
    protected ConfigIO() {
        throw new UnsupportedOperationException("Don't create this class instance");
    }

    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static void writeConfig(ServerConfig config) {
        String json = "";
        try {
            json = mapper.writeValueAsString(config);
        } catch (JsonProcessingException e) {
            HorizonLimit.LOGGER.error("Error while in processing json", e);
        }
        if (!json.isEmpty()) {
            try (var writer = new FileWriter(getConfigFile(), false)) {
                writer.write(json);
            } catch (IOException e) {
                HorizonLimit.LOGGER.error("Failed to write config", e);
            }
        }
    }

    public static ServerConfig readConfig() {
        ServerConfig config = new ServerConfig();
        File configFile = getConfigFile();
        if (!configFile.exists()) writeConfig(config);
        try {
            config = mapper.readValue(configFile, ServerConfig.class);
        } catch (StreamReadException e) {
            HorizonLimit.LOGGER.error("Error in reading stream", e);
        } catch (DatabindException e) {
            HorizonLimit.LOGGER.error("Failed to databind", e);
        } catch (IOException e) {
            HorizonLimit.LOGGER.error("IOException was occurred", e);
        }
        return config;
    }

    private static File getConfigFile() {
        return new File(FabricLoader.getInstance().getConfigDir().toFile(), HorizonLimit.MOD_ID + ".json");
    }
}
