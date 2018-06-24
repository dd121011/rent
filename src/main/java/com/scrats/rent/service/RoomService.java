package com.scrats.rent.service;

import com.scrats.rent.base.service.BaseService;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.entity.Bargin;
import com.scrats.rent.entity.DepositIterm;
import com.scrats.rent.entity.DictionaryIterm;
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

    boolean rent(Bargin bargin, List<DictionaryIterm> extras, List<DepositIterm> depositIterms);

    Room detail(Integer roomId);
}
