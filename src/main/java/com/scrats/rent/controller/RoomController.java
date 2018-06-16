package com.scrats.rent.controller;

import com.alibaba.fastjson.JSON;
import com.scrats.rent.base.service.RedisService;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.common.annotation.APIRequestControl;
import com.scrats.rent.common.annotation.IgnoreSecurity;
import com.scrats.rent.constant.GlobalConst;
import com.scrats.rent.entity.*;
import com.scrats.rent.service.BuildingService;
import com.scrats.rent.service.DictionaryItermService;
import com.scrats.rent.service.ExtraService;
import com.scrats.rent.service.RoomService;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private DictionaryItermService dictionaryItermService;
    @Autowired
    private ExtraService extraService;

    @IgnoreSecurity
    @GetMapping("/goRoom/{buildingId}")
    public String goRoom(String tokenId, @PathVariable(name="buildingId", required = false) Integer buildingId, Map<String, Object> map){

        User user = (User)redisService.get(tokenId);
        //获取所有房子select数据
        PageInfo<Building> pageInfo = buildingService.getBuildingListByUserId(1, 1, user.getUserId(), false);
        //获取所有房间朝向
        List<DictionaryIterm> orientations = dictionaryItermService.getDicItermByDicCode(GlobalConst.ORIENTATION_CODE);
        //获取所有装修情况
        List<DictionaryIterm> decorations = dictionaryItermService.getDicItermByDicCode(GlobalConst.DECORATION_CODE);
        //获取所有配套设施
        List<DictionaryIterm> facilities = dictionaryItermService.getDicItermByDicCode(GlobalConst.FACILITY_CODE);
        //获取所有额外收费项
        List<Extra> extras = extraService.selectAll();


        map.put("user",user);
        map.put("buildingId",buildingId);
        map.put("buildings",pageInfo.getList());
        map.put("orientations",orientations);
        map.put("decorations",decorations);
        map.put("extraList",extras);
        map.put("facilityList",facilities);

        return "landlord/room_list";
    }

    @PostMapping("/list/{buildingId}")
    @ResponseBody
    public String list(@APIRequestControl APIRequest apiRequest, @PathVariable(name="buildingId") Integer buildingId) {

        PageInfo<Room> pageInfo = roomService.getRoomListByBuildingId(apiRequest.getPage(), apiRequest.getRows(), buildingId, true);

        return JSON.toJSONString(new JsonResult<List>(pageInfo.getList(), (int) pageInfo.getTotal()));
    }

    @PostMapping("/edit")
    @ResponseBody
    public String edit(@APIRequestControl APIRequest apiRequest, Room room, String[] dicItermIds, String[] extraIds) {

        String dicItermId = StringUtils.join(dicItermIds, ",");
        String extraId = StringUtils.join(extraIds, ",");
        room.setFacilities(dicItermId);
        room.setExtraFee(extraId);

        if(null != room.getRoomId()){
            room.setUpdateTs(System.currentTimeMillis());
            roomService.updateByPrimaryKeySelective(room);
        }else{
            room.setCreateTs(System.currentTimeMillis());
            roomService.insertSelective(room);
        }

        return JSON.toJSONString(new JsonResult<>());
    }
}
