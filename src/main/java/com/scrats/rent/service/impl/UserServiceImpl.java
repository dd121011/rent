package com.scrats.rent.service.impl;

import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.exception.BusinessException;
import com.scrats.rent.entity.User;
import com.scrats.rent.mapper.UserMapper;
import com.scrats.rent.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
public class UserServiceImpl extends BaseServiceImpl<User, UserMapper> implements UserService {

    @Override
    public User getUserByAccountId(Integer accountId) {
        return dao.getUserByAccountId(accountId);
    }

    @Override
    public JsonResult realCertification(Integer userId, String idCardPic, String idCardPicBack) {
        User user = dao.selectByPrimaryKey(userId);
        if(null == user){
            throw new BusinessException("请求数据有误!");
        }
        if(user.getCheckTs() > 0){
            throw new BusinessException("该用户已实名认证, 请勿重复认证!");
        }
        user.setIdCardPic(idCardPic);
        user.setIdCardPicBack(idCardPicBack);
        user.setUpdateTs(System.currentTimeMillis());
        dao.updateByPrimaryKeySelective(user);
        return new JsonResult();
    }

    @Override
    public List<User> getListByUser(User user) {
        return dao.getListByUser(user);
    }

}
