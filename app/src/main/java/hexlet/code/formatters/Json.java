package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class Json {
    public static final ObjectMapper MAPPER = new ObjectMapper();
    public static String format(List<Map<String, Object>> diffList) throws JsonProcessingException {
        return MAPPER.writeValueAsString(diffList);
    }
}
