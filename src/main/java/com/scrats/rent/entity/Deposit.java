package com.scrats.rent.entity;

import lombok.Data;

/**
 * 押金实体类
 * Created by lol on 15/4/23.
 */
@Data
public class Deposit extends Common{

    private static final long serialVersionUID = -5301479057264706239L;

    private int id;//主键
    private float water;//水费
    private float power;//电费
    private float powerThree;//三相电费
    private float healthRate;//卫生费
    private float internetRate;//网费
    private float parkingRate;//停车费
    private float propertyManagementRate;//物业管理费
    private int   rent;//租金
    private boolean ischarge;//是否已退押金


    private int roomid;//房间id,一个押金对应一个roomId
    private String roomNumber;//房间编号

}
