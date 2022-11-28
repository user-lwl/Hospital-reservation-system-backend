package com.lwl.yygh.oss.controller;

import com.lwl.yygh.common.result.Result;
import com.lwl.yygh.oss.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author user-lwl
 * @createDate 2022/11/28 19:13
 */
@RestController
@RequestMapping("/api/oss/file")
public class FileApiController {
    @Resource
    private FileService fileService;

    /**
     * 上传文件到阿里云oss
     * @return 上传路径
     */
    @PostMapping("file/upload")
    public Result<String> fileUpload(MultipartFile file) {
        String url = fileService.upload(file);
        return Result.ok(url);
    }
}
