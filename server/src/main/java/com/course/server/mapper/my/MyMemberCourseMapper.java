package com.course.server.mapper.my;


import org.apache.ibatis.annotations.Param;



/**
 * @author JT
 */
public interface MyMemberCourseMapper {


    /**
     * 根据courseId更新课程时长
     * @param courseId
     * @return
     */
    int countByCourseId(@Param("courseId") String courseId);


}
