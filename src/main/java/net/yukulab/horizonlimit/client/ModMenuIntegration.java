package net.yukulab.horizonlimit.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import java.util.concurrent.atomic.AtomicReference;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.yukulab.horizonlimit.config.ClientConfig;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Text.translatable("title.horizonlimit.config"));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();
            ConfigCategory hud = builder.getOrCreateCategory(Text.translatable("category.horizonlimit.hud"));
            ClientConfig clientConfig = MinecraftClient.getInstance().horizonlimit$getClientConfig();
            ClientConfig defaultClientConfig = ClientConfig.asDefault();
            AtomicReference<Float> hudScale = new AtomicReference<>(clientConfig.HudScale());
            hud.addEntry(entryBuilder
                    .startFloatField(Text.translatable("option.horizonlimit.hud.scale"), hudScale.get())
                    .setDefaultValue(defaultClientConfig.HudScale())
                    .setSaveConsumer(hudScale::set)
                    .build());
            builder.setSavingRunnable(() -> {
                MinecraftClient.getInstance().horizonlimit$setClientConfig(new ClientConfig(hudScale.get()));
            });
            return builder.build();
        };
    }
}
