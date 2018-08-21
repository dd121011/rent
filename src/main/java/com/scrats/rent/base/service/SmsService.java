package com.scrats.rent.base.service;

import com.alibaba.fastjson.JSONObject;
import com.scrats.rent.util.HttpRequestUtil;
import com.scrats.rent.util.MD5Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/8/13 22:49.
 */
@Service
public class SmsService {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Value("${sms.app.id}")
    private String smsAppId;

    @Value("${sms.app.key}")
    private String smsAppKey;

    @Value("${sms.app.secret}")
    private String smsAppSecret;

    @Value("${sms.send}")
    private String smsSendUrl;

    @Value("${sms.check}")
    private String smsCheckUrl;

    public boolean send(final String tel) {
        long ts = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append(smsAppSecret).append(ts).append(tel);
        String sign = MD5Util.md5(sb.toString());
        String uri = String.format(smsSendUrl, tel, ts, sign.toUpperCase());
        logger.info("========smsSendUrl========" + uri);
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("appid",smsAppId);
        headerMap.put("appkey",smsAppKey);
        JSONObject infoObj = HttpRequestUtil.httpGet2Json(uri, headerMap);
        if (infoObj != null) {
            logger.info(infoObj.toString());
            if("200".equals(infoObj.getString("code"))){
                return true;
            }
        }
        return false;
    }

    public boolean checkCode(final String tel, final String code) {
        JSONObject params = new JSONObject();
        params.put("tel", tel);
        params.put("code", code);
        logger.info("========smsSendUrl========" + smsSendUrl);
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("appid",smsAppId);
        headerMap.put("appkey",smsAppKey);
        JSONObject infoObj = HttpRequestUtil.httpPost2Json(smsSendUrl, params.toJSONString(), headerMap);
        if (infoObj != null) {
            logger.info(infoObj.toString());
            if("200".equals(infoObj.getString("code"))){
                return true;
            }
        }
        return false;
    }
}
