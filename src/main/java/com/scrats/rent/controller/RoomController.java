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
import com.scrats.rent.common.exception.BusinessException;
import com.scrats.rent.common.exception.NotAuthorizedException;
import com.scrats.rent.constant.GlobalConst;
import com.scrats.rent.constant.SexType;
import com.scrats.rent.constant.UserType;
import com.scrats.rent.entity.*;
import com.scrats.rent.service.*;
import com.scrats.rent.util.AccountValidatorUtil;
import com.scrats.rent.util.IdCardUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
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
    private BuildingLandlordService buildingLandlordService;
    @Autowired
    private BarginService barginService;
    @Autowired
    private RenterService renterService;
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private RoomRenterService roomRenterService;
    @Autowired
    private RentService rentService;
    @Autowired
    private BarginExtraService barginExtraService;
    @Autowired
    private DepositService depositService;

    @IgnoreSecurity
    @GetMapping(value = {"/goRoom/{userId}/{buildingId}","/goRoom/{userId}"})
    public String goRoom(String tokenId, @PathVariable(name="userId") Integer userId, @PathVariable(name="buildingId", required = false) Integer buildingId, Map<String, Object> map){

        User user = (User)redisService.get(tokenId);
        if(null == user || (userId -  user.getUserId() != 0)){
            throw new NotAuthorizedException("参数错误");
        }

        //获取所有房子select数据
        PageInfo<Building> pageInfo = buildingService.getBuildingListWithUserId(null, null, user.getUserId(), false);
        Building building = null;
        if(buildingId == null){
            if(pageInfo.getList() != null && pageInfo.getList().size() > 0){
                building = pageInfo.getList().get(0);
            }
        }else{
            building = buildingService.selectByPrimaryKey(buildingId);
        }
        PageInfo<Room> roomPage = null;
        if(null != building){
            Room param = new Room();
            param.setBuildingId(building.getBuildingId());
            roomPage = roomService.getRoomList(null, param, false);
        }


        //获取所有房间朝向
        List<DictionaryIterm> orientations = dictionaryItermService.findListBy("dicCode", GlobalConst.ORIENTATION_CODE);
        //获取所有装修情况
        List<DictionaryIterm> decorations = dictionaryItermService.findListBy("dicCode", GlobalConst.DECORATION_CODE);
        //获取当前房源的所有配套设施
        List<DictionaryIterm> facilities = dictionaryItermService.findListBy("dicCode", GlobalConst.FACILITY_CODE);
        //获取当前房源的所有额外收费项
        List<DictionaryIterm> extras = dictionaryItermService.findListBy("dicCode", GlobalConst.EXTRA_CODE);
        //获取当前房源的所有押金项
        List<DictionaryIterm> depositIterms = dictionaryItermService.findListBy("dicCode", GlobalConst.DEPOSIT_ITERM_CODE);

        map.put("user",user);
        map.put("buildingId",buildingId);
        map.put("buildings",pageInfo.getList());
        map.put("orientations",orientations);
        map.put("decorations",decorations);
        map.put("extraList",extras);
        map.put("facilityList",facilities);
        map.put("depositList",depositIterms);
        map.put("rooms",null == roomPage ? new ArrayList<Room>() : roomPage.getList());

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
    public JsonResult edit(@APIRequestControl APIRequest apiRequest) {

        Room room = JSON.parseObject(JSON.toJSONString(apiRequest.getBody()),Room.class);

        if(null != room.getFacilityIds() && room.getFacilityIds().size()>0){
            room.setFacilities(String.join(",", room.getFacilityIds()));
        }
        if(null != room.getExtraIds() && room.getExtraIds().size()>0){
            room.setExtraFee(String.join(",", room.getExtraIds()));
        }
        if(null != room.getDepositIds() && room.getDepositIds().size()>0){
            room.setDeposits(String.join(",", room.getDepositIds()));
        }

        if(null != room.getRoomId()){
            room.setUpdateTs(System.currentTimeMillis());
            roomService.updateByPrimaryKeySelective(room);
        }else{
            List<Room> rlist = roomService.getRoomByRoomNoAndBuildingId(room.getRoomNo(),room.getBuildingId());
            if(null != rlist && rlist.size() > 1){
                return new JsonResult<>("创建失败,该房间号已存在");
            }
            room.setCreateTs(System.currentTimeMillis());
            roomService.insertSelective(room);

            Building building = buildingService.selectByPrimaryKey(room.getBuildingId());
            building.setRooms(building.getRooms() + 1);
            building.setRoomAble(building.getRoomAble() + 1);
            buildingService.updateByPrimaryKeySelective(building);
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
        Building building = buildingService.selectByPrimaryKey(ids[0]);
        building.setRooms(building.getRooms() - 1);
        building.setRoomAble(building.getRoomAble() - 1);
        buildingService.updateByPrimaryKeySelective(building);

        return new JsonResult();
    }

    /**
     * @description: 办理入住
     * @author: lol
     * @date: 2018/7/4 23:05
     * @param: null
     * @return: JsonResult
     */
    @PostMapping("/rent")
    @ResponseBody
    public JsonResult rent(@APIRequestControl APIRequest apiRequest) {
        Bargin bargin = JSON.parseObject(JSON.toJSONString(apiRequest.getBody()),Bargin.class);
        //补齐landlordId字段
        bargin.setLandlordId(apiRequest.getLanlordId());
        bargin.setRoomId(apiRequest.getRoomId());
        Room room = roomService.selectByPrimaryKey(bargin.getRoomId());
        if(room.getRentTs() != null && room.getRentTs() > 0){
            return new JsonResult("办理入住失败, 请先办理退房");
        }

        return roomService.rent(bargin);
    }

    @IgnoreSecurity
    @GetMapping("/goRoomDetail/{roomId}")
    public String goRoomDetail(String tokenId, @PathVariable(name="roomId") Integer roomId, Map<String, Object> map){

        User user = (User)redisService.get(tokenId);
        Room room = roomService.detail(roomId);

        map.put("user",user);
        map.put("room",room);

        return "landlord/room_detail";
    }

    @GetMapping("/renterAll/{roomId}")
    @ResponseBody
    public String renterAll(@PathVariable(name="roomId") Integer roomId){
        RoomRenter params = new RoomRenter();
        params.setRoomId(roomId);
        List<RoomRenter> list = roomRenterService.getListByRoomrenter(params);
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
            jsonObject.put("checkTs",roomRenter.getCheckTs());
            jsonObject.put("deleteTs",roomRenter.getDeleteTs());
            jsonArray.add(jsonObject);
        }
        return JSON.toJSONString(new JsonResult<List>(jsonArray));
    }

    @PostMapping("/renterAdd/{roomId}")
    @ResponseBody
    public JsonResult renterAdd(@PathVariable(name="roomId") Integer roomId, String idCard, String phone, String name) throws ParseException {
        long createTs = System.currentTimeMillis();
        Renter renter = null;
        if(null == accountService.findBy("phone", phone)){
            if(null != renterService.findBy("idCard", idCard)){
                return new JsonResult("身份证号:" + idCard + "在系统中已被注册");
            }
            if(!AccountValidatorUtil.isMobile(phone)){
                return new JsonResult("该手机号不正确!");
            }
            String idValid = IdCardUtil.IDCardValidate(idCard);
            if(!IdCardUtil.VALIDITY.equals(idValid)){
                return new JsonResult(idValid);
            }
            //创建account
            Account account = new Account(phone, phone, phone);
            account.setCreateTs(createTs);
            accountService.insertSelective(account);
            //创建user
            User user = new User();
            user.setName(name);
            user.setType(UserType.renter.getValue());
            user.setSex(SexType.secret.getValue());
            user.setAccountId(account.getAccountId());
            user.setCreateTs(createTs);
            userService.insertSelective(user);
            //创建renter
            renter = new Renter(idCard, user.getUserId());
            renter.setCreateTs(createTs);
            renterService.insertSelective(renter);
        }else{
            User user = userService.getUserByPhone(phone);
            if(null == user){
                throw new BusinessException("请求数据不正确");
            }
            renter = renterService.findBy("userId",user.getUserId());
            RoomRenter param = new RoomRenter();
            param.setRoomId(roomId);
            param.setUserId(user.getUserId());
            param.setRenterId(renter.getRenterId());
            List<RoomRenter> rrlist = roomRenterService.getListByRoomrenter(param);
            if(null != rrlist && rrlist.size() > 0){
                throw new BusinessException("请求的信息有误,该用户目前正在入住该房间");
            }
        }

        List<Bargin> list = barginService.getBarginByRoomId(roomId, false);
        RoomRenter newRoomRenter = new RoomRenter(roomId, renter.getUserId(), renter.getRenterId(), list.get(0).getBarginId());
        newRoomRenter.setCreateTs(createTs);
        newRoomRenter.setCheckTs(createTs);
        roomRenterService.insertSelective(newRoomRenter);

        return new JsonResult<>();
    }

    @GetMapping("/renterDelete/{roomId}/{roomRenterId}")
    @ResponseBody
    public JsonResult renterDelete(@APIRequestControl APIRequest apiRequest, @PathVariable(name="roomRenterId") Integer roomRenterId, @PathVariable(name="roomId") Integer roomId){
        //检查是否是本人名下的删除
        RoomRenter roomRenter = roomRenterService.selectByPrimaryKey(roomRenterId);
        Room room = roomService.selectByPrimaryKey(roomId);
        if(null == roomRenter || null == room || room.getRoomId() - roomRenter.getRoomId() != 0){
            throw new BusinessException("请求数据不正确");
        }

        List<BuildingLandlord> list = buildingLandlordService.findListBy("landlordId", apiRequest.getUser().getUserId());
        for(BuildingLandlord buildingLandlord : list){
            if(buildingLandlord.getBuildingId() - room.getBuildingId() == 0){
                roomRenterService.deleteRoomRenterById(roomRenter.getRoomRenterId());
                return new JsonResult();
            }
        }

        throw new BusinessException("请求数据不正确");
    }

    @GetMapping("/detail/{roomId}")
    @ResponseBody
    public JsonResult detail(@PathVariable(name="roomId") Integer roomId){

        Room room = roomService.detail(roomId);
        if(null != room){
            return new JsonResult<Room>(room);
        }
        return new JsonResult("数据有误");
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
        //获取所有额外收费项
        List<DictionaryIterm> deposits = dictionaryItermService.selectByIds(room.getDeposits());

        return JSON.toJSONString(new JsonResult<List>(deposits));
    }

    @GetMapping("/barginExtra/{roomId}")
    @ResponseBody
    public JsonResult barginExtra(@PathVariable(name="roomId") Integer roomId){

        List<Bargin> barginList = barginService.getBarginByRoomId(roomId, false);

        List<BarginExtra> list = new ArrayList<>();
        if(!CollectionUtils.isEmpty(barginList)){
            list = barginExtraService.findListBy("barginId",barginList.get(0).getBarginId());
        }
        return new JsonResult<List>(list);
    }

    /**
     * @description: 计算房租
     * @author: lol
     * @date: 2018/7/4 23:04
     * @param: null
     * @return: JsonResult
     */
    @PostMapping("/charge")
    @ResponseBody
    public JsonResult charge(@APIRequestControl APIRequest apiRequest){
        Integer barginId = APIRequest.getParameterValue(apiRequest,"barginId",Integer.class);
        String month = APIRequest.getParameterValue(apiRequest,"month",String.class);
        String remark = APIRequest.getParameterValue(apiRequest,"remark",String.class);
        List<ExtraHistory> list = JSON.parseArray(JSON.toJSONString(apiRequest.getBody().get("barginExtraList")),ExtraHistory.class);

        Rent rent = new Rent();
        rent.setRentMonth(month);
        rent.setRoomId(apiRequest.getRoomId());
        List<Rent> rentList  = rentService.getListByRent(rent);
        if(null != rentList && rentList.size() > 0){
            return new JsonResult("已经计算房租, 请勿重复生成");
        }

        return roomService.charge(list, month, barginId, apiRequest.getRoomId(), remark);
    }

    /**
     * @description: 退房
     * @author: lol
     * @date: 2018/7/4 23:04
     * @param: roomId
     * @return: JsonResult
     */
    @GetMapping("/rentLeave/{roomId}")
    @ResponseBody
    public JsonResult rentLeave(@APIRequestControl APIRequest apiRequest, @PathVariable(name="roomId") Integer roomId){
        Long deleteTs = System.currentTimeMillis();
        //修改房间状态为未出租
        Room room = new Room();
        room.setRoomId(roomId);
        room.setRentTs(0L);
        roomService.updateByPrimaryKeySelective(room);
        //退还押金、取消合同
        List<Bargin> barginList = barginService.getBarginByRoomId(roomId, false);
        barginList.get(0).setDeleteTs(deleteTs);
        barginService.updateByPrimaryKeySelective(barginList.get(0));

        List<Deposit> depositList = depositService.getDepositByRoomId(roomId, false);
        depositList.get(0).setDeleteTs(deleteTs);
        depositService.updateByPrimaryKeySelective(depositList.get(0));

        RoomRenter roomRenter = new RoomRenter();
        roomRenter.setRoomId(roomId);
        List<RoomRenter> roomRenterList = roomRenterService.getListByRoomrenter(roomRenter);
        for(RoomRenter rr : roomRenterList){
            rr.setDeleteTs(deleteTs);
            roomRenterService.updateByPrimaryKeySelective(rr);
        }
        return new JsonResult();
    }

    @GetMapping("/roomAll/{buildingId}")
    @ResponseBody
    public JsonResult roomAll(@PathVariable(name="buildingId") Integer buildingId){

        List<Room> list = roomService.getRoomByRoomNoAndBuildingId(null, buildingId);

        return new JsonResult<List>(list);
    }

    @GetMapping("/renterCheck/{roomId}/{roomRenterId}")
    @ResponseBody
    public JsonResult renterCheck(@APIRequestControl APIRequest apiRequest, @PathVariable(name="roomRenterId") Integer roomRenterId, @PathVariable(name="roomId") Integer roomId){
        //检查是否是本人名下的删除
        RoomRenter roomRenter = roomRenterService.selectByPrimaryKey(roomRenterId);
        Room room = roomService.selectByPrimaryKey(roomId);
        if(null == roomRenter || null == room || room.getRoomId() - roomRenter.getRoomId() != 0){
            throw new BusinessException("请求数据不正确");
        }

        List<BuildingLandlord> list = buildingLandlordService.findListBy("landlordId", apiRequest.getUser().getUserId());
        for(BuildingLandlord buildingLandlord : list){
            if(buildingLandlord.getBuildingId() - room.getBuildingId() == 0){
                roomRenterService.renterCheck(roomRenter.getRoomRenterId());
                return new JsonResult();
            }
        }

        throw new BusinessException("请求数据不正确");
    }
}
