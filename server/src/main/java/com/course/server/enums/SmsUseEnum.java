package com.course.server.enums;

/**
 * @author JT
 */

public enum SmsUseEnum {

    REGISTER("R", "注册"),
    FORGET("F", "找回密码");

    private String code;

    private String desc;

    SmsUseEnum(String code, String desc) {
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
