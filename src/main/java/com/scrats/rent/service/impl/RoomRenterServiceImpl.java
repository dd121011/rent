package com.scrats.rent.service.impl;

import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.entity.RoomRenter;
import com.scrats.rent.mapper.RoomRenterMapper;
import com.scrats.rent.service.RoomRenterService;
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
public class RoomRenterServiceImpl extends BaseServiceImpl<RoomRenter, RoomRenterMapper> implements RoomRenterService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public int deleteRoomRenterById(Integer... ids) {
        long ts = System.currentTimeMillis();
        return dao.deleteRoomRenterById(ts, ids);
    }
}
