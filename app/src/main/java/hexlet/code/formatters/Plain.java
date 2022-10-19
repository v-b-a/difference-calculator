package hexlet.code.formatters;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String format(List<Map<String, Object>> diffList) {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        for (Map<String, Object> listElement : diffList) {
            String key = listElement.get("key").toString();
            String change = listElement.get("change").toString();
            String value = formatValue(listElement.get("value"));
            String oldValue = formatValue(listElement.get("oldValue"));
            String newValue = formatValue(listElement.get("newValue"));

            if (change.equals("UNCHANGED")) {
                continue;
            }
            if (isFirst) {
                isFirst = false;
            } else {
                builder.append("\n");
            }

            String baseLine = "Property '" + key;
            switch (change) {
                case "ADD" -> builder.append(baseLine)
                        .append("' was added with value: ").append(value);
                case "DELETE" -> builder.append(baseLine).append("' was removed");
                case "CHANGE" -> builder.append(baseLine).append("' was updated. From ")
                        .append(oldValue).append(" to ").append(newValue);
                default -> throw new Error("Unknown change: " + change);
            }
        }
        return builder.toString();
    }

    private static String formatValue(Object value) {
        if (value instanceof String) {
            value = String.format("'%s'", value);
        }
        if (value instanceof ArrayList<?> || value instanceof LinkedHashMap<?, ?>) {
            value = "[complex value]";
        }

        return value == null ? null : value.toString();
    }
}
