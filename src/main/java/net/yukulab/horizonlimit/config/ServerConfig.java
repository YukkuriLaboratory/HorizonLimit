package net.yukulab.horizonlimit.config;

public record ServerConfig(
        String skyPlayerMCID,
        String landPlayerMCID,
        int timeLimitToEscape,
        int OverworldSkyMinY,
        int OverworldLandMaxY,
        int NetherSkyMinY,
        int NetherLandMaxY,
        int EndSkyMinY,
        int EndLandMaxY
) {
    public static ServerConfig getAsDefault() {
        return new ServerConfig("skyPlayer", "landPlayer", 10, 60, 70, 60, 70, 60, 70);
    }
}
