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

    @Select("<script>select t.* "+
            "from building t " +
            "right join building_landlord bl on t.building_id = bl.building_id " +
            "where 1=1 " +
            "<if test='userId != null'>and bl.landlord_id = #{userId}</if>" +
            "<if test = 'null != building'>" +
            "   <if test='building.buildingId != null and building.buildingId > 0'>and t.building_id = #{building.buildingId}</if>" +
                "<if test='building.name != null and building.name != \"\"'>and t.name = #{building.name}</if>" +
                "<if test='building.deleteTs != null and building.deleteTs > 0'>and t.delete_ts > #{building.deleteTs}</if>" +
            "</if>" +
            "</script>")
    List<Building> getBuildingListWithUserId(@Param("building")Building building, @Param("userId")Integer userId);

    @Update("<script>update building t set t.delete_ts = #{deleteTs} where 1=1 and t.building_id in <foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'> #{item} </foreach></script>")
    int deleteBuildingByIds(@Param("deleteTs")long deleteTs, @Param("ids") Integer... ids);

    @Select("select b.* from building b left join room r on b.building_id = r.building_id where 1=1 and r.room_id = #{roomId}")
    Building getBuildingByRoomId(Integer roomId);
}
