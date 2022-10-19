package hexlet.code;


import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Objects;
import java.util.Comparator;
import java.util.HashMap;

public class Tree {
    public static List<Map<String, Object>> mapCompare(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> difference = new ArrayList<>();
        Set<String> allKeys = Sets.union(map1.keySet(), map2.keySet());
        for (String key : allKeys) {
            Map<String, Object> currentMap;
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                currentMap = fillMap(key, "DELETE", map1.get(key));
                difference.add(currentMap);
            }
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (Objects.equals(map1.get(key), map2.get(key))) {
                    currentMap = fillMap(key, "UNCHANGED", map1.get(key));
                    difference.add(currentMap);
                } else {
                    currentMap = fillMap(key, "CHANGE", map1.get(key), map2.get(key));
                    difference.add(currentMap);
                }
            }
            if (!map1.containsKey(key) && map2.containsKey(key)) {
                currentMap = fillMap(key, "ADD", map2.get(key));
                difference.add(currentMap);
            }
        }
        difference.sort(Comparator.comparing(s -> s.get("key").toString()));
        return difference;
    }

    private static Map<String, Object> fillMap(String key, String change, Object value) {
        Map<String, Object> result = new HashMap<>();
        result.put("key", key);
        result.put("change", change);
        result.put("value", value);
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
