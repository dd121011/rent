package com.scrats.rent.service.impl;

import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.entity.BuildingLandlord;
import com.scrats.rent.mapper.BuildingLandlordMapper;
import com.scrats.rent.service.BuildingLandlordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:34.
 */
@Service
public class BuildingLandlordServiceImpl extends BaseServiceImpl<BuildingLandlord, BuildingLandlordMapper> implements BuildingLandlordService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

}
