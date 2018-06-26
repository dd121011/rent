package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 押金实体类
 * Created by lol on 15/4/23.
 */
@Data
public class Deposit extends BaseEntity {

    private static final long serialVersionUID = 2180201904798488772L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer depositId;//主键
    private String depositNo;//押金编号,32位
    private Integer fee;//总费用
    private Long payTs;//支付时间戳，13位
    private String payNo;//支付订单号
    private String channel;//支付渠道，99-未支付;0-线下支付;1-微信支付;2-支付宝支付
    private Integer roomId;//房间id,一个押金对应一个roomId,一个roomId可能对应多个押金Id
    private Integer userId;//租客的userId
    private Integer renterId;//租客的renterId
    private Integer buildingId;//buildingId


}
