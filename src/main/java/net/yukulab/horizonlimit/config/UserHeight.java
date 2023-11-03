package net.yukulab.horizonlimit.config;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = UserHeightSerializer.class)
public class UserHeight {
    public boolean isSkySide = false;
    public int limit = 70;

    public UserHeight(boolean isSkySide, int limit) {
        this.isSkySide = isSkySide;
        this.limit = limit;
    }
}
