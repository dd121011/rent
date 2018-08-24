package com.scrats.rent.mapper;

import com.scrats.rent.base.mapper.BaseMapper;
import com.scrats.rent.entity.Bargin;
import com.scrats.rent.entity.BarginExtra;
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
public interface BarginExtraMapper extends BaseMapper<BarginExtra> {

    @Select("<script>select be.dic_iterm_code, be.value " +
            "from bargin_extra be " +
            "left join bargin b on be.bargin_id = b.bargin_id " +
            "where b.delete_ts = 0 and be.number >= 0 and be.delete_ts = 0 " +
            "<if test = 'null != buildingId'> and b.building_id = #{buildingId} </if>" +
            "<if test = 'null != roomId'> and b.room_id = #{roomId} </if>" +
            "<if test = 'null != barginId'> and b.bargin_id = #{barginId} </if>" +
            "group by be.dic_iterm_code </script>")
    List<BarginExtra> getBarginExtraTypeByBargin(Bargin bargin);

    @Select("select be.bargin_extra_id, be.bargin_id, be.room_id, be.dic_iterm_code, be.value, be.number " +
            "from bargin_extra be " +
            "left join bargin b on be.bargin_id = b.bargin_id " +
            "where b.delete_ts = 0 and be.number >=0 and be.delete_ts = 0 and b.building_id = #{buildingId} and be.dic_iterm_code = #{code}" +
            "order by room_id")
    List<BarginExtra> getBarginExtraByBuildingIdAndDicItermCode(@Param("buildingId") Integer buildingId, @Param("code") String code);
}
