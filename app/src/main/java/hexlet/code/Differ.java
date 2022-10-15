package hexlet.code;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


import static hexlet.code.Formatter.defineFormat;
import static hexlet.code.Tree.mapCompare;


public class Differ {
    public static String generate(String filepathString1, String filepathString2, String outputFormat)
            throws Exception {
        Path filepath1 = Paths.get(filepathString1).toAbsolutePath().normalize();
        Path filepath2 = Paths.get(filepathString2).toAbsolutePath().normalize();
        String extensionFile1 = getFileExtension(filepath1);
        String extensionFile2 = getFileExtension(filepath2);

        Map<String, Object> map1 = Parser.parsing(filepath1, extensionFile1);
        Map<String, Object> map2 = Parser.parsing(filepath2, extensionFile2);

        List<Map<String, Object>> mapDifference = mapCompare(map1, map2);

        return defineFormat(mapDifference, outputFormat);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String getFileExtension(Path filepath) {
        String fileName = filepath.toFile().getName();
        int index = fileName.lastIndexOf(".");
        if (index != -1 && index != 0) {
            return fileName.substring(index + 1).toLowerCase();
        } else {
            return "";
        }
    }
}
