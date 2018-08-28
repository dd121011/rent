package com.scrats.rent.mapper;

import com.scrats.rent.base.mapper.BaseMapper;
import com.scrats.rent.entity.RoomRenter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/24 00:12.
 */
public interface RoomRenterMapper extends BaseMapper<RoomRenter> {

    @Update("<script>update room_renter t set t.delete_ts = #{deleteTs} where 1=1 and t.room_renter_id in <foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'> #{item} </foreach></script>")
    int deleteRoomRenterById(@Param("deleteTs") long deleteTs, @Param("ids") Integer... ids);

    @Select("<script>select r.* " +
            "from room_renter r " +
            "where 1=1 " +
            "<if test='roomRenter.roomRenterId != null'>and r.room_rent_id = #{roomRenter.roomRenterId}</if>" +
            "<if test='roomRenter.roomId != null'>and r.room_id = #{roomRenter.roomId}</if>" +
            "<if test='roomRenter.barginId != null'>and r.bargin_id = #{roomRenter.barginId}</if>" +
            "<if test='roomRenter.userId != null'>and r.user_id = #{roomRenter.userId}</if>" +
            "<if test='roomRenter.deleteTs != null and roomRenter.deleteTs != 0'>and r.delete_ts > 0</if>" +
            "<if test='roomRenter.deleteTs == null or roomRenter.deleteTs == 0'>and delete_ts = 0</if>" +
            "<if test='roomRenter.checkTs != null and roomRenter.checkTs == 0'>and check_ts = 0</if>" +
            "<if test='roomRenter.checkTs != null and roomRenter.checkTs != 0'>and r.check_ts > 0</if>" +
            "</script>")
    List<RoomRenter> getListByRoomrenter(@Param("roomRenter") RoomRenter roomRenter);

    @Select("select b.* from room_renter b " +
            "left join room r on r.room_id = b.room_id " +
            "where r.building_id = #{buildingId} and b.delete_ts = 0 and r.delete_ts = 0")
    List<RoomRenter> getRoomRenterByBuildingId(Integer buildingId);

}
