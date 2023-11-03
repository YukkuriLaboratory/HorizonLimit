package net.yukulab.horizonlimit.hud;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public abstract class HudElement {
    public int x;
    public int y;

    protected HudElement() {
        this(5, 10);
    }

    protected HudElement(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int backgroundColor = -0x6FAFAFB0;

    public boolean visible = true;

    public int getHeight() {
        return MinecraftClient.getInstance().inGameHud.getTextRenderer().fontHeight + 2;
    }

    public int getWidth() {
        return MinecraftClient.getInstance().inGameHud.getTextRenderer().getWidth(getText());
    }

    public void render(DrawContext drawContext) {
        drawContext.drawText(MinecraftClient.getInstance().textRenderer, getText(), x + 2, y + 2, -1, false);
    }

    abstract Text getText();
}
