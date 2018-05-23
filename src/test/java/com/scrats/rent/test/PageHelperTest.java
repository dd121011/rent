package com.scrats.rent.test;

import com.scrats.rent.entity.Account;
import com.scrats.rent.service.AccountService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/24 00:21.
 */
public class PageHelperTest  extends BaseTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void tttttt(){
        List<Account> accountList =  accountService.selectAll();
        System.out.println(accountList.toString());
    }
}
