package jp.gmo.project.constant;

public class RegexConstants {
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
    public static final String PASSWORD_REGEX = "^[a-zA-Z0-9@%+\\/'!#$^?:.(){}\\[\\]~\\-_.]*$";
    public static final String NUMBER_REGEX = "^[0-9]$";
    public static final String EMPLOYEES_CODE_REGEX = "^[0-9]*$";
    public static final String DATE_REGEX = "^[0-9]{4}(-)(((0)[1-9])|((1)[0-2]))(-)([0-2][1-9]|[1-2][0-9]|(3)[0-1])$";
    public final static String CONST_STR_PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
}
