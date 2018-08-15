package com.scrats.rent.service;

import com.scrats.rent.base.service.BaseService;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.entity.User;
import com.scrats.rent.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:33.
 */
public interface UserService extends BaseService<User, UserMapper> {

    User getUserByAccountId(Integer accountId);

    JsonResult realCertification(Integer userId, String idCardPic, String idCardPicBack);

    List<User> getListByUser(@Param("user") User user);

}
