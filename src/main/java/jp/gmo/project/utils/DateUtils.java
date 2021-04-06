package jp.gmo.project.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final Logger log = LoggerFactory.getLogger(DateUtils.class);

    public static boolean isDateValid(String str, String pattern) {

        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(pattern)) {
            return false;
        }
        try {
            DateFormat dateFormat = new SimpleDateFormat(pattern);
            dateFormat.setLenient(false);
            dateFormat.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static LocalDate convertStringToLocalDate(String date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(date, formatter);
    }

    public static LocalDate stringToDate(String str, String pattern) {

        if (StringUtils.isAllEmpty(str, pattern)) {
            return null;
        }
        try {
            DateTimeFormatter  dateFormat = DateTimeFormatter.ofPattern(pattern);

            return LocalDate.parse(str, dateFormat);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
