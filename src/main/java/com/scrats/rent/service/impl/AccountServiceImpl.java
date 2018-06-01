package com.scrats.rent.service.impl;

import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.base.service.RedisService;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.entity.Account;
import com.scrats.rent.mapper.AccountMapper;
import com.scrats.rent.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/24 00:17.
 */
@Service
public class AccountServiceImpl extends BaseServiceImpl<Account, AccountMapper> implements AccountService {


    @Autowired
    private RedisService redisService;

    @Override
    public JsonResult login(String username, String pwd, HttpServletRequest request) {

        Account result = dao.login(new Account(username, pwd));
        if(null != result){
            request.getSession().setAttribute("account", result);
            return new JsonResult();
        }
        Account sel = new Account();
        sel.setUsername(username);
        result = dao.login(sel);
        if(null != result){
            return new JsonResult("账号不存在");
        }
        return new JsonResult("账号密码错误");

    }
}
