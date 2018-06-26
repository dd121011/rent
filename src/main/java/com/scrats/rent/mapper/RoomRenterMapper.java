package com.scrats.rent.mapper;

import com.scrats.rent.base.mapper.BaseMapper;
import com.scrats.rent.entity.RoomRenter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

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

}
