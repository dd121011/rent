package com.scrats.rent.controller;

import com.scrats.rent.base.service.RedisService;
import com.scrats.rent.common.annotation.IgnoreSecurity;
import com.scrats.rent.common.exception.BusinessException;
import com.scrats.rent.common.exception.NotAuthorizedException;
import com.scrats.rent.entity.User;
import com.scrats.rent.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Created with jointstarc.
 * @Email: 262297088@qq.com
 * @Description:
 * @User: lol.
 * @Date: 2017/12/28 17:10.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisService redisService;
    @Autowired
    private UserService userService;

    @IgnoreSecurity
    @GetMapping("/goUserDetail/{landlordId}")
    public String goUserDetail(@PathVariable Integer landlordId, String tokenId, Map<String, Object> map) {

        User user = (User)redisService.get(tokenId);
        if(null == user){
            throw new NotAuthorizedException("非法请求, 请登陆");
        }
        if(user.getUserId() - landlordId != 0){
            throw new BusinessException("请求参数错误, 请检查");
        }

        map.put("user",user);

        return "landlord/user_detail";
    }
}
