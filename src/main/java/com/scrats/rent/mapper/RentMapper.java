package com.scrats.rent.mapper;

import com.scrats.rent.base.mapper.BaseMapper;
import com.scrats.rent.entity.Rent;
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
public interface RentMapper extends BaseMapper<Rent> {

    @Select("<script>select r.* from rent r where 1=1 <if test='roomId != null'>and r.room_id = #{roomId}</if> <if test='payFlag == false'>and r.pay_ts = 0</if>  <if test='payFlag == true'>and r.pay_ts > 0</if> and r.delete_ts = 0</script>")
    List<Rent> getRentByRoomId(@Param("roomId") Integer roomId, @Param("payFlag") boolean payFlag);

    @Select("<script>select r.* from rent r where 1=1 " +
            "<if test='rent.rentId != null'>and r.rent_id = #{rent.rentId}</if> " +
            "<if test='rent.roomId != null'>and r.room_id = #{rent.roomId}</if> " +
            "<if test='rent.roomNo != null and rent.roomNo != \"\"'>and r.room_no = #{rent.roomNo}</if> " +
            "<if test='rent.month != null and rent.month != \"\"'>and r.month = #{rent.month}</if> " +
            "<if test='rent.payTs != null and rent.payTs > 0'>and r.pay_ts > 0</if> " +
            "<if test='rent.payTs == null or rent.payTs == 0'>and r.pay_ts = 0</if> " +
            "<if test='rent.deleteTs != null and rent.deleteTs >0'>and r.delete_ts > 0</if>" +
            "<if test='rent.deleteTs == null or rent.deleteTs ==0'>and r.delete_ts = 0</if></script>")
    List<Rent> getListByRent(@Param("rent") Rent rent);

    @Select("<script>select r.* from rent r where 1=1 and r.building_id = #{buildingId} <if test='payFlag == false'>and r.pay_ts = 0</if> <if test='payFlag == true'>and r.pay_ts > 0</if> and r.delete_ts = 0</script>")
    List<Rent> getRentByBuildingIdandPayFlag(@Param("buildingId") Integer buildingId, @Param("payFlag") boolean payFlag);
}
