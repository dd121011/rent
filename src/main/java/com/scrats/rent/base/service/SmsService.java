package com.scrats.rent.base.service;

import com.alibaba.fastjson.JSONObject;
import com.scrats.rent.util.HttpRequestUtil;
import com.scrats.rent.util.MD5Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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

    public String send(final String tel) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        long ts = System.currentTimeMillis();
        String sign = MD5Util.EncoderByMd5(smsAppSecret + ts + tel);
        smsSendUrl = String.format(smsSendUrl, tel, ts, sign);
        logger.info("========smsSendUrl========" + smsSendUrl);
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("appid",smsAppId);
        headerMap.put("appkey",smsAppKey);
        JSONObject infoObj = HttpRequestUtil.httpGet2Json(smsSendUrl, headerMap);
        if (infoObj != null) {
            logger.info(infoObj.toString());
            //wxSns = JSON.parseObject(infoObj.toJSONString(),WxSns.class);
        }
        return null;

    }
}
