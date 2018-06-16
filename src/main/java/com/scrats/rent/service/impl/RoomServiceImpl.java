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

    @Override
    public PageInfo<Room> getRoomListByBuildingId(int page, int rows, Integer buildingId) {
        return getRoomListByBuildingId(page, rows, buildingId, true);
    }

    @Override
    public PageInfo<Room> getRoomListByBuildingId(int page, int rows, Integer buildingId, boolean pageFlag) {
        return getRoomListByBuildingId(page, rows, buildingId, pageFlag, false);
    }

    @Override
    public PageInfo<Room> getRoomListByBuildingId(int page, int rows, Integer buildingId, boolean pageFlag, boolean deleteFlag) {
        if(pageFlag){
            PageHelper.startPage(page, rows);
            List<Room> list;
            if(deleteFlag){
                list= dao.getRoomListByBuildingIdWithDeleted(buildingId);
            }else{
                list= dao.getRoomListByBuildingId(buildingId);
            }
            return new PageInfo<Room>(list);
        }

        List<Room> list;
        if(deleteFlag){
            list = dao.getRoomListByBuildingIdWithDeleted(buildingId);
        }else{
            list = dao.getRoomListByBuildingId(buildingId);
        }
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public int deleteRoomByIds(Integer... ids) {
        long ts = System.currentTimeMillis();
        return dao.deleteRoomByIds(ts, ids);
    }
}
