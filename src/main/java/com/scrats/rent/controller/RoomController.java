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
import com.scrats.rent.constant.GlobalConst;
import com.scrats.rent.constant.UserType;
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
        List<DictionaryIterm> deposits = dictionaryItermService.findListBy("dicCode", GlobalConst.DEPOSIT_ITERM_CODE);

        map.put("user",user);
        map.put("buildingId",buildingId);
        map.put("buildings",pageInfo.getList());
        map.put("orientations",orientations);
        map.put("decorations",decorations);
        map.put("extraList",extras);
        map.put("facilityList",facilities);
        map.put("depositList",deposits);

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
    public JsonResult edit(@APIRequestControl APIRequest apiRequest, Room room, @RequestParam("facilityIds[]") String[] facilityIds, @RequestParam("extraIds[]") String[] extraIds, @RequestParam("depositIds[]") String[] depositIds) {

        String facilityId = StringUtils.join(facilityIds, ",");
        String extraId = StringUtils.join(extraIds, ",");
        String depositItermId = StringUtils.join(depositIds, ",");
        room.setFacilities(facilityId);
        room.setExtraFee(extraId);
        room.setDeposits(depositItermId);

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
        return JSON.toJSONString(new JsonResult<List>(jsonArray));
    }

    @PostMapping("/renterEdit/{roomId}")
    @ResponseBody
    public JsonResult renterEdit(@PathVariable(name="roomId") Integer roomId, @RequestParam(name= "roomRenterId", required= false) int roomRenterId, String idCard, String phone, User user){
        long updatTs = System.currentTimeMillis();
        if(roomRenterId > 0){
            RoomRenter roomRenter = roomRenterService.selectByPrimaryKey(roomRenterId);
            User oldUser = userService.selectByPrimaryKey(roomRenter.getUserId());
            Account account = accountService.selectByPrimaryKey(oldUser.getAccountId());
            if(!account.getPhone().equals(phone)){
                account.setPhone(phone);
                account.setUpdateTs(updatTs);
                accountService.updateByPrimaryKeySelective(account);
            }
        }else{
            //创建account
            Account account = new Account();
            account.setPhone(phone);
            account.setPwd(phone);
            account.setUsername(phone);
            account.setCreateTs(updatTs);
            accountService.insertSelective(account);
            //创建user
            user.setType(UserType.renter.getValue());
            user.setAccountId(account.getAccountId());
            user.setCreateTs(updatTs);
            userService.insertSelective(user);
            //创建renter
            Renter newRenter = new Renter(idCard);
            newRenter.setCreateTs(updatTs);
            newRenter.setUserId(user.getUserId());
            renterService.insertSelective(newRenter);

            RoomRenter newRoomRenter = new RoomRenter(roomId, user.getUserId(), newRenter.getRenterId());
            newRenter.setCreateTs(updatTs);
            roomRenterService.insertSelective(newRoomRenter);
        }

        return new JsonResult<>();
    }

    @GetMapping("/renterDelete/{roomId}/{roomRenterId}")
    @ResponseBody
    public JsonResult renterDelete(@APIRequestControl APIRequest apiRequest, @PathVariable(name="roomRenterId") Integer roomRenterId, @PathVariable(name="roomId") Integer roomId){
        //检查是否是本人名下的删除
        RoomRenter roomRenter = roomRenterService.selectByPrimaryKey(roomRenterId);
        Room room = roomService.selectByPrimaryKey(roomId);
        if(room.getRoomId() - roomRenter.getRoomId() != 0){
            throw new BusinessException("请求数据不正确");
        }

        List<BuildingLandlord> list = buildingLandlordService.findListBy("landlordId", apiRequest.getUser().getUserId());
        for(BuildingLandlord buildingLandlord : list){
            if(buildingLandlord.getBuildingId() - room.getBuildingId() == 0){
                roomRenterService.deleteRoomRenterById(roomRenter.getRenterId());
                return new JsonResult();
            }
        }

        throw new BusinessException("请求数据不正确");
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
        List<BarginExtra> list = barginExtraService.findListBy("barginId",barginList.get(0).getBarginId());
        return new JsonResult<List>(list);
    }

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
        if(null != list && list.size() > 0){
            return new JsonResult("已经计算房租, 请勿重复生成");
        }

        return roomService.charge(list, month, barginId, apiRequest.getRoomId(), remark);
    }

}
