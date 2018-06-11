package com.scrats.rent.service.impl;

import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.entity.Dictionary;
import com.scrats.rent.mapper.DictionaryMapper;
import com.scrats.rent.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:34.
 */
@Service
public class DictionaryServiceImpl extends BaseServiceImpl<Dictionary, DictionaryMapper> implements DictionaryService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

}
