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
            Object singleValue = formatValue(listElement.get("singleValue"));
            Object oldValue = formatValue(listElement.get("oldValue"));
            Object newValue = formatValue(listElement.get("newValue"));

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
                        .append("' was added with value: ").append(singleValue);
                case "DELETE" -> builder.append(baseLine).append("' was removed");
                case "CHANGE" -> builder.append(baseLine).append("' was updated. From ")
                        .append(oldValue).append(" to ").append(newValue);
                default -> throw new Error("Unknown change: " + change);
            }
        }
        return builder.toString();
    }

    private static Object formatValue(Object value) {
        if (value instanceof String) {
            value = "'" + value + "'";
        }
        if (value instanceof ArrayList<?> || value instanceof LinkedHashMap<?, ?>) {
            value = "[complex value]";
        }
        return value;
    }
}
