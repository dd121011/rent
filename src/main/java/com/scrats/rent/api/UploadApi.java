package com.scrats.rent.api;

import com.alibaba.fastjson.JSONObject;
import com.scrats.rent.base.service.UploadService;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.annotation.IgnoreSecurity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/8/14 15:08.
 */
@Slf4j
@RestController
@RequestMapping("/api/upload")
public class UploadApi {

    @Autowired
    private UploadService uploadService;

    @Value("${qiniu.domain}")
    private String domain;

    @IgnoreSecurity
    @PostMapping("/upload")
    public JsonResult upload(MultipartFile file) throws IOException {
        String path = uploadService.upload(file);
        if(StringUtils.isEmpty(path)){
            return new JsonResult("上传错误, 请重试!");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("path",path);
        return new JsonResult<JSONObject>(jsonObject);
    }

    @IgnoreSecurity
    @GetMapping("/uploadToken")
    public JsonResult uploadToken() {
        String token = uploadService.getQiniuToken();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token",token);
        jsonObject.put("domain",domain);
        return new JsonResult<JSONObject>(jsonObject);
    }

}
