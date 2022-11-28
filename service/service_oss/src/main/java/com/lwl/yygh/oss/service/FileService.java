package com.lwl.yygh.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author user-lwl
 * @createDate 2022/11/28 19:15
 */
public interface FileService {
    /**
     * 上传文件到阿里云oss
     * @param file 文件
     * @return 上传路径
     */
    String upload(MultipartFile file);
}
