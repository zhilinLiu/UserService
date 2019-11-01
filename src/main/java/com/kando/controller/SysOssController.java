package com.kando.controller;

import com.kando.dto.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("sys/oss")
public class SysOssController {
public Result upload(MultipartFile file){

    Result result = new Result();
    return result;
}
}
