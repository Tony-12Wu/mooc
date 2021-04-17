package com.course.server.dto;


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
     * 下载地址
     */
    private String downloadpath;

    /**
     * 下载次数
     */
    private Integer frequency;

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

    public String getDownloadpath() {
        return downloadpath;
    }

    public void setDownloadpath(String downloadpath) {
        this.downloadpath = downloadpath;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", courseId=").append(courseId);
        sb.append(", url=").append(url);
        sb.append(", name=").append(name);
        sb.append(", size=").append(size);
        sb.append(", downloadpath=").append(downloadpath);
        sb.append(", frequency=").append(frequency);
        sb.append("]");
        return sb.toString();
    }

}