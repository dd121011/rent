package com.scrats.rent.api;

import com.alibaba.fastjson.JSONObject;
import com.scrats.rent.base.service.RedisService;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.annotation.APIRequestControl;
import com.scrats.rent.common.annotation.IgnoreSecurity;
import com.scrats.rent.common.exception.BusinessException;
import com.scrats.rent.constant.GlobalConst;
import com.scrats.rent.entity.*;
import com.scrats.rent.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/23 15:43.
 */
@RestController
@RequestMapping("/api/renter")
public class RenterApi {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private RenterService renterService;
    @Autowired
    private BarginService barginService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private WxSnsService wxSnsService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private DictionaryItermService dictionaryItermService;
    @Autowired
    private BarginExtraService barginExtraService;
    @Autowired
    private RoomRenterService roomRenterService;
    @Autowired
    private DepositService depositService;
    @Autowired
    private DepositItermService depositItermService;

    @IgnoreSecurity
    @PostMapping("/snsLogin")
    public JsonResult snsLogin(String code, String signature, String rawData){
        if(StringUtils.isEmpty(code)){
            return new JsonResult("获取的微信code为空");
        }
        return renterService.snsLogin(code, signature, rawData);
    }

    @IgnoreSecurity
    @PostMapping("/bindUser")
    public JsonResult bindUser(String tokenId, String openid, Integer roomId){

        String checkTokeId = (String) redisService.get(openid);
        if(StringUtils.isEmpty(checkTokeId)){
            return new JsonResult("请求的openid有误");
        }
        if(!checkTokeId.equals(tokenId)){
            return new JsonResult("请求的tokenId有误");
        }

        List<Bargin> barginList = barginService.getBarginByRoomId(roomId, false);
        if(null == barginList || barginList.size()>1){
            return new JsonResult("该房间还未出租,无法绑定");
        }
        User user = userService.selectByPrimaryKey(barginList.get(0).getUserId());
        WxSns wxSns = wxSnsService.selectByPrimaryKey(openid);
        wxSns.setUserId(user.getUserId());
        wxSns.setUpdateTs(System.currentTimeMillis());
        wxSnsService.updateByPrimaryKeySelective(wxSns);
        redisService.set(tokenId,user, GlobalConst.ACCESS_TOKEN_EXPIRE);
        return new JsonResult<User>(user);
    }

    @GetMapping("/roomList")
    public JsonResult roomList(@APIRequestControl APIRequest apiRequest){

        List<RoomRenter> rrlist = roomRenterService.findListBy("userId", apiRequest.getUser().getUserId());
        HashSet<Integer> roomIdSet = new HashSet<>();
        for(RoomRenter rr :  rrlist){
            roomIdSet.add(rr.getRoomId());
        }
        List<Room> list = new ArrayList<Room>();
        for(Integer id : roomIdSet){
            Room room = roomService.detail(id);
            list.add(room);
        }

        return new JsonResult<List>(list);
    }

    @GetMapping(value={"/bargin/{roomId}"})
    public JsonResult bargin(@APIRequestControl APIRequest apiRequest, @PathVariable(name="roomId") Integer roomId){
        List<Bargin> list = barginService.getBarginByRoomId(roomId, false);
        Bargin bargin = list.get(0);
        List<RoomRenter> rrlist = roomRenterService.findListBy("userId", apiRequest.getUser().getUserId());
        for(RoomRenter rr :  rrlist){
            if(rr.getRoomId() - bargin.getRoomId() == 0 ){
                Building building = buildingService.selectByPrimaryKey(bargin.getBuildingId());
                List<DictionaryIterm> facilities = new ArrayList<DictionaryIterm>();
                if(StringUtils.isNotEmpty(bargin.getFacilities())){
                    facilities = dictionaryItermService.selectByIds(bargin.getFacilities());
                }
                List<BarginExtra> extras = barginExtraService.findListBy("barginId", bargin.getBarginId());
                JSONObject result = new JSONObject();
                result.put("bargin",bargin);
                result.put("building",building);
                result.put("facilities",facilities);
                result.put("extras",extras == null ? new ArrayList<>() : extras);
                return new JsonResult<JSONObject>(result);
            }
        }

        throw new BusinessException("数据有误");

    }

    @GetMapping(value={"/deposit/{roomId}"})
    public JsonResult deposit(@APIRequestControl APIRequest apiRequest, @PathVariable(name="roomId") Integer roomId){
        List<Deposit> list = depositService.getDepositByRoomId(roomId, false);
        Deposit deposit = list.get(0);
        List<RoomRenter> rrlist = roomRenterService.findListBy("userId", apiRequest.getUser().getUserId());
        for(RoomRenter rr :  rrlist){
            if(rr.getRoomId() - deposit.getRoomId() == 0 ){
                Building building = buildingService.selectByPrimaryKey(deposit.getBuildingId());
                List<DepositIterm> depositIterms = depositItermService.findListBy("depositId", deposit.getDepositId());
                JSONObject result = new JSONObject();
                result.put("deposit",deposit);
                result.put("building",building);
                result.put("depositIterms",depositIterms == null ? new ArrayList<>() : depositIterms);
                return new JsonResult<JSONObject>(result);
            }
        }

        throw new BusinessException("数据有误");

    }

    @GetMapping(value={"/unpay/{roomId}", "/unpay"})
    public String unpay(@APIRequestControl APIRequest apiRequest, @PathVariable(name="roomId") Integer roomId){
        return null;
    }



}
