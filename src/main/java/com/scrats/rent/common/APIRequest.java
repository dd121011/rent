package com.scrats.rent.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.scrats.rent.common.exception.BusinessException;
import com.scrats.rent.entity.User;
import com.scrats.rent.util.BaseUtil;
import lombok.Data;

import java.util.Map;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/10 19:59.
 */
@Data
public class APIRequest<K> {

    private String tokenId;

    private Integer page = 1;

    private Integer rows = 10;

    private String searchText;//搜索内容

    private User user;

    private Map<String, Object> body;
    private K bodyObject;
    private Class bodyObjectClass;

    public Integer getRows() {
        return rows > 0 ? rows : 10;
    }

    public Integer getPage() {
        return page > 0 ? page : 1;
    }

    public K getBodyObject() {
        if (bodyObject == null && bodyObjectClass != null && body != null) {
            Object object = JSON.toJavaObject(new JSONObject(body), bodyObjectClass);
            bodyObject = (K) object;
        }
        return bodyObject;
    }

    public static <T> T getParameterValue(APIRequest request, String key, T defaultValue, Class<T> clazz) {
        T value = BaseUtil.getFromMap(request.body, key, clazz);
        if (value == null) {
            if (defaultValue == null) {
                throw new BusinessException("缺少参数:" + key + ", 类型:" + clazz.getName());
            }
            value = defaultValue;
        }

        return value;
    }

    public static <T> T getParameterValue(APIRequest request, String key, Class<T> clazz) {
        return getParameterValue(request, key, null, clazz);
    }



}
