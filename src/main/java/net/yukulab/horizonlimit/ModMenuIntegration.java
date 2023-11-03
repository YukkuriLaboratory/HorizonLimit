package net.yukulab.horizonlimit;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import net.minecraft.text.Text;
import net.yukulab.horizonlimit.config.ServerConfig;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            var builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Text.literal("HorizonLimit Config"));

            var entryBuilder = builder.entryBuilder();

            var serverDefaultConfig = ServerConfig.getAsDefault();
            return builder.build();
        };
    }
}
