package com.scrats.rent.api;

import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.annotation.APIRequestControl;
import com.scrats.rent.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        if(StringUtils.isEmpty(idCardPic) || StringUtils.isEmpty(idCardPicBack)){
            return new JsonResult("认证数据有误");
        }

        return userService.realCertification(apiRequest.getUser().getUserId(), idCardPic, idCardPicBack);
    }

}
