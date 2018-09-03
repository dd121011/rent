package com.scrats.rent.service;

import com.scrats.rent.base.service.BaseService;
import com.scrats.rent.common.APIRequest;
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

    PageInfo<Building> getBuildingListWithUserId(APIRequest apiRequest, Building building, int userId);

    PageInfo<Building> getBuildingListWithUserId(APIRequest apiRequest, Building building, int userId, boolean pageFlag);

    int deleteBuildingByIds(Integer... ids);

    Building getBuildingByRoomId(Integer roomId);

    /**
     * @description: 当月收入
     * @author: lol
     * @date: 2018/8/31 12:37
     * @param: buildingId
     * @return: 
     */
    int incomeThisMonth(Integer buildingId);

    /**
     * @description: 逾期收入
     * @author: lol
     * @date: 2018/8/31 12:37
     * @param: buildingId
     * @return: 
     */
    int expiredMoeny(Integer buildingId);
}
