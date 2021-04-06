package jp.gmo.project.utils.enums;

import org.apache.commons.lang3.StringUtils;

public enum RoleEnum {
    ADMIN(1, "Admin"),
    BRANCH_DIRECTOR(2, "Branch Director"),
    QA_EXECUTIVE(3, "QA Executive"),
    USER(4, "User");

    private final Integer value;
    private final String name;

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    private RoleEnum(Integer value, String name) {
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
