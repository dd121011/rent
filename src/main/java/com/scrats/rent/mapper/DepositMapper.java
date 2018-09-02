package com.scrats.rent.mapper;

import com.scrats.rent.base.mapper.BaseMapper;
import com.scrats.rent.entity.Deposit;
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
public interface DepositMapper extends BaseMapper<Deposit> {

    @Select("<script>select d.* from deposit d where 1=1 <if test='roomId != null'>and d.room_id = #{roomId}</if> <if test='deleteFlag == false'>and d.delete_ts = 0</if>  <if test='deleteFlag == true'>and d.delete_ts > 0</if></script>")
    List<Deposit> getDepositByRoomId(@Param("roomId") Integer roomId, @Param("deleteFlag") boolean deleteFlag);

    @Select("<script>select d.* from deposit d where room_id = #{roomId} and delete_ts = 0 and pay_ts = 0</script>")
    List<Deposit> getUnpayDeposit(Integer roomId);

    @Select("<script>select d.* from deposit d " +
            "where 1=1 and d.delete_ts = 0" +
            "<if test='deposit.depositId != null'>and d.deposit_id = #{deposit.depositId}</if> " +
            "<if test='deposit.depositNo != null'>and d.deposit_no = #{deposit.depositNo}</if> " +
            "<if test='deposit.payNo != null'>and d.pay_no = #{deposit.payNo}</if> " +
            "<if test='deposit.buildingId != null'>and d.building_id = #{deposit.buildingId}</if> " +
            "<if test='deposit.barginId != null'>and d.bargin_id = #{deposit.barginId}</if> " +
            "<if test='deposit.userId != null'>and d.user_id = #{deposit.userId}</if> " +
            "<if test='deposit.channel != null and deposit.channel != \"\"'>and d.channel = #{deposit.channel}</if> " +
            "<if test='deposit.payTs != null and deposit.payTs > 0'>and d.pay_ts > 0</if> " +
            "<if test='deposit.payTs == null or deposit.payTs == 0'>and d.pay_ts = 0</if> " +
            "<if test='deposit.deleteTs != null and deposit.deleteTs >0'>and d.delete_ts > 0</if>" +
            "<if test='deposit.deleteTs == null or deposit.deleteTs ==0'>and d.delete_ts = 0</if>" +
            "<if test='fromTs != null and fromTs > 0'>and d.pay_ts >= #{fromTs}</if> " +
            "<if test='toTs != null and toTs > 0'>and d.pay_ts &lt;= #{toTs}</if> " +
            "order by d.building_id, d.room_id, d.bargin_id desc</script>")
    List<Deposit> payedWithRange(@Param("fromTs") Long fromTs, @Param("toTs")Long toTs, @Param("deposit")Deposit deposit);
}
