package com.scrats.rent.service;

import com.scrats.rent.base.service.BaseService;
import com.scrats.rent.constant.UserType;
import com.scrats.rent.entity.UserRole;
import com.scrats.rent.mapper.UserRoleMapper;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:33.
 */
public interface UserRoleService extends BaseService<UserRole, UserRoleMapper> {

    List<UserRole> getListByUserRole(UserRole userRole);

    boolean noExistRoleWithUserId(UserType userType,  Integer userId);
}
