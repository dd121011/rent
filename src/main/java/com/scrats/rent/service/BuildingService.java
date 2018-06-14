package com.scrats.rent.service;

import com.scrats.rent.base.service.BaseService;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.entity.Building;
import com.scrats.rent.mapper.BuildingMapper;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:33.
 */
public interface BuildingService extends BaseService<Building, BuildingMapper> {

    PageInfo<Building> getBuildingListByUserId(int page, int rows, int userId);

    int deleteBuildingByIds(Integer... ids);
}
