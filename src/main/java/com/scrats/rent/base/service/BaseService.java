package com.scrats.rent.base.service;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/31 10:31.
 */
public interface BaseService<T, D> {

    List<T> selectAll();

    List<T> select(T var1);

    T selectOne(T var1);

    //根据主键查询
    T selectByPrimaryKey(Object var1);

    int delete(T var1);

    int insert(T var1);

    int insertList(List<T> var1);

}
