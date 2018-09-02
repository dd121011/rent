package com.scrats.rent.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.scrats.rent.base.service.RedisService;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.common.annotation.APIRequestControl;
import com.scrats.rent.entity.*;
import com.scrats.rent.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/22 12:42.
 */
@RestController
@RequestMapping("/bargin")
public class BarginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoomService roomService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private BarginService barginService;
    @Autowired
    private DictionaryItermService dictionaryItermService;
    @Autowired
    private BarginExtraService barginExtraService;
    @Autowired
    private UserService userService;
    @Autowired
    private DepositService depositService;
    @Autowired
    private DepositItermService depositItermService;

    @PostMapping("/list")
    public String list(@APIRequestControl APIRequest apiRequest) {
        Bargin bargin = JSON.parseObject(JSON.toJSONString(apiRequest.getBody()),Bargin.class);
        PageInfo<Bargin> pageInfo = barginService.getBarginList(apiRequest, bargin);
        return JSON.toJSONString(new JsonResult<List>(pageInfo.getList(), (int) pageInfo.getTotal()));
    }

    @GetMapping("/bargin/{barginId}")
    @ResponseBody
    public JsonResult bargin(@PathVariable(name="barginId") Integer barginId) {

        Bargin bargin = barginService.selectByPrimaryKey(barginId);
        Building building = buildingService.selectByPrimaryKey(bargin.getBuildingId());
        List<DictionaryIterm> facilities = new ArrayList<DictionaryIterm>();
        if(StringUtils.isNotEmpty(bargin.getFacilities())){
            facilities = dictionaryItermService.selectByIds(bargin.getFacilities());
        }
        List<BarginExtra> extras = barginExtraService.findListBy("barginId", bargin.getBarginId());
        Room room = roomService.selectByPrimaryKey(bargin.getRoomId());
        User landlord = userService.selectByPrimaryKey(bargin.getLandlordId());
        JSONObject result = new JSONObject();
        result.put("bargin",bargin);
        result.put("roomNo",room.getRoomNo());
        result.put("landlordName",landlord.getName());
        result.put("building",building);
        result.put("facilities",facilities);
        result.put("extras",extras == null ? new ArrayList<>() : extras);

        return new JsonResult(result);
    }

    @GetMapping("/deposit/{barginId}")
    public JsonResult deposit(@PathVariable(name="barginId") Integer barginId) {
        Deposit deposit = depositService.findBy("barginId", barginId);
        Bargin bargin = barginService.selectByPrimaryKey(barginId);
        List<DepositIterm> depositItermList = depositItermService.findListBy("depositId", deposit.getDepositId());
        deposit.setDepositItermList(depositItermList);
        Building building = buildingService.selectByPrimaryKey(deposit.getBuildingId());
        Room room = roomService.selectByPrimaryKey(deposit.getRoomId());
        JSONObject result = new JSONObject();
        result.put("deposit",deposit);
        result.put("bargin",bargin);
        result.put("roomNo",room.getRoomNo());
        result.put("building",building);

        return new JsonResult(result);
    }
}
