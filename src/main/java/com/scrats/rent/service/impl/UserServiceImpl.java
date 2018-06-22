package com.scrats.rent.service.impl;

import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.entity.User;
import com.scrats.rent.mapper.UserMapper;
import com.scrats.rent.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User getUserByAccountId(Integer accountId) {
        return dao.getUserByAccountId(accountId);
    }

    @Override
    public User getUserByPhone(String phone) {
        return dao.getUserByPhone(phone);
    }
}
