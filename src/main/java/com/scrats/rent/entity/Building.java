package com.scrats.rent.entity;

import lombok.Data;

/**
 * 房子实体类,一个房东可能有多个房子，一个房子对应多个房间
 * Created by lol on 15/4/23.
 */
@Data
public class Building extends Common{

    private static final long serialVersionUID = -620516728733479628L;

    private int id;//主键
    private float waterRate;//水费
    private float powerRate;//电费
    private float powerThreeRate;//三相电费
    private float healthRate;//卫生费
    private float internetRate;//网费
    private float propertyManagementRate;//物业管理费
    private float parkingRate;//停车费
    private int roomTotal;//总的房间数
    private int roomAvailable;//可用房间数,通过总的房间数和可用房间数可以计算出出租房间数
    private String address;//地址

    private String street;//街道
    private String town;//镇
    private String village;//村
    private String District;//小区

    private int landlordId;//对应的房东id,一栋房子对应一个房东

}
