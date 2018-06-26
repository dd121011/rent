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
public class DepositIterm extends BaseEntity {

    private static final long serialVersionUID = 2180201904798488772L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer depositItermId;//
    private Integer depositId;//主键
    private Integer roomId;//房间Id
    private String name;//押金项目名称
    private Integer price;//押金项目单价[分]
    private String unit;//押金项目单位
    private Integer number;//押金项目数量
    private Integer money;//押金项目金额

}
