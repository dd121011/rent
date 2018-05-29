package com.scrats.rent.service.impl;

import com.scrats.rent.base.service.RedisService;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.entity.Account;
import com.scrats.rent.mapper.AccountMapper;
import com.scrats.rent.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/24 00:17.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public List<Account> selectAll() {
        //分页插件的使用 第一个参数是当前页 第二个参数是每页显示的条数
//        PageHelper.startPage(2, 2);
        return accountMapper.selectAll();
    }

    @Override
    public JsonResult login(String username, String pwd, HttpServletRequest request) {

        Account result = accountMapper.login(new Account(username, pwd));
        if(null != result){
            request.getSession().setAttribute("account", result);
            return new JsonResult();
        }
        Account sel = new Account();
        sel.setUsername(username);
        result = accountMapper.login(sel);
        if(null != result){
            return new JsonResult("账号不存在");
        }
        return new JsonResult("账号密码错误");

    }
}
