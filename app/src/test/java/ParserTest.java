import hexlet.code.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;


public class ParserTest {
    private static final Map<String, Object> JSON_MAP = new LinkedHashMap<>();
    private static final Map<String, Object> YAML_MAP = new LinkedHashMap<>();

    private static final int VALUE1 = 200;
    private static final int VALUE2 = 1;
    private static final int VALUE3 = 2;
    private static final int VALUE4 = 3;
    private static final int VALUE5 = 4;
    private static final int VALUE6 = 5;
    private static final int VALUE7 = 45;

    @BeforeAll
    static void jsonData() {
        JSON_MAP.put("setting1", "Some value");
        JSON_MAP.put("setting2", VALUE1);
        JSON_MAP.put("setting3", true);
        JSON_MAP.put("key1", "value1");
        JSON_MAP.put("numbers1", Arrays.toString(new int[]{VALUE2, VALUE3, VALUE4, VALUE5}));
        JSON_MAP.put("numbers2", Arrays.toString(new int[]{VALUE3, VALUE4, VALUE5, VALUE6}));
        JSON_MAP.put("id", VALUE7);
        JSON_MAP.put("default", null);
        JSON_MAP.put("checked", false);
        JSON_MAP.put("numbers3", Arrays.toString(new int[]{VALUE4, VALUE5, VALUE6}));
        JSON_MAP.put("chars1", Arrays.toString(new String[]{"a", "b", "c"}));
        JSON_MAP.put("chars2", Arrays.toString(new String[]{"d", "e", "f"}));
    }

    @BeforeAll
    static void yamlData() {
        YAML_MAP.put("setting1", "Some value");
        YAML_MAP.put("setting2", VALUE1);
        YAML_MAP.put("setting3", true);
        YAML_MAP.put("key1", "value1");
        YAML_MAP.put("numbers1", Arrays.toString(new int[]{VALUE2, VALUE3, VALUE4, VALUE5}));
        YAML_MAP.put("numbers2", Arrays.toString(new int[]{VALUE3, VALUE4, VALUE5, VALUE6}));
        YAML_MAP.put("id", VALUE7);
        YAML_MAP.put("default", null);
        YAML_MAP.put("checked", false);
        YAML_MAP.put("numbers3", Arrays.toString(new int[]{VALUE4, VALUE5, VALUE6}));
        YAML_MAP.put("chars1", Arrays.toString(new String[]{"a", "b", "c"}));
        YAML_MAP.put("chars2", Arrays.toString(new String[]{"d", "e", "f"}));
    }

    @Test
    void parseJSONRelativePath() throws Exception {
        Path filePath = Paths.get("src/test/resources/file1.json").toAbsolutePath().normalize();
        Map<String, Object> actual = Parser.parsing(filePath, "json");
        Assertions.assertEquals(JSON_MAP.toString(), actual.toString());
    }

    @Test
    void parseYAMLFile() throws Exception {
        Path filePath = Paths.get("src/test/resources/fixtures/yamlFile1.yaml").toAbsolutePath().normalize();
        Map<String, Object> actual = Parser.parsing(filePath, "yaml");
        Assertions.assertEquals(YAML_MAP.toString(), actual.toString());
    }
}
