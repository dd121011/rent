package com.scrats.rent.mapper;

import com.scrats.rent.base.mapper.BaseMapper;
import com.scrats.rent.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:32.
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where 1=1 and account_id = #{accountId} limit 1")
    User getUserByAccountId(Integer accountId);

    @Select("<script>select u.* " +
            "from user u " +
            "where 1=1 " +
            "<if test='user.userId != null'>and r.user_id = #{user.userId}</if>" +
            "<if test='user.name != null and user.name !=\"\"'>and r.name like '%'||#{user.name}||'%'</if>" +
            "<if test='user.qq != null and user.qq !=\"\"'>and r.qq like '%'||#{user.qq}||'%'</if>" +
            "<if test='user.wechat != null and user.wechat !=\"\"'>and r.wechat like '%'||#{user.wechat}||'%'</if>" +
            "<if test='user.email != null and user.email !=\"\"'>and r.email like '%'||#{user.email}||'%'</if>" +
            "<if test='user.profession != null and user.profession !=\"\"'>and r.profession like '%'||#{user.profession}||'%'</if>" +
            "<if test='user.hometown != null and user.hometown !=\"\"'>and r.hometown like '%'||#{user.hometown}||'%'</if>" +
            "<if test='user.phone != null and user.phone !=\"\"'>and r.phone like '%'||#{user.phone}||'%'</if>" +
            "<if test='user.sex != null and user.sex !=\"\"'>and r.sex = #{user.sex}</if>" +
            "<if test='user.userId != null'>and r.user_id = #{user.userId}</if>" +
            "<if test='user.deleteTs != null and user.deleteTs > 0'>and r.delete_ts > 0 and check_ts > 0</if>" +
            "<if test='user.deleteTs == null or user.deleteTs == 0'>and (check_ts = 0 or (check_ts > 0 and delete_ts = 0))</if>" +
            "<if test='user.checkTs != null or user.checkTs == 0'>and checkTs = 0</if>" +
            "<if test='user.checkTs != null or user.checkTs > 0'>and checkTs > 0</if>" +
            "</script>")
    List<User> getListByUser(@Param("user") User user);
}
