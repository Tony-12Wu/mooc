package com.course.file.controller.admin;

import com.course.server.dto.ResponseDto;
import com.course.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;


/**
 * @author JT
 */
@RequestMapping("/admin")
@RestController
public class UploadController {

    private static final Logger LOG = LoggerFactory.getLogger(UploadController.class);

    public static final String BUSINESS_NAME = "文件上传";

    @Value("${file.path}")
    private String FILE_PATH;

    @Value("${file.domain}")
    private String FILE_DOMAIN;

    @PostMapping("/upload")
    public ResponseDto upload(@RequestBody MultipartFile file) throws IOException {
        LOG.info("文件上传开始：{}", file);
        LOG.info(file.getOriginalFilename());
        LOG.info(String.valueOf(file.getSize()));
        //保存文件到本地
        String fileName = file.getOriginalFilename();
        String key = UuidUtil.getShortUuid();
        String fullPath = FILE_PATH +"teacher/" + key + "-" + fileName;
        File dest = new File(fullPath);
        file.transferTo(dest);
        LOG.info(dest.getAbsolutePath());

        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(FILE_DOMAIN + "teacher/" + key + "-" + fileName);
        return responseDto;
    }





}
