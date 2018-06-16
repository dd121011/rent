package com.scrats.rent.mapper;

import com.scrats.rent.base.mapper.BaseMapper;
import com.scrats.rent.entity.Room;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:32.
 */
public interface RoomMapper extends BaseMapper<Room> {

    @Select("select t.*, d.value as orientation_name from room t left join dictionary_iterm d on t.orientation = d.dic_iterm_id where 1=1 and t.building_id = #{buildingId} and t.delete_ts = 0")
    List<Room> getRoomListByBuildingId(Integer buildingId);

    @Select("select t.*, d.value as orientation_name from room t left join dictionary_iterm d on t.orientation = d.dic_iterm_id where 1=1 and t.building_id = #{buildingId}")
    List<Room> getRoomListByBuildingIdWithDeleted(Integer buildingId);

    @Update("<script>update room t set t.delete_ts = #{deleteTs} where 1=1 and t.room_id in <foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'> #{item} </foreach></script>")
    int deleteRoomByIds(@Param("deleteTs")long deleteTs, @Param("ids") Integer... ids);
}
