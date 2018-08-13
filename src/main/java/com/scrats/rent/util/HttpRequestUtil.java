package com.scrats.rent.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class HttpRequestUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);

    public static String httpGet(String url, Map<String, String> headerMap) {
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        if(null != headerMap){
            Header header = new Header();
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                header.setName(entry.getKey());
                header.setValue(entry.getValue());
            }
            getMethod.setRequestHeader(header);
        }
        try {
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            byte[] responseBody = getMethod.getResponseBody();
            return new String(responseBody);
        } catch (Exception e) {
            return null;
        } finally {
            getMethod.releaseConnection();
        }
    }

    public static JSONObject httpGet2Json(String url, Map headerMap) {
        String content = httpGet(url, headerMap);
        if (content == null) {
            return null;
        }

        try {
            return JSON.parseObject(content);
        } catch (Exception e) {
            return null;
        }
    }

    public static String httpPost(String url, String json) {
        HttpClient httpClient = new HttpClient();
        PostMethod method = new PostMethod(url);
        try {
            if(!StringUtils.isEmpty(json)) {
                RequestEntity requestEntity = new StringRequestEntity(json,"application/json","UTF-8");
                method.setRequestEntity(requestEntity);
            }
            httpClient.executeMethod(method);
            return method.getResponseBodyAsString();
        } catch (Exception e) {
            return null;
        } finally {
            method.releaseConnection();
        }
    }

    public static JSONObject httpPost2Json(String url, String json) {
        String res = httpPost(url, json);
        if (res == null) {
            return null;
        }

        try {
            return JSON.parseObject(res);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if(!StringUtils.isEmpty(ip) && !ip.equals("unknown")){
            logger.info("x-forwarded-for = " + ip);
            return ip;
        }

        ip = request.getHeader("X-Real-Ip");
        if(!StringUtils.isEmpty(ip) && !ip.equals("unknown")){
            logger.info("X-Real-Ip = " + ip);
            return ip;
        }

        ip = request.getHeader("Proxy-Client-IP");
        if(!StringUtils.isEmpty(ip) && !ip.equals("unknown")){
            logger.info("Proxy-Client-IP = " + ip);
            return ip;
        }

        ip = request.getHeader("WL-Proxy-Client-IP");
        if(!StringUtils.isEmpty(ip) && !ip.equals("unknown")){
            logger.info("WL-Proxy-Client-IP = " + ip);
            return ip;
        }

        ip = request.getHeader("RemoteAddr-IP");
        if(!StringUtils.isEmpty(ip) && !ip.equals("unknown")){
            logger.info("RemoteAddr-IP = " + ip);
            return ip;
        }

        if(!StringUtils.isEmpty(ip) && !ip.equals("unknown")) {
            ip = ip.split(",")[0];
        }
        return ip;

    }
}
