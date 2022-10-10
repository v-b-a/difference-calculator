package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parsing(String filepath) throws IOException {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        String stringFile = Files.readString(path);
        String fileExtension = getFileExtension(path.toFile());
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

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        int index = fileName.lastIndexOf(".");
        if (index != -1 && index != 0) {
            return fileName.substring(index + 1);
        } else {
            return "";
        }
    }
}
