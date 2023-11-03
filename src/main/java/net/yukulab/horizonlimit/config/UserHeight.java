package net.yukulab.horizonlimit.config;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonSerialize(using = UserHeightSerializer.class)
@JsonDeserialize(using = UserHeightDeserializer.class)
public class UserHeight {
    public boolean isSkySide = false;
    public int limit = 70;

    public UserHeight(boolean isSkySide, int limit) {
        this.isSkySide = isSkySide;
        this.limit = limit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHeight that = (UserHeight) o;
        return isSkySide == that.isSkySide && limit == that.limit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isSkySide, limit);
    }
}
