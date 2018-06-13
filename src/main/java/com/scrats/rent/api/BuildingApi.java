package com.scrats.rent.api;

import com.alibaba.fastjson.JSON;
import com.scrats.rent.base.service.RedisService;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.common.annotation.APIRequestControl;
import com.scrats.rent.constant.GlobalConst;
import com.scrats.rent.entity.Building;
import com.scrats.rent.entity.BuildingLandlord;
import com.scrats.rent.entity.DictionaryIterm;
import com.scrats.rent.entity.Extra;
import com.scrats.rent.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/7 23:30.
 */
@RestController
@RequestMapping("/api/building")
public class BuildingApi {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private DictionaryItermService dictionaryItermService;

    @Autowired
    private ExtraService extraService;

    @Autowired
    private BuildingLandlordService buildingLandlordService;


    @PostMapping("/list")
    public String list(@APIRequestControl APIRequest apiRequest) {
        PageInfo<Building> pageInfo = buildingService.getBuildingListByUserId(apiRequest.getPage(), apiRequest.getRows(), apiRequest.getUser().getUserId());

        return JSON.toJSONString(new JsonResult<List>(pageInfo.getList(), (int) pageInfo.getTotal()));
    }

    @PostMapping("/extrasAll")
    public String extrasAll() {
        List<Extra> extras = extraService.selectAll();
        return JSON.toJSONString(new JsonResult<List>(extras));
    }

    @PostMapping("/facilitiestAll")
    public String extras() {
        List<DictionaryIterm> facilities = dictionaryItermService.getDicItermByDicCode(GlobalConst.FACILITY_CODE);
        return JSON.toJSONString(new JsonResult<List>(facilities));
    }

    @PostMapping("/edit")
    public String edit(@APIRequestControl APIRequest apiRequest, Building building) {

        if(null != building.getBuildingId()){
            buildingService.updateByPrimaryKeySelective(building);
        }else{
            building.setCreateTs(System.currentTimeMillis());
            buildingService.insertSelective(building);
            BuildingLandlord buildingLandlord = new BuildingLandlord(apiRequest.getUser().getUserId(), building.getBuildingId());
            buildingLandlordService.insertSelective(buildingLandlord);
        }

        return JSON.toJSONString(new JsonResult<>());
    }
}
