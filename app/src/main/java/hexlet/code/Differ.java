package hexlet.code;

import hexlet.code.formatters.Formatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Differ {
    public static String generate(String filepath1, String filepath2, String outputFormat) throws Exception {
        Map<String, Object> map1 = Parser.parsing(filepath1);
        Map<String, Object> map2 = Parser.parsing(filepath2);

        List<Map<String, Object>> difference = new ArrayList<>();
        for (Map.Entry<String, Object> i : map1.entrySet()) {
            Map<String, Object> currentMap;
            if (!map2.containsKey(i.getKey())) {
                currentMap = fillMap(i.getKey(), "DELETE", i.getValue());
                difference.add(currentMap);
            }
            if (map2.containsKey(i.getKey())) {
                if (Objects.equals(i.getValue(), map2.get(i.getKey()))) {
                    currentMap = fillMap(i.getKey(), "UNCHANGED", i.getValue());
                    difference.add(currentMap);
                } else {
                    currentMap = fillMap(i.getKey(), "CHANGE", i.getValue(), map2.get(i.getKey()));
                    difference.add(currentMap);
                }
            }
        }
        for (Map.Entry<String, Object> i : map2.entrySet()) {
            Map<String, Object> currentMap;
            if (!map1.containsKey(i.getKey())) {
                currentMap = fillMap(i.getKey(), "ADD", i.getValue());
                difference.add(currentMap);
            }
        }
        difference.sort(Comparator.comparing(s -> s.get("key").toString()));
        return Formatter.defineFormat(difference, outputFormat);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        String test = generate(filepath1, filepath2, "stylish");
        return test;
    }

    private static Map<String, Object> fillMap(String key, String change, Object singleValue) {
        Map<String, Object> result = new HashMap<>();
        result.put("key", key);
        result.put("change", change);
        result.put("singleValue", singleValue);
        return result;
    }
    private static Map<String, Object> fillMap(String key, String change, Object oldValue, Object newValue) {
        Map<String, Object> result = new HashMap<>();
        result.put("key", key);
        result.put("change", change);
        result.put("oldValue", oldValue);
        result.put("newValue", newValue);
        return result;
    }
}
