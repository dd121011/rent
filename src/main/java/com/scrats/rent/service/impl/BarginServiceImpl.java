package com.scrats.rent.service.impl;

import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.entity.Bargin;
import com.scrats.rent.mapper.BarginMapper;
import com.scrats.rent.service.BarginService;
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
public class BarginServiceImpl extends BaseServiceImpl<Bargin, BarginMapper> implements BarginService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Bargin> getBarginByRoomId(Integer roomId, boolean deleteFlag) {

        return dao.getBarginByRoomId(roomId, deleteFlag);
    }

}
