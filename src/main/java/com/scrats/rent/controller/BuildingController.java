package com.scrats.rent.controller;

import com.alibaba.fastjson.JSON;
import com.scrats.rent.base.service.RedisService;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.common.annotation.APIRequestControl;
import com.scrats.rent.common.annotation.IgnoreSecurity;
import com.scrats.rent.constant.GlobalConst;
import com.scrats.rent.entity.Building;
import com.scrats.rent.entity.BuildingLandlord;
import com.scrats.rent.entity.DictionaryIterm;
import com.scrats.rent.entity.User;
import com.scrats.rent.service.BuildingLandlordService;
import com.scrats.rent.service.BuildingService;
import com.scrats.rent.service.DictionaryItermService;
import com.scrats.rent.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.AuthenticationException;
import java.util.*;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:20.
 */
@Controller
@RequestMapping("/building")
public class BuildingController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BuildingService buildingService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private DictionaryItermService dictionaryItermService;
    @Autowired
    private BuildingLandlordService buildingLandlordService;

    @IgnoreSecurity
    @GetMapping("/goBuilding")
    public String goBuilding(String tokenId, Map<String, Object> map) throws AuthenticationException {

        User user = (User)redisService.get(tokenId);
        if(null == user){
            throw new AuthenticationException("非法请求, 请登陆");
        }

        List<DictionaryIterm> facilities = dictionaryItermService.findListBy("dicCode", GlobalConst.FACILITY_CODE);
        List<DictionaryIterm> extras = dictionaryItermService.findListBy("dicCode", GlobalConst.EXTRA_CODE);
        List<DictionaryIterm> depositIterms = dictionaryItermService.findListBy("dicCode", GlobalConst.DEPOSIT_ITERM_CODE);

        map.put("user",user);
        map.put("extraList",extras);
        map.put("facilityList",facilities);
        map.put("depositItermList",depositIterms);

        return "landlord/building_list";
    }

    @PostMapping("/list")
    @ResponseBody
    public String list(@APIRequestControl APIRequest apiRequest, Building building) {

        PageInfo<Building> pageInfo = buildingService.getBuildingListWithUserId(apiRequest, building, apiRequest.getUser().getUserId(), true);

        return JSON.toJSONString(new JsonResult<List>(pageInfo.getList(), (int) pageInfo.getTotal()));
    }

    @PostMapping("/edit")
    @ResponseBody
    public JsonResult edit(@APIRequestControl APIRequest apiRequest) {
        Building building = JSON.parseObject(JSON.toJSONString(apiRequest.getBody()),Building.class);

        if(null != building.getFacilityIds() && building.getFacilityIds().size()>0){
            building.setFacilities(String.join(",", building.getFacilityIds()));
        }
        if(null != building.getExtraIds() && building.getExtraIds().size()>0){
            building.setExtraFee(String.join(",", building.getExtraIds()));
        }
        if(null != building.getDepositIds() && building.getDepositIds().size()>0){
            building.setDeposits(String.join(",", building.getDepositIds()));
        }

        if(null != building.getBuildingId()){
            building.setUpdateTs(System.currentTimeMillis());
            buildingService.updateByPrimaryKeySelective(building);
        }else{
            Building b = buildingService.findBy("name",building.getName());
            if(null != b){
                return new JsonResult<>("创建失败,该房源已存在");
            }
            building.setCreateTs(System.currentTimeMillis());
            buildingService.insertSelective(building);
            BuildingLandlord buildingLandlord = new BuildingLandlord(apiRequest.getUser().getUserId(), building.getBuildingId());
            buildingLandlord.setCreateTs(System.currentTimeMillis());
            buildingLandlordService.insertSelective(buildingLandlord);
        }

        return new JsonResult<>();
    }

    @PostMapping("/delete")
    @ResponseBody
    public JsonResult delete(@APIRequestControl APIRequest apiRequest, Integer... ids){
        //校验是否是本人名下的删除
        List<BuildingLandlord> list = buildingLandlordService.findListBy("landlordId", apiRequest.getUser().getUserId());
        Set<Integer> idSet = new HashSet<>(Arrays.asList(ids));
        for(BuildingLandlord buildingLandlord : list){
            if(!idSet.contains(buildingLandlord.getBuildingId())){
                return new JsonResult<>("删除失败");
            }
        }

        //执行删除动作,将delete_ts副职
        buildingLandlordService.deleteBuildingByLandloordIds(ids);
        buildingService.deleteBuildingByIds(ids);

        return new JsonResult();
    }

}
