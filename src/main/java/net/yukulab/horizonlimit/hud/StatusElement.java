package net.yukulab.horizonlimit.hud;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class StatusElement extends HudElement {
    public static final StatusElement INSTANCE = new StatusElement();

    private int nowTime = -1; // tick

    public void setNowTime(int time) {
        this.setVisible(time != -1);
        this.nowTime = time;
    }

    public int getNowTime() {
        return nowTime;
    }

    private StatusElement() {
        super();
        this.setVisible(false);
    }

    @Override
    Text getText() {
        this.scale =
                MinecraftClient.getInstance().horizonlimit$getClientConfig().HudScale();
        return Text.translatable("hud.countdown", String.valueOf(String.format("%.2f", (double) nowTime / 20)))
                .setStyle(Style.EMPTY.withColor(Formatting.RED));
    }
}
