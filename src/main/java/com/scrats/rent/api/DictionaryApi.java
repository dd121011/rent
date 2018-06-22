package com.scrats.rent.api;

import com.alibaba.fastjson.JSON;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.constant.GlobalConst;
import com.scrats.rent.entity.Dictionary;
import com.scrats.rent.entity.DictionaryIterm;
import com.scrats.rent.service.DictionaryItermService;
import com.scrats.rent.service.DictionaryService;
import com.scrats.rent.service.ExtraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/dicListAll")
    public String dicListAll() {

        List<Dictionary> list = dictionaryService.selectAll();

        return JSON.toJSONString(new JsonResult<List>(list));
    }

    @GetMapping("/dicItermListAll")
    public String dicItermListAll(String dicCode) {
        List<DictionaryIterm> list = dictionaryItermService.findListBy("dicCode", dicCode);
        return JSON.toJSONString(new JsonResult<List>(list));
    }

    @GetMapping("/facilitiestAll")
    public String facilitiestAll() {
        List<DictionaryIterm> facilities = dictionaryItermService.findListBy("dicCode", GlobalConst.FACILITY_CODE);
        return JSON.toJSONString(new JsonResult<List>(facilities));
    }

    @GetMapping("/decorationAll")
    public String decorationAll() {
        List<DictionaryIterm> decorations = dictionaryItermService.findListBy("dicCode", GlobalConst.DECORATION_CODE);
        return JSON.toJSONString(new JsonResult<List>(decorations));
    }

    @GetMapping("/orientationAll")
    public String orientationAll() {
        List<DictionaryIterm> orientations = dictionaryItermService.findListBy("dicCode", GlobalConst.ORIENTATION_CODE);;
        return JSON.toJSONString(new JsonResult<List>(orientations));
    }

    @GetMapping("/extrasAll")
    public String extrasAll() {
        List<DictionaryIterm> extras = dictionaryItermService.findListBy("dicCode", GlobalConst.EXTRA_CODE);
        return JSON.toJSONString(new JsonResult<List>(extras));
    }

}
