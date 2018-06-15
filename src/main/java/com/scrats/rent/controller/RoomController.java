package com.scrats.rent.controller;

import com.alibaba.fastjson.JSON;
import com.scrats.rent.base.service.RedisService;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.common.annotation.APIRequestControl;
import com.scrats.rent.common.annotation.IgnoreSecurity;
import com.scrats.rent.entity.Building;
import com.scrats.rent.entity.Room;
import com.scrats.rent.entity.User;
import com.scrats.rent.service.BuildingService;
import com.scrats.rent.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/15 12:37.
 */
@Controller
@RequestMapping("/room")
public class RoomController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoomService roomService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private BuildingService buildingService;

    @IgnoreSecurity
    @GetMapping("/goRoom/{buildingId}")
    public String goRoom(String tokenId, @PathVariable(name="buildingId") Integer buildingId, Map<String, Object> map){

        User user = (User)redisService.get(tokenId);
        //获取所有房子select数据
        PageInfo<Building> pageInfo = buildingService.getBuildingListByUserId(1, 1, user.getUserId(), false);

        map.put("user",user);
        map.put("buildings",pageInfo.getList());

        return "landlord/room_list";
    }

    @PostMapping("/list")
    @ResponseBody
    public String list(@APIRequestControl APIRequest apiRequest, Integer buildingId) {

        PageInfo<Room> pageInfo = roomService.getRoomListByBuildingId(apiRequest.getPage(), apiRequest.getRows(), buildingId, true);

        return JSON.toJSONString(new JsonResult<List>(pageInfo.getList(), (int) pageInfo.getTotal()));
    }
}
