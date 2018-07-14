package com.scrats.rent.mapper;

import com.scrats.rent.base.mapper.BaseMapper;
import com.scrats.rent.entity.Bargin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/24 00:12.
 */
public interface BarginMapper extends BaseMapper<Bargin> {

    @Select("<script>select b.* from bargin b where 1=1 <if test='roomId != null'>and b.room_id = #{roomId}</if> <if test='deleteFlag == false'>and b.delete_ts = 0</if>  <if test='deleteFlag == true'>and b.delete_ts > 0</if></script>")
    List<Bargin> getBarginByRoomId(@Param("roomId") Integer roomId, @Param("deleteFlag") boolean deleteFlag);

    @Select("<script>select b.* from bargin b where 1=1 " +
            "<if test = 'null != sex and sex != \"\"'> and b.sex = #{sex}</if> " +
            "<if test = 'null != roomId'> and b.room_id = #{roomId}</if> " +
            "<if test = 'null != sex and sex != \"\"'> and b.sex = #{sex}</if> " +
            "<if test = 'null != buildingId'> and b.building_id = #{buildingId}</if> " +
            "<if test = 'null != userId'> and b.user_id = #{userId}</if> " +
            "<if test = 'null != renterId'> and b.renter_id = #{renterId}</if>" +
            "<if test = 'null != landlordId'> and b.landlord_id = #{landlordId}</if>" +
            "<if test = 'null != rentDay'> and b.rent_day = #{rentDay}</if>" +
            "<if test = 'null != deleteTs and deleteTs > 0'> and b.delete_ts > 0</if>" +
            "<if test = 'null == deleteTs or deleteTs = 0'> and b.delete_ts = 0</if>" +
            "</script>")
    List<Bargin> getBarginList(Bargin bargin);

}
