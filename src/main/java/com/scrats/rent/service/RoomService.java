package com.scrats.rent.service;

import com.scrats.rent.base.service.BaseService;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.entity.Bargin;
import com.scrats.rent.entity.ExtraHistory;
import com.scrats.rent.entity.Room;
import com.scrats.rent.mapper.RoomMapper;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:33.
 */
public interface RoomService extends BaseService<Room, RoomMapper> {

    PageInfo<Room> getRoomList(APIRequest apiRequest, Room room);

    PageInfo<Room> getRoomList(APIRequest apiRequest, Room room, boolean pageFlag);

    int deleteRoomByIds(Integer... ids);

    JsonResult rent(Bargin bargin);

    Room detail(Integer roomId);

    Room detailForRenter(Integer roomId);

    List<Room> getRoomByRoomNoAndBuildingId(String roomNo, Integer buildingId);

    JsonResult charge(List<ExtraHistory> chargeList, String month, Integer barginId, Integer roomId, String remark);
}
