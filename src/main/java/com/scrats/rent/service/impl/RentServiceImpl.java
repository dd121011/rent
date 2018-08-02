package com.scrats.rent.service.impl;

import com.github.pagehelper.PageHelper;
import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.entity.Rent;
import com.scrats.rent.mapper.RentMapper;
import com.scrats.rent.service.RentService;
import com.scrats.rent.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
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
public class RentServiceImpl extends BaseServiceImpl<Rent, RentMapper> implements RentService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Rent> getRentByRoomId(Integer roomId, boolean payFlag) {
        return dao.getRentByRoomId(roomId, payFlag);
    }

    @Override
    public List<Rent> getListByRent(Rent rent) {
        return dao.getListByRent(rent);
    }

    @Override
    public List<Rent> getRentByBuildingIdandPayFlag(Integer buildingId, boolean payFlag) {
        return dao.getRentByBuildingIdandPayFlag(buildingId, payFlag);
    }

    @Override
    public PageInfo<Rent> getRentPageList(APIRequest apiRequest, Rent rent) {
        return this.getRentPageList(apiRequest, rent, true);
    }

    @Override
    public PageInfo<Rent> getRentPageList(APIRequest apiRequest, Rent rent, boolean pageFlag) {
        List<Rent> list;
        if(pageFlag){
            PageHelper.startPage(apiRequest.getPage(), apiRequest.getRows());
            list = dao.getListByRent(rent);
            return new PageInfo<Rent>(list);
        }

        list = dao.getListByRent(rent);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public boolean pay(Integer rentId, String channel) {
        Long payTs = System.currentTimeMillis();
        Rent rent = new Rent();
        rent.setRentId(rentId);
        rent.setPayNo("haozu-payno-" + RandomUtil.generateLowerString(6) + "-" + payTs);
        rent.setPayTs(payTs);
        if(StringUtils.isNotEmpty(channel)){
            rent.setChannel(channel);
        }
        int res = dao.updateByPrimaryKeySelective(rent);
        return res > 0 ? true : false;
    }
}
