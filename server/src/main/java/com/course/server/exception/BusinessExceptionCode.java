package com.course.server.exception;

/**
 * @author jtwur
 */

public enum BusinessExceptionCode {

    MEMBER_NOT_EXIST("学生不存在"),
    USER_LOGIN_NAME_EXIST("登录名已存在"),
    ADMIN_NOT_DELETE("系统管理员不可删除"),
    ROLE_NOT_DELETE("系统角色不可删除"),
    LOGIN_USER_ERROR("用户名不存在或密码错误"),
    LOGIN_MEMBER_ERROR("手机号不存在或密码错误"),
    MOBILE_CODE_TOO_FREQUENT("短信请求过于频繁"),
    MOBILE_CODE_ERROR("短信验证码不正确"),
    MOBILE_CODE_EXPIRED("短信验证码不存在或已过期，请重新发送短信"),
    ;

    private String desc;

    BusinessExceptionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
