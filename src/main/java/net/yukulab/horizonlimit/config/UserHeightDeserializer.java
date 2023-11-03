package net.yukulab.horizonlimit.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import java.io.IOException;

public class UserHeightDeserializer extends StdDeserializer<UserHeight> {
    public UserHeightDeserializer() {
        this(null);
    }

    protected UserHeightDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public UserHeight deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = p.getCodec().readTree(p);
        int limit = (Integer) ((IntNode) node.get("limit")).numberValue();
        boolean isSkySide = node.get("isSkySide").booleanValue();
        return new UserHeight(isSkySide, limit);
    }
}
