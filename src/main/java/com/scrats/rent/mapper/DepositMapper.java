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

}
