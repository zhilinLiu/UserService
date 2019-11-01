package com.kando.service.impl;

import com.kando.common.exception.MeioException;
import com.kando.common.utils.DisposeUtils;
import com.kando.service.SysOssService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class SysOssServiceImpl implements SysOssService {
    @Override
    public String upload(MultipartFile file) {
        String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String path = DisposeUtils.getPath(type);
        File file1=new File("/home/ftpuser/www/images/"+path);
        if(!file1.exists()){
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        byte bytes[];
        try {
            bytes=file.getBytes();
        } catch (IOException e) {
            throw new MeioException("文件解析错误"+e);
        }
        int b=bytes.length;   //是字节的长度，不是字符串的长度
        try{
            FileOutputStream fos=new FileOutputStream(file1);
            fos.write(bytes,0,b);
            fos.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return "http://192.168.50.171/"+path;
    }

    @Override
    public String uploadftp(MultipartFile file) {
        return null;
    }
}
