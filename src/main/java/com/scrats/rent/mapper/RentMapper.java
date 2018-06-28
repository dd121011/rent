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
}
