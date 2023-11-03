package net.yukulab.horizonlimit.hud;

import net.minecraft.text.Text;

public class TestElement extends HudElement {
    public static final TestElement INSTANCE = new TestElement();

    private TestElement() {
        super();
    }

    @Override
    Text getText() {
        return Text.literal("Hello World!");
    }
}
