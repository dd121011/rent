package com.scrats.rent.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scrats.rent.common.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Created with jointstarc.
 * @Email: 262297088@qq.com
 * @Description:
 * @User: lol.
 * @Date: 2017/12/28 17:10.
 */
@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/tt")
    public JsonResult hello(){

        return new JsonResult();
    }

    @GetMapping("/testPage")
    public String testPage(){

        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        int id = 1000;

        for(int i = 0; i<30; i++  ){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",id++);
            jsonObject.put("username","user-" + i);
            jsonObject.put("sex",i%2==0 ? "女" : "男");
            jsonObject.put("city","城市-" + i);
            jsonObject.put("sign","签名-" + i);
            jsonObject.put("experience","签名-" + (255+ i));
            jsonObject.put("logins",40+ i);
            jsonObject.put("wealth",82830700);
            jsonObject.put("classify",i%2==0 ? "作家" : "诗人");
            jsonObject.put("score",85 - i);
            jsonArray.add(jsonObject);
        }

        json.put("code",0);
        json.put("msg","");
        json.put("count",1000);
        json.put("data",jsonArray);

        return json.toJSONString();
    }
}
