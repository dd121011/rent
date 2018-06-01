package com.scrats.rent.mapper;

import com.scrats.rent.base.mapper.BaseMapper;
import com.scrats.rent.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/24 00:12.
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

    @Select("select username, pwd, phone, account_id, create_ts, update_ts, delete_ts from account\n" +
            "    where 1=1 and username = #{username} and pwd = #{pwd}\n" +
            "    limit 1")
    Account login(Account account);
}
