package com.scrats.rent.service;

import com.scrats.rent.base.service.BaseService;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.entity.Rent;
import com.scrats.rent.mapper.RentMapper;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:33.
 */
public interface RentService extends BaseService<Rent, RentMapper> {

    List<Rent> getRentByRoomId(Integer roomId, boolean payFlag);

    List<Rent> getListByRent(Rent rent);

    List<Rent> getRentByBuildingIdandPayFlag(Integer buildingId, boolean payFlag);

    PageInfo<Rent> getRentPageList(APIRequest apiRequest, Rent rent);

    PageInfo<Rent> getRentPageList(APIRequest apiRequest, Rent rent, boolean pageFlag);

    boolean pay(Integer rentId, String channel);
}
