package com.course.server.service;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.ChapterPageDto;
import com.course.server.mapper.ChapterMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JT
 */
@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    /**
     * 列表查询
     * @param chapterPageDto
     */
    public void list(ChapterPageDto chapterPageDto){
        PageHelper.startPage(chapterPageDto.getPage(),chapterPageDto.getSize());
        ChapterExample chapterExample = new ChapterExample();
        ChapterExample.Criteria criteria = chapterExample.createCriteria();
        if (!StringUtils.isEmpty(chapterPageDto.getCourseId())){
            criteria.andCourseIdEqualTo(chapterPageDto.getCourseId());
        }
        chapterExample.setOrderByClause("sort asc");
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        chapterPageDto.setTotal(pageInfo.getTotal());
        List<ChapterDto> chapterDtoList = new ArrayList<ChapterDto>();
        chapterDtoList = CopyUtil.copyList(chapterList, ChapterDto.class);
        chapterPageDto.setList(chapterDtoList);
    }

    /**
     * 保存，有id时更新，无id时新增
     * @param chapterDto
     * @return
     */
    public ChapterDto save(ChapterDto chapterDto){
        Chapter chapter = CopyUtil.copy(chapterDto, Chapter.class);
        if (StringUtil.isEmpty(chapter.getId())){
            chapter = insert(chapter);
        }else {
            chapter = update(chapter);
        }
        return CopyUtil.copy(chapter, ChapterDto.class);
    }

    /**
     * 根据id删除
     * @param id
     */
    public void delete(String id){
        chapterMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增
     * @param chapter
     * @return
     */
    private Chapter insert(Chapter chapter){
        chapter.setId(UuidUtil.getShortUuid());
        chapterMapper.insert(chapter);
        return chapter;
    }

    /**
     * 更新
     * @param chapter
     * @return
     */
    private Chapter update(Chapter chapter){
        chapterMapper.updateByPrimaryKey(chapter);
        return chapter;
    }

    /**
     * 查询某一课程下的所有章
     */
    public List<ChapterDto> listByCourse(String courseId) {
        ChapterExample example = new ChapterExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        example.setOrderByClause("sort asc");
        List<Chapter> chapterList = chapterMapper.selectByExample(example);
        List<ChapterDto> chapterDtoList = CopyUtil.copyList(chapterList, ChapterDto.class);
        return chapterDtoList;
    }

}
