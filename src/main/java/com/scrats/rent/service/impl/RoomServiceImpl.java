package com.scrats.rent.service.impl;

import com.github.pagehelper.PageHelper;
import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.entity.Room;
import com.scrats.rent.mapper.RoomMapper;
import com.scrats.rent.service.RoomService;
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
public class RoomServiceImpl extends BaseServiceImpl<Room, RoomMapper> implements RoomService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public PageInfo<Room> getRoomListByBuildingId(int page, int rows, Integer buildingId) {
        PageHelper.startPage(page, rows);
        List<Room> list = dao.getRoomListByBuildingId(buildingId);
        return new PageInfo<Room>(list);
    }

    @Override
    public PageInfo<Room> getRoomListByBuildingId(int page, int rows, Integer buildingId, boolean pageFlag) {
        if(pageFlag){
            return getRoomListByBuildingId(page, rows, buildingId);
        }
        List<Room> list = dao.getRoomListByBuildingId(buildingId);
        return new PageInfo<Room>(list);
    }
}
