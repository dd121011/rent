package com.scrats.rent.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scrats.rent.base.service.BaseServiceImpl;
import com.scrats.rent.base.service.RedisService;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.constant.GlobalConst;
import com.scrats.rent.entity.*;
import com.scrats.rent.mapper.RenterMapper;
import com.scrats.rent.service.*;
import com.scrats.rent.util.BaseUtil;
import com.scrats.rent.util.DateUtils;
import com.scrats.rent.util.weixin.sns.WxAuthorize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;



/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:34.
 */
@Service
public class RenterServiceImpl extends BaseServiceImpl<Renter, RenterMapper> implements RenterService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxAuthorize wxAuthorize;
    @Autowired
    private WxSnsService wxSnsService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomRenterService roomRenterService;

    @Override
    public JsonResult snsLogin(String code, String signature, String rawData) {

        WxSns checkSns = wxAuthorize.checkUserInfoFromWx(code);
        if(null == checkSns){
            return new JsonResult("获取签名失败");
        }
        logger.info("checkSns is: " + JSON.toJSONString(checkSns));
        //检查signature的正确性
        logger.info("request signature is: " + signature);
        String newSignature = BaseUtil.getSha1(rawData+checkSns.getSession_key());
        logger.info("check signature is: " + newSignature);
        if(!signature.equals(newSignature)){
            return new JsonResult("签名不正确");
        }
        //生成token,保存登录
        WxSns wxSns = wxSnsService.selectByPrimaryKey(checkSns.getOpenid());
        logger.info("wxSns login: " + JSON.toJSONString(wxSns));
        String tokenId = UUID.randomUUID().toString().replace("-","");
        JSONObject result = new JSONObject();
        result.put("tokenId", tokenId);
        if(null == wxSns){
            checkSns.setCreateTs(System.currentTimeMillis());
            wxSnsService.insertSelective(checkSns);
            checkSns.setUserId(-1);
            result.put("wxSns", checkSns);
            redisService.set(checkSns.getOpenid(),tokenId, GlobalConst.SNS_FIRST_ACCESS_TOKEN_EXPIRE);
            return new JsonResult<JSONObject>(result);
        }else{
            if(null!= wxSns.getUserId() && wxSns.getUserId() > 0){
                //已有userId，保存登录状态
                User user = userService.selectByPrimaryKey(wxSns.getUserId());
                redisService.set(tokenId,user, GlobalConst.ACCESS_TOKEN_EXPIRE);
            }else{
                wxSns.setUserId(-1);
                redisService.set(checkSns.getOpenid(),tokenId, GlobalConst.SNS_FIRST_ACCESS_TOKEN_EXPIRE);
            }
            result.put("wxSns", wxSns);
            return new JsonResult<JSONObject>(result);
        }
    }

    @Override
    public JSONArray getRoomList(Integer userId) {
        Date date = new Date();

        List<RoomRenter> rrlist = roomRenterService.findListBy("userId", userId);
        HashSet<Integer> roomIdSet = new HashSet<>();
        for(RoomRenter rr :  rrlist){
            roomIdSet.add(rr.getRoomId());
        }
        JSONArray result = new JSONArray();
        for(Integer id : roomIdSet){
            JSONObject jsonObject = new JSONObject();
            Room room = roomService.detailForRenter(id);
            Date rentDay = this.getPayTime(date,room.getBarginList().get(0).getRentDay());
            String payStatus = "pay";
            if(null != room.getRentList() && room.getRentList().size() > 0){
                payStatus = "unpay";
            }
            jsonObject.put("roomId", room.getRoomId());
            jsonObject.put("roomNo", room.getRoomNo());
            jsonObject.put("buildingName", room.getBuilding().getName());
            jsonObject.put("nextTime", rentDay.getTime()-date.getTime());
            jsonObject.put("payTime", rentDay.getTime());
            jsonObject.put("payStatus", payStatus);
            result.add(jsonObject);
        }
        return result;
    }

    private Date getPayTime(Date date, int rentDay){
        Date rent = DateUtils.oneDayOfThisMonth(date, rentDay);
        if(rent.getTime() - date.getTime() < 0){
            rent = DateUtils.oneDayOfNextMonth(date, rentDay);
        }
        return rent;
    }
}
