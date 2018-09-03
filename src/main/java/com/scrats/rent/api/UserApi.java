package com.scrats.rent.api;

import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.annotation.APIRequestControl;
import com.scrats.rent.common.exception.BusinessException;
import com.scrats.rent.entity.User;
import com.scrats.rent.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/23 09:12.
 */
@RestController
@RequestMapping("/api/user")
public class UserApi {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @PostMapping("/realCertification")
    public JsonResult realCertification(@APIRequestControl APIRequest apiRequest) {

        String idCardPic = APIRequest.getParameterValue(apiRequest,"idCardPic",String.class);
        String idCardPicBack = APIRequest.getParameterValue(apiRequest,"idCardPicBack",String.class);

        return userService.realCertification(apiRequest.getUser().getUserId(), idCardPic, idCardPicBack);
    }

    @GetMapping("/realConfirm/{userId}")
    public JsonResult checkReal(@APIRequestControl APIRequest apiRequest, @PathVariable(name = "userId") String userId) {

        if(!apiRequest.isAdminFlag()){
            throw new BusinessException("请求数据有误!");
        }
        User checkUser = userService.selectByPrimaryKey(userId);
        if(null == checkUser || checkUser.getCheckTs() > 0){
            throw new BusinessException("认证数据有误!");
        }

        Long checkTs = System.currentTimeMillis();
        checkUser.setCheckTs(checkTs);
        checkUser.setUpdateTs(checkTs);
        userService.updateByPrimaryKeySelective(checkUser);
        return new JsonResult();
    }

}
