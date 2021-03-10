package com.course.business.controller.admin;

import com.course.server.domain.Chapter;
import com.course.server.dto.ChapterDto;
import com.course.server.service.ChapterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JT
 */
@RestController
@RequestMapping("/admin")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @RequestMapping("/chapter")
    public List<ChapterDto> chapter(){
        return chapterService.list();
    }

    @RequestMapping("/success1")
    public String success(){
        return "success1";
    }
}