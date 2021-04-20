package com.course.server.dto;

/**
 * @author JT
 */
public class CoursePageDto extends PageDto {

    private String status;

    private String categoryId;

    private String teacherId;

    private String courseName;

    private String teacherName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

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
        return "CoursePageDto{" +
                "status='" + status + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
