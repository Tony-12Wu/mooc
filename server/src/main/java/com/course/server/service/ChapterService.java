package com.course.server.service;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.mapper.ChapterMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JT
 */
@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    public List<Chapter> list(){
        ChapterExample chapterExample = new ChapterExample();
        chapterExample.setOrderByClause("id desc");
        return chapterMapper.selectByExample(chapterExample);
    }

    public List<Chapter> selectOneChapter(){
        ChapterExample chapterExample = new ChapterExample();
        chapterExample.createCriteria().andIdEqualTo("1");
        return chapterMapper.selectByExample(chapterExample);
    }
}
