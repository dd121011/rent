package com.scrats.rent.service.impl;

import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.entity.Bargin;
import com.scrats.rent.entity.BarginExtra;
import com.scrats.rent.mapper.BarginExtraMapper;
import com.scrats.rent.service.BarginExtraService;
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
public class BarginExtraServiceImpl extends BaseServiceImpl<BarginExtra, BarginExtraMapper> implements BarginExtraService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<BarginExtra> getBarginExtraTypeByBargin(Bargin bargin) {
        return dao.getBarginExtraTypeByBargin(bargin);
    }
}
