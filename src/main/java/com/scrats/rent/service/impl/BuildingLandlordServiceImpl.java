package com.scrats.rent.service.impl;

import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.entity.BuildingLandlord;
import com.scrats.rent.mapper.BuildingLandlordMapper;
import com.scrats.rent.service.BuildingLandlordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:34.
 */
@Slf4j
@Service
public class BuildingLandlordServiceImpl extends BaseServiceImpl<BuildingLandlord, BuildingLandlordMapper> implements BuildingLandlordService {

    @Override
    public int deleteBuildingByLandloordIds(Integer... ids) {
        long ts = System.currentTimeMillis();
        return dao.deleteBuildingByLandloordIds(ts, ids);
    }
}
