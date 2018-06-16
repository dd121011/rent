package com.scrats.rent.api;

import com.alibaba.fastjson.JSON;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.constant.GlobalConst;
import com.scrats.rent.entity.Dictionary;
import com.scrats.rent.entity.DictionaryIterm;
import com.scrats.rent.entity.Extra;
import com.scrats.rent.service.DictionaryItermService;
import com.scrats.rent.service.DictionaryService;
import com.scrats.rent.service.ExtraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:20.
 */
@RestController
@RequestMapping("/api/dic")
public class DictionaryApi {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private DictionaryItermService dictionaryItermService;
    @Autowired
    private ExtraService extraService;

    @PostMapping("/dicListAll")
    public String dicListAll() {

        List<Dictionary> list = dictionaryService.selectAll();

        return JSON.toJSONString(new JsonResult<List>(list));
    }

    @PostMapping("/dicItermListAll")
    public String dicItermListAll(int dicId) {
        List<DictionaryIterm> list;
        if(dicId > 0){
            list = dictionaryItermService.getDicItermByDicId(dicId);
        }else{
            list = dictionaryItermService.selectAll();
        }
        return JSON.toJSONString(new JsonResult<List>(list));
    }

    @PostMapping("/extrasAll")
    public String extrasAll() {
        List<Extra> extras = extraService.selectAll();
        return JSON.toJSONString(new JsonResult<List>(extras));
    }

    @PostMapping("/facilitiestAll")
    public String facilitiestAll() {
        List<DictionaryIterm> facilities = dictionaryItermService.getDicItermByDicCode(GlobalConst.FACILITY_CODE);
        return JSON.toJSONString(new JsonResult<List>(facilities));
    }

    @PostMapping("/decorationAll")
    public String decorationAll() {
        List<DictionaryIterm> facilities = dictionaryItermService.getDicItermByDicCode(GlobalConst.DECORATION_CODE);
        return JSON.toJSONString(new JsonResult<List>(facilities));
    }

    @PostMapping("/orientationAll")
    public String orientationAll() {
        List<DictionaryIterm> facilities = dictionaryItermService.getDicItermByDicCode(GlobalConst.ORIENTATION_CODE);
        return JSON.toJSONString(new JsonResult<List>(facilities));
    }

}
