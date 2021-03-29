package com.course.server.dto;


/**
 * @author JT
 */
public class LoginUserDto {

    /**
     * id
     */
    private String id;

    /**
     * 昵称
     */
    private String name;

    /**
     * 账号名
     */
    private String loginName;

    /**
     * 权限 | 0为超级管理员，1位普通管理员
     */
    private Integer power;

    /**
     * 登录凭证
     */
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LoginUserDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", loginName='").append(loginName).append('\'');
        sb.append(", power=").append(power);
        sb.append(", token='").append(token).append('\'');
        sb.append('}');
        return sb.toString();
    }

}