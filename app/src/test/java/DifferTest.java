
import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class DifferTest {
    private final String absoluteFilePathJSON1 =
            "src/test/resources/file1.json";
    private final String absoluteFileJSONPath2 =
            "src/test/resources/file2.json";
    private final String absoluteFileYAMLPath1 =
            "src/test/resources/fixtures/yamlFile1.yaml";
    private final String absoluteFileYAMLPath2 =
            "src/test/resources/fixtures/yamlFile2.yaml";
    private static final String DIFF_STYLISH = """
            {
                chars1: [a, b, c]
              - chars2: [d, e, f]
              + chars2: false
              - checked: false
              + checked: true
              - default: null
              + default: [value1, value2]
              - id: 45
              + id: null
              - key1: value1
              + key2: value2
                numbers1: [1, 2, 3, 4]
              - numbers2: [2, 3, 4, 5]
              + numbers2: [22, 33, 44, 55]
              - numbers3: [3, 4, 5]
              + numbers4: [4, 5, 6]
              + obj1: {nestedKey=value, isNested=true}
              - setting1: Some value
              + setting1: Another value
              - setting2: 200
              + setting2: 300
              - setting3: true
              + setting3: none
            }""";

    private static final String DIFF_STYLISH_2 = "{\n"
            + "    chars1: [a, b, c]\n"
            + "  - chars2: [d, e, f]\n"
            + "  + chars2: false\n"
            + "  - checked: false\n"
            + "  + checked: true\n"
            + "  - default: null\n"
            + "  + default: [value1, value2]\n"
            + "  - id: 45\n"
            + "  + id: null\n"
            + "  - key1: value1\n"
            + "  + key2: value2\n"
            + "    numbers1: [1, 2, 3, 4]\n"
            + "  - numbers2: [2, 3, 4, 5]\n"
            + "  + numbers2: [22, 33, 44, 55]\n"
            + "  - numbers3: [3, 4, 5]\n"
            + "  + numbers4: [4, 5, 6]\n"
            + "  + obj1: {nestedKey=value, isNested=true}\n"
            + "  - setting1: Some value\n"
            + "  + setting1: Another value\n"
            + "  - setting2: 200\n"
            + "  + setting2: 300\n"
            + "  - setting3: true\n"
            + "  + setting3: none\n"
            + "}";
    private static final String DIFF_PLAIN = """
            Property 'chars2' was updated. From [complex value] to false
            Property 'checked' was updated. From false to true
            Property 'default' was updated. From null to [complex value]
            Property 'id' was updated. From 45 to null
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'
            Property 'numbers2' was updated. From [complex value] to [complex value]
            Property 'numbers3' was removed
            Property 'numbers4' was added with value: [complex value]
            Property 'obj1' was added with value: [complex value]
            Property 'setting1' was updated. From 'Some value' to 'Another value'
            Property 'setting2' was updated. From 200 to 300
            Property 'setting3' was updated. From true to 'none'""";

    private static final String DIFF_JSON =
            "[{\"change\":\"UNCHANGED\",\"singleValue\":[\"a\",\"b\",\"c\"],\"key\":\"chars1\"},"
                    + "{\"newValue\":false,\"change\":\"CHANGE\",\"oldValue\":[\"d\",\"e\",\"f\"],"
                    + "\"key\":\"chars2\"},"
                    + "{\"newValue\":true,\"change\":\"CHANGE\",\"oldValue\":false,\"key\":\"checked\"},"
                    + "{\"newValue\":[\"value1\",\"value2\"],\"change\":\"CHANGE\","
                    + "\"oldValue\":null,\"key\":\"default\"},"
                    + "{\"newValue\":null,\"change\":\"CHANGE\",\"oldValue\":45,\"key\":\"id\"},"
                    + "{\"change\":\"DELETE\",\"singleValue\":\"value1\",\"key\":\"key1\"},"
                    + "{\"change\":\"ADD\",\"singleValue\":\"value2\",\"key\":\"key2\"},"
                    + "{\"change\":\"UNCHANGED\",\"singleValue\":[1,2,3,4],\"key\":\"numbers1\"},"
                    + "{\"newValue\":[22,33,44,55],\"change\":\"CHANGE\",\"oldValue\":[2,3,4,5],\"key\":\"numbers2\"},"
                    + "{\"change\":\"DELETE\",\"singleValue\":[3,4,5],\"key\":\"numbers3\"},"
                    + "{\"change\":\"ADD\",\"singleValue\":[4,5,6],\"key\":\"numbers4\"},"
                    + "{\"change\":\"ADD\",\"singleValue\":{\"nestedKey\":\"value\",\"isNested\":true},"
                    + "\"key\":\"obj1\"},"
                    + "{\"newValue\":\"Another value\",\"change\":\"CHANGE\",\"oldValue\":\"Some value\","
                    + "\"key\":\"setting1\"},"
                    + "{\"newValue\":300,\"change\":\"CHANGE\",\"oldValue\":200,\"key\":\"setting2\"},"
                    + "{\"newValue\":\"none\",\"change\":\"CHANGE\",\"oldValue\":true,\"key\":\"setting3\"}]";

    @Test
    public void stylishFormatTest() throws Exception {
        String actual = Differ.generate(absoluteFilePathJSON1, absoluteFileJSONPath2, "stylish");
        Assertions.assertEquals(DIFF_STYLISH, actual);
    }

    @Test
    public void plainFormatTest() throws Exception {
        String actual = Differ.generate(absoluteFilePathJSON1, absoluteFileJSONPath2, "plain");
        Assertions.assertEquals(DIFF_PLAIN, actual);
    }

    @Test
    public void jsonFormatTest() throws Exception {
        String actual = Differ.generate(absoluteFilePathJSON1, absoluteFileJSONPath2, "json");
        Assertions.assertEquals(DIFF_JSON, actual);
    }

    @Test
    public void withoutFormatTest() throws Exception {
        String actual = Differ.generate(absoluteFilePathJSON1, absoluteFileJSONPath2);
        Assertions.assertEquals(DIFF_STYLISH, actual);
    }

    @Test
    public void diffYAMLinJSONFormat() throws Exception {
        String actual = Differ.generate(absoluteFileYAMLPath1, absoluteFileYAMLPath2);
        Assertions.assertEquals(DIFF_STYLISH_2, actual);
    }
}
