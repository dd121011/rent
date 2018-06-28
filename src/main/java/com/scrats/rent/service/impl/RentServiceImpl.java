package com.scrats.rent.service.impl;

import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.entity.Rent;
import com.scrats.rent.mapper.RentMapper;
import com.scrats.rent.service.RentService;
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
public class RentServiceImpl extends BaseServiceImpl<Rent, RentMapper> implements RentService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Rent> getRentByRoomId(Integer roomId, boolean payFlag) {
        return dao.getRentByRoomId(roomId, payFlag);
    }
}
