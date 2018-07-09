package com.scrats.rent.mapper;

import com.scrats.rent.base.mapper.BaseMapper;
import com.scrats.rent.entity.Account;
import com.scrats.rent.view.LandlordView;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/24 00:12.
 */
public interface AccountMapper extends BaseMapper<Account> {

    @Select("select username, pwd, phone, account_id, create_ts, update_ts, delete_ts from account\n" +
            "    where 1=1 and username = #{username} and pwd = #{pwd}\n" +
            "    limit 1")
    Account login(Account account);

    @Select("select a.phone, u.name from building_landlord bl left join user u on u.user_id = bl.landlord_id left join account a on a.account_id = u.account_id where bl.building_id = #{building_id}")
    List<LandlordView> getPhoneByBuildingId(Integer building_id);
}
