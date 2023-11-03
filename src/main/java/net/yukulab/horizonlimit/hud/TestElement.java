package net.yukulab.horizonlimit.hud;

import net.minecraft.text.Text;

import java.time.Instant;

public class TestElement extends HudElement {
    public static final TestElement INSTANCE = new TestElement();
    private TestElement() {}

    @Override
    Text getText() {
        return Text.literal("Hello World!");
    }
}

