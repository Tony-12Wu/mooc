package com.course.server.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author JT
 */

public class CourseDto {

    /**
     * id
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 概述
     */
    private String summary;

    /**
     * 时长|单位秒
     */
    private Integer time;

    /**
     * 封面
     */
    private String image;

    /**
     * 线上|枚举[CourseOnlineEnum]: ONLINE("0","线上"), Offline("1","线下")
     */
    private String online;

    /**
     * 状态|枚举[CourseStatusEnum]: PUBLISH("P","发布"), DRAFT("D","草稿")
     */
    private String status;

    /**
     * 类别|枚举[CourseCategoryEnum]: PUBLIC_ELECTIVE("0","校公选课"), PUBLIC_COMPULSORY("2","校公共必修课"), MAJOR_REQUIRED("3","专业必修课"),DEGREE("4","学位课")
     */
    private String category;

    /**
     * 报名数
     */
    private Integer enroll;

    /**
     * 顺序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createAt;

    /**
     * 修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateAt;

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getEnroll() {
        return enroll;
    }

    public void setEnroll(Integer enroll) {
        this.enroll = enroll;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", summary=").append(summary);
        sb.append(", time=").append(time);
        sb.append(", image=").append(image);
        sb.append(", online=").append(online);
        sb.append(", status=").append(status);
        sb.append(", category=").append(category);
        sb.append(", enroll=").append(enroll);
        sb.append(", sort=").append(sort);
        sb.append(", createAt=").append(createAt);
        sb.append(", updateAt=").append(updateAt);
        sb.append("]");
        return sb.toString();
    }

}