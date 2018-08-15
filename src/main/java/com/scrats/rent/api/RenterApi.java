package com.scrats.rent.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scrats.rent.base.service.RedisService;
import com.scrats.rent.base.service.SmsService;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.common.annotation.APIRequestControl;
import com.scrats.rent.common.annotation.IgnoreSecurity;
import com.scrats.rent.common.exception.BusinessException;
import com.scrats.rent.entity.*;
import com.scrats.rent.service.*;
import com.scrats.rent.util.AccountValidatorUtil;
import com.scrats.rent.util.IdCardUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
    @Autowired
    private SmsService smsService;

    @IgnoreSecurity
    @PostMapping("/snsLogin")
    public JsonResult snsLogin(@RequestBody APIRequest apiRequest){
        String code = APIRequest.getParameterValue(apiRequest,"code",String.class);
        String signature = APIRequest.getParameterValue(apiRequest,"signature",String.class);
        String rawData = APIRequest.getParameterValue(apiRequest,"rawData",String.class);
        if(StringUtils.isEmpty(code) || StringUtils.isEmpty(signature) || StringUtils.isEmpty(rawData)){
            throw new BusinessException("请求的信息有误");
        }
        return renterService.snsLogin(code, signature, rawData);
    }

    @IgnoreSecurity
    @PostMapping("/snsRegist")
    public JsonResult snsRegist(@RequestBody APIRequest apiRequest) throws ParseException {
        String openid = apiRequest.getOpenid();
        String tokenId = (String) redisService.get(openid);
        if(StringUtils.isEmpty(tokenId)){
            return new JsonResult("该openid" + openid + "已失效, 请重新获取!");
        }

        String name = APIRequest.getParameterValue(apiRequest,"name",String.class);
        String phone = APIRequest.getParameterValue(apiRequest,"phone",String.class);
        String idCard = APIRequest.getParameterValue(apiRequest,"idCard",String.class);
        String code = APIRequest.getParameterValue(apiRequest,"code",String.class);

        if(StringUtils.isEmpty(name) || StringUtils.isEmpty(phone) || StringUtils.isEmpty(idCard) || StringUtils.isEmpty(code)){
            throw new BusinessException("请求的信息有误!");
        }

        if(!AccountValidatorUtil.isMobile(phone)){
            return new JsonResult("该手机号不正确!");
        }

        String idValid = IdCardUtil.IDCardValidate(idCard);
        if(!IdCardUtil.VALIDITY.equals(idValid)){
            return new JsonResult(idValid);
        }

        if(!smsService.checkCode(phone, code)){
            return new JsonResult("手机验证码不正确!");
        }

        return renterService.snsRenterRegist(tokenId, openid, name, phone, idCard);
    }


    @GetMapping("/bindRoom/{roomId}")
    public JsonResult bindRoom(@APIRequestControl APIRequest apiRequest, @PathVariable(name="roomId") Integer roomId){
        Room room = roomService.selectByPrimaryKey(roomId);
        if(null == room){
            throw new BusinessException("请求的信息有误");
        }

        RoomRenter param = new RoomRenter();
        param.setRoomId(roomId);
        param.setUserId(apiRequest.getUser().getUserId());
        List<RoomRenter> rrlist = roomRenterService.getListByRoomrenter(param);
        if(null != rrlist && rrlist.size() > 0){
            throw new BusinessException("请求的信息有误,该用户目前正在入住该房间");
        }

        List<Bargin> list = barginService.getBarginByRoomId(roomId, false);
        RoomRenter newRoomRenter = new RoomRenter(roomId, apiRequest.getUser().getUserId(), list.get(0).getBarginId());
        newRoomRenter.setCreateTs(System.currentTimeMillis());
        roomRenterService.insertSelective(newRoomRenter);
        return new JsonResult<>();
    }

    @GetMapping("/roomList")
    public JsonResult roomList(@APIRequestControl APIRequest apiRequest){
        return new JsonResult<JSONArray>(renterService.getRoomList(apiRequest.getUser().getUserId()));
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

    @GetMapping("/rent/{roomId}")
    public JsonResult rent(@APIRequestControl APIRequest apiRequest, @PathVariable(name="roomId") Integer roomId){
        Rent rent = new Rent();
        rent.setRoomId(roomId);
        rent.setPayTs(-1L);
        PageInfo<Rent> pageInfo = rentService.getRentPageList(apiRequest, rent);

        return new JsonResult<PageInfo>(pageInfo);
    }

    @GetMapping("/rentDetail/{rentId}")
    public JsonResult rentDetail(@PathVariable(name="rentId") Integer rentId){

        return new JsonResult<>(rentItermService.findListBy("rentId", rentId));
    }

}
