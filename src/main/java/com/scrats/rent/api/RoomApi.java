package com.scrats.rent.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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

import java.util.List;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/15 12:37.
 */
@RestController
@RequestMapping("/api/room")
public class RoomApi {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoomService roomService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private BuildingLandlordService buildingLandlordService;
    @Autowired
    private DictionaryItermService dictionaryItermService;
    @Autowired
    private RenterService renterService;
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private RoomRenterService roomRenterService;

    @PostMapping("/list/{buildingId}")
    public String list(@APIRequestControl APIRequest apiRequest, @PathVariable(name="buildingId") Integer buildingId, Room room) {
        room.setBuildingId(buildingId);
        PageInfo<Room> pageInfo = roomService.getRoomList(apiRequest, room);
        return JSON.toJSONString(new JsonResult<List>(pageInfo.getList(), (int) pageInfo.getTotal()));
    }

    @PostMapping("/edit")
    public String edit(@APIRequestControl APIRequest apiRequest, Room room, @RequestParam("facilityIds[]") String[] facilityIds, @RequestParam("extraIds[]") String[] extraIds) {

        String facilityId = StringUtils.join(facilityIds, ",");
        String extraId = StringUtils.join(extraIds, ",");
        room.setFacilities(facilityId);
        room.setExtraFee(extraId);

        if(null != room.getRoomId()){
            room.setUpdateTs(System.currentTimeMillis());
            roomService.updateByPrimaryKeySelective(room);
        }else{
            List<Room> rlist = roomService.getRoomByRoomNoAndBuildingId(room.getRoomNo(),room.getBuildingId());
            if(null != rlist && rlist.size() > 1){
                return JSON.toJSONString(new JsonResult<>("创建失败,该房间号已存在"));
            }
            room.setCreateTs(System.currentTimeMillis());
            roomService.insertSelective(room);
        }

        return JSON.toJSONString(new JsonResult());
    }

    @PostMapping("/delete")
    public String delete(@APIRequestControl APIRequest apiRequest, Integer... ids){
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
                return JSON.toJSONString(new JsonResult("删除失败"));
            }
        }

        roomService.deleteRoomByIds(ids);

        return JSON.toJSONString(new JsonResult());
    }

    @GetMapping("/detail/{roomId}")
    public String detail(@PathVariable(name="roomId") Integer roomId) {
        //获取所有房子select数据
        Room room = roomService.detail(roomId);
        if(null == room){
            return JSON.toJSONString(new JsonResult("获取房间详情失败"));
        }
        return JSON.toJSONString(new JsonResult<Room>(room));
    }

    @GetMapping("/renterAll/{roomId}")
    public String renterAll(@PathVariable(name="roomId") Integer roomId){

        List<RoomRenter> list = roomRenterService.findListBy("roomId", roomId);
        JSONArray jsonArray = new JSONArray();
        for(RoomRenter roomRenter : list){
            User user = userService.selectByPrimaryKey(roomRenter.getUserId());
            Account account = accountService.selectByPrimaryKey(user.getAccountId());
            Renter renter = renterService.selectByPrimaryKey(roomRenter.getRenterId());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("idCard",renter.getIdCard());
            jsonObject.put("user",user);
            jsonObject.put("phone",account.getPhone());
            jsonObject.put("roomRenterId",roomRenter.getRoomRenterId());
            jsonArray.add(jsonObject);
        }
        return JSON.toJSONString(new JsonResult<JSONArray>(jsonArray));
    }

    @GetMapping(value={"/buildingInfo/{roomId}"})
    public String buildingInfo(@PathVariable(name="roomId") Integer roomId){
        Building building = buildingService.getBuildingByRoomId(roomId);
        return JSON.toJSONString(new JsonResult<Building>(building));

    }

    @GetMapping(value={"/extras/{roomId}"})
    public String extras(@PathVariable(name="roomId") Integer roomId){
        Room room = roomService.selectByPrimaryKey(roomId);
        List<DictionaryIterm> extras = dictionaryItermService.selectByIds(room.getExtraFee());
        return JSON.toJSONString(new JsonResult<List>(extras));

    }

    @GetMapping(value={"/facilities/{roomId}"})
    public String facilities(@PathVariable(name="roomId") Integer roomId){
        Room room = roomService.selectByPrimaryKey(roomId);
        List<DictionaryIterm> facilities = dictionaryItermService.selectByIds(room.getFacilities());
        return JSON.toJSONString(new JsonResult<List>(facilities));

    }
}
