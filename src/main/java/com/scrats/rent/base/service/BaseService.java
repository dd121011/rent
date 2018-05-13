package com.scrats.rent.base.service;


import com.scrats.rent.base.RestResp;

import java.util.List;

public interface BaseService<T,PK> {
    T save(T pojo);
    T saveSelective(T pojo);
    void deleteByPrimaryKey(PK id);
    T getByPrimaryKey(PK id);
    void updateByPrimaryKeySelective(T pojo);
    RestResp<List<T>> listByPage(T pojo);

}
