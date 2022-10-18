package hexlet.code;

public enum FilesExtension {
    YAML("yaml"),
    YML("yml"),
    JSON("json");

    private final String extension;

    FilesExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
