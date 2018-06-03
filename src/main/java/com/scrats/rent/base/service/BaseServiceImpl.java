package com.scrats.rent.base.service;

import com.scrats.rent.base.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/31 10:35.
 */
public class BaseServiceImpl<T, D extends BaseMapper<T>> implements BaseService<T, D> {

    @Autowired
    protected D dao;

    @Override
    public List<T> select(T var1) {
        return dao.select(var1);
    }

    @Override
    public T selectByPrimaryKey(Object var1) {
        return dao.selectByPrimaryKey(var1);
    }

    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }

    @Override
    public T selectOne(T var1) {
        return dao.selectOne(var1);
    }

    @Override
    public int selectCount(T var1) {
        return dao.selectCount(var1);
    }

    @Override
    public List<T> selectByIds(String ids) {
        return dao.selectByIds(ids);
    }

    @Override
    public int insert(T var1) {
        return dao.insert(var1);
    }

    @Override
    public int insertSelective(T var1) {
        return dao.insertSelective(var1);
    }

    @Override
    public int insertList(List<T> var1) {
        return dao.insertList(var1);
    }

    @Override
    public int updateByPrimaryKey(T var1){
        return  dao.updateByPrimaryKey(var1);
    }

    @Override
    public int updateByPrimaryKeySelective(T var1){
        return  dao.updateByPrimaryKeySelective(var1);
    }

    @Override
    public int delete(T var1) {
        return dao.delete(var1);
    }

    @Override
    public int deleteByPrimaryKey(Object var1) {
        return dao.deleteByPrimaryKey(var1);
    }

    @Override
    public int deleteByIds(String ids) {
        return dao.deleteByIds(ids);
    }

}
