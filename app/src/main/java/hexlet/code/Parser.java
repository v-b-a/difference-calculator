package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;


public class Parser {
    public static Map<String, Object> parsing(Path filepath, String fileExtension) throws Exception {
        String fileContent = Files.readString(filepath);
        enum Extensions {
            json, yaml, yml
        }
        Extensions cFileExtension;
        try {
            cFileExtension = Extensions.valueOf(fileExtension);
        } catch (Exception e) {
            throw new Exception("Unknown extension: " + fileExtension);
        }
        return switch (cFileExtension) {
            case json -> parsingJSON(fileContent);
            case yml, yaml -> parsingYAML(fileContent);
        };
    }

    private static Map<String, Object> parsingJSON(String fileContent) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(fileContent, new TypeReference<>() {
        });
    }

    private static Map<String, Object> parsingYAML(String fileContent) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(fileContent, new TypeReference<>() {
        });
    }


}
