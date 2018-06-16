package com.scrats.rent.controller;

import com.scrats.rent.common.JsonResult;
import com.scrats.rent.constant.GlobalConst;
import com.scrats.rent.entity.Dictionary;
import com.scrats.rent.entity.DictionaryIterm;
import com.scrats.rent.service.DictionaryItermService;
import com.scrats.rent.service.DictionaryService;
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
@RequestMapping("/dic")
public class DictionaryController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private DictionaryItermService dictionaryItermService;


    @PostMapping("/dicListAll")
    public JsonResult dicListAll() {

        List<Dictionary> list = dictionaryService.selectAll();

        return new JsonResult<List>(list);
    }

    @PostMapping("/dicItermListAll")
    public JsonResult dicItermListAll(int dicId) {
        List<DictionaryIterm> list;
        if(dicId > 0){
            list = dictionaryItermService.getDicItermByDicId(dicId);
        }else{
            list = dictionaryItermService.selectAll();
        }
        return new JsonResult<List>(list);
    }

    @PostMapping("/facilitiestAll")
    public JsonResult facilitiestAll() {
        List<DictionaryIterm> facilities = dictionaryItermService.getDicItermByDicCode(GlobalConst.FACILITY_CODE);
        return new JsonResult<List>(facilities);
    }

    @PostMapping("/decorationAll")
    public JsonResult decorationAll() {
        List<DictionaryIterm> facilities = dictionaryItermService.getDicItermByDicCode(GlobalConst.DECORATION_CODE);
        return new JsonResult<List>(facilities);
    }

    @PostMapping("/orientationAll")
    public JsonResult orientationAll() {
        List<DictionaryIterm> facilities = dictionaryItermService.getDicItermByDicCode(GlobalConst.ORIENTATION_CODE);
        return new JsonResult<List>(facilities);
    }

}
