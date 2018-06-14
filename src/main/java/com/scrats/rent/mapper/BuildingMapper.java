package com.scrats.rent.mapper;

import com.scrats.rent.base.mapper.BaseMapper;
import com.scrats.rent.entity.Building;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:32.
 */
public interface BuildingMapper extends BaseMapper<Building> {

    @Select("select b.* from building b right join building_landlord bl on b.building_id = bl.building_id where bl.landlord_id = #{userId} and b.delete_ts = 0")
    List<Building> getBuildingListByUserId(int userId);

    @Update("<script>update building t set t.delete_ts = #{deleteTs} where 1=1 and building_id in <foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'> #{item} </foreach></script>")
    int deleteBuildingByIds(@Param("deleteTs")long deleteTs, @Param("ids") Integer... ids);
}
