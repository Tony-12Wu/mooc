package com.course.file.controller.web;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import com.course.file.controller.admin.FileController;
import com.course.server.dto.CourseResourceDto;
import com.course.server.dto.FileDto;
import com.course.server.dto.ResponseDto;
import com.course.server.enums.FileUseEnum;
import com.course.server.service.CourseResourceService;
import com.course.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.File;

/**
 * @author JT
 */
@RestController
@RequestMapping("/web")
public class OssDownLoadController {

    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);

    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.bucket}")
    private String bucket;

    @Value("${oss.domain}")
    private String ossDomain;

    private String targetDir = "D:\\imooc\\file\\download\\";

    public static final String BUSINESS_NAME = "文件下载";

    @Resource
    private CourseResourceService courseResourceService;

    @GetMapping("/oss-download")
    public ResponseDto fileDownload(@RequestParam String filePath, @RequestParam String fileName, @RequestParam String id) throws Throwable {
        LOG.info("文件下载开始");
        // 填写Bucket名称。
        String bucketName = bucket;
        String objectName = filePath;
        //如果文件夹不存在则创建
        File fullDir = new File(targetDir);
        if (!fullDir.exists()) {
            fullDir.mkdirs();
        }
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 下载Object到本地文件，并保存到指定的本地路径中。如果指定的本地文件存在会覆盖，不存在则新建。
        // 如果未指定本地路径，则下载后的文件默认保存到示例程序所属项目对应本地路径中。
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(fullDir+"/"+fileName));

        // 关闭OSSClient。
        ossClient.shutdown();

        LOG.info("文件下载完成");
        LOG.info("下载次数更新完成");
        CourseResourceDto courseResourceDto = courseResourceService.updateFrequency(id);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(courseResourceDto);
        return responseDto;
    }



    @PostMapping("/oss-simple")
    public ResponseDto fileUpload(@RequestParam MultipartFile file, String use) throws Exception {
        LOG.info("上传文件开始");
        FileUseEnum useEnum = FileUseEnum.getByCode(use);
        String key = UuidUtil.getShortUuid();
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        String dir = useEnum.name().toLowerCase();
        String path = dir + "/" + key + "." + suffix;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建PutObjectRequest对象。
//        String content = "Hello OSS";
        // <yourObjectName>表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, path, new ByteArrayInputStream(file.getBytes()));

        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        // ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // metadata.setObjectAcl(CannedAccessControlList.Private);
        // putObjectRequest.setMetadata(metadata);

        // 上传字符串。
        ossClient.putObject(putObjectRequest);

//        LOG.info("保存文件记录开始");
//        fileDto.setPath(path);
//        fileService.save(fileDto);

        ResponseDto responseDto = new ResponseDto();
        FileDto fileDto = new FileDto();
        fileDto.setPath(ossDomain + path);
        responseDto.setContent(fileDto);

        return responseDto;
    }
}
