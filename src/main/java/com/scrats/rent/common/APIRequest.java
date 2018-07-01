package com.scrats.rent.common;

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
public class APIRequest {

    private String tokenId;
    private int page = 1;
    private int rows = 10;
    private String searchText;//搜索内容
    private User user;
    private int lanlordId = 0;
    private int renterId = 0;
    private String openid = "";
    private int adminId = 0;
    private int guardId = 0;
    private boolean administratorFlag = false;
    private Integer roomId;
    private Integer buildingId;

    private Map<String, Object> body;

    public int getRows() {
        return rows > 0 ? rows : 10;
    }

    public int getPage() {
        return page > 0 ? page : 1;
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
