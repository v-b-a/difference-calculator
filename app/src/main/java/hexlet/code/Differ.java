package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


import static hexlet.code.Formatter.format;
import static hexlet.code.Tree.mapCompare;


public class Differ {
    public static String generate(String filepathString1, String filepathString2, String outputFormat)
            throws Exception {
        Path filepath1 = getAbsolutePath(filepathString1);
        Path filepath2 = getAbsolutePath(filepathString2);

        String extensionFile1 = getFileExtension(filepath1);
        String extensionFile2 = getFileExtension(filepath2);

        String contentFile1 = getContentFile(filepath1);
        String contentFile2 = getContentFile(filepath2);

        Map<String, Object> map1 = Parser.parsing(contentFile1, extensionFile1);
        Map<String, Object> map2 = Parser.parsing(contentFile2, extensionFile2);

        List<Map<String, Object>> mapDifference = mapCompare(map1, map2);

        return format(mapDifference, outputFormat);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    private static String getFileExtension(Path filepath) {
        String fileName = filepath.toFile().getName();
        int index = fileName.lastIndexOf(".");
        if (index != -1 && index != 0) {
            return fileName.substring(index + 1).toLowerCase();
        } else {
            return "";
        }
    }
    private static Path getAbsolutePath(String filepathString) {
        return Paths.get(filepathString).toAbsolutePath().normalize();
    }
    private static String getContentFile(Path filepath) throws IOException {
        return Files.readString(filepath);
    }
}
