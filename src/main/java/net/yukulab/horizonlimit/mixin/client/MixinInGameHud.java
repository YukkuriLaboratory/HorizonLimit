package net.yukulab.horizonlimit.mixin.client;

import com.google.common.collect.Lists;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.yukulab.horizonlimit.hud.StatusElement;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class MixinInGameHud {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "render", at = @At("RETURN"))
    private void renderModElements(DrawContext context, float tickDelta, CallbackInfo ci) {
        if (client.options.debugEnabled) {
            return;
        }

        Lists.newArrayList(StatusElement.INSTANCE).forEach(i -> {
            if (i.visible) {
                i.render(context);
            }
        });
    }
}
