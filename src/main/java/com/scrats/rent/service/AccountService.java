package com.scrats.rent.service;

import com.scrats.rent.common.JsonResult;
import com.scrats.rent.entity.Account;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/24 00:16.
 */
public interface AccountService {

    List<Account> selectAll();

    JsonResult login(String username, String pwd, HttpServletRequest request);
}
