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
import com.scrats.rent.common.exception.NotAuthorizedException;
import com.scrats.rent.entity.*;
import com.scrats.rent.service.*;
import com.scrats.rent.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @Autowired
    private ExtraHistoryService extraHistoryService;
    @Autowired
    private BarginExtraService barginExtraService;

    @IgnoreSecurity
    @GetMapping(value = {"/goRent/{userId}/{roomId}","/goRent/{userId}"})
    public String goRoom(String tokenId, @PathVariable(name="userId") Integer userId, @PathVariable(name="roomId", required = false) Integer roomId, Map<String, Object> map){

        User user = (User)redisService.get(tokenId);
        if(null == user || (userId -  user.getUserId() != 0)){
            throw new NotAuthorizedException("参数错误");
        }

        //获取所有房子select数据
        PageInfo<Building> buildingPage = buildingService.getBuildingListWithUserId(null, null, user.getUserId(), false);
        List<Room> roomList = new ArrayList<Room>();
        Integer buildingId = null;
        if(null == roomId){
            buildingId = buildingPage.getList().get(0).getBuildingId();
            Room param = new Room();
            param.setBuildingId(buildingPage.getList().get(0).getBuildingId());
            PageInfo<Room> roomPage = roomService.getRoomList(null, param, false);
            roomList = roomPage.getList();
        }else{
            Room room = roomService.selectByPrimaryKey(roomId);
            if(null != room){
                buildingId = room.getBuildingId();
                Room param = new Room();
                param.setBuildingId(buildingPage.getList().get(0).getBuildingId());
                PageInfo<Room> roomPage = roomService.getRoomList(null, param, false);
                roomList = roomPage.getList();
            }
        }

        map.put("user",user);
        map.put("roomId",roomId);
        map.put("buildingId",buildingId);
        map.put("buildings",buildingPage.getList());
        map.put("rooms",roomList);

        return "landlord/rent_list";
    }

    @PostMapping("/list")
    @ResponseBody
    public String list(@APIRequestControl APIRequest apiRequest, Rent rent) {
        PageInfo<Rent> pageInfo = rentService.getRentPageList(apiRequest, rent);
        return JSON.toJSONString(new JsonResult<List>(pageInfo.getList(), (int) pageInfo.getTotal()));
    }

    @GetMapping("/pay/{rentId}")
    @ResponseBody
    public JsonResult pay(@APIRequestControl APIRequest apiRequest, @PathVariable(name="rentId") Integer rentId){
        boolean res = rentService.pay(apiRequest.getUser(), rentId, null);
        if(res){
            return new JsonResult();
        }
        return new JsonResult("缴费失败，请重试");

    }

    @GetMapping("/detail/{rentId}")
    @ResponseBody
    public JsonResult detail(@APIRequestControl APIRequest apiRequest, @PathVariable(name="rentId") Integer rentId){
        Rent rent = rentService.detail(rentId);
        if(null != rent){
            return new JsonResult<Rent>(rent);
        }
        return new JsonResult("数据有误,请检查");

    }

    @GetMapping("/editExtra/{rentId}")
    @ResponseBody
    public JsonResult extra(@APIRequestControl APIRequest apiRequest, @PathVariable(name="rentId") Integer rentId){
        List<ExtraHistory> list = extraHistoryService.getRentEditExtraHistory(rentId);
        return new JsonResult<List>(list);

    }

    @PostMapping("/edit/{rentId}")
    @ResponseBody
    public JsonResult edit(@APIRequestControl APIRequest apiRequest, @PathVariable(name="rentId") Integer rentId) {
        Rent rent = JSON.parseObject(JSON.toJSONString(apiRequest.getBody()),Rent.class);
        List<ExtraHistory> list = JSON.parseArray(JSON.toJSONString(apiRequest.getBody().get("extraList")),ExtraHistory.class);
        return rentService.rentEdit(rent, list);
    }

    @IgnoreSecurity
    @GetMapping("/goRentMulti/{userId}/{buildingId}")
    public String goRentMulti(String tokenId, @PathVariable(name="userId") Integer userId, @PathVariable(name="buildingId") Integer buildingId, Map<String, Object> map){

        User user = (User)redisService.get(tokenId);
        if(null == user || (userId -  user.getUserId() != 0)){
            throw new NotAuthorizedException("参数错误");
        }

        //获取所有房子select数据
        PageInfo<Building> buildingPage = buildingService.getBuildingListWithUserId(null, null, user.getUserId(), false);
        //获取所有抄表合同额外收费项
        Bargin param = new Bargin();
        param.setBuildingId(buildingId);
        List<BarginExtra> barginExtraList = barginExtraService.getBarginExtraTypeByBargin(param);

        map.put("user",user);
        map.put("buildingId",buildingId);
        map.put("buildings",buildingPage.getList());
        map.put("extraType",barginExtraList);

        return "landlord/rent_charge_multi";
    }

    @GetMapping("/extraType/{buildingId}")
    @ResponseBody
    public JsonResult goRentMulti(@PathVariable(name="buildingId") Integer buildingId){

        //获取所有抄表合同额外收费项
        Bargin param = new Bargin();
        param.setBuildingId(buildingId);
        List<BarginExtra> barginExtraList = barginExtraService.getBarginExtraTypeByBargin(param);

        return new JsonResult<List>(barginExtraList);
    }

    @GetMapping("/multi/{buildingId}/{code}/{month}")
    @ResponseBody
    public JsonResult multi(@PathVariable(name="buildingId") Integer buildingId, @PathVariable(name="code") String code, @PathVariable(name="month") String month){
        JSONArray jsonArray = new JSONArray();
        //获取所有含有该额外收费项code的房间
        List<BarginExtra> barginExtraList = barginExtraService.getBarginExtraByBuildingIdAndDicItermCode(buildingId, code);
        for(BarginExtra barginExtra : barginExtraList){
            //获取所有已记录
            List<ExtraHistory> extraHistoryList = extraHistoryService.getListByBarginExtraAndMonth(barginExtra, month);
            if(extraHistoryList.size() < 1){
                JSONObject jsonObject = (JSONObject) JSON.toJSON(barginExtra);
                ExtraHistory query = new ExtraHistory();
                query.setBarginExtraId(barginExtra.getBarginExtraId());
                query.setMonth(DateUtils.beforeMonth(month));
                List<ExtraHistory> before = extraHistoryService.select(query);
                //没有记录
                if(null == before || before.size() < 1){
                    jsonObject.put("beforeCount", barginExtra.getNumber());
                }else{
                    jsonObject.put("beforeCount", before.get(0).getCount());
                }
                jsonArray.add(jsonObject);
            }
        }
        return new JsonResult<JSONArray>(jsonArray);
    }

}
