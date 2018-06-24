package com.scrats.rent.service.impl;

import com.github.pagehelper.PageHelper;
import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.constant.GlobalConst;
import com.scrats.rent.constant.UserType;
import com.scrats.rent.entity.*;
import com.scrats.rent.mapper.RoomMapper;
import com.scrats.rent.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RoomServiceImpl extends BaseServiceImpl<Room, RoomMapper> implements RoomService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private BarginService barginService;
    @Autowired
    private RenterService renterService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private DictionaryItermService dictionaryItermService;

    @Override
    public PageInfo<Room> getRoomList(APIRequest apiRequest, Room room) {
        return getRoomList(apiRequest, room, true);
    }

    @Override
    public PageInfo<Room> getRoomList(APIRequest apiRequest, Room room, boolean pageFlag) {
        List<Room> list;
        if(null == room){
            room = new Room();
        }
        if(pageFlag){
            PageHelper.startPage(apiRequest.getPage(), apiRequest.getRows());
            list = dao.getRoomList(room);
            return new PageInfo<Room>(list);
        }

        list = dao.getRoomList(room);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public int deleteRoomByIds(Integer... ids) {
        long ts = System.currentTimeMillis();
        return dao.deleteRoomByIds(ts, ids);
    }

    @Override
    public boolean rent(Bargin bargin, List<DictionaryIterm> extras, List<DepositIterm> depositIterms) {
        Long createTs = System.currentTimeMillis();

        //填充buildingId
        Building building = buildingService.getBuildingByRoomId(bargin.getRoomId());
        bargin.setBuildingId(building.getBuildingId());

        //TODO 填充renterId
        Renter renter = renterService.findBy("idCard", bargin.getIdCard());
        if(null == renter){
            Account account = new Account();
            account.setPhone(bargin.getPhone());
            account.setPwd(bargin.getPhone());
            account.setUsername(bargin.getPhone());
            account.setCreateTs(createTs);
            accountService.insertSelective(account);

            User user = new User(UserType.renter.getValue());
            user.setAccountId(account.getAccountId());
            user.setName(bargin.getName());
            user.setSex(bargin.getSex());
            user.setCreateTs(createTs);
            userService.insertSelective(user);

            Renter newRenter = new Renter(bargin.getIdCard());
            newRenter.setCreateTs(createTs);
            newRenter.setUserId(user.getUserId());
            renterService.insertSelective(newRenter);

            //补齐renterId字段
            bargin.setRenterId(user.getUserId());


        }else{
            bargin.setRenterId(renter.getRenterId());
        }

        //TODO 保存合同额外收费项，便于以后计算每月房租，另外还要获取水、电、三相电、天然气的初始读数
        for (DictionaryIterm extra: extras) {
            
        }
        //TODO 保存押金项和生成押金，填充bargin的guarantyFee字段和total字段
        bargin.setGuarantyFee(20000);
        bargin.setTotal(50000);

        bargin.setCreateTs(createTs);
        barginService.insertSelective(bargin);

        //更新房间状态
        Room room = dao.selectByPrimaryKey(bargin.getRoomId());
        room.setRentTs(createTs);
        return false;
    }

    @Override
    public Room detail(Integer roomId) {
        //获取所有房子select数据
        Room room = dao.selectByPrimaryKey(roomId);
        if(null == room){
            return room;
        }
        //获取房子
        Building building = buildingService.selectByPrimaryKey(room.getBuildingId());
        //获取房间朝向name
        DictionaryIterm orientation = dictionaryItermService.selectByPrimaryKey(room.getOrientation());
        //获取装修情况name
        DictionaryIterm decoration = dictionaryItermService.selectByPrimaryKey(room.getDecoration());
        //获取所有配套设施
        List<DictionaryIterm> facilities = dictionaryItermService.selectByIds(room.getFacilities());
        //获取所有额外收费项
        List<DictionaryIterm> extras = dictionaryItermService.findListBy("dicCode", GlobalConst.EXTRA_CODE);

        room.setBuilding(building);
        room.setOrientationName(orientation.getValue());
        room.setDecorationName(decoration.getValue());
        room.setFacilitiesIterm(facilities);
        room.setExtraFeeIterm(extras);

        return room;
    }
}
