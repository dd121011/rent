package com.scrats.rent.service.impl;

import com.github.pagehelper.PageHelper;
import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.entity.*;
import com.scrats.rent.mapper.RentMapper;
import com.scrats.rent.service.*;
import com.scrats.rent.util.DateUtils;
import com.scrats.rent.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:34.
 */
@Slf4j
@Service
public class RentServiceImpl extends BaseServiceImpl<Rent, RentMapper> implements RentService {

    @Autowired
    private RoomService roomService;
    @Autowired
    private RentItermService rentItermService;
    @Autowired
    private BarginService barginService;
    @Autowired
    private ExtraHistoryService extraHistoryService;

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
        pageInfo.setTotal(list.size());
        return pageInfo;
    }

    @Override
    public boolean pay(User landlord, Integer rentId, String channel) {
        Long payTs = System.currentTimeMillis();
        Rent rent = new Rent();
        rent.setRentId(rentId);
        rent.setPayNo("haozu-payno-" + RandomUtil.generateLowerString(6) + "-" + payTs);
        rent.setPayTs(payTs);
        if(StringUtils.isNotEmpty(channel)){
            rent.setChannel(channel);
            //TODO 收款人
        }
        int res = dao.updateByPrimaryKeySelective(rent);
        return res > 0 ? true : false;
    }

    @Override
    public Rent detail(Integer rentId) {
        Rent rent = dao.selectByPrimaryKey(rentId);
        if(null != rent){
            Room room = roomService.detail(rent.getRoomId());
            Bargin bargin = barginService.selectByPrimaryKey(rent.getBarginId());
            List<RentIterm> list = rentItermService.findListBy("rentId", rentId);
            rent.setRoom(room);
            rent.setRentItermList(list);
            rent.setBargin(bargin);
        }
        return rent;
    }

    @Override
    @Transactional
    public JsonResult rentEdit(Rent rent, List<ExtraHistory> extraHistoryList) {
        Long updateTs = System.currentTimeMillis();
        List<RentIterm> rentItermList = new ArrayList<RentIterm>();
        int fee = 0;
        for(ExtraHistory extraHistory : extraHistoryList){
            RentIterm rentItermQuery = new RentIterm();
            rentItermQuery.setRentId(rent.getRentId());
            rentItermQuery.setBarginExtraId(extraHistory.getBarginExtraId());
            List<RentIterm> rentItermQueryList = rentItermService.select(rentItermQuery);
            RentIterm rentIterm = rentItermQueryList.get(0);
            if(null == rentIterm){
                return new JsonResult("数据有误!");
            }
            if(extraHistory.getNumber() != -1){
                //找上一个月的读数
                int beforeCount = 0;
                ExtraHistory query = new ExtraHistory();
                query.setBarginExtraId(extraHistory.getBarginExtraId());
                query.setMonth(DateUtils.beforeMonth(extraHistory.getMonth()));
                List<ExtraHistory> before = extraHistoryService.select(query);
                //没有记录
                if(null == before || before.size() < 1){
                    beforeCount = extraHistory.getNumber();
                }else{
                    beforeCount = before.get(0).getCount();
                }
                int nowCount = extraHistory.getCount() - beforeCount;
                if(nowCount < 0){
                    return new JsonResult(rentIterm.getValue() + "数据录入有误, 请核查, 上月数据为" + beforeCount);
                }
                extraHistory.setUpdateTs(updateTs);
                rentIterm.setDescription(beforeCount + "---" + extraHistory.getCount());
                rentIterm.setNumber(nowCount);
                rentIterm.setMoney(rentIterm.getPrice() * rentIterm.getNumber());
            }else{
                extraHistory.setUpdateTs(updateTs);
                rentIterm.setPrice(extraHistory.getCount());
                rentIterm.setNumber(1);
                rentIterm.setMoney(rentIterm.getPrice());
            }
            fee += rentIterm.getMoney();
            rentItermList.add(rentIterm);
        }

        rent.setFee(fee);
        rent.setCount(0);
        rent.setRealFee(fee);
        rent.setUpdateTs(updateTs);
        dao.updateByPrimaryKeySelective(rent);

        for(RentIterm iterm : rentItermList){
            rentItermService.updateByPrimaryKeySelective(iterm);
        }

        for(ExtraHistory ext : extraHistoryList){
            extraHistoryService.updateByPrimaryKeySelective(ext);
        }
        return new JsonResult();
    }

    @Override
    public List<Rent> payedWithRange(Long fromTs, Long toTs, Rent rent) {
        return dao.payedWithRange(fromTs, toTs, rent);
    }
}
