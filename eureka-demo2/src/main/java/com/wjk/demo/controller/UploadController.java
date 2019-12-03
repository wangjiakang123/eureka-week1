package com.wjk.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/file/")
public class UploadController {

    @Value("${file.path}")
    private String filePatch;

    @Value("${file.domain}")
    private String fileDomain;


    @RequestMapping("upload")
    public Object upload(@RequestParam(defaultValue = "file")MultipartFile file)throws IOException {
      String originalFilename=  file.getOriginalFilename();
      String fileName=  UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));
        File savefile=new File(filePatch+fileName);
        file.transferTo(savefile);
        Map<String,Object> reultMap=new HashMap<>();
        reultMap.put("result",true);
        reultMap.put("fileName",fileName);
        reultMap.put("fileUrl",fileDomain+fileName);
        return  reultMap;
    }

    @RequestMapping("delByFileName")
    public Object deal(@RequestParam("fileName") String fileName){
        File file=new File(filePatch+fileName);
        file.delete();
        Map<String,Object> resultMap=new HashMap<>();
        return resultMap;


    }


}
