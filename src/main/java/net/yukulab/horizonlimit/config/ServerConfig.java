package net.yukulab.horizonlimit.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@JsonSerialize(using = UserHeightSerializer.class)
class UserHeight {
    public boolean isSkySide = false;
    public int limit = 70;
}

class UserHeightSerializer extends StdSerializer<UserHeight> {

    public UserHeightSerializer() {
        this(null);
    }

    public UserHeightSerializer(Class<UserHeight> t) {
        super(t);
    }

    @Override
    public void serialize(UserHeight value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("limit", value.limit);
        gen.writeBooleanField("isSkySide", value.isSkySide);
        gen.writeEndObject();
    }
}

public class ServerConfig {
    public int timeLimit = 10;
    public HashMap<String, HashMap<UUID, UserHeight>> limit = new HashMap<>();
}
