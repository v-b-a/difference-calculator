package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;


public class Parser {
    public static Map<String, Object> parsing(Path filepath, String fileExtension) throws Exception {
        String stringFile = Files.readString(filepath);
        Map<String, Object> result;
        enum extensions {json, yaml, yml}
        extensions cFileExtension = extensions.valueOf(fileExtension);
        switch (cFileExtension) {
            case json:
                ObjectMapper objectMapper = new ObjectMapper();
                result = objectMapper.readValue(stringFile, new TypeReference<>() {});
                break;
            case yml, yaml:
                objectMapper = new ObjectMapper(new YAMLFactory());
                result = objectMapper.readValue(stringFile, new TypeReference<>() {});
                break;
            default:
                throw new Exception("Расширения не поддерживаются");
        }
        return result;
    }

}
