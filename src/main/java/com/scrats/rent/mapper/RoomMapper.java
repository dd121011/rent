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

    @Select("<script>select t.*, d.value as orientation_name " +
            "from room t " +
            "left join dictionary_iterm d on t.orientation = d.dic_iterm_id " +
            "where 1=1 " +
            "<if test='buildingId != null'>and t.building_id = #{buildingId}</if>" +
            "<if test='roomNo != null and roomNo != \"\"'>and t.room_no = #{roomNo}</if>" +
            "<if test='rentTs != null and rentTs > 0'>and t.rent_ts > #{rentTs}</if>" +
            "<if test='rentTs != null and rentTs == 0'>and t.rent_ts = #{rentTs}</if>" +
            "<if test='deleteTs != null and deleteTs > 0'>and t.delete_ts > #{deleteTs}</if>" +
            "</script>")
    List<Room> getRoomList(Room room);

    @Update("<script>update room t set t.delete_ts = #{deleteTs} where 1=1 and t.room_id in <foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'> #{item} </foreach></script>")
    int deleteRoomByIds(@Param("deleteTs")long deleteTs, @Param("ids") Integer... ids);
}
