package com.course.server.service;

import com.course.server.domain.CourseResource;
import com.course.server.domain.CourseResourceExample;
import com.course.server.domain.Section;
import com.course.server.domain.SectionExample;
import com.course.server.dto.CourseResourceDto;
import com.course.server.dto.CourseResourcePageDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.SectionDto;
import com.course.server.mapper.CourseResourceMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author JT
 */
@Service
public class CourseResourceService {

    @Resource
    private CourseResourceMapper courseResourceMapper;

    /**
     * 列表查询
     */
    public void list(CourseResourcePageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CourseResourceExample courseResourceExample = new CourseResourceExample();
        courseResourceExample.createCriteria().andCourseIdEqualTo(pageDto.getCourseId());
        courseResourceExample.setOrderByClause("at desc");
        List<CourseResource> courseResourceList = courseResourceMapper.selectByExample(courseResourceExample);
        PageInfo<CourseResource> pageInfo = new PageInfo<>(courseResourceList);
        pageDto.setTotal(pageInfo.getTotal());
        List<CourseResourceDto> courseResourceDtoList = CopyUtil.copyList(courseResourceList, CourseResourceDto.class);
        pageDto.setList(courseResourceDtoList);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(CourseResourceDto courseResourceDto) {
        CourseResource courseResource = CopyUtil.copy(courseResourceDto, CourseResource.class);
        Date now = new Date();
        courseResource.setAt(now);
        this.insert(courseResource);
        courseResourceDto.setAt(now);
    }

    /**
     * 新增
     */
    private void insert(CourseResource courseResource) {
        courseResource.setId(UuidUtil.getShortUuid());
        courseResourceMapper.insert(courseResource);
    }

    /**
     * 更新
     */
    private void update(CourseResource courseResource) {
        courseResourceMapper.updateByPrimaryKey(courseResource);
    }

    /**
     * 更新下载次数
     */
    public CourseResourceDto updateFrequency(String id) {
        CourseResource courseResource = courseResourceMapper.selectByPrimaryKey(id);
        if (courseResource.getFrequency() == null){
            courseResource.setFrequency(0);
        }
        courseResource.setFrequency(courseResource.getFrequency()+1);
        courseResourceMapper.updateByPrimaryKey(courseResource);
        return CopyUtil.copy(courseResource, CourseResourceDto.class);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        courseResourceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询某一课程下的所有资源
     */
    public List<CourseResourceDto> listByCourse(String courseId) {
        CourseResourceExample example = new CourseResourceExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        List<CourseResource> courseResourceList = courseResourceMapper.selectByExample(example);
        List<CourseResourceDto> courseResourceDtoList = CopyUtil.copyList(courseResourceList, CourseResourceDto.class);
        return courseResourceDtoList;
    }
}
