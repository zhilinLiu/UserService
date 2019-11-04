package com.kando.controller;

import com.kando.common.IdWorker;
import com.kando.common.exception.MeioException;
import com.kando.configuration.FTPProperties;
import com.kando.configuration.FTPUtils;
import com.kando.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("sys/oss")
public class SysOssController {
    @Autowired
    IdWorker idWorker;
    @Autowired
    FTPProperties ftpProperties;

    @RequestMapping("/upload")
    public Result upload(MultipartFile file) {
        Result result = new Result();
        try {
            InputStream inputStream = file.getInputStream();
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String name = idWorker.nextId()+suffix;
            result.setSuccess(FTPUtils.uploadFile(name, inputStream));
            String path = ftpProperties.getAgentPath()+name;
            result.setData(path);
        } catch (IOException e) {
            throw new MeioException(408,"上传错误");
        }

        return result;
    }
}
