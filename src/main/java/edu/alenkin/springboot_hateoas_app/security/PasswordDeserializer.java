package edu.alenkin.springboot_hateoas_app.security;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import edu.alenkin.springboot_hateoas_app.config.SecurityConfig;

import java.io.IOException;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class PasswordDeserializer {
    public static class JsonPassDeserializer extends JsonDeserializer<String> {
        public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                       ObjectCodec oc = jsonParser.getCodec();
                       JsonNode node = oc.readTree(jsonParser);
                        String rawPassword = node.asText();
                        return SecurityConfig.ENCODER.encode(rawPassword);
                    }
    }
}
