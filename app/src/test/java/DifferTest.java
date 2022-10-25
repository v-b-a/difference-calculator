import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferTest {
    private final String absoluteJsonFilepath1 =
            "src/test/resources/fixtures/file1.json";
    private final String absoluteJsonFilepath2 =
            "src/test/resources/fixtures/file2.json";
    private final String absoluteYamlFilepath1 =
            "src/test/resources/fixtures/yamlFile1.yaml";
    private final String absoluteYamlFilepath2 =
            "src/test/resources/fixtures/yamlFile2.yaml";

    private final Path diffStylishFormatPath =
            Paths.get("src/test/resources/fixtures/differTest/diffStylishFormat").toAbsolutePath().normalize();
    private final String diffStylishFormat = Files.readString(diffStylishFormatPath);

    private final Path diffPlainFormatPath =
            Paths.get("src/test/resources/fixtures/differTest/diffPlainFormat").toAbsolutePath().normalize();
    private final String diffPlainFormat = Files.readString(diffPlainFormatPath);


    private final Path diffJsonFormatPath =
            Paths.get("src/test/resources/fixtures/differTest/diffJsonFormat.json").toAbsolutePath().normalize();
    private final String diffJsonFormat = Files.readString(diffJsonFormatPath);

    public DifferTest() throws IOException {
    }

    @Test
    public void stylishFormatTest() throws Exception {
        String actual1 = Differ.generate(absoluteJsonFilepath1, absoluteJsonFilepath2, "stylish");
        Assertions.assertEquals(diffStylishFormat, actual1);

        String actual2 = Differ.generate(absoluteYamlFilepath1, absoluteYamlFilepath2, "stylish");
        Assertions.assertEquals(diffStylishFormat, actual2);
    }

    @Test
    public void jsonPlainFormatTest() throws Exception {
        String actual1 = Differ.generate(absoluteJsonFilepath1, absoluteJsonFilepath2, "plain");
        Assertions.assertEquals(diffPlainFormat, actual1);

        String actual2 = Differ.generate(absoluteYamlFilepath1, absoluteYamlFilepath2, "plain");
        Assertions.assertEquals(diffPlainFormat, actual2);
    }

    @Test
    public void jsonJsonFormatTest() throws Exception {
        String actual1 = Differ.generate(absoluteJsonFilepath1, absoluteJsonFilepath2, "json");
        Assertions.assertEquals(diffJsonFormat, actual1);

        String actual2 = Differ.generate(absoluteYamlFilepath1, absoluteYamlFilepath2, "json");
        Assertions.assertEquals(diffJsonFormat, actual2);
    }

    @Test
    public void jsonWithoutFormatTest() throws Exception {
        String actual1 = Differ.generate(absoluteJsonFilepath1, absoluteJsonFilepath2);
        Assertions.assertEquals(diffStylishFormat, actual1);

        String actual2 = Differ.generate(absoluteYamlFilepath1, absoluteYamlFilepath2);
        Assertions.assertEquals(diffStylishFormat, actual2);
    }
}
