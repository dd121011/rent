package com.scrats.rent.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scrats.rent.base.service.RedisService;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.common.annotation.APIRequestControl;
import com.scrats.rent.common.annotation.IgnoreSecurity;
import com.scrats.rent.constant.GlobalConst;
import com.scrats.rent.entity.*;
import com.scrats.rent.service.*;
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
    @Autowired
    private BuildingLandlordService buildingLandlordService;
    @Autowired
    private BarginService barginService;
    @Autowired
    private RenterService renterService;
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    @IgnoreSecurity
    @GetMapping("/goRoom/{buildingId}")
    public String goRoom(String tokenId, @PathVariable(name="buildingId") Integer buildingId, Map<String, Object> map){

        User user = (User)redisService.get(tokenId);
        //获取所有房子select数据
        PageInfo<Building> pageInfo = buildingService.getBuildingListWithUserId(null, null, user.getUserId(), false);
        //获取所有房间朝向
        List<DictionaryIterm> orientations = dictionaryItermService.findListBy("dicCode", GlobalConst.ORIENTATION_CODE);
        //获取所有装修情况
        List<DictionaryIterm> decorations = dictionaryItermService.findListBy("dicCode", GlobalConst.DECORATION_CODE);
        //获取所有配套设施
        List<DictionaryIterm> facilities = dictionaryItermService.findListBy("dicCode", GlobalConst.FACILITY_CODE);
        //获取所有额外收费项
        List<DictionaryIterm> extras = dictionaryItermService.findListBy("dicCode", GlobalConst.EXTRA_CODE);

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
    public String list(@APIRequestControl APIRequest apiRequest, @PathVariable(name="buildingId") Integer buildingId, Room room) {
        room.setBuildingId(buildingId);
        PageInfo<Room> pageInfo = roomService.getRoomList(apiRequest, room);
        return JSON.toJSONString(new JsonResult<List>(pageInfo.getList(), (int) pageInfo.getTotal()));
    }

    @PostMapping("/edit")
    @ResponseBody
    public JsonResult edit(@APIRequestControl APIRequest apiRequest, Room room, String[] facilityIds, String[] extraIds) {

        String facilityId = StringUtils.join(facilityIds, ",");
        String extraId = StringUtils.join(extraIds, ",");
        room.setFacilities(facilityId);
        room.setExtraFee(extraId);

        if(null != room.getRoomId()){
            room.setUpdateTs(System.currentTimeMillis());
            roomService.updateByPrimaryKeySelective(room);
        }else{
            room.setCreateTs(System.currentTimeMillis());
            roomService.insertSelective(room);
        }

        return new JsonResult();
    }

    @PostMapping("/delete")
    @ResponseBody
    public JsonResult delete(@APIRequestControl APIRequest apiRequest, Integer... ids){
        //校验是否是本人名下的删除
        List<BuildingLandlord> list = buildingLandlordService.findListBy("landlordId", apiRequest.getUser().getUserId());

        String roomIds = StringUtils.join(ids,",");
        List<Room> roomList = roomService.selectByIds(roomIds);

        for(Room room : roomList){
            boolean flag = true;
            for(BuildingLandlord buildingLandlord : list){
                if((buildingLandlord.getBuildingId() - room.getBuildingId()) == 0){
                    flag = false;
                    break;
                }
            }
            if(flag){
                return new JsonResult("删除失败");
            }
        }

        roomService.deleteRoomByIds(ids);

        return new JsonResult();
    }

    @PostMapping("/rent")
    @ResponseBody
    public JsonResult rentAdd(@APIRequestControl APIRequest apiRequest, Bargin bargin, String extras, String depositIterms) {
        //补齐landlordId字段
        bargin.setLandlordId(apiRequest.getUser().getUserId());

        //TODO 保存合同额外收费项，便于以后计算每月房租，另外还要获取水、电、三相电、天然气的初始读数
        List<Extra> extraList = JSON.parseArray(extras,Extra.class);

        //TODO 保存押金项和生成押金，填充bargin的guarantyFee字段和total字段
        List<DepositIterm> depositItermList = JSON.parseArray(depositIterms,DepositIterm.class);

        roomService.rent(bargin, extraList, depositItermList);

        return new JsonResult();
    }

    @IgnoreSecurity
    @GetMapping("/goRoomDetail/{roomId}")
    public String goRoomDetail(String tokenId, @PathVariable(name="roomId") Integer roomId, Map<String, Object> map){

        User user = (User)redisService.get(tokenId);

        Room room = roomService.selectByPrimaryKey(roomId);
        //获取房子
        Building building = buildingService.selectByPrimaryKey(room.getBuildingId());
        //获取房间朝向name
        DictionaryIterm orientation = dictionaryItermService.selectByPrimaryKey(room.getOrientation());
        //获取装修情况name
        DictionaryIterm decoration = dictionaryItermService.selectByPrimaryKey(room.getDecoration());
        //获取所有配套设施
        List<DictionaryIterm> facilities = dictionaryItermService.selectByIds(room.getFacilities());
        //获取所有额外收费项
        List<DictionaryIterm> extras = dictionaryItermService.selectByIds(room.getExtraFee());

        room.setBuilding(building);
        room.setOrientationName(orientation.getValue());
        room.setDecorationName(decoration.getValue());
        room.setFacilitiesIterm(facilities);
        room.setExtraFeeIterm(extras);

        map.put("user",user);
        map.put("room",room);

        return "landlord/room_detail";
    }

    @GetMapping("/renterAll/{roomId}")
    @ResponseBody
    public String renterAll(@PathVariable(name="roomId") Integer roomId){

        List<Renter> list = renterService.findListBy("roomId", roomId);
        JSONArray jsonArray = new JSONArray();
        for(Renter renter : list){
            User user = userService.selectByPrimaryKey(renter.getUserId());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("renter",renter);
            jsonObject.put("user",user);
            jsonArray.add(jsonObject);
        }
        return JSON.toJSONString(new JsonResult<List>(jsonArray));
    }

    @GetMapping("/extra/{roomId}")
    @ResponseBody
    public String extra(@PathVariable(name="roomId") Integer roomId){

        Room room = roomService.selectByPrimaryKey(roomId);
        //获取所有额外收费项
        List<DictionaryIterm> extras = dictionaryItermService.selectByIds(room.getExtraFee());
        return JSON.toJSONString(new JsonResult<List>(extras));
    }

    @GetMapping("/depositIterm/{roomId}")
    @ResponseBody
    public String depositIterm(@PathVariable(name="roomId") Integer roomId){

        Room room = roomService.selectByPrimaryKey(roomId);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","租金");
        jsonObject.put("unit","月");
        jsonObject.put("price",room.getRentFee());
        jsonObject.put("number",room.getGuaranty());
        jsonObject.put("money",room.getRentFee()*room.getGuaranty());
        jsonArray.add(jsonObject);

        return JSON.toJSONString(new JsonResult<JSONArray>(jsonArray));
    }

}
