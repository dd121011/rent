package com.scrats.rent.controller;

import com.alibaba.fastjson.JSONObject;
import com.scrats.rent.base.service.RedisService;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.common.annotation.APIRequestControl;
import com.scrats.rent.common.annotation.IgnoreSecurity;
import com.scrats.rent.common.exception.BusinessException;
import com.scrats.rent.common.exception.NotAuthorizedException;
import com.scrats.rent.entity.Building;
import com.scrats.rent.entity.Rent;
import com.scrats.rent.entity.RoomRenter;
import com.scrats.rent.entity.User;
import com.scrats.rent.service.BuildingLandlordService;
import com.scrats.rent.service.BuildingService;
import com.scrats.rent.service.RentService;
import com.scrats.rent.service.RoomRenterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Created with scrat.
 * @Description: 房东首页.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/7/6 12:56.
 */
@Slf4j
@Controller
@RequestMapping("/landlord")
public class LandlordController {

    @Autowired
    private RedisService redisService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private BuildingLandlordService buildingLandlordService;
    @Autowired
    private RentService rentService;
    @Autowired
    private RoomRenterService roomRenterService;

    @IgnoreSecurity
    @GetMapping("/goHome/{landlordId}")
    public String home(@PathVariable Integer landlordId, String tokenId, Map<String, Object> map) {

        User user = (User)redisService.get(tokenId);
        if(null == user){
            throw new NotAuthorizedException("非法请求, 请登陆");
        }
        if(user.getUserId() - landlordId != 0){
            throw new BusinessException("请求参数错误, 请检查");
        }

        PageInfo<Building> buildingPageInfo = buildingService.getBuildingListWithUserId(null,null, landlordId, false);

        map.put("buildingList",buildingPageInfo.getList());


        return "landlord/home";
    }

    @GetMapping("/homeData/{buildingId}")
    @ResponseBody
    public JsonResult homeData(@APIRequestControl APIRequest apiRequest, @PathVariable Integer buildingId){
        if(!apiRequest.isLandlordFlag()){
            throw new BusinessException("数据错误!");
        }
        Building building = buildingService.selectByPrimaryKey(buildingId);
        List<RoomRenter> roomRenterList = roomRenterService.getRoomRenterByBuildingId(buildingId);
        List<Rent> rentList = rentService.getRentByBuildingIdandPayFlag(buildingId, false);
        int income = buildingService.incomeThisMonth(buildingId);
        int expire = buildingService.expiredMoeny(buildingId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("income", income);
        jsonObject.put("expire", expire);
        jsonObject.put("roomNum", building.getRooms());
        jsonObject.put("avaliableNum", building.getRoomAble());
        jsonObject.put("renterNum", roomRenterList.size());
        jsonObject.put("noRentNum", rentList.size());
        return new JsonResult(jsonObject);
    }
}
