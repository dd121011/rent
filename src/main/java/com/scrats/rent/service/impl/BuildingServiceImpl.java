package com.scrats.rent.service.impl;

import com.github.pagehelper.PageHelper;
import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.entity.Building;
import com.scrats.rent.mapper.BuildingMapper;
import com.scrats.rent.service.BuildingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:34.
 */
@Service
public class BuildingServiceImpl extends BaseServiceImpl<Building, BuildingMapper> implements BuildingService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public PageInfo<Building> getBuildingListWithUserId(APIRequest apiRequest, Building building, int userId) {
        return getBuildingListWithUserId(apiRequest, building, userId, true);
    }

    @Override
    public PageInfo<Building> getBuildingListWithUserId(APIRequest apiRequest, Building building, int userId, boolean pageFlag) {
        List<Building> list;
        if(pageFlag){
            PageHelper.startPage(apiRequest.getPage(), apiRequest.getRows());
            list = dao.getBuildingListWithUserId(building, userId);
            return new PageInfo<Building>(list);
        }

        list = dao.getBuildingListWithUserId(building, userId);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public int deleteBuildingByIds(Integer... ids) {
        long ts = System.currentTimeMillis();
        return dao.deleteBuildingByIds(ts, ids);
    }

    @Override
    public Building getBuildingByRoomId(Integer roomId) {
        return dao.getBuildingByRoomId(roomId);
    }
}
