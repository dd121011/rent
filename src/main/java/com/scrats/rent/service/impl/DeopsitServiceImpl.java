package com.scrats.rent.service.impl;

import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.entity.Deposit;
import com.scrats.rent.mapper.DepositMapper;
import com.scrats.rent.service.DepositService;
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
public class DeopsitServiceImpl extends BaseServiceImpl<Deposit, DepositMapper> implements DepositService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Deposit> getDepositByRoomId(Integer roomId, boolean deleteFlag) {
        return dao.getDepositByRoomId(roomId, deleteFlag);
    }

    @Override
    public List<Deposit> getUnpayDeposit(Integer roomId) {
        return dao.getUnpayDeposit(roomId);
    }

    @Override
    public List<Deposit> payedWithRange(Long fromTs, Long toTs, Deposit deposit) {
        return dao.payedWithRange(fromTs, toTs, deposit);
    }

}
