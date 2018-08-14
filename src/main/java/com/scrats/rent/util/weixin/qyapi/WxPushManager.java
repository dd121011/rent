package com.scrats.rent.util.weixin.qyapi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scrats.rent.util.HttpRequestUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by scrat on 2017/11/29.
 */
@Component
public class WxPushManager {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Value("${wx.qy.agent.id}")
    private String AGEN_ID;
    @Value("${wx.qy.corp.id}")
    private String CROP_ID;
    @Value("${wx.qy.corp.secret}")
    private String CROP_SECRET;

    private static final String PUSH_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=%s";
    private static final String USERINFO_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=%s&code=%s";

    private static final String TEXTCARD = "textcard";
    private static final String TOUSER = "touser";
    private static final String MSGTYPE = "msgtype";
    private static final String AGENTID = "agentid";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String BTNTXT = "btntxt";
    private static final String URL = "url";
    private static final String ERRCODE = "errcode";
    private static final String PICURL = "picurl";
    private static final String ARTICLES = "articles";
    private static final String NEWS = "news";

    public JSONObject getUserInfo(String code){
        String token = WxAccessTokenManager.getInstance(CROP_ID, CROP_SECRET).getAccessToken();

        String pushUrl = String.format(USERINFO_URL, token, code);
        logger.info("========pushUrl========" + pushUrl);
        logger.info(pushUrl);
        JSONObject userinfo = HttpRequestUtil.httpGet2Json(pushUrl, null);

        return userinfo;
    }

    public boolean pushArticles(String url, String picUrl, String title, String description, String... user) {
        String json = initArticlesJson(url, picUrl, title, description, user);
        return push(json);
    }

    public JSONObject pushArticlesJson(String url, String picUrl, String title, String description, String... user) {
        String json = initArticlesJson(url, picUrl, title, description, user);
        return pushJson(json);
    }

    public boolean pushCard(String url, String title, String description, String... user) {
        String json = initCardJson(url, title, description, user);
        return push(json);
    }

    public JSONObject  pushCardJson(String url, String title, String description, String... user) {
        String json = initCardJson(url, title, description, user);
        return pushJson(json);
    }

    private boolean push(String json) {
        String token = WxAccessTokenManager.getInstance(CROP_ID, CROP_SECRET).getAccessToken();

        String pushUrl = String.format(PUSH_URL, token);
        JSONObject pushObj = HttpRequestUtil.httpPost2Json(pushUrl, json, null);
        if (pushObj == null) {
            return false;
        }

        logger.info(pushObj.toString());
        return pushObj.getIntValue("ERRCODE") - 1 == 0;
    }

    private JSONObject pushJson(String json) {
        String token = WxAccessTokenManager.getInstance(CROP_ID, CROP_SECRET).getAccessToken();

        String pushUrl = String.format(PUSH_URL, token);
        return HttpRequestUtil.httpPost2Json(pushUrl, json, null);
    }

    private String getToUserStr(String... toUser) {
        StringBuilder sb = new StringBuilder();
        for (String user : toUser) {
            sb.append(user).append("|");
        }
        if (sb.length() > 0) {
            sb = sb.deleteCharAt(sb.lastIndexOf("|"));
        }
        return sb.toString();
    }

    private String initCardJson(String url, String title, String description, String... user) {
        JSONObject obj = new JSONObject();
        obj.put(TOUSER, getToUserStr(user));
        obj.put(MSGTYPE, TEXTCARD);
        obj.put(AGENTID, AGEN_ID);
        JSONObject cardObj = new JSONObject();
        cardObj.put(TITLE, title);
        cardObj.put(DESCRIPTION, description);
        cardObj.put(URL, url);
        cardObj.put(BTNTXT, "更多");
        obj.put(TEXTCARD, cardObj);
        return obj.toString();
    }

    private String initArticlesJson(String url, String picUrl, String title, String description, String... user) {
        JSONObject obj = new JSONObject();
        obj.put(TOUSER, getToUserStr(user));
        obj.put(MSGTYPE, NEWS);
        obj.put(AGENTID, AGEN_ID);
        JSONObject newsObj = new JSONObject();
        JSONArray arr = new JSONArray();
        JSONObject articleObj = new JSONObject();
        articleObj.put(TITLE, title);
        articleObj.put(DESCRIPTION, description);
        articleObj.put(URL, url);
        articleObj.put(PICURL, picUrl);
        articleObj.put(BTNTXT, "更多");
        arr.add(articleObj);
        newsObj.put(ARTICLES, arr);
        obj.put(NEWS, newsObj);
        return obj.toString();
    }

}
