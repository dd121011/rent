package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;

/**
 * 押金实体类
 * Created by lol on 15/4/23.
 */
@Data
public class Deposit extends BaseEntity {

    private int id;//主键
    private String roomNo;//房间编号
    private int water;//水费[分], eg 1个月1KG水700
    private int power;//电费[分], eg 1个月1kwh电120
    private int powerThree;//三相电费[分], eg 1个月1kwh电150
    private int health;//卫生费[分], eg 1个月1000
    private int internet;//网费[分], eg 1个月1000
    private int management;//物业管理费[分], eg 1个月1000
    private int parking;//停车费[分], eg 1个月1000
    private int rent;//租金[分], eg 1个月50000

    private int roomId;//房间id,一个押金对应一个roomId,一个roomId可能对应多个押金Id

}
