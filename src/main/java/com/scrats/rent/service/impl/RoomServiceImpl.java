package com.scrats.rent.service.impl;

import com.github.pagehelper.PageHelper;
import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.common.APIRequest;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.common.exception.BusinessException;
import com.scrats.rent.constant.SexType;
import com.scrats.rent.constant.UserType;
import com.scrats.rent.entity.*;
import com.scrats.rent.mapper.RoomMapper;
import com.scrats.rent.service.*;
import com.scrats.rent.util.DateUtils;
import com.scrats.rent.util.IdCardUtil;
import com.scrats.rent.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private ExtraHistoryService extraHistoryService;
    @Autowired
    private RentItermService rentItermService;
    @Autowired
    private UserRoleService userRoleService;

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
        pageInfo.setTotal(list.size());
        return pageInfo;
    }

    @Override
    public int deleteRoomByIds(Integer... ids) {
        long ts = System.currentTimeMillis();
        return dao.deleteRoomByIds(ts, ids);
    }

    @Override
    @Transactional//添加事物一致性注解
    public JsonResult rent(Bargin bargin) {
        Long createTs = System.currentTimeMillis();

        //填充buildingId
        Building building = buildingService.getBuildingByRoomId(bargin.getRoomId());
        bargin.setBuildingId(building.getBuildingId());

        //填充renterId
        Account account = accountService.findBy("phone", bargin.getPhone());
        User user = null;
        if(null == account){
            //创建account
            account = new Account(bargin.getPhone(), bargin.getPhone(), bargin.getPhone());
            account.setCreateTs(createTs);
            accountService.insertSelective(account);
            //创建user
            user = new User(bargin.getName(),SexType.secret, bargin.getPhone(), bargin.getIdCard());
            user.setAccountId(account.getAccountId());
            user.setName(bargin.getName());
            user.setSex(bargin.getSex());
            user.setCreateTs(createTs);
            userService.insertSelective(user);
            //创建userRole
            UserRole userRole = new UserRole(UserType.renter, user.getUserId());
            userRole.setCreateTs(createTs);
            userRoleService.insertSelective(userRole);
        }else{
            user = userService.findBy("phone", bargin.getPhone());
            if(null == user){
                throw new BusinessException("请求数据不正确!!!");
            }
            if(!user.getName().equals(bargin.getName())){
                throw new BusinessException("该租户对应手机号在系统中的姓名和输入不一致, 请修改!!!");
            }
            if(!user.getIdCard().equals(bargin.getIdCard())){
                throw new BusinessException("该租户对应手机号在系统中的身份证号和输入不一致, 请修改!!!");
            }
            if(userRoleService.noExistRoleWithUserId(UserType.renter, user.getUserId())){
                UserRole userRole = new UserRole(UserType.renter, user.getUserId());
                userRole.setCreateTs(createTs);
                userRoleService.insertSelective(userRole);
            }
        }
        //补齐renterId字段
        bargin.setUserId(user.getUserId());
        bargin.setBarginNo("haozu-bargin-" + RandomUtil.generateLowerString(5) + "-" + createTs);
        bargin.setCreateTs(createTs);
        barginService.insertSelective(bargin);

        //创建roomRenter
        RoomRenter roomRenter = new RoomRenter(bargin.getRoomId(), bargin.getUserId(), bargin.getBarginId());
        roomRenter.setCreateTs(createTs);
        roomRenter.setCheckTs(createTs);
        roomRenterService.insertSelective(roomRenter);

        //生成租金的extra
        BarginExtra barginExtraRoom = new BarginExtra(bargin.getBarginId(), bargin.getRoomId(), bargin.getBuildingId(), "0000", "租金", "月", bargin.getRentFee(), -1);
        barginExtraRoom.setCreateTs(createTs);
        barginExtraService.insertSelective(barginExtraRoom);


        //保存合同额外收费项，便于以后计算每月房租
        for (BarginExtra extra: bargin.getBarginExtraList()) {
            extra.setRoomId(bargin.getRoomId());
            extra.setBuildingId(bargin.getBuildingId());
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
        deposit.setUserId(bargin.getUserId());
        deposit.setCreateTs(createTs);
        deposit.setBarginId(bargin.getBarginId());
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

        building.setRoomAble(building.getRoomAble() - 1);
        buildingService.updateByPrimaryKeySelective(building);
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
        List<DictionaryIterm> facilities = new ArrayList<DictionaryIterm>();
        if(StringUtils.isNotEmpty(room.getFacilities())){
            facilities = dictionaryItermService.selectByIds(room.getFacilities());
        }
        //获取所有额外收费项
        List<DictionaryIterm> extras = new ArrayList<DictionaryIterm>();
        if(StringUtils.isNotEmpty(room.getExtraFee())){
            extras = dictionaryItermService.selectByIds(room.getExtraFee());
        }
        //获取所有押金项
        List<DictionaryIterm> deposits = new ArrayList<DictionaryIterm>();
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
    public List<Room> getRoomByRoomNoAndBuildingId(String roomNo, Integer buildingId) {
        return dao.getRoomByRoomNoAndBuildingId(roomNo, buildingId);
    }

    @Override
    @Transactional
    public JsonResult charge(List<ExtraHistory> chargeList, String month, Integer barginId, Integer roomId, String remark) {
        Long createTs = System.currentTimeMillis();
        Bargin bargin = barginService.selectByPrimaryKey(barginId);
        List<BarginExtra> list = barginExtraService.findListBy("barginId",barginId);
        Map<Integer, ExtraHistory> extraHistoryMap = new HashMap<>();
        for(ExtraHistory extraHistory : chargeList){
            extraHistoryMap.put(extraHistory.getBarginExtraId(),extraHistory);
        }

        Rent rent = new Rent();
        List<RentIterm> rentItermList = new ArrayList<>();
        rent.setRentMonth(month);
        rent.setRoomId(roomId);
        rent.setBuildingId(bargin.getBuildingId());
        rent.setBarginId(barginId);
        rent.setRentNo("haozu-rent-" + month + RandomUtil.generateLowerString(1) + "-" + createTs);
        rent.setRemark(remark);

        int fee = 0;
        List<ExtraHistory> extraHistories = new ArrayList<>();
        for(BarginExtra barginExtra : list){
            RentIterm rentIterm = new RentIterm();
            rentIterm.setBarginExtraId(barginExtra.getBarginExtraId());
            rentIterm.setValue(barginExtra.getValue());
            rentIterm.setUnit(barginExtra.getUnit());
            rentIterm.setCreateTs(createTs);
            ExtraHistory origin = extraHistoryMap.get(barginExtra.getBarginExtraId());
            if(barginExtra.getNumber() > -1){
                //找上一个月的读数
                int beforeCount = 0;
                ExtraHistory query = new ExtraHistory();
                query.setBarginExtraId(barginExtra.getBarginExtraId());
                query.setMonth(DateUtils.beforeMonth(month));
                List<ExtraHistory> before = extraHistoryService.select(query);
                //没有记录
                if(null == before || before.size() < 1){
                    beforeCount = barginExtra.getNumber();
                }else{
                    beforeCount = before.get(0).getCount();
                }
                ExtraHistory extra = new ExtraHistory(roomId, origin.getCount(),month, origin.getDicItermCode(), origin.getBarginExtraId(), barginId, bargin.getBuildingId());
                extra.setCreateTs(createTs);
                int nowCount = origin.getCount() - beforeCount;
                if(nowCount < 0){
                    return new JsonResult(rentIterm.getValue() + "数据录入有误, 请核查, 上月数据为" + beforeCount);
                }
                rentIterm.setDescription(beforeCount + "---" + origin.getCount());
                rentIterm.setPrice(barginExtra.getPrice());
                rentIterm.setNumber(nowCount);
                rentIterm.setMoney(rentIterm.getPrice() * rentIterm.getNumber());
                extraHistories.add(extra);
            }else{
                ExtraHistory extra = new ExtraHistory(roomId, origin.getCount()*100, month, origin.getDicItermCode(), origin.getBarginExtraId(), barginId, bargin.getBuildingId());
                extra.setCreateTs(createTs);
                extraHistories.add(extra);
                rentIterm.setPrice(origin.getCount()*100);
                rentIterm.setNumber(1);
                rentIterm.setMoney(rentIterm.getPrice());
            }
            fee += rentIterm.getMoney();
            rentItermList.add(rentIterm);
        }

        rent.setFee(fee);
        rent.setCount(0);
        rent.setRealFee(fee);
        rent.setCreateTs(createTs);
        rentService.insertSelective(rent);

        for(RentIterm iterm : rentItermList){
            iterm.setRentId(rent.getRentId());
            rentItermService.insertSelective(iterm);
        }

        for(ExtraHistory ext : extraHistories){
            ext.setRentId(rent.getRentId());
            extraHistoryService.insertSelective(ext);
        }
        return new JsonResult();
    }

    @Transactional
    @Override
    public JsonResult addMulity(Room room) {
        List<Room> rlist = dao.getRoomByRoomNoAndBuildingId(null,room.getBuildingId());
        for(Room r : rlist){
            if(room.getRoomNoMulity().contains(r.getRoomNo())){
                return new JsonResult<>("创建失败,房间号" + r.getRoomNo() + "已存在");
            }
        }
        room.setCreateTs(System.currentTimeMillis());
        Building building = buildingService.selectByPrimaryKey(room.getBuildingId());
        building.setRooms(building.getRooms() + room.getRoomNoMulity().size());
        building.setRoomAble(building.getRoomAble() + room.getRoomNoMulity().size());
        buildingService.updateByPrimaryKeySelective(building);
        for(String no : room.getRoomNoMulity()){
            Room r = new Room();
            BeanUtils.copyProperties(room, r);
            r.setRoomNo(no);
            dao.insertSelective(r);
        }
        return new JsonResult();
    }

    @Transactional
    @Override
    public JsonResult renterAdd(Integer roomId, User newUser) throws ParseException {
        long createTs = System.currentTimeMillis();
        if(null == accountService.findBy("phone", newUser.getPhone())){
            if(null != userService.findBy("idCard", newUser.getIdCard())){
                return new JsonResult("身份证号:" + newUser.getIdCard() + "在系统中已被注册");
            }

            String idValid = IdCardUtil.IDCardValidate(newUser.getIdCard());
            if(!IdCardUtil.VALIDITY.equals(idValid)){
                return new JsonResult(idValid);
            }
            //创建account
            Account account = new Account(newUser.getPhone(), newUser.getPhone(), newUser.getPhone());
            account.setCreateTs(createTs);
            accountService.insertSelective(account);

            //创建user
            newUser.setAccountId(account.getAccountId());
            newUser.setCreateTs(createTs);
            userService.insertSelective(newUser);
            //创建userRole
            UserRole userRole = new UserRole(UserType.renter, newUser.getUserId());
            userRole.setCreateTs(createTs);
            userRoleService.insertSelective(userRole);
        }else{
            User user = userService.findBy("phone",newUser.getPhone());
            if(null == user){
                throw new BusinessException("请求数据不正确");
            }
            if(!newUser.getName().trim().equals(user.getName())){
                throw new BusinessException("填写的姓名与该手机号在系统中记录的有误");
            }
            if(!newUser.getIdCard().trim().equals(user.getIdCard())){
                throw new BusinessException("填写的身份证号与该手机号在系统中记录的有误");
            }
            RoomRenter param = new RoomRenter();
            param.setRoomId(roomId);
            param.setUserId(user.getUserId());
            List<RoomRenter> rrlist = roomRenterService.getListByRoomrenter(param);
            if(null != rrlist && rrlist.size() > 0){
                throw new BusinessException("请求的信息有误,该用户目前正在入住该房间");
            }
            newUser = user;
        }

        List<Bargin> list = barginService.getBarginByRoomId(roomId, false);
        RoomRenter newRoomRenter = new RoomRenter(roomId, newUser.getUserId(), list.get(0).getBarginId());
        newRoomRenter.setCreateTs(createTs);
        newRoomRenter.setCheckTs(createTs);
        roomRenterService.insertSelective(newRoomRenter);

        return new JsonResult<>();
    }

    @Override
    @Transactional
    public JsonResult rentLeave(Integer roomId) {
        //先判断是否有未缴费的房租和押金
        List<Rent> unpayRentList = rentService.getRentByRoomId(roomId, false);
        if(unpayRentList.size() > 0){
            return new JsonResult("还有未支付房租, 请先支付后退房!");
        }
        List<Deposit> unpayDepositList = depositService.getUnpayDeposit(roomId);
        if(unpayDepositList.size() > 0){
            return new JsonResult("还有未支付押金, 请先支付后退房!");
        }

        Long deleteTs = System.currentTimeMillis();
        //修改房间状态为未出租
        Room room = new Room();
        room.setRoomId(roomId);
        room.setRentTs(0L);
        dao.updateByPrimaryKeySelective(room);
        //退还押金、取消合同
        Bargin bargin = barginService.getRoomBargin(roomId);
        bargin.setDeleteTs(deleteTs);
        barginService.updateByPrimaryKeySelective(bargin);

        List<Deposit> depositList = depositService.getDepositByRoomId(roomId, false);
        depositList.get(0).setDeleteTs(deleteTs);
        depositService.updateByPrimaryKeySelective(depositList.get(0));

        RoomRenter roomRenter = new RoomRenter();
        roomRenter.setRoomId(roomId);
        List<RoomRenter> roomRenterList = roomRenterService.getListByRoomrenter(roomRenter);
        for(RoomRenter rr : roomRenterList){
            rr.setDeleteTs(deleteTs);
            roomRenterService.updateByPrimaryKeySelective(rr);
        }
        return new JsonResult();
    }
}
