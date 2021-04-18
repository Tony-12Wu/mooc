package com.course.server.service;

import com.course.server.domain.CourseResource;
import com.course.server.domain.CourseResourceExample;
import com.course.server.dto.CourseResourceDto;
import com.course.server.dto.PageDto;
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
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CourseResourceExample courseResourceExample = new CourseResourceExample();
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
     * 删除
     */
    public void delete(String id) {
        courseResourceMapper.deleteByPrimaryKey(id);
    }
}
