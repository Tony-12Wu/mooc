package com.course.server.service;

import com.course.server.domain.*;
import com.course.server.dto.*;
import com.course.server.enums.CourseStatusEnum;
import com.course.server.mapper.CourseContentMapper;
import com.course.server.mapper.CourseMapper;
import com.course.server.mapper.RoleUserMapper;
import com.course.server.mapper.my.MyCourseMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

/**
 * @author JT
 */
@Service
public class CourseService {

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private MyCourseMapper myCourseMapper;

    @Resource
    private CourseCategoryService courseCategoryService;

    @Resource
    private CourseContentMapper courseContentMapper;

    @Resource
    private TeacherService teacherService;

    @Resource
    private ChapterService chapterService;

    @Resource
    private SectionService sectionService;

    @Resource
    private RoleUserService roleUserService;

    /**
     * 新课列表查询，只查询已发布的，按创建日期倒序
     */
    public List<CourseDto> listByCourseId(CoursePageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CourseExample courseExample = new CourseExample();
        // 判断teacherId是否为讲师管理员，是的话则只查询讲师id的课程
        String teacherId = pageDto.getTeacherId();
        boolean isTeacherAdmin = roleUserService.isTeacherAdmin(teacherId);
        if (isTeacherAdmin){
            courseExample.createCriteria().andTeacherIdEqualTo(pageDto.getTeacherId());
        }
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        pageDto.setList(courseList);
        return CopyUtil.copyList(courseList, CourseDto.class);
    }

    /**
     * 列表查询
     */
    public void list(CoursePageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        List<CourseDto> courseDtoList = myCourseMapper.list(pageDto);
        PageInfo<CourseDto> pageInfo = new PageInfo<>(courseDtoList);
        pageDto.setTotal(pageInfo.getTotal());
        pageDto.setList(courseDtoList);
    }

    /**
     * 新课列表查询，只查询已发布的，按创建日期倒序
     */
    public List<CourseDto> listNew(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CourseExample courseExample = new CourseExample();
        courseExample.createCriteria().andStatusEqualTo(CourseStatusEnum.PUBLISH.getCode());
        courseExample.setOrderByClause("create_at desc");
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        return CopyUtil.copyList(courseList, CourseDto.class);
    }


    /**
     * 保存，id有值时更新，无值时新增
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(CourseDto courseDto) {
        Course course = CopyUtil.copy(courseDto, Course.class);
        if (StringUtils.isEmpty(courseDto.getId())) {
            this.insert(course);
        } else {
            this.update(course);
        }
        //批量保存课程
        courseCategoryService.saveBatch(course.getId(), courseDto.getCategorys());
    }

    /**
     * 新增
     */
    private void insert(Course course) {
        Date now = new Date();
        course.setCreateAt(now);
        course.setUpdateAt(now);
        course.setId(UuidUtil.getShortUuid());
        courseMapper.insert(course);
    }

    /**
     * 更新
     */
    private void update(Course course) {
        course.setUpdateAt(new Date());
        courseMapper.updateByPrimaryKey(course);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        courseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据courseId更新课程时长
     * @param courseId
     */
    public void updateTime(String courseId) {
        myCourseMapper.updateTime(courseId);
    }


    /**
     * 查找课程内容
     */
    public CourseContentDto findContent(String id) {
        CourseContent content = courseContentMapper.selectByPrimaryKey(id);
        if (content == null) {
            return null;
        }
        return CopyUtil.copy(content, CourseContentDto.class);
    }

    /**
     * 保存课程内容，包含新增和修改
     */
    public int saveContent(CourseContentDto contentDto) {
        CourseContent content = CopyUtil.copy(contentDto, CourseContent.class);
        int i = courseContentMapper.updateByPrimaryKeyWithBLOBs(content);
        if (i == 0) {
            i = courseContentMapper.insert(content);
        }
        return i;
    }

    /**
     * 排序
     * @param sortDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void sort(SortDto sortDto) {
        // 修改当前记录的排序值
        myCourseMapper.updateSort(sortDto);

        // 如果排序值变大
        if (sortDto.getNewSort() > sortDto.getOldSort()) {
            myCourseMapper.moveSortsForward(sortDto);
        }

        // 如果排序值变小
        if (sortDto.getNewSort() < sortDto.getOldSort()) {
            myCourseMapper.moveSortsBackward(sortDto);
        }
    }

    /**
     * 查找某一课程，供web模块用，只能查已发布的
     * @param id
     * @return
     */
    public CourseDto findCourseById(String id) {
        Course course = courseMapper.selectByPrimaryKey(id);
        if (course == null || !CourseStatusEnum.PUBLISH.getCode().equals(course.getStatus())) {
            return null;
        }
        CourseDto courseDto = CopyUtil.copy(course, CourseDto.class);

        // 查询内容
        CourseContent content = courseContentMapper.selectByPrimaryKey(id);
        if (content != null) {
            courseDto.setContent(content.getContent());
        }

        // 查找讲师信息
        TeacherDto teacherDto = teacherService.findById(courseDto.getTeacherId());
        courseDto.setTeacher(teacherDto);

        // 查找章信息
        List<ChapterDto> chapterDtoList = chapterService.listByCourse(id);
        courseDto.setChapters(chapterDtoList);

        // 查找节信息
        List<SectionDto> sectionDtoList = sectionService.listByCourse(id);
        courseDto.setSections(sectionDtoList);

        return courseDto;
    }


}
