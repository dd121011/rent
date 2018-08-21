package com.scrats.rent.controller;

import com.alibaba.fastjson.JSON;
import com.scrats.rent.base.service.RedisService;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.annotation.APIRequestControl;
import com.scrats.rent.common.annotation.IgnoreSecurity;
import com.scrats.rent.common.exception.BusinessException;
import com.scrats.rent.common.exception.NotAuthorizedException;
import com.scrats.rent.constant.GlobalConst;
import com.scrats.rent.entity.Account;
import com.scrats.rent.entity.User;
import com.scrats.rent.service.AccountService;
import com.scrats.rent.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private AccountService accountService;

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

    @PostMapping("/updatePersonalInfo")
    @ResponseBody
    public JsonResult updatePersonalInfo(@APIRequestControl APIRequest apiRequest) {

        User updateUser = JSON.parseObject(JSON.toJSONString(apiRequest.getBody()),User.class);
        if(null == updateUser){
            throw new BusinessException("请求参数有误");
        }

        updateUser.setUserId(apiRequest.getUser().getUserId());
        updateUser.setUpdateTs(System.currentTimeMillis());

        userService.updateByPrimaryKeySelective(updateUser);

        //更新缓存数据
        redisService.set(apiRequest.getTokenId(),userService.selectByPrimaryKey(updateUser.getUserId()), GlobalConst.ACCESS_TOKEN_EXPIRE);

        return new JsonResult();
    }

    @GetMapping("/personalInfo")
    @ResponseBody
    public JsonResult personalInfo(@APIRequestControl APIRequest apiRequest) {

        User user = apiRequest.getUser();

        return new JsonResult<User>(user);
    }

    @PostMapping("/checkPwd")
    @ResponseBody
    public JsonResult checkPwd(@APIRequestControl APIRequest apiRequest) {

        String checkPwd = APIRequest.getParameterValue(apiRequest, "pwd", String.class);

        Account account = accountService.selectByPrimaryKey(apiRequest.getUser().getAccountId());


        if(checkPwd.equals(account.getPwd())){
            return new JsonResult();
        }

        return new JsonResult("校验失败, 请输入正确的密码!");
    }

}
