package com.scrats.rent.service.impl;

import com.github.pagehelper.PageHelper;
import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.entity.Building;
import com.scrats.rent.entity.Deposit;
import com.scrats.rent.entity.Rent;
import com.scrats.rent.mapper.BuildingMapper;
import com.scrats.rent.service.BuildingService;
import com.scrats.rent.service.DepositService;
import com.scrats.rent.service.RentService;
import com.scrats.rent.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:34.
 */
@Service
public class BuildingServiceImpl extends BaseServiceImpl<Building, BuildingMapper> implements BuildingService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RentService rentService;
    @Autowired
    private DepositService depositService;

    @Override
    public PageInfo<Building> getBuildingListWithUserId(APIRequest apiRequest, Building building, int userId) {
        return getBuildingListWithUserId(apiRequest, building, userId, true);
    }

    @Override
    public PageInfo<Building> getBuildingListWithUserId(APIRequest apiRequest, Building building, int userId, boolean pageFlag) {
        List<Building> list;
        if(pageFlag){
            PageHelper.startPage(apiRequest.getPage(), apiRequest.getRows());
            list = dao.getBuildingListWithUserId(building, userId);
            return new PageInfo<Building>(list);
        }

        list = dao.getBuildingListWithUserId(building, userId);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(list);
        pageInfo.setTotal(list.size());
        return pageInfo;
    }

    @Override
    public int deleteBuildingByIds(Integer... ids) {
        long ts = System.currentTimeMillis();
        return dao.deleteBuildingByIds(ts, ids);
    }

    @Override
    public Building getBuildingByRoomId(Integer roomId) {
        return dao.getBuildingByRoomId(roomId);
    }

    @Override
    public int incomeThisMonth(Integer buildingId) {
        //获取本月第一天
        Date date = new Date();
        System.out.println(date.getTime());
        Date firstDay = DateUtils.firstDayZeroOfThisMonth(date);
        long fromTs = firstDay.getTime();
        System.out.println(fromTs);
        Rent param = new Rent();
        param.setBuildingId(buildingId);
        param.setPayTs(1L);
        int income = 0;
        //租金收入
        List<Rent> rentList = rentService.payedWithRange(fromTs, null, param);
        for(Rent r : rentList){
            income += r.getRealFee();
        }
        Deposit dparam = new Deposit();
        dparam.setBuildingId(buildingId);
        dparam.setPayTs(1L);
        List<Deposit> depositList = depositService.payedWithRange(fromTs, null, dparam);
        for (Deposit d : depositList){
            income += d.getFee();
        }
        //押金收入
        return income;
    }

    @Override
    public int expiredMoeny(Integer buildingId) {
        int expire = 0;
        //租金逾期
        Rent param = new Rent();
        param.setBuildingId(buildingId);
        List<Rent> rentList = rentService.payedWithRange(null, null, param);
        for(Rent r : rentList){
            expire += r.getRealFee();
        }

        //押金逾期
        Deposit dparam = new Deposit();
        dparam.setBuildingId(buildingId);
        List<Deposit> depositList = depositService.payedWithRange(null, null, dparam);
        for (Deposit d : depositList){
            expire += d.getFee();
        }
        return expire;

    }
}
