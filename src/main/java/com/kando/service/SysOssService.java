package com.kando.service;

import org.springframework.web.multipart.MultipartFile;

public interface SysOssService {
    /**
     * 上传图片(本地)
     * @param file 上传文件
     * @return url 返回地址
     */
    String upload(MultipartFile file);
    /**
     * 上传图片(ftp)
     * @param file 上传文件
     * @return url 返回地址
     */
    String uploadftp(MultipartFile file);
}
