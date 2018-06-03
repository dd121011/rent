package com.scrats.rent.base.service;

import java.util.List;

/**
 * Created with scrat.
 * Description: BaseService.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/31 10:31.
 */
public interface BaseService<T, D> {

    /**
     * @description: 根据实体中的属性值进行查询，查询条件使用等号
     * @author: lol
     * @date: 2018/6/3 22:17
     * @param: var1
     * @return: List<T>
     */
    List<T> select(T var1);

    /**
     * @description: 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     * @author: lol
     * @date: 2018/6/3 22:20
     * @param: var1
     * @return: T
     */
    T selectByPrimaryKey(Object var1);

    /**
     * @description: 查询全部结果，select(null)方法能达到同样的效果
     * @author: lol
     * @date: 2018/6/3 22:20
     * @param: null
     * @return: List<T>
     */
    List<T> selectAll();

    /**
     * @description: 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     * @author: lol
     * @date: 2018/6/3 22:21
     * @param: var1
     * @return: T
     */
    T selectOne(T var1);

    /**
     * @description: 根据实体中的属性查询总数，查询条件使用等号
     * @author: lol
     * @date: 2018/6/3 22:21
     * @param: var1
     * @return: int
     */
    int selectCount(T var1);

    /**
     * @description: 根据主键字符串(“1,2,3”)进行查询，类中只有存在一个带有@Id注解的字段
     * @author: lol
     * @date: 2018/6/3 22:45
     * @param: ids
     * @return: int
     */
    List<T> selectByIds(String ids);

    /**
     * @description: 保存一个实体，null的属性也会保存，不会使用数据库默认值
     * @author: lol
     * @date: 2018/6/3 22:21
     * @param: var1
     * @return: int
     */
    int insert(T var1);

    /**
     * @description: 保存一个实体，null的属性不会保存，会使用数据库默认值
     * @author: lol
     * @date: 2018/6/3 22:22
     * @param: var1
     * @return: int
     */
    int insertSelective(T var1);

    /**
     * @description: 批量插入,该接口限制实体包含id属性并且必须为自增列
     * @author: lol
     * @date: 2018/6/3 22:22
     * @param: var1
     * @return: int
     */
    int insertList(List<T> var1);

    /**
     * @description: 根据主键更新实体全部字段，null值会被更新
     * @author: lol
     * @date: 2018/6/3 22:22
     * @param: var1
     * @return: int
     */
    int updateByPrimaryKey(T var1);

    /**
     * @description: 根据主键更新属性不为null的值
     * @author: lol
     * @date: 2018/6/3 22:23
     * @param: var1
     * @return: int
     */
    int updateByPrimaryKeySelective(T var1);

    /**
     * @description: 根据实体属性作为条件进行删除，查询条件使用等号
     * @author: lol
     * @date: 2018/6/3 22:23
     * @param: var1
     * @return: int
     */
    int delete(T var1);

    /**
     * @description: 根据主键字段进行删除，方法参数必须包含完整的主键属性
     * @author: lol
     * @date: 2018/6/3 22:23
     * @param: var1
     * @return: int
     */
    int deleteByPrimaryKey(Object var1);

    /**
     * @description: 根据主键字符串(“1,2,3”)进行删除，类中只有存在一个带有@Id注解的字段
     * @author: lol
     * @date: 2018/6/3 22:45
     * @param: ids
     * @return: int
     */
    int deleteByIds(String ids);
}
