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

    @Select("<script>select r.* from rent r where 1=1 <if test='roomId != null'>and r.room_id = #{roomId}</if> <if test='payFlag == false'>and r.pay_ts = 0</if>  <if test='payFlag == true'>and r.pay_ts > 0</if> and r.delete_ts = 0 order by r.rent_month desc</script>")
    List<Rent> getRentByRoomId(@Param("roomId") Integer roomId, @Param("payFlag") boolean payFlag);

    @Select("<script>select r.*, ro.room_no from rent r " +
            "left join room ro on r.room_id = ro.room_id " +
            "where 1=1 " +
            "<if test='rentId != null'>and r.rent_id = #{rentId}</if> " +
            "<if test='roomId != null'>and r.room_id = #{roomId}</if> " +
            "<if test='buildingId != null'>and r.building_id = #{buildingId}</if> " +
            "<if test='barginId != null'>and r.bargin_id = #{barginId}</if> " +
            "<if test='rentNo != null'>and r.rent_no = #{rentNo}</if> " +
            "<if test='rentMonth != null and rentMonth != \"\"'>and r.rent_month = #{rentMonth}</if> " +
            "<if test='payTs != null and payTs > 0'>and r.pay_ts > 0</if> " +
            "<if test='payTs == null or payTs == 0'>and r.pay_ts = 0</if> " +
            "<if test='deleteTs != null and deleteTs >0'>and r.delete_ts > 0</if>" +
            "<if test='deleteTs == null or deleteTs ==0'>and r.delete_ts = 0</if>" +
            "order by r.building_id, ro.room_no, r.rent_month desc</script>")
    List<Rent> getListByRent(Rent rent);

    @Select("<script>select r.* from rent r where 1=1 and r.building_id = #{buildingId} <if test='payFlag == false'>and r.pay_ts = 0</if> <if test='payFlag == true'>and r.pay_ts > 0</if> and r.delete_ts = 0 order by r.rent_month desc</script>")
    List<Rent> getRentByBuildingIdandPayFlag(@Param("buildingId") Integer buildingId, @Param("payFlag") boolean payFlag);

    @Select("<script>select r.*,ro.room_no from rent r " +
            "left join room ro on r.room_id = ro.room_id " +
            "where 1=1" +
            "<if test='rent.rentId != null'>and r.rent_id = #{rent.rentId}</if> " +
            "<if test='rent.roomId != null'>and r.room_id = #{rent.roomId}</if> " +
            "<if test='rent.buildingId != null'>and r.building_id = #{rent.buildingId}</if> " +
            "<if test='rent.barginId != null'>and r.bargin_id = #{rent.barginId}</if> " +
            "<if test='rent.rentNo != null'>and r.rent_no = #{rentNo}</if> " +
            "<if test='rent.payNo != null'>and r.pay_no = #{rent.payNo}</if> " +
            "<if test='rent.rentMonth != null and rent.rentMonth != \"\"'>and r.rent_month = #{rent.rentMonth}</if> " +
            "<if test='rent.payTs != null and rent.payTs > 0'>and r.pay_ts > 0</if> " +
            "<if test='rent.payTs == null or rent.payTs == 0'>and r.pay_ts = 0</if> " +
            "<if test='rent.deleteTs != null and rent.deleteTs >0'>and r.delete_ts > 0</if>" +
            "<if test='rent.deleteTs == null or rent.deleteTs ==0'>and r.delete_ts = 0</if>" +
            "<if test='fromTs != null and fromTs > 0'>and r.pay_ts >= #{fromTs}</if> " +
            "<if test='toTs != null and toTs > 0'>and r.pay_ts &lt;= #{toTs}</if> " +
            "order by r.building_id, ro.room_no, r.rent_month desc</script>")
    List<Rent> payedWithRange(@Param("fromTs") Long fromTs, @Param("toTs")Long toTs, @Param("rent")Rent rent);
}
