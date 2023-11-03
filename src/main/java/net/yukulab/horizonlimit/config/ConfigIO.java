package net.yukulab.horizonlimit.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import net.fabricmc.loader.api.FabricLoader;
import net.yukulab.horizonlimit.HorizonLimit;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class ConfigIO {
    protected ConfigIO() {
        throw new UnsupportedOperationException("Don't create this class instance");
    }
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static <T> void writeConfig(T config) {
        String json = GSON.toJson(config);
        try (var writer = new FileWriter(getConfigFile(config.getClass()), false)) {
            writer.write(json);
        } catch (IOException e) {
            HorizonLimit.LOGGER.error("Failed to write config", e);
        }
    }

    public static <T> Optional<T> readConfig(Class<T> targetObject) {
        File configFile = getConfigFile(targetObject);
        try (FileReader reader = new FileReader(configFile)) {
            var config = GSON.fromJson(reader, targetObject);
            return Optional.of(config);
        } catch (FileNotFoundException e) {
            HorizonLimit.LOGGER.warn("Config file is not found. Load default data");
        } catch (IOException e) {
            HorizonLimit.LOGGER.error("Failed to load config", e);
        } catch (JsonParseException e) {
            var ignored = configFile.renameTo(new File(configFile.getParent(), configFile.getName() + ".old"));
            HorizonLimit.LOGGER.warn("Exists old file. Loads default config.");
        }
        return Optional.empty();
    }

    private static <T> File getConfigFile(Class<T> targetObject) {
        StringBuilder builder = new StringBuilder().append(HorizonLimit.MOD_NAME);
        if (targetObject.equals(ServerConfig.class)) {
            builder.append("_server");
        } else {
            builder.append("_unknown");
        }
        builder.append(".json");
        return new File(FabricLoader.getInstance().getConfigDir().toFile(), builder.toString());
    }
}
