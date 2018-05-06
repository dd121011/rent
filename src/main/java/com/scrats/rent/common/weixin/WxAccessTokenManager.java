package com.scrats.rent.common.weixin;

import com.alibaba.fastjson.JSONObject;
import com.scrats.rent.util.HttpRequestUtil;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 * Created by scrat on 2017/11/30.
 */
public class WxAccessTokenManager {
    private final Logger logger = Logger.getLogger(this.getClass());

    private static final String ACCESS_TOKEN_URL = "/cgi-bin/gettoken?corpid=%s&corpsecret=%s";

    private String nginxServer;
    private String cropId;
    private String cropSecret;
    private String accessToken;
    private long expiredTs;
    private static final String ACCESS_TOKEN = "access_token";

    private static WxAccessTokenManager INSTANCE;

    private WxAccessTokenManager(String nginxServer, String cropId, String cropSecret) {
        this.nginxServer = nginxServer;
        this.cropId = cropId;
        this.cropSecret = cropSecret;
    }

    public static WxAccessTokenManager getInstance(String nginxServer, String cropId, String cropSecret) {
        if (INSTANCE == null) {
            // Double-Check Locking
            synchronized (WxAccessTokenManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WxAccessTokenManager(nginxServer, cropId, cropSecret);
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
        logger.info("expiredTs=" + expiredTs);
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
            String tokenUrl = String.format(nginxServer + ACCESS_TOKEN_URL, cropId, cropSecret);
            logger.info("========tokenUrl========" + tokenUrl);
            JSONObject tokenObj = HttpRequestUtil.httpGet2Json(tokenUrl);
            if (tokenObj != null) {
                logger.info(tokenObj.toString());
                String token = tokenObj.getString(ACCESS_TOKEN);
                if (!StringUtils.isEmpty(token)) {
                    return token;
                }
            }
            logger.info("retry:" + retryTimes);
        }
        return null;
    }
}
