package jp.gmo.project.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.gmo.project.dto.EmployeeDto;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Utils {

    public static String getMessage(String messageCode) {
        try {
            InputStream utf8in = Utils.class.getClassLoader().getResourceAsStream("i18n/messages.properties");
            Properties props = new Properties();
            if (utf8in != null) {
                Reader reader = new InputStreamReader(utf8in, StandardCharsets.UTF_8);
                props.load(reader);
            }
            return props.getProperty(messageCode);
        } catch (Exception e) {
            return "";
        }
    }

    public static String getUpdateBy(String email) {
        return email.split("@")[0];
    }

    public static <T> T convertJsonStringToObject(String jsonData, TypeReference<T> typeClass) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonData, typeClass);
    }

    public static <T> T convertJsonStringToObject(String jsonData, Class<T> typeClass) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonData, typeClass);
    }

    public static <T> List<T> findDuplicate(List<T> list) {

        return list.stream()
                .collect(Collectors.groupingBy(Function.identity()
                        , Collectors.counting()))    // create a map
                .entrySet().stream()                 // Map -> Stream
                .filter(m -> m.getValue() > 1)       // if map value > 1, duplicate element
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
