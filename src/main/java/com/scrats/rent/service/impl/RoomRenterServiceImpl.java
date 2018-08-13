package com.scrats.rent.service.impl;

import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.entity.RoomRenter;
import com.scrats.rent.mapper.RoomRenterMapper;
import com.scrats.rent.service.RoomRenterService;
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
public class RoomRenterServiceImpl extends BaseServiceImpl<RoomRenter, RoomRenterMapper> implements RoomRenterService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public int deleteRoomRenterById(Integer... ids) {
        long ts = System.currentTimeMillis();
        return dao.deleteRoomRenterById(ts, ids);
    }

    @Override
    public List<RoomRenter> getListByRoomrenter(RoomRenter roomRenter) {
        return dao.getListByRoomrenter(roomRenter);
    }

    @Override
    public List<RoomRenter> getRoomRenterByBuildingId(Integer buildingId) {
        return dao.getRoomRenterByBuildingId(buildingId);
    }

    @Override
    public int renterCheck(Integer roomRenterId) {
        RoomRenter roomRenter = dao.selectByPrimaryKey(roomRenterId);
        if(null == roomRenter){
            return 0;
        }
        Long updateTs = System.currentTimeMillis();
        roomRenter.setCheckTs(updateTs);
        roomRenter.setUpdateTs(updateTs);
        return dao.updateByPrimaryKeySelective(roomRenter);
    }

}
