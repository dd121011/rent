package com.scrats.rent.common.jpush;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Created with jointstarc.
 * @Email: 262297088@qq.com
 * @Description:
 * @User: lol.
 * @Date: 2018/1/4 11:49.
 */
@Component
public class EafJPushManager {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Value("${edc.jpush.server}")
    private String jpushServer;

    @Value("${edc.jpush.key}")
    private String jpushKey;

    private static final String jpushSendUrl = "/api/notify/jg/send";

    public JSONObject sendPush(String pushStr) {
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(60000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(60000);
        String result = null;
        try {
            PostMethod post = new PostMethod(jpushServer + jpushSendUrl);
            post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
            post.addParameter("key", jpushKey);
            post.addParameter("pushStr", pushStr);
            logger.info(pushStr);
            httpClient.executeMethod(post);
            result = new String(post.getResponseBody(), "gbk");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StringToJson(result);
    }

    private JSONObject StringToJson(String json) {
        if (json == null) {
            return null;
        }
        try {
            if(json.equals("")){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("success",true);
                return jsonObject;
            }else{
                return JSONObject.parseObject(json);
            }

        } catch (Exception e) {
            return null;
        }
    }
}
