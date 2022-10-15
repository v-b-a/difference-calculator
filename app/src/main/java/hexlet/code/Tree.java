package hexlet.code;

import java.util.*;

public class Tree {
    public static List<Map<String, Object>> mapCompare(Map<String, Object> map1, Map<String, Object> map2) {
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
        return difference;
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
