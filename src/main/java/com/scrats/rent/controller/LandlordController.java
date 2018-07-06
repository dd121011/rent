package com.scrats.rent.controller;

import com.scrats.rent.base.service.RedisService;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.common.annotation.IgnoreSecurity;
import com.scrats.rent.common.exception.BusinessException;
import com.scrats.rent.common.exception.NotAuthorizedException;
import com.scrats.rent.entity.Building;
import com.scrats.rent.entity.User;
import com.scrats.rent.service.BuildingLandlordService;
import com.scrats.rent.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/7/6 12:56.
 */
@Controller
@RequestMapping("/landlord")
public class LandlordController {

    @Autowired
    private RedisService redisService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private BuildingLandlordService buildingLandlordService;

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
}
