package com.heima.controller;

import com.heima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {


    @PostMapping("/upload")
    public Result upload(String name, MultipartFile image) throws IOException {
        String fileName = image.getName();

        String originalFilename = image.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        log.info("尾缀序号 : {} , 原始档名 : {}", index, originalFilename);
        String suffix = originalFilename.substring(index);


        image.transferTo(new File("C:\\Users\\j0981\\OneDrive\\桌面\\javademo\\"+ UUID.randomUUID().toString() + suffix));

        log.info("传送人姓名: {} 挡案名 : {}", name, fileName);

        return Result.success("上传成功!档名 : " + UUID.randomUUID().toString() + suffix,"");
    }
}
