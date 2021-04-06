package jp.gmo.project.utils.enums;

import org.apache.commons.lang3.StringUtils;

public enum PositionEnum {
    PROJECT_MANAGER(1, "Project Manager"),
    TEAM_LEADER(2, "Team Leader"),
    BRSE(3, "BrSE"),
    QA_EXECUTIVE(4, "QA Executive"),
    BUSINESS_ANALYST(5, "Business Analyst"),
    DEVELOPER(6, "Developer"),
    TESTER(7, "Tester"),
    COMTOR(8, "Comtor"),
    EDUCATE(9, "Đào tạo"),
    APPRENTICE(10, "Học việc"),
    ADMIN_EXECUTIVE(11, "Admin Executive"),
    HR_EXECUTIVE(12, "HR Executive"),
    NETWORK_ADMIN(13, "Network Administrator");

    private final Integer value;
    private final String name;

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    private PositionEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static PositionEnum get(String name) {
        for (PositionEnum e : PositionEnum.values()) {
            if (StringUtils.equals(name, e.getName())) {
                return e;
            }
        }

        throw new RuntimeException(" [not found] Mode for type contact info >>" + name);
    }
}
