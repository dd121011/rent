package com.scrats.rent.api;

import com.alibaba.fastjson.JSON;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.PageInfo;
import com.scrats.rent.entity.Building;
import com.scrats.rent.entity.User;
import com.scrats.rent.service.BuildingService;
import com.scrats.rent.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/7 23:30.
 */
@RestController
@RequestMapping("/api/building")
public class BuildingApi {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private UserService userService;


    @GetMapping("/list")
    public String list(int page, int rows, int userId, HttpServletRequest request) {

        if(rows < 1){
            rows = 10;
        }

        User user = userService.selectByPrimaryKey(userId);
        if(null == user){
            return JSON.toJSONString(new JsonResult("userId不正确"));
        }
        PageInfo<Building> pageInfo = buildingService.getBuildingListByUserId(page, rows, userId);

        return JSON.toJSONString(new JsonResult(pageInfo.getList(), (int) pageInfo.getTotal()));
    }
}
