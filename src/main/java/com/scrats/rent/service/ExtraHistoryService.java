package com.scrats.rent.service;

import com.scrats.rent.base.service.BaseService;
import com.scrats.rent.entity.ExtraHistory;
import com.scrats.rent.mapper.ExtraHistoryMapper;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:33.
 */
public interface ExtraHistoryService extends BaseService<ExtraHistory, ExtraHistoryMapper> {

    List<ExtraHistory> getListByExtraHistory(ExtraHistory extraHistory);
}
