package com.scrats.rent.controller;

import com.alibaba.fastjson.JSON;
import com.scrats.rent.base.service.RedisService;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.common.annotation.APIRequestControl;
import com.scrats.rent.common.annotation.IgnoreSecurity;
import com.scrats.rent.common.exception.NotAuthorizedException;
import com.scrats.rent.entity.Building;
import com.scrats.rent.entity.Rent;
import com.scrats.rent.entity.Room;
import com.scrats.rent.entity.User;
import com.scrats.rent.service.BuildingService;
import com.scrats.rent.service.RentService;
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
 * @Date: 2018/7/15 23:11.
 */
@Controller
@RequestMapping("/rent")
public class RentController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoomService roomService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private RentService rentService;

    @IgnoreSecurity
    @GetMapping(value = {"/goRent/{userId}/{roomId}","/goRent/{userId}"})
    public String goRoom(String tokenId, @PathVariable(name="userId") Integer userId, @PathVariable(name="roomId", required = false) Integer roomId, Map<String, Object> map){

        User user = (User)redisService.get(tokenId);
        if(null == user || (userId -  user.getUserId() != 0)){
            throw new NotAuthorizedException("参数错误");
        }

        //获取所有房子select数据
        PageInfo<Building> buildingPage = buildingService.getBuildingListWithUserId(null, null, user.getUserId(), false);
        PageInfo<Room> roomPage = null;
        Integer buildingId = buildingPage.getList().get(0).getBuildingId();
        if(null == roomId){
            Room param = new Room();
            param.setBuildingId(buildingPage.getList().get(0).getBuildingId());
            roomPage = roomService.getRoomList(null, param, false);
            roomId = roomPage.getList().get(0).getRoomId();
        }else{
            Room room = roomService.selectByPrimaryKey(roomId);
            buildingId = room.getBuildingId();
        }

        map.put("user",user);
        map.put("roomId",roomId);
        map.put("buildingId",roomId);
        map.put("buildings",buildingPage.getList());
        map.put("rooms",roomPage.getList());

        return "landlord/rent_list";
    }

    @PostMapping("/list")
    @ResponseBody
    public String list(@APIRequestControl APIRequest apiRequest, Rent rent) {
        PageInfo<Rent> pageInfo = rentService.getRentPageList(apiRequest, rent);
        return JSON.toJSONString(new JsonResult<List>(pageInfo.getList(), (int) pageInfo.getTotal()));
    }

}
