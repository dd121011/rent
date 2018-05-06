package com.scrats.rent.entity;

import lombok.Data;

/**
 * 房租实体类
 * Created by lol on 15/4/23.
 */
@Data
public class Rent extends Common {

    private static final long serialVersionUID = -7942452150917227172L;

    private int id;//主键
    private String rentNumber;//房租收据单号
    private float waterRate;//水费
    private float powerRate;//电费
    private float powerThreeRate;//三相电费
    private float healthRate;//卫生费
    private float internetRate;//网费
    private float parkingRate;//停车费
    private float propertyManagementRate;//物业管理费
    private float rent;//租金
    private float waterLast;//上月用水
    private float wateThis;//本月用水
    private float powerLast;//上月用电
    private float powerThis;//本月用电
    private float powerThreeLast;//上月用三相电
    private float powerThreeThis;//本月用三相电
    private boolean ischarge;//是否已收房租


    private int landlordId;//房东id
    private String landlordName;//房东用户名
    private int roomid;//房间id,一个房租对应一个roomId
    private String roomNumber;//房间编号

}
