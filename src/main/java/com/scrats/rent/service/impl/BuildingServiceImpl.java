package com.scrats.rent.service.impl;

import com.github.pagehelper.PageHelper;
import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.entity.Building;
import com.scrats.rent.mapper.BuildingMapper;
import com.scrats.rent.service.BuildingService;
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


    @Override
    public PageInfo<Building> getBuildingListByUserId(int page, int rows, int userId) {
        PageHelper.startPage(page, rows);
        List<Building> list = dao.getBuildingListByUserId(userId);
        return new PageInfo<Building>(list);
    }
}