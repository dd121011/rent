package com.scrats.rent.api;

import com.alibaba.fastjson.JSON;
import com.scrats.rent.base.service.RedisService;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.annotation.APIRequestControl;
import com.scrats.rent.common.annotation.IgnoreSecurity;
import com.scrats.rent.constant.GlobalConst;
import com.scrats.rent.entity.Bargin;
import com.scrats.rent.entity.Room;
import com.scrats.rent.entity.User;
import com.scrats.rent.entity.WxSns;
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

    @IgnoreSecurity
    @PostMapping("/snsLogin")
    public String snsLogin(String code, String signature, String rawData){
        if(StringUtils.isEmpty(code)){
            return JSON.toJSONString(new JsonResult("获取的微信code为空"));
        }
        return JSON.toJSONString(renterService.snsLogin(code, signature, rawData));
    }

    @IgnoreSecurity
    @PostMapping("/bindUser")
    public String bindUser(String tokenId, String openid, Integer roomId){

        String checkTokeId = (String) redisService.get(openid);
        if(StringUtils.isEmpty(checkTokeId)){
            return JSON.toJSONString(new JsonResult("请求的openid有误"));
        }
        if(!checkTokeId.equals(tokenId)){
            return JSON.toJSONString(new JsonResult("请求的tokenId有误"));
        }

        List<Bargin> barginList = barginService.getBarginValidByRoomIdAndUserId(roomId,null);
        if(null == barginList || barginList.size()>1){
            return JSON.toJSONString(new JsonResult("roomId有误"));
        }
        User user = userService.selectByPrimaryKey(barginList.get(0).getRenterId());
        WxSns wxSns = wxSnsService.selectByPrimaryKey(openid);
        wxSns.setUserId(user.getUserId());
        wxSns.setUpdateTs(System.currentTimeMillis());
        wxSnsService.updateByPrimaryKeySelective(wxSns);
        redisService.set(tokenId,user, GlobalConst.ACCESS_TOKEN_EXPIRE);
        return JSON.toJSONString(new JsonResult<User>(user));
    }

    @GetMapping("/roomList")
    public String roomList(@APIRequestControl APIRequest apiRequest){

        List<Bargin> barginList = barginService.getBarginValidByRoomIdAndUserId(null,apiRequest.getUser().getUserId());
        List<Room> list = new ArrayList<Room>();
        for(Bargin bargin : barginList){
            Room room = roomService.detail(bargin.getRoomId());
            list.add(room);
        }
        return JSON.toJSONString(new JsonResult<List>(list));
    }

    @GetMapping(value={"/bargin/{roomId}", "/bargin"})
    public String bargin(@APIRequestControl APIRequest apiRequest, @PathVariable(name="roomId",required = false) Integer roomId){
        List<Bargin> list = barginService.getBarginValidByRoomIdAndUserId(roomId, apiRequest.getUser().getUserId());
        return JSON.toJSONString(new JsonResult<List>(list));

    }

    @GetMapping(value={"/unpay/{roomId}", "/unpay"})
    public String unpay(@APIRequestControl APIRequest apiRequest, @PathVariable(name="roomId") Integer roomId){
        return null;
    }


}
