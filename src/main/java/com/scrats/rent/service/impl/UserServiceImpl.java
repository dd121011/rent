package com.scrats.rent.service.impl;

import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.entity.User;
import com.scrats.rent.mapper.UserMapper;
import com.scrats.rent.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:34.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserMapper> implements UserService {


    @Override
    public User getUserByAccountId(int accountId) {
        return dao.getUserByAccountId(accountId);
    }
}
