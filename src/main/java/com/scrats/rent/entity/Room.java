package com.scrats.rent.entity;

import lombok.Data;

/**
 * 房间实体类
 * Created by lol on 15/4/23.
 */
@Data
public class Room extends Common{

    private static final long serialVersionUID = -7847591953561080347L;

    private int id;//主键
    private String roomNo;//房间号
    private int deposit;//押金
    private int rent;//租金
    private float waterLast;//上月用水
    private float waterThis;//本月用水
    private float powerLast;//上月用电
    private float powerThis;//本月用电
    private float powerThreeLast;//上月用三相电
    private float powerThreeThis;//本月用三相电
    private int rentDay;//收租日
    private Boolean available;//是否可用,1-可用,0-不可用

    private int buildingId;//房子id,一个房间对应一个房子id
    private int depositId;//一个房间对应一个押金id

}
