package com.scrats.rent.util;

import java.util.Map;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/11 12:28.
 */
public class BaseUtil {

    public static <T> T getFromMap(Map map, Object key, Class<T> clazz){
        if(null == map){
            return null;
        }
        Object obj = map.get(key);
        if(null != obj && clazz.isAssignableFrom(obj.getClass())){
            return (T)obj;
        }
        return null;
    }
}
