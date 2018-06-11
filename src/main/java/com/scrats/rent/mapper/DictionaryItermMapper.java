package com.scrats.rent.mapper;

import com.scrats.rent.base.mapper.BaseMapper;
import com.scrats.rent.entity.DictionaryIterm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:32.
 */
@Mapper
public interface DictionaryItermMapper extends BaseMapper<DictionaryIterm> {

    @Select("select dic_iterm_id, dic_id, value, remark, create_ts, update_ts, delete_ts from dictionary_iterm where 1=1 and dic_id = #{dicId}")
    List<DictionaryIterm> getDicItermByDicId(Integer dicId);
}
