package com.scrats.rent.mapper;

import com.scrats.rent.base.mapper.BaseMapper;
import com.scrats.rent.entity.DictionaryIterm;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:32.
 */
public interface DictionaryItermMapper extends BaseMapper<DictionaryIterm> {

    @Select("select t.dic_iterm_id, t.dic_id, t.value, t.remark, t.create_ts, t.update_ts, t.delete_ts" +
            " from dictionary_iterm t" +
            " where 1=1 and dic_id = #{dicId}")
    List<DictionaryIterm> getDicItermByDicId(Integer dicId);

    @Select("select t.dic_iterm_id, t.dic_id, t.VALUE, t.remark\n" +
            "from dictionary_iterm t " +
            "left join dictionary d ON d.delete_ts = 0 and t.dic_id = d.dic_id \n" +
            "where 1 = 1 and d.CODE = #{dicCode} and t.delete_ts = 0")
    List<DictionaryIterm> getDicItermByDicCode(String dicCode);
}
