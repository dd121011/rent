package com.scrats.rent.mapper;

import com.scrats.rent.base.mapper.BaseMapper;
import com.scrats.rent.entity.Room;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:32.
 */
public interface RoomMapper extends BaseMapper<Room> {

    @Select("select * from room t where 1=1 and t.building_id = #{buildingId}")
    List<Room> getRoomListByBuildingId(Integer buildingId);
}
