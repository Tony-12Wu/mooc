package com.course.server.enums;

/**
 * @author JT
 */

public enum CourseOnlineEnum {

    ONLINE("0","线上"),
    Offline("1","线下");

    private String code;

    private String desc;

    CourseOnlineEnum(String code, String desc) {
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
