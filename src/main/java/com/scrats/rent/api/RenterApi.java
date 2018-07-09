package com.scrats.rent.api;

import com.alibaba.fastjson.JSONArray;
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
import com.scrats.rent.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    @Autowired
    private RentService rentService;
    @Autowired
    private RentItermService rentItermService;
    @Autowired
    private ExtraHistoryService extraHistoryService;
    @Autowired
    private AccountService accountService;

    @IgnoreSecurity
    @PostMapping("/snsLogin")
    public JsonResult snsLogin(@RequestBody APIRequest apiRequest){
        String code = APIRequest.getParameterValue(apiRequest,"code",String.class);
        String signature = APIRequest.getParameterValue(apiRequest,"signature",String.class);
        String rawData = APIRequest.getParameterValue(apiRequest,"rawData",String.class);
        if(StringUtils.isEmpty(code) || StringUtils.isEmpty(signature) || StringUtils.isEmpty(rawData)){
            return new JsonResult("请求的信息有误");
        }
        return renterService.snsLogin(code, signature, rawData);
    }

    @IgnoreSecurity
    @PostMapping("/bindUser")
    public JsonResult bindUser(@RequestBody APIRequest apiRequest){

        if(StringUtils.isEmpty(apiRequest.getOpenid()) || StringUtils.isEmpty(apiRequest.getTokenId()) || null == apiRequest.getRoomId()){
            return new JsonResult("请求的信息有误");
        }

        String checkTokeId = (String) redisService.get(apiRequest.getOpenid());
        if(StringUtils.isEmpty(checkTokeId)){
            return new JsonResult("请求的openid有误");
        }
        if(!checkTokeId.equals(apiRequest.getTokenId())){
            return new JsonResult("请求的tokenId有误");
        }

        List<Bargin> barginList = barginService.getBarginByRoomId(apiRequest.getRoomId(), false);
        if(null == barginList || barginList.size()>1){
            return new JsonResult("该房间还未出租,无法绑定");
        }
        User user = userService.selectByPrimaryKey(barginList.get(0).getUserId());
        WxSns wxSns = wxSnsService.selectByPrimaryKey(apiRequest.getOpenid());
        wxSns.setUserId(user.getUserId());
        wxSns.setUpdateTs(System.currentTimeMillis());
        wxSnsService.updateByPrimaryKeySelective(wxSns);
        redisService.set(apiRequest.getTokenId(),user, GlobalConst.ACCESS_TOKEN_EXPIRE);
        return new JsonResult<User>(user);
    }

    @GetMapping("/roomList")
    public JsonResult roomList(@APIRequestControl APIRequest apiRequest){
        Date date = new Date();

        List<RoomRenter> rrlist = roomRenterService.findListBy("userId", apiRequest.getUser().getUserId());
        HashSet<Integer> roomIdSet = new HashSet<>();
        for(RoomRenter rr :  rrlist){
            roomIdSet.add(rr.getRoomId());
        }
        JSONArray result = new JSONArray();
        for(Integer id : roomIdSet){
            JSONObject jsonObject = new JSONObject();
            Room room = roomService.detailForRenter(id);
            Date rentDay = this.getPayTime(date,room.getBarginList().get(0).getRentDay());
            String payStatus = "pay";
            if(null != room.getRentList() && room.getRentList().size() > 0){
                payStatus = "unpay";
            }
            jsonObject.put("roomId", room.getRoomId());
            jsonObject.put("roomNo", room.getRoomNo());
            jsonObject.put("buildingName", room.getBuilding().getName());
            jsonObject.put("nextTime", rentDay.getTime()-date.getTime());
            jsonObject.put("payTime", rentDay.getTime());
            jsonObject.put("payStatus", payStatus);
            result.add(jsonObject);
        }

        return new JsonResult<JSONArray>(result);
    }

    @GetMapping("/bargin/{roomId}")
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
                Room room = roomService.selectByPrimaryKey(roomId);
                JSONObject result = new JSONObject();
                result.put("bargin",bargin);
                result.put("landlord",accountService.getPhoneByBuildingId(building.getBuildingId()));
                result.put("roomNo",room.getRoomNo());
                result.put("building",building);
                result.put("facilities",facilities);
                result.put("extras",extras == null ? new ArrayList<>() : extras);
                return new JsonResult<JSONObject>(result);
            }
        }

        throw new BusinessException("数据有误");
    }

    @GetMapping("/deposit/{roomId}")
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
                result.put("depositIterms",depositIterms);
                return new JsonResult<JSONObject>(result);
            }
        }

        throw new BusinessException("数据有误");
    }

    @GetMapping("/unpay/{roomId}")
    public JsonResult unpay(@PathVariable(name="roomId") Integer roomId){
        JSONArray result = new JSONArray();
        List<Rent> list = rentService.getRentByRoomId(roomId,false);
        for(Rent rent : list){
            JSONObject jsonObject = new JSONObject();
            List<RentIterm> rentIterm = rentItermService.findListBy("rentId",rent.getRentId());
            jsonObject.put("rent",rent);
            jsonObject.put("rentIterms",rentIterm);
            result.add(jsonObject);
        }

        return new JsonResult<JSONArray>(result);
    }

    @GetMapping("/extraHistory/{barginExtraId}")
    public JsonResult extraHistory(@PathVariable(name="barginExtraId") Integer barginExtraId){
        List<ExtraHistory> list = extraHistoryService.findListBy("barginExtraId", barginExtraId);
        return new JsonResult<List>(list);
    }


    private Date getPayTime(Date date, int rentDay){
        Date rent = DateUtils.oneDayOfThisMonth(date, rentDay);
        if(rent.getTime() - date.getTime() < 0){
            rent = DateUtils.oneDayOfNextMonth(date, rentDay);
        }
        return rent;
    }

}
