package com.scrats.rent.service.impl;

import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.entity.DictionaryIterm;
import com.scrats.rent.mapper.DictionaryItermMapper;
import com.scrats.rent.service.DictionaryItermService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:34.
 */
@Service
public class DictionaryItermServiceImpl extends BaseServiceImpl<DictionaryIterm, DictionaryItermMapper> implements DictionaryItermService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<DictionaryIterm> getDicItermByDicId(Integer dicId) {
        return dao.getDicItermByDicId(dicId);
    }
}
