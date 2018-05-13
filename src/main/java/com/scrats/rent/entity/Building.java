package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;

/**
 * 房子实体类,一个房东可能有多个房子，一个房子对应多个房间
 * Created by lol on 15/4/23.
 */
@Data
public class Building extends BaseEntity {

    private int id;//主键
    private int water;//水费[分], eg 1个月1KG水700
    private int power;//电费[分], eg 1个月1kwh电120
    private int powerThree;//三相电费[分], eg 1个月1kwh电150
    private int health;//卫生费[分], eg 1个月1000
    private int internet;//网费[分], eg 1个月1000
    private int management;//物业管理费[分], eg 1个月1000
    private int parking;//停车费[分], eg 1个月1000
    private int rooms;//总的房间数
    private int roomAble;//可用房间数,通过总的房间数和可用房间数可以计算出出租房间数

    private String desc;//描述

    private int addressId;//地址Id

}
