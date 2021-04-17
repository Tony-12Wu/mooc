package com.course.business.controller.admin;

import com.course.server.dto.CourseResourceDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.CourseResourceService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author JT
 */
@RestController
@RequestMapping("/admin/courseResource")
public class CourseResourceController {

    private static final Logger LOG = LoggerFactory.getLogger(CourseResourceController.class);
    public static final String BUSINESS_NAME = "课程资源表";

    @Resource
    private CourseResourceService courseResourceService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        courseResourceService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody CourseResourceDto courseResourceDto) {
        // 保存校验
        ValidatorUtil.require(courseResourceDto.getCourseId(), "课程id");
        ValidatorUtil.length(courseResourceDto.getCourseId(), "课程id", 1, 100);
        ValidatorUtil.length(courseResourceDto.getUrl(), "相对地址", 1, 100);
        ValidatorUtil.length(courseResourceDto.getName(), "文件名", 1, 100);
        ValidatorUtil.length(courseResourceDto.getDownloadpath(), "下载地址", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        courseResourceService.save(courseResourceDto);
        responseDto.setContent(courseResourceDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        courseResourceService.delete(id);
        return responseDto;
    }
}
