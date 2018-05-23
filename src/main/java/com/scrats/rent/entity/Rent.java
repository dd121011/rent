package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;

/**
 * 房租实体类
 * Created by lol on 15/4/23.
 */
@Data
public class Rent extends BaseEntity {

    private int rentId;//主键
    private String rentNo;//房租收据单号
    private int water;//水费[分], eg 1个月1KG水700
    private int power;//电费[分], eg 1个月1kwh电120
    private int powerThree;//三相电费[分], eg 1个月1kwh电150
    private int health;//卫生费[分], eg 1个月1000
    private int internet;//网费[分], eg 1个月1000
    private int management;//物业管理费[分], eg 1个月1000
    private int parking;//停车费[分], eg 1个月1000
    private int rent;//租金[分], eg 1个月50000
    private int otherFee;//其他费用
    private int waterLast;//上月水表读数[升]
    private int wateThis;//本月水表读数[升]
    private int powerLast;//上月电表读数[kwh]
    private int powerThis;//本月电表读数[kwh]
    private int powerThreeLast;//上月用三电表读数[kwh]
    private int powerThreeThis;//本月用三电表读数[kwh]
    private long payTs;//是否已收房租
    private String channel;//支付渠道，0-线下支付；1-微信支付；2-支付宝支付


    private int roomId;//房间id,一个房租对应一个roomId

}
