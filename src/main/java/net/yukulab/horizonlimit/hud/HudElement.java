package net.yukulab.horizonlimit.hud;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public abstract class HudElement {
    TextRenderer renderer = MinecraftClient.getInstance().textRenderer;
    public int x;
    public int y;

    public float scale = 1f;

    protected HudElement() {
        this(5, 5);
    }

    protected HudElement(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int backgroundColor = -0x6FAFAFB0;

    public boolean visible = true;

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean getVisible() {
        return this.visible;
    }

    public int getHeight() {
        return MinecraftClient.getInstance().inGameHud.getTextRenderer().fontHeight + 2;
    }

    public int getWidth() {
        return MinecraftClient.getInstance().inGameHud.getTextRenderer().getWidth(getText());
    }

    public void render(DrawContext drawContext) {
        drawContext.getMatrices().push();
        drawContext.getMatrices().scale(scale, scale, 0f);
        drawContext.fill(x, y, x + getWidth() + 3, y + getHeight(), backgroundColor);
        drawContext.drawText(renderer, getText(), x + 2, y + 2, -1, false);
        drawContext.getMatrices().pop();
    }

    abstract Text getText();
}
