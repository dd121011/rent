package com.scrats.rent.mapper;

import com.scrats.rent.base.mapper.BaseMapper;
import com.scrats.rent.entity.UserRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:32.
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    @Select("<script>select u.* from user_role u where 1=1 " +
            "<if test='userRoleId != null'>and u.user_role_id = #{userRoleId}</if>" +
            "<if test='roleCode != null and roleCode != \"\"'>and u.role_code = #{roleCode}</if>" +
            "<if test='userId != null'>and u.user_id = #{userId}</if>" +
            "</script>")
    List<UserRole> getListByUserRole(UserRole userRole);

}
