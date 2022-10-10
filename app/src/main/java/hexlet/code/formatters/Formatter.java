package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String defineFormat(List<Map<String, Object>> diffList, String format)
            throws JsonProcessingException {
        switch (format) {
            case ("stylish"):
                return Stylish.format(diffList);
            case ("plain"):
                return Plain.format(diffList);
            case ("json"):
                return Json.format(diffList);
        }
        throw new Error("Unknown format: " + format);
    }
}
