package com.scrats.rent.api;

import com.alibaba.fastjson.JSON;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.annotation.IgnoreSecurity;
import com.scrats.rent.entity.User;
import com.scrats.rent.service.UserService;
import com.scrats.rent.util.weixin.sns.WxAuthorize;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    private WxAuthorize wxAuthorize;
    @Autowired
    private UserService userService;

    @IgnoreSecurity
    @GetMapping("/bindUser")
    public String bindUser(String code, String userId){
        User oldUser = userService.selectByPrimaryKey(userId);
        if(oldUser == null){
            return JSON.toJSONString(new JsonResult("请求的userId=" + userId + "无效, 数据库中无数据"));
        }
        if(StringUtils.isEmpty(code)){
            return JSON.toJSONString(new JsonResult("获取的微信code为空"));
        }
        User user = wxAuthorize.checkUserInfoFromWx(code);
        if(null == user){
            return JSON.toJSONString(new JsonResult("通过code=" + code + "获取绑定openid失败"));
        }

        oldUser.setUnionid(user.getUnionid());
        oldUser.setOpenid(user.getOpenid());
        oldUser.setUpdateTs(System.currentTimeMillis());
        userService.updateByPrimaryKeySelective(oldUser);

        return JSON.toJSONString(new JsonResult<User>(oldUser));
    }
}
