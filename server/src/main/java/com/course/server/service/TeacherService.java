package com.course.server.service;

import com.course.server.domain.Teacher;
import com.course.server.domain.TeacherExample;
import com.course.server.domain.Teacher;
import com.course.server.domain.TeacherExample;
import com.course.server.dto.CoursePageDto;
import com.course.server.dto.TeacherDto;
import com.course.server.dto.TeacherDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.TeacherMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JT
 */
@Service
public class TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private RoleUserService roleUserService;

    /**
     * 列表查询
     * 管理员为讲师管理员时，查询该讲师用户的信息
     */
    public void list(CoursePageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        TeacherExample teacherExample = new TeacherExample();
        // 判断teacherId是否为讲师管理员，是的话则只查询讲师id的课程
        String teacherId = pageDto.getTeacherId();
        boolean isTeacherAdmin = roleUserService.isTeacherAdmin(teacherId);
        if (isTeacherAdmin){
            teacherExample.createCriteria().andIdEqualTo(pageDto.getTeacherId());
        }
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
        PageInfo<Teacher> pageInfo = new PageInfo<>(teacherList);
        pageDto.setTotal(pageInfo.getTotal());
        List<TeacherDto> teacherDtoList = CopyUtil.copyList(teacherList, TeacherDto.class);
        pageDto.setList(teacherDtoList);
    }

    /**
     * 查询所有
     * 管理员为讲师管理员时，查询该讲师用户的信息
     */
    public List<TeacherDto> all(CoursePageDto pageDto) {
        TeacherExample teacherExample = new TeacherExample();
        // 判断teacherId是否为讲师管理员，是的话则只查询讲师id的课程
        String teacherId = pageDto.getTeacherId();
        boolean isTeacherAdmin = roleUserService.isTeacherAdmin(teacherId);
        if (isTeacherAdmin){
            teacherExample.createCriteria().andIdEqualTo(pageDto.getTeacherId());
        }
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
        pageDto.setList(teacherList);
        return CopyUtil.copyList(teacherList, TeacherDto.class);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(TeacherDto teacherDto) {
        Teacher teacher = CopyUtil.copy(teacherDto, Teacher.class);
        if (StringUtils.isEmpty(teacherDto.getId())) {
            this.insert(teacher);
        } else {
            this.update(teacher);
        }
    }

    /**
     * 新增
     */
    private void insert(Teacher teacher) {
        teacher.setId(UuidUtil.getShortUuid());
        teacherMapper.insert(teacher);
    }

    /**
     * 更新
     */
    private void update(Teacher teacher) {
        teacherMapper.updateByPrimaryKey(teacher);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        teacherMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查找
     * @param id
     */
    public TeacherDto findById(String id) {
        Teacher teacher = teacherMapper.selectByPrimaryKey(id);
        return CopyUtil.copy(teacher, TeacherDto.class);
    }
}
