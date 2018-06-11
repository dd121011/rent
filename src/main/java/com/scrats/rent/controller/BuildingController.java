package com.scrats.rent.controller;

import com.alibaba.fastjson.JSON;
import com.scrats.rent.base.service.RedisService;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.common.annotation.APIRequestControl;
import com.scrats.rent.common.annotation.IgnoreSecurity;
import com.scrats.rent.entity.Building;
import com.scrats.rent.service.BuildingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:20.
 */
@Controller
@RequestMapping("/building")
public class BuildingController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private RedisService redisService;

    @IgnoreSecurity
    @GetMapping("/goBuilding")
    public String list(){
        return "landlord/building_list";
    }

    @PostMapping("/list")
    @ResponseBody
    public String list(@APIRequestControl APIRequest apiRequest) {

        PageInfo<Building> pageInfo = buildingService.getBuildingListByUserId(apiRequest.getPage(), apiRequest.getRows(), apiRequest.getUser().getUserId());

        return JSON.toJSONString(new JsonResult<List>(pageInfo.getList(), (int) pageInfo.getTotal()));
    }

}
