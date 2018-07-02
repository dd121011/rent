package com.scrats.rent.service.impl;

import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.entity.ExtraHistory;
import com.scrats.rent.mapper.ExtraHistoryMapper;
import com.scrats.rent.service.ExtraHistoryService;
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
public class ExtraHistoryServiceImpl extends BaseServiceImpl<ExtraHistory, ExtraHistoryMapper> implements ExtraHistoryService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<ExtraHistory> getListByExtraHistory(ExtraHistory extraHistory) {
        return dao.getListByExtraHistory(extraHistory);
    }
}
