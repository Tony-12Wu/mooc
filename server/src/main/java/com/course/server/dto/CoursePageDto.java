package com.course.server.dto;

/**
 * @author JT
 */
public class CoursePageDto extends PageDto {

    private String status;

    private String categoryId;

    private String teacherId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CoursePageDto{");
        sb.append("status='").append(status).append('\'');
        sb.append(", categoryId='").append(categoryId).append('\'');
        sb.append(", teacherId='").append(teacherId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
