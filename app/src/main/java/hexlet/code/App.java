package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "bari 0.1",
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<Integer> {
    @Parameters(index = "0", description = "path to first file")
    private static String filepath1;

    @Parameters(index = "1", description = "path to second file")
    private static String filepath2;

    @Option(names = {"-f", "--format"}, description = "output format", defaultValue = "stylish")
    private static String format;

    @Override
    public Integer call() throws Exception {
        String filesDifference = Differ.generate(filepath1, filepath2, format);
        System.out.println(filesDifference);
        return 0;
    }

    public static void main(String[] args) throws IOException {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
