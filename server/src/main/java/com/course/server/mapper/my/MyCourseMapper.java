package com.course.server.mapper.my;


import com.course.server.dto.CourseDto;
import com.course.server.dto.CoursePageDto;
import com.course.server.dto.SortDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author JT
 */
public interface MyCourseMapper {

    /**
     *
     * @param pageDto
     * @return
     */
    List<CourseDto> list(@Param("pageDto") CoursePageDto pageDto);

    /**
     * 根据courseId更新课程时长
     * @param courseId
     * @return
     */
    int updateTime(@Param("courseId") String courseId);

    int updateSort(SortDto sortDto);

    int updateEnroll(int enroll, String courseId);

    int moveSortsBackward(SortDto sortDto);

    int moveSortsForward(SortDto sortDto);

    /**
     * 根据课程名模糊搜索，根据老师名精确搜索
     * @param teacherName
     * @return
     */
    List<CourseDto> search(@Param("teacherName") String teacherName);
}
