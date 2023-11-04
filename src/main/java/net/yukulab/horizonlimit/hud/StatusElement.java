package net.yukulab.horizonlimit.hud;

import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class StatusElement extends HudElement {

    public enum State {
        SAFE,
        DANGER
    }

    public static final StatusElement INSTANCE = new StatusElement();
    public State NowState = State.SAFE;
    private int nowTime = -1; // tick

    public void setNowTime(int time) {
        this.nowTime = time;
    }

    public int getNowTime() {
        return nowTime;
    }

    private StatusElement() {
        super();
        this.scale = 1.5f;
    }

    @Override
    Text getText() {
        if (nowTime != -1) {
            return Text.translatable("hud.countdown", String.valueOf(String.format("%.2f", (double) nowTime / 20)))
                    .setStyle(Style.EMPTY.withColor(Formatting.RED));
        } else {
            return Text.translatable("hud.alert." + NowState.name().toLowerCase())
                    .setStyle(Style.EMPTY.withColor(Formatting.GREEN));
        }
    }
}
