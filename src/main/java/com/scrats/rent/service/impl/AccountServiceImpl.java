package com.scrats.rent.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.base.service.RedisService;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.constant.GlobalConst;
import com.scrats.rent.entity.Account;
import com.scrats.rent.entity.User;
import com.scrats.rent.mapper.AccountMapper;
import com.scrats.rent.service.AccountService;
import com.scrats.rent.service.UserService;
import com.scrats.rent.view.LandlordView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/24 00:17.
 */
@Service
public class AccountServiceImpl extends BaseServiceImpl<Account, AccountMapper> implements AccountService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;

    @Override
    public JsonResult login(String username, String pwd) {

        Account result = dao.login(new Account(username, pwd));
        if(null != result){
            User user = userService.getUserByAccountId(result.getAccountId());
            String tokenId = UUID.randomUUID().toString().replace("-","");
            redisService.set(tokenId,user, GlobalConst.ACCESS_TOKEN_EXPIRE);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("tokenId", tokenId);
            jsonObject.put("userId", user.getUserId());
            return new JsonResult<JSONObject>(jsonObject);
        }
        Account sel = new Account();
        sel.setUsername(username);
        result = dao.login(sel);
        if(null != result){
            return new JsonResult("账号不存在");
        }
        return new JsonResult("账号密码错误");

    }

    @Override
    public List<LandlordView> getPhoneByBuildingId(Integer building_id) {
        return dao.getPhoneByBuildingId(building_id);
    }
}
