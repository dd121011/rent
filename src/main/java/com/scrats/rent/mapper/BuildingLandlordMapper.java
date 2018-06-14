package com.scrats.rent.mapper;

import com.scrats.rent.base.mapper.BaseMapper;
import com.scrats.rent.entity.BuildingLandlord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:32.
 */
public interface BuildingLandlordMapper extends BaseMapper<BuildingLandlord> {

    @Update("<script>update building_landlord t set t.delete_ts = #{deleteTs} where 1=1 and building_id in <foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'> #{item} </foreach></script>")
    int deleteBuildingByLandloordIds(@Param("deleteTs") long deleteTs, @Param("ids") Integer... ids);
}
