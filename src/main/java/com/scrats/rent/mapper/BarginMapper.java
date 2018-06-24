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

    @Select("<script>select b.* from bargin b where 1=1 <if test='roomId != null'>and b.room_id = #{roomId}</if> <if test='userId != null'>and b.renter_id = #{userId}</if> and b.delete_ts = 0</script>")
    List<Bargin> getBarginValidByRoomIdAndUserId(@Param("roomId") Integer roomId, @Param("userId") Integer userId);

    @Select("<script>select b.* from bargin b where 1=1 <if test='roomId != null'>and b.room_id = #{roomId}</if> <if test='userId != null'>and b.renter_id = #{userId}</if> and b.delete_ts > 0</script>")
    List<Bargin> getBarginInvalidByRoomIdAndUserId(@Param("roomId") Integer roomId, @Param("userId") Integer userId);
}
