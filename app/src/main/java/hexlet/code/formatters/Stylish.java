package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String format(List<Map<String, Object>> diffList) {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        for (Map<String, Object> listElement : diffList) {
            String key = listElement.get("key").toString();
            String change = listElement.get("change").toString();
            Object value = listElement.get("value");
            Object oldValue = listElement.get("oldValue");
            Object newValue = listElement.get("newValue");

            switch (change) {
                case "ADD" -> builder.append(fillLine("+", key, value));
                case "DELETE" -> builder.append(fillLine("-", key, value));
                case "UNCHANGED" -> builder.append(fillLine(" ", key, value));
                case "CHANGE" -> {
                    builder.append(fillLine("-", key, oldValue));
                    builder.append(fillLine("+", key, newValue));
                }
                default -> throw new Error("Unknown change: " + change);
            }
        }
        builder.append("}");
        return builder.toString();
    }
    private static String fillLine(String changeSign, String key, Object anyValue) {
        return String.format("  %s %s: %s\n", changeSign, key, anyValue);
    }
}
