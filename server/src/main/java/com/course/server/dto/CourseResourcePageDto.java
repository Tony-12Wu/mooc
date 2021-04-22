package com.course.server.dto;

/**
 * @author JT
 */
public class CourseResourcePageDto extends PageDto {

    private String courseId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "CourseResourcePageDto{" +
                "courseId='" + courseId + '\'' +
                '}';
    }
}
