package com.course.server.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author JT
 */
public class CourseResourceDto {

    /**
     * id
     */
    private String id;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 相对地址
     */
    private String url;

    /**
     * 文件名
     */
    private String name;

    /**
     * 大小|字节B
     */
    private Integer size;

    /**
     * 下载次数
     */
    private Integer frequency;

    /**
     *  上传时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date at;

    public Date getAt() {
        return at;
    }

    public void setAt(Date at) {
        this.at = at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CourseResourceDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", courseId='").append(courseId).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", size=").append(size);
        sb.append(", frequency=").append(frequency);
        sb.append(", at=").append(at);
        sb.append('}');
        return sb.toString();
    }

}