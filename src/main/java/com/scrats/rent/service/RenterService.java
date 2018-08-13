package com.scrats.rent.service;

import com.alibaba.fastjson.JSONArray;
import com.scrats.rent.base.service.BaseService;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.entity.Renter;
import com.scrats.rent.mapper.RenterMapper;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:33.
 */
public interface RenterService extends BaseService<Renter, RenterMapper> {

    JsonResult snsLogin(String code, String signature, String rawData);

    JSONArray getRoomList(Integer userId);

    JsonResult snsRenterRegist(String tokenId, String openid, String name, String phone, String idCard);

}
