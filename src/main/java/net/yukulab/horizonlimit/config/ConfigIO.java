package net.yukulab.horizonlimit.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import net.fabricmc.loader.api.FabricLoader;
import net.yukulab.horizonlimit.HorizonLimit;
import org.jetbrains.annotations.VisibleForTesting;

public class ConfigIO {
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static File getConfigDir() {
        return FabricLoader.getInstance().getConfigDir().toFile();
    }

    public static <T> void writeConfig(T config) {
        writeConfig(getConfigDir(), config);
    }

    public static <T> void writeConfig(File baseDir, T config) {
        String json = "";
        try {
            json = mapper.writeValueAsString(config);
        } catch (JsonProcessingException e) {
            HorizonLimit.LOGGER.error("Error while in processing json", e);
        }
        if (!json.isEmpty()) {
            try (var writer = new FileWriter(getConfigFile(baseDir, config.getClass()), false)) {
                writer.write(json);
                HorizonLimit.LOGGER.info("Config was wrote.");
            } catch (IOException e) {
                HorizonLimit.LOGGER.error("Failed to write config", e);
            }
        }
    }

    public static <T> Optional<T> readConfig(Class<T> configClass) {
        return readConfig(getConfigDir(), configClass);
    }

    public static <T> Optional<T> readConfig(File baseDir, Class<T> configClass) {
        File configFile = getConfigFile(baseDir, configClass);
        if (!configFile.exists()) return Optional.empty();
        try {
            return Optional.of(mapper.readValue(configFile, configClass));
        } catch (StreamReadException e) {
            HorizonLimit.LOGGER.error("Error in reading stream", e);
        } catch (DatabindException e) {
            HorizonLimit.LOGGER.error("Failed to databind", e);
        } catch (IOException e) {
            HorizonLimit.LOGGER.error("IOException was occurred", e);
        }
        return Optional.empty();
    }

    @VisibleForTesting
    public static <T> File getConfigFile(File baseDir, Class<T> configClass) {
        StringBuilder builder = new StringBuilder(HorizonLimit.MOD_ID);
        if (configClass.equals(ServerConfig.class)) {
            builder.append("_server");
        } else if (configClass.equals(ClientConfig.class)) {
            builder.append("_client");
        } else {
            builder.append("_unknown");
        }
        builder.append(".json");
        return new File(baseDir, builder.toString());
    }
}
