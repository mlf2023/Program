package com.ztt.mt.controller;

import com.ztt.mt.utils.QiniuUploadUtils;
import com.ztt.mt.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.channels.MulticastChannel;

@RestController
@RequestMapping("common")
@Slf4j
public class CommonController {
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        log.info("file:{}",file);
        String filename=file.getOriginalFilename();
        log.info("上传文件的名称：{}",filename);
        return QiniuUploadUtils.uploadImage(file);
    }
}
