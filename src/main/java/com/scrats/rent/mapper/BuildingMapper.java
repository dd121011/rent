package com.scrats.rent.mapper;

import com.scrats.rent.base.mapper.BaseMapper;
import com.scrats.rent.entity.Building;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:32.
 */
@Mapper
public interface BuildingMapper extends BaseMapper<Building> {

    @Select("select b.* from building b right join building_landlord bl on b.building_id = bl.building_id where bl.landlord_id = #{userId} and b.delete_ts = 0")
    List<Building> getBuildingListByUserId(int userId);
}
