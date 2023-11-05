package net.yukulab.horizonlimit.config;

public record ClientConfig(float HudScale) {
    public static ClientConfig asDefault() {
        return new ClientConfig(1.5f);
    }
}
