package com.scrats.rent.controller;

import com.alibaba.fastjson.JSON;
import com.scrats.rent.base.service.RedisService;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.common.annotation.APIRequestControl;
import com.scrats.rent.common.annotation.IgnoreSecurity;
import com.scrats.rent.constant.GlobalConst;
import com.scrats.rent.entity.*;
import com.scrats.rent.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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
    private ExtraService extraService;

    @Autowired
    private BuildingLandlordService buildingLandlordService;

    @IgnoreSecurity
    @GetMapping("/goBuilding")
    public String goBuilding(String tokenId, Map<String, Object> map){

        User user = (User)redisService.get(tokenId);

        List<DictionaryIterm> facilities = dictionaryItermService.findListBy("dicCode", GlobalConst.FACILITY_CODE);
        List<Extra> extras = extraService.selectAll();

        map.put("user",user);
        map.put("extraList",extras);
        map.put("facilityList",facilities);

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
    public JsonResult edit(@APIRequestControl APIRequest apiRequest, Building building, String[] facilityIds, String[] extraIds) {

        String facilityId = StringUtils.join(facilityIds, ",");
        String extraId = StringUtils.join(extraIds, ",");
        building.setFacilities(facilityId);
        building.setExtraFee(extraId);

        if(null != building.getBuildingId()){
            building.setUpdateTs(System.currentTimeMillis());
            buildingService.updateByPrimaryKeySelective(building);
        }else{
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
        for(int id : ids){
            boolean flag = true;
            for(BuildingLandlord buildingLandlord : list){
                if((buildingLandlord.getBuildingId() - id) == 0){
                    flag = false;
                    break;
                }
            }
            if(flag){
                return new JsonResult("删除失败");
            }
        }

        //执行删除动作,将delete_ts副职
        buildingLandlordService.deleteBuildingByLandloordIds(ids);
        buildingService.deleteBuildingByIds(ids);

        return new JsonResult();
    }

}
