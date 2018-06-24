package com.scrats.rent.api;

import com.scrats.rent.service.UserService;
import com.scrats.rent.service.WxSnsService;
import com.scrats.rent.util.weixin.sns.WxAuthorize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private WxSnsService wxSnsService;


}
