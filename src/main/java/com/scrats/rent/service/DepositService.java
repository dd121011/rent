package com.scrats.rent.service;

import com.scrats.rent.base.service.BaseService;
import com.scrats.rent.entity.Deposit;
import com.scrats.rent.mapper.DepositMapper;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:33.
 */
public interface DepositService extends BaseService<Deposit, DepositMapper> {

    List<Deposit> getDepositByRoomId(Integer roomId, boolean deleteFlag);

    List<Deposit> getUnpayDeposit(Integer roomId);

}
