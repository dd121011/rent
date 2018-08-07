package com.scrats.rent.mapper;

import com.scrats.rent.base.mapper.BaseMapper;
import com.scrats.rent.entity.ExtraHistory;
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
public interface ExtraHistoryMapper extends BaseMapper<ExtraHistory> {

    @Select("<script>select r.* from extra_history r where 1=1 " +
            "<if test='extraHistory.extraHistoryId != null'>and r.extra_history_id = #{extraHistory.extraHistoryId}</if> " +
            "<if test='extraHistory.roomId != null'>and r.room_id = #{extraHistory.roomId}</if> " +
            "<if test='extraHistory.rentId != null'>and r.rent_id = #{extraHistory.rentId}</if> " +
            "<if test='extraHistory.barginEtraId != null'>and r.bargin_etra_id = #{extraHistory.barginEtraId}</if> " +
            "<if test='extraHistory.month != null and extraHistory.month != \"\" '>and r.month = #{extraHistory.month}</if> " +
            "<if test='extraHistory.dicItermCode != null and extraHistory.dicItermCode != \"\" '>and r.dic_iterm_code = #{extraHistory.dicItermCode}</if> " +
            "<if test='extraHistory.deleteTs != null and extraHistory.deleteTs > 0'>and r.delete_ts > 0</if> " +
            "<if test='extraHistory.deleteTs == null or extraHistory.deleteTs = 0'>and r.delete_ts = 0</if> " +
            "</script>")
    List<ExtraHistory> getListByExtraHistory(@Param("extraHistory") ExtraHistory extraHistory);

    @Select("select r.*, b.value, b.number from extra_history r left join bargin_extra b on r.bargin_extra_id = b.bargin_extra_id where 1=1 and r.rent_id = #{rentId}")
    List<ExtraHistory> getRentEditExtraHistory(Integer rentId);
}
