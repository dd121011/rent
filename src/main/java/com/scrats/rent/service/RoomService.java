package com.scrats.rent.service;

import com.scrats.rent.base.service.BaseService;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.entity.Room;
import com.scrats.rent.mapper.RoomMapper;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:33.
 */
public interface RoomService extends BaseService<Room, RoomMapper> {

    PageInfo<Room> getRoomListByBuildingId(int page, int rows, Integer buildingId);

    PageInfo<Room> getRoomListByBuildingId(int page, int rows, Integer buildingId, boolean pageFlag);

    PageInfo<Room> getRoomListByBuildingId(int page, int rows, Integer buildingId, boolean pageFlag, boolean deleteFlag);

    int deleteRoomByIds(Integer... ids);
}
