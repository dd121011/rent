package com.scrats.rent.service.impl;

import com.github.pagehelper.PageHelper;
import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.constant.UserType;
import com.scrats.rent.entity.*;
import com.scrats.rent.mapper.RoomMapper;
import com.scrats.rent.service.*;
import com.scrats.rent.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private RoomRenterService roomRenterService;
    @Autowired
    private RentService rentService;
    @Autowired
    private DepositService depositService;
    @Autowired
    private DepositItermService depositItermService;
    @Autowired
    private BarginExtraService barginExtraService;

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
    @Transactional//添加食物一致性注解
    public JsonResult rent(Bargin bargin) {
        Long createTs = System.currentTimeMillis();

        //填充buildingId
        Building building = buildingService.getBuildingByRoomId(bargin.getRoomId());
        bargin.setBuildingId(building.getBuildingId());

        //填充renterId
        Renter renter = renterService.findBy("idCard", bargin.getIdCard());
        if(null == renter){
            //创建account
            Account account = new Account();
            account.setPhone(bargin.getPhone());
            account.setPwd(bargin.getPhone());
            account.setUsername(bargin.getPhone());
            account.setCreateTs(createTs);
            accountService.insertSelective(account);
            //创建user
            User user = new User(UserType.renter.getValue());
            user.setAccountId(account.getAccountId());
            user.setName(bargin.getName());
            user.setSex(bargin.getSex());
            user.setCreateTs(createTs);
            userService.insertSelective(user);
            //创建renter
            Renter newRenter = new Renter(bargin.getIdCard());
            newRenter.setCreateTs(createTs);
            newRenter.setUserId(user.getUserId());
            renterService.insertSelective(newRenter);

            //补齐renterId字段
            bargin.setUserId(user.getUserId());
            bargin.setRenterId(newRenter.getRenterId());
        }else{
            bargin.setUserId(renter.getUserId());
            bargin.setRenterId(renter.getRenterId());
        }

        //创建roomRenter
        RoomRenter roomRenter = new RoomRenter(bargin.getRoomId(), bargin.getUserId(), bargin.getRenterId());
        roomRenter.setCreateTs(bargin.getLiveTs());
        roomRenterService.insertSelective(roomRenter);

        bargin.setBarginNo("haozu-bargin-" + RandomUtil.generateLowerString(5) + "-" + createTs);
        bargin.setCreateTs(createTs);
        barginService.insertSelective(bargin);

        //保存合同额外收费项，便于以后计算每月房租
        for (BarginExtra extra: bargin.getBarginExtraList()) {
            extra.setRoomId(bargin.getRoomId());
            extra.setBarginId(bargin.getBarginId());
            extra.setCreateTs(createTs);
            barginExtraService.insertSelective(extra);
        }

        //保存押金项和生成押金
        Deposit deposit = new Deposit();
        deposit.setRoomId(bargin.getRoomId());
        deposit.setBuildingId(building.getBuildingId());
        deposit.setDepositNo("haozu-deposit-" + RandomUtil.generateLowerString(4) + "-" + createTs);
        deposit.setFee(bargin.getGuarantyFee());
        deposit.setRenterId(bargin.getRenterId());
        deposit.setUserId(bargin.getUserId());
        deposit.setCreateTs(createTs);
        depositService.insertSelective(deposit);

        for (DepositIterm iterm: bargin.getDepositItermList()) {
            iterm.setRoomId(bargin.getRoomId());
            iterm.setDepositId(deposit.getDepositId());
            iterm.setCreateTs(createTs);
            depositItermService.insertSelective(iterm);
        }

        //更新房间状态
        Room room = dao.selectByPrimaryKey(bargin.getRoomId());
        room.setRentTs(createTs);
        dao.updateByPrimaryKeySelective(room);

        return new JsonResult();
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
        List<DictionaryIterm> facilities = null;
        if(StringUtils.isNotEmpty(room.getFacilities())){
            facilities = dictionaryItermService.selectByIds(room.getFacilities());
        }
        //获取所有额外收费项
        List<DictionaryIterm> extras = null;
        if(StringUtils.isNotEmpty(room.getExtraFee())){
            extras = dictionaryItermService.selectByIds(room.getExtraFee());
        }
        //获取所有押金项
        List<DictionaryIterm> deposits = null;
        if(StringUtils.isNotEmpty(room.getDeposits())){
            deposits = dictionaryItermService.selectByIds(room.getDeposits());
        }

        room.setBuilding(building);
        room.setOrientationName(orientation.getValue());
        room.setDecorationName(decoration.getValue());
        room.setFacilitiesIterm(facilities);
        room.setExtraFeeIterm(extras);
        room.setDepositIterm(deposits);

        return room;
    }

    @Override
    public Room detailForRenter(Integer roomId) {
        //获取所有房子select数据
        Room room = dao.selectByPrimaryKey(roomId);
        if(null == room){
            return room;
        }
        //获取房子
        Building building = buildingService.selectByPrimaryKey(room.getBuildingId());
        List<Bargin> barginList = barginService.getBarginByRoomId(room.getRoomId(), false);
        List<Rent> rentList = rentService.getRentByRoomId(room.getRoomId(), false);

        room.setBuilding(building);
        room.setBarginList(barginList);
        room.setRentList(rentList);

        return room;
    }

    @Override
    public List<Room> getRoomByRoomNoAndBuildingId(String roomNo, Integer buildingId) {
        return dao.getRoomByRoomNoAndBuildingId(roomNo, buildingId);
    }
}
