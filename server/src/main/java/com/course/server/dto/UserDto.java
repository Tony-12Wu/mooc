package com.course.server.dto;


/**
 * @author JT
 */
public class UserDto {

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
     * 密码
     */
    private String password;

    /**
     * 权限 | 0为超级管理员，1位普通管理员
     */
    private Integer power;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", loginName=").append(loginName);
        sb.append(", password=").append(password);
        sb.append(", power=").append(power);
        sb.append("]");
        return sb.toString();
    }

}