package com.scrats.rent.service.impl;

import com.github.pagehelper.PageHelper;
import com.scrats.rent.base.service.BaseServiceImpl;
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

    private PageInfo<Building> getBuildingListByUserId(int page, int rows, int userId) {
        PageHelper.startPage(page, rows);
        List<Building> list = dao.getBuildingListByUserId(userId);
        return new PageInfo<Building>(list);
    }

    @Override
    public PageInfo<Building> getBuildingListByUserId(int page, int rows, int userId, boolean pageFlag) {
        if(pageFlag){
            return getBuildingListByUserId(page, rows, userId);
        }
        List<Building> list = dao.getBuildingListByUserId(userId);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public int deleteBuildingByIds(Integer... ids) {
        long ts = System.currentTimeMillis();
        return dao.deleteBuildingByIds(ts, ids);
    }
}
