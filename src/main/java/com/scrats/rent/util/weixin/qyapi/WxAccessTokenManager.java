package com.scrats.rent.util.weixin.qyapi;

import com.alibaba.fastjson.JSONObject;
import com.scrats.rent.util.HttpRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * Created by scrat on 2017/11/30.
 */
@Slf4j
public class WxAccessTokenManager {

    private static final String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s";

    private String cropId;
    private String cropSecret;
    private String accessToken;
    private long expiredTs;
    private static final String ACCESS_TOKEN = "access_token";

    private static WxAccessTokenManager INSTANCE;

    private WxAccessTokenManager(String cropId, String cropSecret) {
        this.cropId = cropId;
        this.cropSecret = cropSecret;
    }

    public static WxAccessTokenManager getInstance(String cropId, String cropSecret) {
        if (INSTANCE == null) {
            // Double-Check Locking
            synchronized (WxAccessTokenManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WxAccessTokenManager(cropId, cropSecret);
                }
            }
        }
        return INSTANCE;
    }

    public void refreshAccessToken() {
        getAccessToken(true);
    }

    public String getAccessToken() {
        return getAccessToken(false);
    }

    public String getAccessToken(boolean refresh) {
        log.info("expiredTs=" + expiredTs);
        long nowTs = System.currentTimeMillis();
        if (!refresh && nowTs <= expiredTs) {
            return this.accessToken;
        }

        String accessToken = getAccessTokenFromWx();
        if (StringUtils.isEmpty(accessToken)) {
            return this.accessToken;
        }
        this.accessToken = accessToken;
        expiredTs = nowTs + 3600000;
        return accessToken;
    }

    private String getAccessTokenFromWx() {
        int retryTimes = 0;
        while (retryTimes < 3) {
            retryTimes++;
            String tokenUrl = String.format(ACCESS_TOKEN_URL, cropId, cropSecret);
            log.info("========tokenUrl========" + tokenUrl);
            JSONObject tokenObj = HttpRequestUtil.httpGet2Json(tokenUrl, null);
            if (tokenObj != null) {
                log.info(tokenObj.toString());
                String token = tokenObj.getString(ACCESS_TOKEN);
                if (!StringUtils.isEmpty(token)) {
                    return token;
                }
            }
            log.info("retry:" + retryTimes);
        }
        return null;
    }
}
