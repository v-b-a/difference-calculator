package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;


public class Parser {
    public static Map<String, Object> parsing(Path filepath, String fileExtension) throws IOException {
        String stringFile = Files.readString(filepath);
        Map<String, Object> result = new HashMap<>();
        if (fileExtension.equals("json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.readValue(stringFile, new TypeReference<>() {
            });
        } else if (fileExtension.equals("yaml")) {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            result = objectMapper.readValue(stringFile, new TypeReference<>() {
            });
        }
        return result;
    }
}
