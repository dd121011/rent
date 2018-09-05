package com.scrats.rent.util.weixin.sns;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.scrats.rent.entity.WxSns;
import com.scrats.rent.util.HttpRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/23 08:56.
 */
@Slf4j
@Component
public class WxAuthorize {

    private static final String AUTHORIZE_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    @Value("${wx.sns.appid}")
    private String appId;
    @Value("${wx.sns.secret}")
    private String secret;

    private long expiredTs;
    private static final String OPENID = "openid";
    private static final String UNIONID = "unionid";

    public WxSns checkUserInfoFromWx(String code) {
        WxSns wxSns = null;
        String checkUrl = String.format(AUTHORIZE_URL, appId, secret, code);
        log.info("========checkUrl========" + checkUrl);
        JSONObject infoObj = HttpRequestUtil.httpGet2Json(checkUrl, null);
        if (infoObj != null) {
            log.info(infoObj.toString());
            wxSns = JSON.parseObject(infoObj.toJSONString(),WxSns.class);
        }
        return wxSns;
    }

}
