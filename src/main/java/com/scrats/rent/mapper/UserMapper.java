package com.scrats.rent.mapper;

import com.scrats.rent.base.mapper.BaseMapper;
import com.scrats.rent.entity.User;
import org.apache.ibatis.annotations.Select;

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

}
