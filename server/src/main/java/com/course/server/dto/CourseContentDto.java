package com.course.server.dto;


/**
 * @author JT
 */
public class CourseContentDto {

    /**
     * id
     */
    private String id;

    /**
     * 课程内容
     */
    private String courseContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", courseContent=").append(courseContent);
        sb.append("]");
        return sb.toString();
    }

}