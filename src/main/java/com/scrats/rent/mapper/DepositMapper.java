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

    @Select("<script>select d.* from deposit d where 1=1 <if test='roomId != null'>and d.room_id = #{roomId}</if> <if test='userId != null'>and d.renter_id = #{userId}</if> and d.delete_ts = 0</script>")
    List<Deposit> getDepositValidByRoomIdAndUserId(@Param("roomId") Integer roomId, @Param("userId") Integer userId);

    @Select("<script>select d.* from deposit d where 1=1 <if test='roomId != null'>and d.room_id = #{roomId}</if> <if test='userId != null'>and d.renter_id = #{userId}</if> and d.delete_ts > 0</script>")
    List<Deposit> getDepositInvalidByRoomIdAndUserId(@Param("roomId") Integer roomId, @Param("userId") Integer userId);
}
