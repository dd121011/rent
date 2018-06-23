package com.scrats.rent.util.weixin.sns;

import com.alibaba.fastjson.JSONObject;
import com.scrats.rent.entity.User;
import com.scrats.rent.util.HttpRequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/23 08:56.
 */
@Component
public class WxAuthorize {

    private final Logger logger = Logger.getLogger(this.getClass());

    private static final String AUTHORIZE_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    @Value("wx.sns.appid")
    private String appId;
    @Value("wx.sns.secret")
    private String secret;

    private long expiredTs;
    private static final String OPENID = "openid";
    private static final String UNIONID = "unionid";

    public User checkUserInfoFromWx(String code) {
        User user = null;
        String checkUrl = String.format(AUTHORIZE_URL, appId, secret, code);
        logger.info("========checkUrl========" + checkUrl);
        JSONObject infoObj = HttpRequestUtil.httpGet2Json(checkUrl);
        if (infoObj != null) {
            logger.info(infoObj.toString());
            String openid = infoObj.getString(OPENID);
            if(StringUtils.isNotEmpty(openid)){
                user = new User();
                user.setOpenid(openid);
                user.setUnionid(infoObj.getString(UNIONID));
            }
        }
        return user;
    }

}
