package com.scrats.rent.base.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/22 17:10.
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
