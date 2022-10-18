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
        if (fileExtension.equals(FilesExtension.JSON.getExtension())) {
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.readValue(stringFile, new TypeReference<>() {
            });
        } else if (fileExtension.equals(FilesExtension.YAML.getExtension()) ||
                fileExtension.equals(FilesExtension.YML.getExtension())) {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            result = objectMapper.readValue(stringFile, new TypeReference<>() {
            });
        } else {
            throw new Exception("Ошибка формата");
        }
//        FilesExtension filesExtension2 = FilesExtension.valueOf(fileExtension);  // другого способа работать в свиче с енам не нашел
//        switch (filesExtension2) {
//            case FilesExtension.JSON.getExtension():
//                objectMapper = new ObjectMapper();
//                result = objectMapper.readValue(stringFile, new TypeReference<>() {});
//                break;
//            case FilesExtension.YAML.getExtension(), FilesExtension.YML.getExtension():
//                objectMapper = new ObjectMapper(new YAMLFactory());
//                result = objectMapper.readValue(stringFile, new TypeReference<>() {});
//                break;
//            default:
//                throw new Exception("throw new Exception("Гавно");");
//        }
        return result;
    }

}
