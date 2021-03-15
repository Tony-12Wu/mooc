package com.course.server.enums;

/**
 * @author JT
 */

public enum CourseCategoryEnum {

    PUBLIC_ELECTIVE("0","校公选课"),
    PUBLIC_COMPULSORY("2","校公共必修课"),
    MAJOR_REQUIRED("3","专业必修课"),
    DEGREE("4","学位课");

    private String code;

    private String desc;

    CourseCategoryEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
