package com.scrats.rent.controller;

import com.alibaba.fastjson.JSON;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.annotation.APIRequestControl;
import com.scrats.rent.entity.Dictionary;
import com.scrats.rent.entity.DictionaryIterm;
import com.scrats.rent.service.DictionaryItermService;
import com.scrats.rent.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:20.
 */
@RestController
@RequestMapping("/dic")
public class DictionaryController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private DictionaryItermService dictionaryItermService;


    @PostMapping("/list")
    public String list() {

        List<Dictionary> list = dictionaryService.selectAll();

        return JSON.toJSONString(new JsonResult<List>(list));
    }

    @PostMapping("/dicItermList")
    public String dicItermList(@APIRequestControl APIRequest apiRequest) {
        List<DictionaryIterm> list = null;
        int dicId = APIRequest.getParameterValue(apiRequest,"dicId",0,Integer.class);
        if(dicId > 0){
            list = dictionaryItermService.getDicItermByDicId(dicId);
        }else{
            list = dictionaryItermService.selectAll();
        }
        return JSON.toJSONString(new JsonResult<List>(list));
    }

}
