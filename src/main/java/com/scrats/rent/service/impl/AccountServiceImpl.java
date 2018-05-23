package com.scrats.rent.service.impl;

import com.github.pagehelper.PageHelper;
import com.scrats.rent.entity.Account;
import com.scrats.rent.mapper.AccountDao;
import com.scrats.rent.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private AccountDao accountDao;

    @Override
    public List<Account> selectAll() {
        //分页插件的使用 第一个参数是当前页 第二个参数是每页显示的条数
        PageHelper.startPage(2, 2);
        return accountDao.selectAll();
    }
}
