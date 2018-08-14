package com.scrats.rent.api;

import com.scrats.rent.base.service.UploadService;
import com.scrats.rent.common.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RestController
@RequestMapping("/api/upload")
public class UploadApi {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    @ResponseBody
    public JsonResult upload(MultipartFile file) throws IOException {
        String path = uploadService.upload(file);
        if(StringUtils.isEmpty(path)){
            return new JsonResult("上传错误, 请重试!");
        }
        return new JsonResult();
    }

}
