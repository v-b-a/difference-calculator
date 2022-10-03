import hexlet.code.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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
        YAML_MAP.put("foo", "bar");
        YAML_MAP.put("new prop1", "new prop value");
        YAML_MAP.put("aboolean", true);
        YAML_MAP.put("int", VALUE3);
        YAML_MAP.put("one", "In Treatment");
        YAML_MAP.put("fry", Arrays.toString(new int[]{VALUE2, VALUE3, VALUE4, VALUE5}));
        YAML_MAP.put("rar", null);
    }

    @Test
    void parseJSONRelativePath() throws IOException {
        String relativeFilePath1 = "src/test/resources/file1.json";
        Map<String, Object> actual = Parser.parsing(relativeFilePath1);
        Assertions.assertEquals(JSON_MAP.toString(), actual.toString());
    }
    @Test
    void parseYAMLFile() throws IOException {
        String relativeYAMLFilePath1 = "src/test/resources/yamlFile1.yaml";
        Map<String, Object> actual = Parser.parsing(relativeYAMLFilePath1);
        Assertions.assertEquals(YAML_MAP.toString(), actual.toString());
    }
}
