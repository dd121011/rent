package com.scrats.rent.service.impl;

import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.constant.UserType;
import com.scrats.rent.entity.UserRole;
import com.scrats.rent.mapper.UserRoleMapper;
import com.scrats.rent.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:34.
 */
@Slf4j
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole, UserRoleMapper> implements UserRoleService {

    @Override
    public List<UserRole> getListByUserRole(UserRole userRole) {
        return dao.getListByUserRole(userRole);
    }

    @Override
    public boolean noExistRoleWithUserId(UserType userType, Integer userId) {
        UserRole param = new UserRole(userType, userId);
        List<UserRole> result = dao.getListByUserRole(param);
        if(result.size() > 0){
            return false;
        }
        return true;
    }
}
