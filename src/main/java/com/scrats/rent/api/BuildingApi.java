package com.scrats.rent.api;

import com.alibaba.fastjson.JSON;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.common.annotation.APIRequestControl;
import com.scrats.rent.common.annotation.IgnoreSecurity;
import com.scrats.rent.entity.Building;
import com.scrats.rent.entity.BuildingLandlord;
import com.scrats.rent.service.BuildingLandlordService;
import com.scrats.rent.service.BuildingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/7 23:30.
 */
@RestController
@RequestMapping("/api/building")
public class BuildingApi {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BuildingService buildingService;
    @Autowired
    private BuildingLandlordService buildingLandlordService;


    @PostMapping("/list")
    public String list(@APIRequestControl APIRequest apiRequest, Building building) {
        PageInfo<Building> pageInfo = buildingService.getBuildingListWithUserId(apiRequest, building, apiRequest.getUser().getUserId(), true);

        return JSON.toJSONString(new JsonResult<List>(pageInfo.getList(), (int) pageInfo.getTotal()));
    }

    @PostMapping("/edit")
    public String edit(@APIRequestControl APIRequest apiRequest, Building building) {

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

        return JSON.toJSONString(new JsonResult<>());
    }

    @PostMapping("/delete")
    public String delete(@APIRequestControl APIRequest apiRequest, Integer... ids){
        //校验是否是本人名下的删除
        List<BuildingLandlord> list = buildingLandlordService.findListBy("landlord_id", apiRequest.getUser().getUserId());
        for(int id : ids){
            boolean flag = true;
            for(BuildingLandlord buildingLandlord : list){
                if((buildingLandlord.getBuilding_id() - id) == 0){
                    flag = false;
                    break;
                }
            }
            if(flag){
                return JSON.toJSONString(new JsonResult<>("删除失败"));
            }
        }

        //执行删除动作,将delete_ts副职
        buildingLandlordService.deleteBuildingByLandloordIds(ids);
        buildingService.deleteBuildingByIds(ids);

        return JSON.toJSONString(new JsonResult<>());
    }

    @PostMapping("/buildingAll")
    public String buildingAll(@APIRequestControl APIRequest apiRequest) {
        //获取所有房子select数据
        PageInfo<Building> pageInfo = buildingService.getBuildingListWithUserId(null, null, apiRequest.getUser().getUserId(), false);
        return JSON.toJSONString(new JsonResult<List>(pageInfo.getList()));
    }

    @GetMapping("/detail/{buildingId}")
    @IgnoreSecurity
    public String detail(@PathVariable(name="buildingId") Integer buildingId) {
        //获取所有房子select数据
        Building building = buildingService.selectByPrimaryKey(buildingId);
        return JSON.toJSONString(new JsonResult<Building>(building));
    }
}
