package com.course.business.controller.admin;

import com.course.server.dto.ChapterDto;
import com.course.server.dto.ChapterPageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.ChapterService;
import com.course.server.util.ValidatorUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author JT
 */
@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {

    public static final String BUSINESS_NAME = "大章";

    @Resource
    private ChapterService chapterService;

    /**
     * 查询列表
     * @param chapterPageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody ChapterPageDto chapterPageDto){
        ResponseDto responseDto = new ResponseDto();
        ValidatorUtil.require(chapterPageDto.getCourseId(),"课程ID");
        chapterService.list(chapterPageDto);
        responseDto.setContent(chapterPageDto);
        return responseDto;
    }

    /**
     * 保存，有id时更新，无id时更新
     * @param chapterDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody ChapterDto chapterDto){
        //数据校验
        ValidatorUtil.require(chapterDto.getName(),"大章名称");
        ValidatorUtil.require(chapterDto.getCourseId(),"课程ID");
        ValidatorUtil.length(chapterDto.getCourseId(),"课程ID", 1, 8);

        ChapterDto chapterDto1 = chapterService.save(chapterDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(chapterDto1);
        return responseDto;
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        chapterService.delete(id);
        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }
}
